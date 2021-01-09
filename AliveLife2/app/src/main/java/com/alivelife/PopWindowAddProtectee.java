package com.alivelife;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PopWindowAddProtectee extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pop_up_protectees);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width*.8),(int) (height*.3));
    }

    public void addListenerOnButton() {

        //*************************** ACCEPT BUTTON ****************************************
        Button acceptBttn = findViewById(R.id.acceptBttn);
        acceptBttn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //addProtectee();
                Intent i = new Intent(PopWindowAddProtectee.this, Protectees.class);
                startActivity(i);
            }

        });

        //*************************** CANCEL BUTTON ****************************************
        Button cancelBttn = findViewById(R.id.cancelBttn);
        cancelBttn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //addProtectee();
                Intent i = new Intent(PopWindowAddProtectee.this, Protectees.class);
                startActivity(i);
            }

        });

    }
}
