package com.alivelife;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Protectees extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protectees);
        addListenerOnButton();

    }

    public void addListenerOnButton() {

        ImageButton BackBttn = findViewById(R.id.BackButton);

        BackBttn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(Protectees.this, Settings.class);
            startActivity(i);
        }
        });

        ListView listView = (ListView) this.findViewById(R.id.tv);
        ProtecteeList protectees = new ProtecteeList();
        protectees.AddItem(new ProtecteeItem(1, "mariapr1407@gmail.com"));
        protectees.AddItem(new ProtecteeItem(1, "david.cen.lop5@gmail.com"));
        Log.d("*****", "list: "+protectees.ToString());
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

    }
}
