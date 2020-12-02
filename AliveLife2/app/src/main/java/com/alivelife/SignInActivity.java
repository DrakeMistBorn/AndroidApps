package com.alivelife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton signinBttn = findViewById(R.id.sign_in_button);
        signinBttn.setOnClickListener(new View.OnClickListener() {
            SignIn_btn(View v)
            Intent i = new Intent( SignInActivity.this, UserView.class);
            startActivity(i)
        });

    }

    public void SignIn_btn(View view) {

    }
}