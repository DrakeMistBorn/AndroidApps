
package com.alivelife;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import static android.app.Service.START_FLAG_REDELIVERY;

public class UserView extends AppCompatActivity {
    ImageButton redButton;
    Switch locationButton, picturesButton, audioButton;
    private NotificationUtils mNotificationUtils;

    // Creating variables for text view,
    // sensor manager and our sensor.

    boolean proximitySensorBool = false;
    static boolean buttonIsSelected = false;

    int volumeUpCounter = 0;

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d("*****", "In user view");

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
        TextView textBackground = findViewById( R.id.backgroundID );

        //set the click listener
        redButton.setOnClickListener(new View.OnClickListener() {
            Intent backgroundIntent = new Intent(getApplicationContext(), YourService.class);

            public void onClick(View button) {
                //Set the button's appearance
                button.setSelected(!button.isSelected());
                changeButtonColor(button);
                if ( !button.isSelected() ){
                    //stopBackground();
                    buttonIsSelected = false;
                    stopService(backgroundIntent);
                    textBackground.setText("Not working on background");
                }
                else {
                    // Run app in background
                    startService ( backgroundIntent );
                    buttonIsSelected = true;
                    textBackground.setText("Working on background");
                }
            }

        });
        //***************



        //Settings configuration
        ImageButton settLocationBtn = findViewById(R.id.settingsLocation);
        settLocationBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick (View vL) {
                Intent settings_activity = new Intent(UserView.this, SensorSettings.class);
                startActivity(settings_activity);
            }
        });

        ImageButton settPicturesBtn = findViewById(R.id.settingsPictures);
        settPicturesBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick (View vP) {
                Intent settings_activity = new Intent(UserView.this, SensorSettings.class);
                startActivity(settings_activity);
            }
        });

        ImageButton settAudioBtn = findViewById(R.id.settingsAudio);
        settAudioBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick (View vA) {
                Intent settings_activity = new Intent(UserView.this, SensorSettings.class);
                startActivity(settings_activity);
            }
        });

        //protector view button settings
        Button protectorButton = findViewById(R.id.protectorViewBttn);
        protectorButton.setOnClickListener(new View.OnClickListener() {
            public void onClick (View pv) {
                Intent settings_activity = new Intent(UserView.this, ProtectorView.class);
                startActivity(settings_activity);
            }
        });

        //settings button
        ImageButton settingsButton = findViewById(R.id.settings);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick (View pv) {
                Intent settings_activity = new Intent(UserView.this, Settings.class);
                startActivity(settings_activity);
            }
        });

        loadPreferences();

        setButtonColor(redButton);

    }


    @Override
    public boolean onKeyUp (int keyCode, KeyEvent event) {
        Log.d("*****", "UploadingContent: " + buttonIsSelected +
                ". KeyCode: " + keyCode + " ," + KeyEvent.KEYCODE_VOLUME_UP +
                ". ProximitySensor, " + YourService.isProximitySensorOn() +
                ". OnKeyUp, " + volumeUpCounter);

        if (buttonIsSelected) {
            if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
                if ( YourService.isProximitySensorOn() ) {
                    if ( volumeUpCounter < 2 ){
                        // If you have the phone on the ear but you haven't pressed 3 times the button
                        volumeUpCounter++;
                    }
                    else {
                        // Empezar a subir contenido
                        Log.d("*****", "Uploading content");
                        buttonIsSelected = true;
                    }
                }
            }
        }
        return true;
    }

    private void saveState() {
        SharedPreferences preferences=getSharedPreferences("state", Context.MODE_PRIVATE);

        Log.d("*****", "Red button is selected (SAVE): "+String.valueOf(redButton.isSelected()));

        boolean button = redButton.isSelected(); // true: orange button; false: red button
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

    @Override
    protected void onPause() {
        super.onPause();
        saveState();
    }

    public void loadPreferences() {
        SharedPreferences preferences=getSharedPreferences("state", Context.MODE_PRIVATE);

        Log.d("*****", "Red button is selected (LOAD): "+String.valueOf(redButton.isSelected()));

        boolean button = preferences.getBoolean("red_button", false);

        boolean location = preferences.getBoolean("location_activated", false);
        boolean pictures = preferences.getBoolean("pictures_activated", false);
        boolean audio = preferences.getBoolean("audio_activated", false);
        // loading preferences
        redButton.setSelected(button);
        locationButton.setChecked(location);
        picturesButton.setChecked(pictures);
        audioButton.setChecked(audio);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void setButtonColor(View button){
        Log.d("*****", "Red button, set color: "+String.valueOf(redButton.isSelected()));

        if (button.isSelected()) {
            redButton.setImageDrawable(getBaseContext().getResources().getDrawable(R.drawable.orange_button));
        } else {
            redButton.setImageDrawable(getBaseContext().getResources().getDrawable(R.drawable.red_button));
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void changeButtonColor(View button){
        Log.d("*****", "Red button, change color: "+String.valueOf(redButton.isSelected()));

        if (button.isSelected()) {
            redButton.setImageDrawable(getBaseContext().getResources().getDrawable(R.drawable.orange_button));
            startNotification();

        } else {
            redButton.setImageDrawable(getBaseContext().getResources().getDrawable(R.drawable.red_button));
            stopNotification();
        }

    }


    private void startNotification() {
        Notification.Builder nb = mNotificationUtils.getAndroidChannelNotification("Madaro", "Play today to maintain your streak");
        mNotificationUtils.getManager().notify(101, nb.build());
    }

    private void stopNotification() {
        mNotificationUtils.getManager().cancel(101);
    }

}
