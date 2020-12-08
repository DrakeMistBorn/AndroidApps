package com.alivelife;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.autofill.AutofillValue;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;

public class Profile extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        TextView nameID = findViewById(R.id.textView4);
        TextView emailID = findViewById(R.id.textView5);
        //String idName = ;
        //nameID.autofill(AutofillValue.forText(String.valueOf(GoogleSignIn.getLastSignedInAccount(this).getGivenName())));
        nameID.setText(String.valueOf(GoogleSignIn.getLastSignedInAccount(this).getGivenName()));
        emailID.setText(String.valueOf(GoogleSignIn.getLastSignedInAccount(this).getEmail()));
        addListenerOnButton();

    }

    public void addListenerOnButton() {

        Button LogOutBttn = findViewById(R.id.LogOutButton);

        LogOutBttn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                LogOut_btn(v);
                Intent i = new Intent(Profile.this, PopWindowLogOut.class);
                startActivity(i);

            }

        });

        ImageButton BackBttn = (ImageButton) findViewById(R.id.BackButton);

        BackBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Back_btn(v);
                Intent i = new Intent(Profile.this, UserView.class);
                startActivity(i);
            }
        });

    }

    public void LogOut_btn(View view) {

    }

    public void Back_btn(View view) {

    }
}
