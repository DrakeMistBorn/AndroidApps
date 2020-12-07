
package com.alivelife;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class UserView extends AppCompatActivity {
    ImageButton redButton;
    Switch locationButton, picturesButton, audioButton;
    TextView txtRedButton, txtLocation, txtPictures, txtAudio;

    @SuppressLint({"UseCompatLoadingForDrawables", "WrongViewCast"})
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view);

        //************ Settings

        locationButton = findViewById(R.id.switchLocation);
        picturesButton = findViewById(R.id.switchPictures);
        audioButton = findViewById(R.id.switchAudio);
        txtRedButton = (TextView)  findViewById(R.id.redButton);
        txtLocation = (TextView)  findViewById(R.id.redButton);
        txtPictures = (TextView)  findViewById(R.id.redButton);
        txtAudio = (TextView) findViewById(R.id.switchAudio);

        //*************

        //Red button settings
        //assign the image in code (or you can do this in your layout xml with the src attribute)
        redButton = findViewById(R.id.redButton);
        redButton.setImageDrawable(getBaseContext().getResources().getDrawable(R.drawable.red_button));


        //set the click listener
        redButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View button) {
                saveState();

                //Set the button's appearance
                button.setSelected(!button.isSelected());

                if (button.isSelected()) {
                    redButton.setImageDrawable(getBaseContext().getResources().getDrawable(R.drawable.orange_button));
                    //onSaveInstanceState(Bundle outState);
                } else {
                    redButton.setImageDrawable(getBaseContext().getResources().getDrawable(R.drawable.red_button));
                }

            }

        });





        //Settings configuration
        ImageButton settLocationBtn = findViewById(R.id.settingsLocation);
        settLocationBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick (View vL) {
                Intent settings_activity = new Intent(UserView.this, Settings.class);
                startActivity(settings_activity);
            }
        });
        ImageButton settPicturesBtn = findViewById(R.id.settingsPictures);
        settPicturesBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick (View vP) {
                Intent settings_activity = new Intent(UserView.this, Settings.class);
                startActivity(settings_activity);
            }
        });
        ImageButton settAudioBtn = findViewById(R.id.settingsAudio);
        settAudioBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick (View vA) {
                Intent settings_activity = new Intent(UserView.this, Settings.class);
                startActivity(settings_activity);
            }
        });


        //protector view button settings
        ImageButton protectorButton = findViewById(R.id.protectorViewBttn);
        protectorButton.setOnClickListener(new View.OnClickListener() {
            public void onClick (View pv) {
                Intent settings_activity = new Intent(UserView.this, ProtectorView.class);
                startActivity(settings_activity);
            }
        });

        loadPreferences();
    }

    private void saveState() {
        SharedPreferences preferences=getSharedPreferences("state", Context.MODE_PRIVATE);

        boolean button = redButton.isSelected(); //isSelected: true, false
        boolean location = locationButton.isChecked();
        boolean pictures = picturesButton.isChecked();
        boolean audio = audioButton.isChecked();


        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("red_button", button);
        editor.putBoolean("location_activated", location);
        editor.putBoolean("pictures_activated", pictures);
        editor.putBoolean("audio_activated", audio);


        editor.commit();


    }

    private void loadPreferences() {
        SharedPreferences preferences=getSharedPreferences("state", Context.MODE_PRIVATE);

        Boolean button = preferences.getBoolean("red_button", false);
        Boolean location = preferences.getBoolean("location_activated", false);
        Boolean pictures = preferences.getBoolean("pictures_activated", false);
        Boolean audio = preferences.getBoolean("audio_button", false);

        redButton.setSelected(button);
        locationButton.setChecked(location);
        picturesButton.setChecked(pictures);
        audioButton.setChecked(audio);
    }


}
