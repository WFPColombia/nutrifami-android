package org.pma.nutrifami.view.activity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import org.pma.nutrifami.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class StartActivity extends AppCompatActivity {

    private VideoView mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                        .setDefaultFontPath("fonts/century_gothic.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build());

        setContentView(R.layout.activity_start);

        final Button mStartButton = (Button) findViewById(R.id.get_started_button);

        final StartActivity activity = this;
        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(activity, LoginActivity.class);
                startActivity(intent);
            }
        });


        this.mVideoView = (VideoView) findViewById(R.id.start_video_view);
        try {
            this.mVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.fruit_market));
        } catch (Exception exception) {
            Log.e("VideoView", "Failed setting uri: " + exception);
        }

        this.mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
                mVideoView.start();
            }
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext((CalligraphyContextWrapper.wrap(newBase)));
    }
}
