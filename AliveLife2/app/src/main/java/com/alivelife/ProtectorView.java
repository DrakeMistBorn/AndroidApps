package com.alivelife;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class ProtectorView extends AppCompatActivity {

    String etiqueta;

    @SuppressLint("UseCompatLoadingForDrawables")
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protector_view);

        //User button settings
        ImageButton userButton = findViewById(R.id.userViewBttn);
        userButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View button) {
                Intent settings_activity = new Intent(ProtectorView.this, UserView.class);
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
}
