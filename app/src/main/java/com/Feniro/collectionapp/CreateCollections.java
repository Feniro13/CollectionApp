package com.Feniro.collectionapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

    RecyclerView columnRecycler;
    ColumnAdapter columnAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_collections);

        List<ColumnModel> list = new ArrayList<>();
        for(int i = 1; i <= 12; i++) {
            list.add(new ColumnModel(i, Integer.toString(i)));
        }

        addColumn = findViewById(R.id.Create_Button_Add);
        delColumn = findViewById(R.id.Create_Button_Delete);
        create = findViewById(R.id.Create_Button_Create);
        name = findViewById(R.id.Create_EditText_Name).toString();

        addColumn.setOnClickListener(view -> {
            kol++;
            setColumnRecycler(list, kol);
        });

        delColumn.setOnClickListener(view -> {
            kol--;
            setColumnRecycler(list, kol);
        });

        create.setOnClickListener(view -> {
            database = Room.databaseBuilder(this, Database.class, name)
                    .allowMainThreadQueries()
                    .build();
            CollectionEntity entity = new CollectionEntity();
            entity.column1 = list.get(0).getName();
            if(kol >= 2) {
                entity.column2 = list.get(1).getName();
            }
            if(kol >= 3) {
                entity.column3 = list.get(2).getName();
            }
            if(kol >= 4) {
                entity.column4 = list.get(3).getName();
            }
            if(kol >= 5) {
                entity.column5 = list.get(4).getName();
            }
            if(kol >= 6) {
                entity.column6 = list.get(5).getName();
            }
            if(kol >= 7) {
                entity.column7 = list.get(6).getName();
            }
            if(kol >= 8) {
                entity.column8 = list.get(7).getName();
            }
            if(kol >= 9) {
                entity.column9 = list.get(8).getName();
            }
            if(kol >= 10) {
                entity.column10 = list.get(9).getName();
            }
            if(kol >= 11) {
                entity.column11 = list.get(10).getName();
            }
            if(kol >= 12) {
                entity.column12 = list.get(11).getName();
            }

            database.dao().insert(entity);

        });
    }
    public void setColumnRecycler(List<ColumnModel> list, int kol) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        columnRecycler = findViewById(R.id.CollectionRecycler);
        columnRecycler.setLayoutManager(layoutManager);

        List<ColumnModel> listToShow = new ArrayList<>();
        for (int i = 0; i < kol; i++) {
            listToShow.add(new ColumnModel(i + 1, list.get(i + 1).getName()));
        }
        columnAdapter = new ColumnAdapter(this, list);
        columnRecycler.setAdapter(columnAdapter);
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(CreateCollections.this, MainActivity.class);
        startActivity(intent);
    }
}