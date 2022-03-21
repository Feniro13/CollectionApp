package com.Feniro.collectionapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.Feniro.collectionapp.adapter.CollectionAdapter;
import com.Feniro.collectionapp.model.CollectionModel;

import java.util.ArrayList;
import java.util.List;

public class RuleCollections extends AppCompatActivity {
    RecyclerView collectionRecycler;
    CollectionAdapter collectionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rule_collections);
        List<CollectionModel> list = new ArrayList<>();

        setCollectionRecycler(list);
    }
    public void setCollectionRecycler(List<CollectionModel> list) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        collectionRecycler = findViewById(R.id.CollectionRecycler);
        collectionRecycler.setLayoutManager(layoutManager);

        collectionAdapter = new CollectionAdapter(this, list);
        collectionRecycler.setAdapter(collectionAdapter);
    }
    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(RuleCollections.this, MainActivity.class);
        startActivity(intent);
    }
}

