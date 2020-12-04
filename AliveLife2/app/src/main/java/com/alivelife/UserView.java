package com.alivelife;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class UserView extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view);

        ImageButton redBttn = findViewById(R.id.redButton);
        redBttn.setOnClickListener(new View.OnClickListener() {
            public void onClick (View v) {
                Intent i = new Intent(UserView.this, SignInActivity.class);
                startActivity(i);
            }
        });
        ImageButton settLocationBtn = findViewById(R.id.settingsLocation);
        Intent settings_activity = new Intent(UserView.this, Settings.class);
        settLocationBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick (View vL) {
                startActivity(settings_activity);
            }
        });
        ImageButton settPicturesBtn = findViewById(R.id.settingsPictures);
        settPicturesBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick (View vP) {
                startActivity(settings_activity);
            }
        });
        ImageButton settAudioBtn = findViewById(R.id.settingsAudio);
        settAudioBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick (View vA) {
                startActivity(settings_activity);
            }
        });

    }
}
