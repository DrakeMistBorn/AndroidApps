package com.alivelife;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class PopWindowAddProtector extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_protectors);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width*.8),(int) (height*.3));
        addListenerOnButton();
    }

    public void addListenerOnButton() {
        UserList userList = loadProtectors();

        //*************************** EMAIL TEXT ****************************************
        EditText text = findViewById(R.id.editTextTextEmailAddress);

        //*************************** ACCEPT BUTTON ****************************************
        Button acceptBttn = findViewById(R.id.acceptBttn);

        acceptBttn.setOnClickListener(v -> {
            String email = text.getText().toString();
            UserItem protector = new UserItem(email);
            userList.AddItem(protector);
            saveProtectors(userList);

            Intent i = new Intent(PopWindowAddProtector.this, Protectors.class);
            startActivity(i);
        });

        //*************************** CANCEL BUTTON ****************************************
        Button cancelBttn = findViewById(R.id.cancelBttn);
        cancelBttn.setOnClickListener(v -> {
            Intent i = new Intent(PopWindowAddProtector.this, Protectors.class);
            startActivity(i);
        });

    }

    public void saveProtectors(UserList items){
        SharedPreferences preferences = getSharedPreferences("state", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putStringSet("protectors", items.ToStringSet());
        editor.apply();
    }

    private UserList loadProtectors() {
        SharedPreferences preferences=getSharedPreferences("state", Context.MODE_PRIVATE);
        ArrayList<String> items;
        items = new ArrayList<>(preferences.getStringSet("protectors", new HashSet<>()));
        return ToList(items);
    }

    public UserList ToList (List<String> protectors){
        UserList prot = new UserList();
        int i;
        for (i = 0; i < protectors.size(); i++){
            prot.AddItem(new UserItem(protectors.get(i)));
        }
        return prot;
    }
}