package org.pma.nutrifami.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import org.pma.nutrifami.R;
import org.pma.nutrifami.activities.games.PairsActivity;
import org.pma.nutrifami.activities.games.PictureQuizActivity;
import org.pma.nutrifami.activities.games.QuizActivity;
import org.pma.nutrifami.activities.games.SwipeActivity;

public class ModuleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module);

        createOnClickListener(R.id.module_button_pairs, PairsActivity.class);
        createOnClickListener(R.id.module_button_picture_quiz, PictureQuizActivity.class);
        createOnClickListener(R.id.module_button_quiz, QuizActivity.class);
        createOnClickListener(R.id.module_button_swipe, SwipeActivity.class);
    }

    private void createOnClickListener(int id, final Class<? extends AppCompatActivity> classIdentifier) {
        final ModuleActivity activity = this;
        Button button = (Button) findViewById(id);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, classIdentifier);
                startActivity(intent);
            }
        });
    }
}
