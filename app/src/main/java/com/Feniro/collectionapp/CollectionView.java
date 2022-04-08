package com.Feniro.collectionapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.Feniro.collectionapp.adapter.ColumnAdapter;
import com.Feniro.collectionapp.adapter.EntitiesAdapter;
import com.Feniro.collectionapp.database.Database;
import com.Feniro.collectionapp.database.GlobalDatabase;
import com.Feniro.collectionapp.database.dao.DAO;
import com.Feniro.collectionapp.database.entities.CollectionEntity;
import com.Feniro.collectionapp.database.entities.DatabaseGlobalEntity;
import com.Feniro.collectionapp.model.CollectionModel;
import com.Feniro.collectionapp.model.ColumnModel;
import com.Feniro.collectionapp.model.EntityModel;

import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Scanner;

public class CollectionView extends AppCompatActivity {

    int numberOfColumns;
    String name;

    Database database;
    GlobalDatabase gDatabase;

    RecyclerView recyclerView;
    EntitiesAdapter entitiesAdapter;
    List<CollectionEntity> collectionEntities;
    List<EntityModel> entities;
    DatabaseGlobalEntity databaseGlobalEntity;

    TextView textView;
    TableLayout table = new TableLayout(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_view);


        Bundle arguments = getIntent().getExtras();
        name = arguments.get("Check").toString();
        table = findViewById(R.id.CollectionView_TableLayout);

        databaseGlobalEntity = gDatabase.dao_global().getByName(name);
        database = databaseGlobalEntity.database;
        numberOfColumns = databaseGlobalEntity.numberOfColumns;
        collectionEntities = database.dao().getAll();
//        for(int i = 0; i < collectionEntities.size(); i++) {
//            entities.add(new EntityModel(i, collectionEntities.get(0).column1, collectionEntities.get(0).column2,
//                    collectionEntities.get(0).column3, collectionEntities.get(0).column4,
//                    collectionEntities.get(0).column5, collectionEntities.get(0).column6,
//                    collectionEntities.get(0).column7, collectionEntities.get(0).column8,
//                    collectionEntities.get(0).column9, collectionEntities.get(0).column10,
//                    collectionEntities.get(0).column11, collectionEntities.get(0).column12));
//        }
        for(int cols = 0; cols < numberOfColumns; cols++) {
            TableRow tableRow = new TableRow(this);
            TableRow.LayoutParams params = new TableRow.LayoutParams();
            params.span = numberOfColumns;
            for (int i = 0; i < collectionEntities.size(); i++) {
                textView.setText(collectionEntities.get(i).column1);
                tableRow.addView(textView);
                table.addView(tableRow);
            }
        }



       // setColumnRecycler(entities, numberOfColumns);




    }

    public void setColumnRecycler(List<EntityModel> list, int numberOfColumns) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView = findViewById(R.id.Create_RView_Columns);
        recyclerView.setLayoutManager(layoutManager);
        entitiesAdapter = new EntitiesAdapter(this, list, numberOfColumns);
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(CollectionView.this, EditCollections.class);
        startActivity(intent);
    }
}