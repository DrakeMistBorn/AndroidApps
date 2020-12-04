
package com.alivelife;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class UserView extends AppCompatActivity {

    String etiqueta;

    @SuppressLint("UseCompatLoadingForDrawables")
    protected void onCreate(Bundle savedInstanceState) {
        String etiqueta;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view);

        //Red button settings
        //assign the image in code (or you can do this in your layout xml with the src attribute)
        ImageButton redButton = findViewById(R.id.redButton);
        redButton.setImageDrawable(getBaseContext().getResources().getDrawable(R.drawable.red_button));

        //set the click listener
        redButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View button) {
                //Set the button's appearance
                button.setSelected(!button.isSelected());

                if (button.isSelected()) {
                    redButton.setImageDrawable(getBaseContext().getResources().getDrawable(R.drawable.orange_button));
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

    @Override
    protected void onStart(){
        super.onStart();
        Log.d(etiqueta, "onStart: ");

    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.d(etiqueta, "onStart:");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(etiqueta, "onDestroy:");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(etiqueta, "onPause:");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(etiqueta, "onResume:");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(etiqueta, "onRestart: ");
    }
}
