package com.alivelife;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Help extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        addListenerOnButton();

    }


    public void addListenerOnButton() {

        ImageButton LogOutBttn = findViewById(R.id.BackButton);

        LogOutBttn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(Help.this, Settings.class);
                startActivity(i);

            }

        });
    }
}
