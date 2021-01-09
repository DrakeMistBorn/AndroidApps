package com.alivelife;

import android.content.Intent;
import android.hardware.Sensor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        addListenerOnButton();

    }

    public void addListenerOnButton() {

        //*************************** BACK BUTTON ****************************************
        ImageButton backBttn = (ImageButton) findViewById(R.id.BackButton);
        backBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check David's project (Profile)
                Log.d("******", "On back button");
                Intent i = new Intent(Settings.this, UserView.class);
                startActivity(i);
            }
        });

        //*************************** PROFILE BUTTON ****************************************
        Button profileBttn = (Button) findViewById(R.id.profileBtn);
        profileBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("******", "On profile button");
                Intent i = new Intent(Settings.this, Profile.class);
                startActivity(i);
            }
        });

        //*************************** PROTECTORS BUTTON ****************************************
        Button protectorBttn = (Button) findViewById(R.id.protectorsBtn);
        protectorBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("******", "On protectors button");
                Intent i = new Intent(Settings.this, Protectors.class);
                startActivity(i);
            }
        });

        //*************************** PROTECTEES BUTTON ****************************************
        Button protecteesBttn = (Button) findViewById(R.id.protecteesBtn);
        protecteesBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("******", "On protectees button");
                Intent i = new Intent(Settings.this, Protectees.class);
                startActivity(i);
            }
        });

        //*************************** SENSORS BUTTON ****************************************
        Button sensorBttn = (Button) findViewById(R.id.sensorBtn);
        sensorBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("******", "On sensor button");
                Intent i = new Intent(Settings.this, SensorSettings.class);
                startActivity(i);
            }
        });

        //*************************** HELP BUTTON ****************************************
        Button helpBttn = (Button) findViewById(R.id.helpBtn);
        helpBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("******", "On help button");
                Intent i = new Intent(Settings.this, Help.class);
                startActivity(i);
            }
        });

    }

}
