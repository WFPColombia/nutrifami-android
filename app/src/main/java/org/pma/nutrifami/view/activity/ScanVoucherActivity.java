package org.pma.nutrifami.view.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.Result;

import org.pma.nutrifami.R;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ScanVoucherActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private TextView mExplanationText;
    private ViewGroup mBarFrame;
    private ZXingScannerView mScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/century_gothic.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());
        setContentView(R.layout.activity_scan_voucher);

        this.mExplanationText = (TextView) findViewById(R.id.barcode_explanation_text);
        this.mBarFrame = (ViewGroup) findViewById(R.id.start_qr_layout);

        final ImageView wfpImage = (ImageView) findViewById(R.id.wfp_image_view);
        wfpImage.setOnClickListener(new View.OnClickListener() {
            private boolean clicked = false;
            @Override
            public void onClick(View view) {
                if (!clicked) {
                    clicked = true;
                    handleResult(null);
                }
            }
        });

        this.mScannerView = new ZXingScannerView(this);
        this.mBarFrame.addView(this.mScannerView);

        this.mScannerView.setResultHandler(this);
        getCameraPermission();
    }

    private void getCameraPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA}, 1);
        } else {
            startCamera();
        }
    }

    private void startCamera() {
        this.mScannerView.setFlash(true);
        this.mScannerView.startCamera();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        startCamera();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext((CalligraphyContextWrapper.wrap(newBase)));
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

        try {
            this.mScannerView.stopCamera();
        } catch (Exception e) {

        }

        final View welcomeView = findViewById(R.id.barcode_success_welcome_text);
        final Animation fadeAnimation = AnimationUtils.loadAnimation(this, R.anim.fade);

        fadeAnimation.setStartOffset(0);
        fadeAnimation.setFillAfter(true);
        fadeAnimation.setDuration(2000);
        welcomeView.startAnimation(fadeAnimation);

        final View checkView = findViewById(R.id.qr_check_text);
        final AnimationSet scaleAnimation = (AnimationSet) AnimationUtils.loadAnimation(this, R.anim.overshoot_scale);
        final OvershootInterpolator interpolator = new OvershootInterpolator(4);
        scaleAnimation.setInterpolator(interpolator);
        checkView.startAnimation(scaleAnimation);

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
