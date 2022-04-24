package com.Feniro.collectionapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.Feniro.collectionapp.adapter.ItemAddAdapter;
import com.Feniro.collectionapp.adapter.ItemViewAdapter;
import com.Feniro.collectionapp.database.LocalDatabase;
import com.Feniro.collectionapp.database.GlobalDatabase;
import com.Feniro.collectionapp.database.entities.DatabaseLocalEntities;

import java.lang.invoke.LambdaConversionException;
import java.util.ArrayList;
import java.util.List;

public class CollectionView extends AppCompatActivity {

    int numberOfColumns;
    String name;

    LocalDatabase localDatabase;
    GlobalDatabase globalDatabase;

    List<DatabaseLocalEntities> collectionEntities;

    RecyclerView recyclerView;
    ItemViewAdapter itemViewAdapter;
    Button add, diff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_view);

        DatabaseLocalEntities databaseLocalEntities;
        name = getIntent().getStringExtra("check");

        numberOfColumns = globalDatabase.dao_global().getNumberOfColumnsByName(name);
        collectionEntities = localDatabase.dao().getAllByName(name);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        add = findViewById(R.id.view_button_add);
        diff = findViewById(R.id.view_button_diff);

        for(int i = 0; i < collectionEntities.size(); i++) {
            if(collectionEntities.get(i).isFirstLine) {
                databaseLocalEntities = collectionEntities.get(0);
                collectionEntities.set(0, collectionEntities.get(i));
                collectionEntities.set(i, databaseLocalEntities);
            }
        }

        setItemRecycler(collectionEntities, numberOfColumns, viewGroup);

        add.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View view1 = LayoutInflater.from(view.getContext()).inflate(R.layout.dialog_add_to_collection, viewGroup, false);

            builder.setView(view1);
            final AlertDialog alertDialog = builder.show();

            Button itemAdd = (Button) view1.findViewById(R.id.dialog_add_item_button_add);
            Button itemBack = (Button) view1.findViewById(R.id.dialog_add_item_button_back);
            RecyclerView itemRecycler = view1.findViewById(R.id.dialog_add_item_recyclerview);

            DatabaseLocalEntities localEntity = new DatabaseLocalEntities();
            localEntity.isFirstLine = false;
            localEntity.DatabaseName = name;

            ItemAddAdapter itemAddAdapter = new ItemAddAdapter(this, localEntity, collectionEntities.get(0), numberOfColumns);
            itemRecycler.setAdapter(itemAddAdapter);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
            itemRecycler.setLayoutManager(layoutManager);

            itemBack.setOnClickListener(view2 -> {
                alertDialog.dismiss();
            });

            itemAdd.setOnClickListener(view22 -> {
                List<String> names = itemAddAdapter.entityList;

            });

        });

        diff.setOnClickListener(view -> {

        });


    }

    public void setItemRecycler(List<DatabaseLocalEntities> listOfItems, int numberOfColumns, ViewGroup viewGroup) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView = findViewById(R.id.View_RecyclerView);
        recyclerView.setLayoutManager(layoutManager);
        itemViewAdapter = new ItemViewAdapter(listOfItems, numberOfColumns, this, viewGroup);
        recyclerView.setAdapter(itemViewAdapter);
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(CollectionView.this, ActivityStart.class);
        startActivity(intent);
    }

    public List<List<String>> converterToList(List<DatabaseLocalEntities> list){
        List<List<String>> listOfStrings = new ArrayList<>();
        List<String> strings;
        int k = 1;
        for(int i = 0 ; i < list.size();i++){
            strings = new ArrayList<>();
            strings.add(list.get(i).column1);
            k++;
            if(k > numberOfColumns) {
                listOfStrings.add(strings);
                continue;
            }
            strings.add(list.get(i).column2);
            k++;
            if(k > numberOfColumns) {
                listOfStrings.add(strings);
                continue;
            }
            strings.add(list.get(i).column3);
            k++;
            if(k > numberOfColumns) {
                listOfStrings.add(strings);
                continue;
            }
            strings.add(list.get(i).column4);
            k++;
            if(k > numberOfColumns) {
                listOfStrings.add(strings);
                continue;
            }
            strings.add(list.get(i).column5);
            k++;
            if(k > numberOfColumns) {
                listOfStrings.add(strings);
                continue;
            }
            strings.add(list.get(i).column6);
            k++;
            if(k > numberOfColumns) {
                listOfStrings.add(strings);
                continue;
            }
            strings.add(list.get(i).column7);
            k++;
            if(k > numberOfColumns) {
                listOfStrings.add(strings);
                continue;
            }
            strings.add(list.get(i).column8);
            k++;
            if(k > numberOfColumns) {
                listOfStrings.add(strings);
                continue;
            }
            strings.add(list.get(i).column9);
            k++;
            if(k > numberOfColumns) {
                listOfStrings.add(strings);
                continue;
            }
            strings.add(list.get(i).column10);
            k++;
            if(k > numberOfColumns) {
                listOfStrings.add(strings);
                continue;
            }
            strings.add(list.get(i).column11);
            k++;
            if(k > numberOfColumns) {
                listOfStrings.add(strings);
                continue;
            }
            strings.add(list.get(i).column12);
            listOfStrings.add(strings);
        }
        return listOfStrings;
    }
}