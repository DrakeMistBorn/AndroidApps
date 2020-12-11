
package com.alivelife;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

public class UserView extends AppCompatActivity {
    ImageButton redButton;
    Switch locationButton, picturesButton, audioButton;
    private NotificationUtils mNotificationUtils;
    //TextView txtRedButton, txtLocation, txtPictures, txtAudio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view);


        //************ Settings

        locationButton = findViewById(R.id.switchLocation);
        picturesButton = findViewById(R.id.switchPictures);
        audioButton = findViewById(R.id.switchAudio);
        mNotificationUtils = new NotificationUtils(this);

        //*************

        //*************** Red button settings
        //assign the image in code (or you can do this in your layout xml with the src attribute)
        redButton = findViewById(R.id.redButton);
        redButton.setImageDrawable(getBaseContext().getResources().getDrawable(R.drawable.red_button));


        //set the click listener
        redButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View button) {
                //Set the button's appearance
                button.setSelected(!button.isSelected());
                changeButtonColor(button);
                saveState();
            }

        });

        //***************



        //Settings configuration
        ImageButton settLocationBtn = findViewById(R.id.settingsLocation);
        settLocationBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick (View vL) {
                saveState();
                Intent settings_activity = new Intent(UserView.this, Settings.class);
                startActivity(settings_activity);
            }
        });
        ImageButton settPicturesBtn = findViewById(R.id.settingsPictures);
        settPicturesBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick (View vP) {
                saveState();
                Intent settings_activity = new Intent(UserView.this, Profile.class);
                startActivity(settings_activity);
            }
        });
        ImageButton settAudioBtn = findViewById(R.id.settingsAudio);
        settAudioBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick (View vA) {
                saveState();
                Intent settings_activity = new Intent(UserView.this, Settings.class);
                startActivity(settings_activity);
            }
        });


        //protector view button settings
        ImageButton protectorButton = findViewById(R.id.protectorViewBttn);
        protectorButton.setOnClickListener(new View.OnClickListener() {
            public void onClick (View pv) {
                saveState();
                Intent settings_activity = new Intent(UserView.this, ProtectorView.class);
                startActivity(settings_activity);
            }
        });


        loadPreferences();
        setButtonColor(redButton);
    }

    private void saveState() {
        SharedPreferences preferences=getSharedPreferences("state", Context.MODE_PRIVATE);

        System.out.println("******************************************************************************");
        System.out.println("************* SAVE **************"+redButton.isSelected());

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

    public void loadPreferences() {
        SharedPreferences preferences=getSharedPreferences("state", Context.MODE_PRIVATE);
        System.out.println("******************************************************************************");
        System.out.println("************* LOAD **************"+redButton.isSelected());

        Boolean button = preferences.getBoolean("red_button", false);
        System.out.println("************* LOAD **************"+button);

        Boolean location = preferences.getBoolean("location_activated", false);
        Boolean pictures = preferences.getBoolean("pictures_activated", false);
        Boolean audio = preferences.getBoolean("audio_activated", false);
        // loading preferences
        redButton.setSelected(button);
        locationButton.setChecked(location);
        picturesButton.setChecked(pictures);
        audioButton.setChecked(audio);
    }

    private void setButtonColor(View button){
        System.out.println("******************************************************************************");
        System.out.println("************* SET COLOR **************"+redButton.isSelected());

        if (button.isSelected()) {
            redButton.setImageDrawable(getBaseContext().getResources().getDrawable(R.drawable.orange_button));
        } else {
            redButton.setImageDrawable(getBaseContext().getResources().getDrawable(R.drawable.red_button));
        }
    }

    private void changeButtonColor(View button){
        System.out.println("******************************************************************************");
        System.out.println("************* Change COLOR **************"+redButton.isSelected());

        if (button.isSelected()) {
            redButton.setImageDrawable(getBaseContext().getResources().getDrawable(R.drawable.orange_button));
            startNotification();
        } else {
            redButton.setImageDrawable(getBaseContext().getResources().getDrawable(R.drawable.red_button));
            stopNotification();
        }

    }

    private void startNotification() {
        Notification.Builder nb = mNotificationUtils.getAndroidChannelNotification("Candy Splash", "Play today to maintain your streak");
        mNotificationUtils.getManager().notify(101, nb.build());
    }

    private void stopNotification() {
        mNotificationUtils.getManager().cancel(101);
    }


}
