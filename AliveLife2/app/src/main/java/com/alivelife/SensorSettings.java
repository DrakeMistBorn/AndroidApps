package com.alivelife;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SensorSettings extends AppCompatActivity {
    Switch locationButton, picturesButton, audioButton;
    SeekBar locationBar, photoBar, audioBar, audioBarDuration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_settings);

        changeDefaultValues();
        addListenerOnButton();
        loadPreferences();
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveState();
    }

    private void changeDefaultValues() {
        //locationBar.setMin(15);
        //locationBar.setMax(60);

    }

    public void addListenerOnButton() {

        locationButton = findViewById(R.id.switchLocationSett);
        picturesButton = findViewById(R.id.switchPicturesSett);
        audioButton = findViewById(R.id.switchAudioSett);

        locationBar = (SeekBar)findViewById(R.id.locationBar);
        final TextView locationBarValue = (TextView)findViewById(R.id.textLocation);
        locationBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // TODO Auto-generated method stub
                locationBarValue.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
        });

        photoBar = (SeekBar)findViewById(R.id.photoBar);
        final TextView photoBarValue = (TextView)findViewById(R.id.textPhoto);
        photoBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // TODO Auto-generated method stub
                photoBarValue.setText(String.valueOf(progress));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
        });

        audioBar = (SeekBar)findViewById(R.id.audioBar);
        final TextView audioBarValue = (TextView)findViewById(R.id.textAudio);
        audioBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // TODO Auto-generated method stub
                audioBarValue.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
        });

        audioBarDuration = (SeekBar)findViewById(R.id.audioBarDuration);
        final TextView audioBarValueDuration = (TextView)findViewById(R.id.textAudioDuration);
        audioBarDuration.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // TODO Auto-generated method stub
                audioBarValueDuration.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
        });

        ImageButton BackBttn = (ImageButton) findViewById(R.id.BackButton);
        BackBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SensorSettings.this, UserView.class);
                startActivity(i);
            }
        });
    }

    private void saveState() {
        SharedPreferences preferences=getSharedPreferences("state", Context.MODE_PRIVATE);

        boolean location = locationButton.isChecked();
        boolean pictures = picturesButton.isChecked();
        boolean audio = audioButton.isChecked();

        int location_bar = locationBar.getProgress();
        int photo_bar = photoBar.getProgress();
        int audio_bar = audioBar.getProgress();
        int audio_bar_duration = audioBarDuration.getProgress();

        SharedPreferences.Editor editor = preferences.edit();

        editor.putBoolean("location_activated", location);
        editor.putBoolean("pictures_activated", pictures);
        editor.putBoolean("audio_activated", audio);

        editor.putInt("location_bar_progress", location_bar);
        editor.putInt("photo_bar_progress", photo_bar);
        editor.putInt("audio_bar_progress", audio_bar);
        editor.putInt("audio_bar_duration_progress", audio_bar_duration);

        editor.commit();
    }

    public void loadPreferences() {
        SharedPreferences preferences=getSharedPreferences("state", Context.MODE_PRIVATE);

        Boolean location = preferences.getBoolean("location_activated", false);
        Boolean pictures = preferences.getBoolean("pictures_activated", false);
        Boolean audio = preferences.getBoolean("audio_activated", false);
        int location_bar = preferences.getInt("location_bar_progress", 0);
        int photo_bar = preferences.getInt("photo_bar_progress", 0);
        int audio_bar = preferences.getInt("audio_bar_progress", 0);
        int audio_bar_duration = preferences.getInt("audio_bar_duration_progress", 0);

        // loading preferences
        locationButton.setChecked(location);
        picturesButton.setChecked(pictures);
        audioButton.setChecked(audio);
        locationBar.setProgress(location_bar);
        photoBar.setProgress(photo_bar);
        audioBar.setProgress(audio_bar);
        audioBarDuration.setProgress(audio_bar_duration);
    }
}
