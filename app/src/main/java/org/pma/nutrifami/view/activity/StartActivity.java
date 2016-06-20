package org.pma.nutrifami.view.activity;

import android.Manifest;
import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;

import org.pma.nutrifami.R;

import java.util.ArrayList;
import java.util.List;

import me.dm7.barcodescanner.core.IViewFinder;
import me.dm7.barcodescanner.core.ViewFinderView;
import me.dm7.barcodescanner.zxing.ZXingScannerView;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class StartActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView mScannerView;
    private Button mScanButton;
    private VideoView mVideoView;
    private ImageView mVideoScreenView;
    private TextView mExplanationText;
    private ViewGroup mBarFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                        .setDefaultFontPath("fonts/century_gothic.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build());

        setContentView(R.layout.activity_start);

        this.mScanButton = (Button) findViewById(R.id.get_started_button);
        this.mVideoScreenView = (ImageView) findViewById(R.id.video_view_screenshot);
        this.mExplanationText = (TextView) findViewById(R.id.barcode_explanation_text);

        this.mScanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCameraPermission();
                animateToQRView();
//                handleResult(null);
            }
        });


        this.mVideoView = (VideoView) findViewById(R.id.start_video_view);
        try {
            this.mVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.fruit_market));
        } catch (Exception exception) {
            Log.e("Videoview", "Failed setting uri: " + exception);
        }

        this.mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
                mVideoView.start();
            }
        });
    }

    private void animateToQRView() {
        final AnimationSet set = new AnimationSet(false);

        final TranslateAnimation translateAnim = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_SELF, 0,
                TranslateAnimation.RELATIVE_TO_SELF, -0.275f,
                TranslateAnimation.RELATIVE_TO_SELF, 0,
                TranslateAnimation.RELATIVE_TO_SELF, -0.8f
        );
        translateAnim.setDuration(1000);
        translateAnim.setFillAfter(true);
        set.addAnimation(translateAnim);

        final ScaleAnimation scaleAnim = new ScaleAnimation(0, 2.25f, 0, 16);
        scaleAnim.setDuration(1000);
        scaleAnim.setFillAfter(true);
        set.addAnimation(scaleAnim);

        final float[] from = new float[3],
                to =   new float[3];

        Color.colorToHSV(Color.parseColor("#FFFFFFFF"), from);
        Color.colorToHSV(org.pma.nutrifami.util.Color.getAccentColor(this), to);

        ValueAnimator anim = ValueAnimator.ofFloat(0, 1);
        anim.setDuration(200);

        final float[] hsv  = new float[3];                  // transition color
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){
            @Override public void onAnimationUpdate(ValueAnimator animation) {
                // Transition along each axis of HSV (hue, saturation, value)
                hsv[0] = from[0] + (to[0] - from[0])*animation.getAnimatedFraction();
                hsv[1] = from[1] + (to[1] - from[1])*animation.getAnimatedFraction();
                hsv[2] = from[2] + (to[2] - from[2])*animation.getAnimatedFraction();

                mScanButton.setTextColor(Color.HSVToColor(hsv));
            }
        });


        anim.start();

        set.setFillAfter(true);


        final ImageView wfpImage = (ImageView) findViewById(R.id.wfp_image_view);
        wfpImage.setOnClickListener(new View.OnClickListener() {
            private int clickCount = 0;
            @Override
            public void onClick(View view) {
               clickCount++;
                if (clickCount >= 5) {
                    handleResult(null);
                }
            }
        });
        this.mBarFrame = (ViewGroup) findViewById(R.id.start_qr_layout);

        final Animation alphaAnim = createAlphaAnimation();


        final StartActivity activity = this;

        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                mScanButton.setText("                                        ");
                mScanButton.setEnabled(false);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                mExplanationText.startAnimation(alphaAnim);
                mBarFrame.startAnimation(alphaAnim);
                wfpImage.startAnimation(alphaAnim);

                mScannerView = new ZXingScannerView(activity);
                mBarFrame.addView(mScannerView);

                mScannerView.setResultHandler(activity);
                mScannerView.startCamera();
                mVideoView.stopPlayback();
                mVideoView.setVisibility(View.INVISIBLE);
                final View darkeningView = activity.findViewById(R.id.start_darkening_view);
                darkeningView.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        this.mScanButton.startAnimation(set);
    }

    private Animation createAlphaAnimation() {
        final Animation alphaAnim = new AlphaAnimation(0, 1);
        alphaAnim.setStartOffset(1000);
        alphaAnim.setDuration(1000);
        alphaAnim.setFillAfter(true);
        return alphaAnim;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext((CalligraphyContextWrapper.wrap(newBase)));
    }

    private void getCameraPermission() {
        if (ContextCompat.checkSelfPermission(this,
            Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CAMERA}, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (this.mScannerView == null) {
            return;
        }
        this.mScannerView.setResultHandler(this);
        this.mScannerView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (this.mScannerView == null) {
            return;
        }
        this.mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result result) {
        this.mExplanationText.setText("LOGIN SUCCESSFUL");
        if (this.mBarFrame != null) {
            this.mBarFrame.setVisibility(View.INVISIBLE);
        }
        if (this.mScannerView != null) {
            this.mScannerView.setVisibility(View.INVISIBLE);
        }

        final View welcomeView = findViewById(R.id.barcode_success_welcome_text);
        welcomeView.startAnimation(createAlphaAnimation());

        final Context context = this;
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                final Intent intent = new Intent(context, ExplanationActivity.class);
                startActivity(intent);
            }
        }, 3000);
    }
}
