package com.alivelife;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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

        addListenerOnButton();
    }

    public void addListenerOnButton() {
        UserList userList = loadProtectees();

        //*************************** EMAIL TEXT ****************************************
        EditText text = findViewById(R.id.editTextTextEmailAddress);

        //*************************** ACCEPT BUTTON ****************************************
        Button acceptBttn = findViewById(R.id.acceptBttn);
        acceptBttn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String email = text.getText().toString();
                UserItem protectee = new UserItem(email);
                userList.AddItem(protectee);
                saveProtectees(userList);


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

    public void saveProtectees(UserList items){
        SharedPreferences preferences = getSharedPreferences("state", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putStringSet("protectees", items.ToStringSet());
        editor.commit();
    }

    public UserList loadProtectees() {
        SharedPreferences preferences=getSharedPreferences("state", Context.MODE_PRIVATE);
        ArrayList<String> items;
        items = new ArrayList<>(preferences.getStringSet("protectees", new HashSet<>()));
        return ToList(items);
    }

    public UserList ToList (List<String> protectees){
        UserItem item;
        UserList prot = new UserList();
        int i;
        for (i = 0; i < protectees.size(); i++){
            prot.AddItem(new UserItem(protectees.get(i)));
        }
        return prot;
    }
}
