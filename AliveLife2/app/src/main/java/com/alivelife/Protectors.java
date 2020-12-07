package com.alivelife;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Protectors extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        addListenerOnButton();

    }

    public void addListenerOnButton() {

        ImageButton BackBttn = (ImageButton) findViewById(R.id.BackButton);

            BackBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Back_btn(v);
                Intent i = new Intent(Protectors.this, UserView.class);
                startActivity(i);
            }
        });

    }

    public void Back_btn(View view) {

    }
}
