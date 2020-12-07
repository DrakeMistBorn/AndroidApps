
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

import androidx.appcompat.app.AppCompatActivity;

public class UserView extends AppCompatActivity {
    ImageButton redButton = findViewById(R.id.redButton);
    Switch locationButton = findViewById(R.id.switchLocation);
    Switch picturesButton = findViewById(R.id.switchPictures);
    Switch audioButton = findViewById(R.id.switchAudio);

    @SuppressLint("UseCompatLoadingForDrawables")
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view);


        //Red button settings
        //assign the image in code (or you can do this in your layout xml with the src attribute)

        redButton.setImageDrawable(getBaseContext().getResources().getDrawable(R.drawable.red_button));

        //set the click listener
        redButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View button) {
                //saveState();
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
    }

    /*private void saveState() {
        SharedPreferences preferences=getSharedPreferences("state", Context.MODE_PRIVATE);

        boolean button = redButton.isSelected(); //isSelected: true, false
        boolean location = locationButton.isSelected();
        boolean pictures = picturesButton.isSelected();
        boolean audio = audioButton.isSelected();


        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("red_button", button);
        editor.putBoolean("location_activated", location);
        editor.putBoolean("pictures_activated", pictures);
        editor.putBoolean("audio_activated", audio);

    }*/


}
