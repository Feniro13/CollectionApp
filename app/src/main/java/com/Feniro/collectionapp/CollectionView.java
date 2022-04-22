package com.Feniro.collectionapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.Feniro.collectionapp.adapter.ItemViewAdapter;
import com.Feniro.collectionapp.database.LocalDatabase;
import com.Feniro.collectionapp.database.GlobalDatabase;
import com.Feniro.collectionapp.database.entities.DatabaseLocalEntities;

import java.util.ArrayList;
import java.util.List;

public class CollectionView extends AppCompatActivity {

    int numberOfColumns;
    String name;

    LocalDatabase localDatabase;
    GlobalDatabase globalDatabase;

    List<DatabaseLocalEntities> collectionEntities;
    List<List<String>> list;

//    TextView textView, firstLine;
//    TableLayout table = new TableLayout(this);
//    TableRow tableRow;

    RecyclerView recyclerView;
    ItemViewAdapter itemViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_view);

//        table = findViewById(R.id.CollectionView_TableLayout);
//        tableRow = findViewById(R.id.CollectionView_TableRow);
        ;
        name = getIntent().getStringExtra("check");

        collectionEntities = localDatabase.dao().getAllByName(name);
        numberOfColumns = globalDatabase.dao_global().getNumberOfColumnsByName(name);

        ViewGroup viewGroup = findViewById(android.R.id.content);
        list = converterToList(collectionEntities);

        setItemRecycler(list, numberOfColumns, viewGroup);






//
//            TableRow firstRow = new TableRow(this);
//            TableRow.LayoutParams fparams = new TableRow.LayoutParams();
//            fparams.span = numberOfColumns;
//
//            listOfEntities = converterToList(collectionEntities);
//
//            for (int i = 0; i < numberOfColumns; i++) {
//                firstLine.setText(listOfEntities.get(0).get(i));
//                firstRow.addView(firstLine);
//                tableRows.add(firstRow);
//            }
//
//
//            for (int cols = 0; cols < collectionEntities.size(); cols++) {
//                TableRow tableRow = new TableRow(this);
//                TableRow.LayoutParams params = new TableRow.LayoutParams();
//                params.span = numberOfColumns;
//                for (int i = 0; i < numberOfColumns; i++) {
//                    textView.setText(collectionEntities.get(i).column1);
//                    tableRow.addView(textView);
//                }
//                tableRows.add(tableRow);
//            }
//            for (int i = 0; i < collectionEntities.size(); i++) {
//                table.addView(tableRows.get(i));
//            }
//
//            tableRow.setOnClickListener(view -> {
//
//            });

    }

    public void setItemRecycler(List<List<String>> listOfItems, int numberOfColumns, ViewGroup viewGroup) {
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