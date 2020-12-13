
package com.alivelife;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import com.alivelife.R;

import androidx.appcompat.app.AppCompatActivity;

import static android.app.Service.START_FLAG_REDELIVERY;

public class UserView extends AppCompatActivity {
    ImageButton redButton;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch locationButton, picturesButton, audioButton;
    private NotificationUtils mNotificationUtils;
    TextView txt = findViewById(R.id.textChanger);
    //TextView txtRedButton, txtLocation, txtPictures, txtAudio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d("****", "In user view");

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
            int num = startBackground();
            Log.d("We are on: ", "starting background"+num);

            //YourService.startForeground();
        } else {
            redButton.setImageDrawable(getBaseContext().getResources().getDrawable(R.drawable.red_button));
            stopNotification();
            Intent intent = new Intent (this, YourService.class);
            stopService(intent);
            stopBackground();
        }

    }

    private int startBackground() {
        Log.d("Estamos en: ", "starting background 2");
        Intent intent = new Intent(this, YourService.class);
        Log.d("Estamos en: ", "starting background 2, after intent");
        startService(intent);
        Log.d("Estamos en: ", "starting background 2, after start");
        int num = new YourService().onStartCommand(intent, START_FLAG_REDELIVERY, 101);
        Log.d("Estamos en: ", "starting background 2, after onStart");
        return num;
    }

    private void stopBackground() {
        Intent intent = new Intent(this, YourService.class);
        new YourService().onHandleIntent(intent);
        stopService(intent);
    }

    private void startNotification() {
        Notification.Builder nb = mNotificationUtils.getAndroidChannelNotification("Candy Splash", "Play today to maintain your streak");
        mNotificationUtils.getManager().notify(101, nb.build());
    }

    private void stopNotification() {
        mNotificationUtils.getManager().cancel(101);
    }

    public void setText (String str){
        txt.setText(str);
    }
}
