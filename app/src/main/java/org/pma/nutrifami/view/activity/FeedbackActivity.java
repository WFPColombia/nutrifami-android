package org.pma.nutrifami.view.activity;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.VideoView;

import org.pma.nutrifami.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class FeedbackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                        .setDefaultFontPath("fonts/century_gothic.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build());
        setContentView(R.layout.activity_feedback);

        final VideoView videoView = (VideoView) findViewById(R.id.feedback_video);
        try {
            videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.credits));
        } catch (Exception exception) {
            Log.e("VideoView", "Failed setting uri: " + exception);
        }

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
                videoView.start();
            }
        });

        final View starView = findViewById(R.id.feedback_star);
        final Animation bounce = AnimationUtils.loadAnimation(this, R.anim.bounce);
        starView.startAnimation(bounce);

        final View creditsText = findViewById(R.id.feedback_voucher_text);
        final Animation fadeCredits = AnimationUtils.loadAnimation(this, R.anim.fade);
        creditsText.startAnimation(fadeCredits);

        final View feedbackText = findViewById(R.id.feedback_healthy_text);
        final Animation fadeFeedback = AnimationUtils.loadAnimation(this, R.anim.fade);
        fadeFeedback.setStartOffset(fadeFeedback.getStartOffset() + 1000);
        feedbackText.startAnimation(fadeFeedback);

        final Button finishButton = (Button) findViewById(R.id.feedback_finish_button);
        finishButton.startAnimation(fadeFeedback);
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LessonActivity.voucherCredits += 10;
                finish();
            }
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext((CalligraphyContextWrapper.wrap(newBase)));
    }
}
