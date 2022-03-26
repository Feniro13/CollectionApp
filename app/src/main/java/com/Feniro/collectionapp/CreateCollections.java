package com.Feniro.collectionapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.Feniro.collectionapp.adapter.CollectionAdapter;
import com.Feniro.collectionapp.adapter.ColumnAdapter;
import com.Feniro.collectionapp.database.Database;
import com.Feniro.collectionapp.database.entities.CollectionEntity;
import com.Feniro.collectionapp.model.CollectionModel;
import com.Feniro.collectionapp.model.ColumnModel;

import java.util.ArrayList;
import java.util.List;

public class CreateCollections extends AppCompatActivity {

    Button addColumn, delColumn, create;
    Database database;
    String name;
    int kol = 3;
    TextView warning;
    EditText columnName;

    RecyclerView columnRecycler;
    ColumnAdapter columnAdapter;

    List<ColumnModel> list = new ArrayList<>();
    List<String> names = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_collections);
        list.add(new ColumnModel(0, "Столбец №1"));
        list.add(new ColumnModel(1, "Столбец №2"));
        list.add(new ColumnModel(2, "Столбец №3"));
        names.add("");
        names.add("");
        names.add("");

        setColumnRecycler(list);

        addColumn = findViewById(R.id.Create_Button_Add);
        delColumn = findViewById(R.id.Create_Button_Delete);
        create = findViewById(R.id.Create_Button_Create);
        name = findViewById(R.id.Create_EditText_Name).toString();



        addColumn.setOnClickListener(view -> {

            warning = findViewById(R.id.Create_TextView_Warner);
            if(kol == 12) {
                warning.setText("Максимум 12 столбиков");
            }
            else if(kol >= 1 && kol < 12) {
                kol++;
                list.add(new ColumnModel(kol - 1, "Столбец №" + kol));
                names.add("");
                warning.setText("");
            }
            setColumnRecycler(list);
        });

        delColumn.setOnClickListener(view -> {
            warning = findViewById(R.id.Create_TextView_Warner);
            if(kol == 1) {
                warning.setText("Минимум                   1 столбик");
            }
            if(kol > 1 && kol <= 12) {
                list.remove(kol - 1);
                names.remove(kol - 1);
                kol--;
                warning.setText("");
            }
            setColumnRecycler(list);
        });

        create.setOnClickListener(view -> {
            database = Room.databaseBuilder(this, Database.class, name)
                    .allowMainThreadQueries()
                    .build();
            CollectionEntity entity = new CollectionEntity();
            if(kol >= 1) {
                entity.column1 = names.get(0);
            }
            if(kol >= 2) {
                entity.column2 = names.get(1);
            }
            if(kol >= 3) {
                entity.column3 = names.get(2);
            }
            if(kol >= 4) {
                entity.column4 = names.get(3);
            }
            if(kol >= 5) {
                entity.column5 = names.get(4);
            }
            if(kol >= 6) {
                entity.column6 = names.get(5);
            }
            if(kol >= 7) {
                entity.column7 = names.get(6);
            }
            if(kol >= 8) {
                entity.column8 = names.get(7);
            }
            if(kol >= 9) {
                entity.column9 = names.get(8);
            }
            if(kol >= 10) {
                entity.column10 = names.get(9);
            }
            if(kol >= 11) {
                entity.column11 = names.get(10);
            }
            if(kol >= 12) {
                entity.column12 = names.get(11);
            }
            database.dao().insert(entity);

            Intent intent = new Intent(CreateCollections.this, CollectionView.class);
            startActivity(intent);

        });
    }
    public void setColumnRecycler(List<ColumnModel> list) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        columnRecycler = findViewById(R.id.Create_RView_Columns);
        columnRecycler.setLayoutManager(layoutManager);
        columnAdapter = new ColumnAdapter(this, list, names);
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(CreateCollections.this, MainActivity.class);
        startActivity(intent);
    }
}