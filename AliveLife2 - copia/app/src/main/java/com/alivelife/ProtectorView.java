package com.alivelife;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

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

        //get the spinner from the xml.
        Spinner dropdown = findViewById(R.id.protecteesList);
        //create a list of items for the spinner.
        String[] items = new String[]{"1", "2", "three"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        switch (position) {
            case 0:
                // Whatever you want to happen when the first item gets selected
                break;
            case 1:
                // Whatever you want to happen when the second item gets selected
                break;
            case 2:
                // Whatever you want to happen when the thrid item gets selected
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore UI state from the savedInstanceState.
        // This bundle has also been passed to onCreate.
        boolean myBoolean = savedInstanceState.getBoolean("MyBoolean");
        double myDouble = savedInstanceState.getDouble("myDouble");
        int myInt = savedInstanceState.getInt("MyInt");
        String myString = savedInstanceState.getString("MyString");
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
