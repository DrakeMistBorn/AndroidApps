package com.alivelife;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Help extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        addListenerOnButton();
        Log.d("******", "On create help class");

    }


    public void addListenerOnButton() {

        ImageButton LogOutBttn = findViewById(R.id.backButton);

        LogOutBttn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(Help.this, Settings.class);
                startActivity(i);

            }

        });

        TextView helpText = findViewById(R.id.body);
        String text = "How to use AliveLife\n" +
                "\n" +
                "Register / Log in and first steps\n" +
                "\n" +
                "The first step to do in AliveLife app is to have an Google account. To do so, it is possible either to register " +
                "or to log in (just if the register has been done before).\n" +
                "\n" +
                "First view after the login is the User View. The main function that is the in this view is the central button. " +
                "When it is triggered, the application starts running in the backgroung. Also there are three switch buttons that" +
                " the user can activate them to send the different types of data in case of emergency.\n" +
                "The Protector button on the top changes the view into the Protector View.\n" +
                "\n" +
                "Profile View\n" +
                "\n" +
                "In the profile view, the user can see his username and also his email. There is also the option to log out of\n" +
                "the account or to delete account. \n" +
                "\n" +
                "Settings \n \n" +
                "All the settings for the funtionalities are available here. " +
                "In addition, the help informations could be found in this section.";
        helpText.setText( text );
        helpText.setMovementMethod( new ScrollingMovementMethod() );

    }
}
