package com.alivelife;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Profile extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        addListenerOnButton();

    }

    public void addListenerOnButton() {

        Button LogOutBttn = findViewById(R.id.LogOutButton);

        LogOutBttn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                LogOut_btn(v);
                Intent i = new Intent(Profile.this, UserView.class);
                startActivity(i);

            }

        });

    }

    public void LogOut_btn(View view) {

    }
}
