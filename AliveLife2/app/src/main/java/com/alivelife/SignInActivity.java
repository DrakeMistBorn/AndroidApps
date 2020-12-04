package com.alivelife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        addListenerOnButton();

    }

    public void addListenerOnButton() {

        ImageButton signinBttn = (ImageButton) findViewById(R.id.sign_in_button);

        signinBttn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                SignIn_btn(v);
                Intent i = new Intent(SignInActivity.this, UserView.class);
                startActivity(i);

            }

        });

    }

    public void SignIn_btn(View view) {

    }
}