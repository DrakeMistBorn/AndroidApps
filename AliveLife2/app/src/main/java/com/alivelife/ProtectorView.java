package com.alivelife;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ProtectorView extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String etiqueta;

    @SuppressLint("UseCompatLoadingForDrawables")
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protector_view);

        //User button settings
        Button userButton = findViewById(R.id.userViewBttn);
        userButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View button) {
                Intent settings_activity = new Intent(ProtectorView.this, UserView.class);
                startActivity(settings_activity);
            }

        });

        // Settings
        ImageButton settingsButton = findViewById(R.id.settings);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick (View pv) {
                Intent settings_activity = new Intent(ProtectorView.this, Settings.class);
                startActivity(settings_activity);
            }
        });


        Spinner dropdown = findViewById(R.id.protecteesList);
        UserList protectees = loadProtectors();
        Log.d("*****", "list: "+protectees.ToString());
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, protectees.ToString());
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                    Log.i("*****", "You have changed the view to "+this);
                }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
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

    private UserList loadProtectors() {
        SharedPreferences preferences=getSharedPreferences("state", Context.MODE_PRIVATE);
        ArrayList<String> items;
        items = new ArrayList<>(preferences.getStringSet("protectors", new HashSet<>()));
        return ToList(items);
    }

    public UserList ToList (List<String> protectees){
        UserList prot = new UserList();
        int i;
        for (i = 0; i < protectees.size(); i++){
            prot.AddItem(new UserItem(protectees.get(i)));
        }
        return prot;
    }
}
