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

public class ProtectorView extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

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


        Spinner dropdown = findViewById(R.id.protecteesList);
        String[] items = new String[]{"Protectee1", "Protectee2", "Protectee3"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(this);
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


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
