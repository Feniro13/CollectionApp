package com.Feniro.collectionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button create, rule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        create = findViewById(R.id.Start_Button_Create);
        rule = findViewById(R.id.Start_Button_Rule);

        create.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, CreateCollections.class);
            startActivity(intent);

        });

        rule.setOnClickListener(view -> {
                Intent intent = new Intent(MainActivity.this, EditCollections.class);
                startActivity(intent);

        });
    }
}