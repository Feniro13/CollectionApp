package com.Feniro.collectionapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.Feniro.collectionapp.adapter.ItemAddAdapter;

public class ItemAddToCollection extends AppCompatActivity {

    RecyclerView recyclerView;
    ItemAddAdapter itemAddAdapter;

    String name;
    TextView itemName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra_edit_item);

        Bundle arguments = getIntent().getExtras();
        name = arguments.get("Check").toString();

        itemName.setText(name);

        recyclerView = findViewById(R.id.extra_recyclerview_item_edit);


    }
}