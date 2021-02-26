package com.alivelife;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Protectees extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protectees);
        addListenerOnButton();
    }


    public void addListenerOnButton() {
        // **************************** BACK BUTTON
        ImageButton BackBttn = findViewById(R.id.BackButton);
        BackBttn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(Protectees.this, Settings.class);
            startActivity(i);
        }
        });

        // **************************** LIST VIEW
        ListView listView = (ListView) this.findViewById(R.id.tv);
        UserList protectees = loadProtectees();
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, protectees.ToString()) {
            @SuppressLint("ResourceAsColor")
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View view = super.getView(position, convertView, parent);
                TextView text = (TextView) view.findViewById(android.R.id.text1);
                text.setTextColor(R.color.white);

                return view;
            }
        }
        );

        // **************************** ADD PROTECTEE
        Button addProtecteeBttn = findViewById(R.id.addProtectee);
        addProtecteeBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Protectees.this, PopWindowAddProtectee.class);
                startActivity(i);
            }
        });

    }

    public UserList loadProtectees() {
        SharedPreferences preferences=getSharedPreferences("state", Context.MODE_PRIVATE);
        ArrayList <String> items;
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
