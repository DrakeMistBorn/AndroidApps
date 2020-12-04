
package com.alivelife;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class UserView extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view);

        ImageButton redBttn = findViewById(R.id.redButton);
        redBttn.setOnClickListener(new View.OnClickListener() {
            public void onClick (View v) {
                Intent i = new Intent(UserView.this, SignInActivity.class);
                startActivity(i);
            }
        });

    }
}
