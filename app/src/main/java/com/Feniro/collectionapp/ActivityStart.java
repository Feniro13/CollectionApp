package com.Feniro.collectionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class ActivityStart extends AppCompatActivity {

    Button create, edit;
    String name;
    Boolean isNameGiven;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        try { // Сохранить имя, если оно было введено в CollectionCreate
            Bundle args = getIntent().getExtras();
            name = args.get("check").toString();
            isNameGiven = true;
        } catch (Exception E){isNameGiven = false;}

        create = findViewById(R.id.start_Button_Create);
        edit = findViewById(R.id.start_Button_Edit);

        create.setOnClickListener(view -> { // Создать коллекцию
            Intent intent = new Intent(ActivityStart.this, CollectionCreate.class);
            if(isNameGiven) {
                intent.putExtra("check", name);
            }
            startActivity(intent);

        });

        edit.setOnClickListener(view -> { // Редактировать коллекции
                Intent intent = new Intent(ActivityStart.this, CollectionEdit.class);
                startActivity(intent);

        });
    }
}