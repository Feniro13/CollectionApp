package com.Feniro.collectionapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ViewGroup;

import com.Feniro.collectionapp.adapter.CollectionAdapter;
import com.Feniro.collectionapp.database.GlobalDatabase;
import com.Feniro.collectionapp.database.entities.DatabaseGlobalEntity;
import com.Feniro.collectionapp.model.CollectionModel;

import java.util.List;

public class EditCollections extends AppCompatActivity {
    RecyclerView collectionRecycler;
    CollectionAdapter collectionAdapter;

    List<CollectionModel> listOfCollections;
    GlobalDatabase globalDatabase;
    List<DatabaseGlobalEntity> listGlobal;
    SharedPreferences names;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_collections);

        globalDatabase = GlobalDatabaseLoader.getInstance().getDatabase(); // Глобальная база данных
        listGlobal = globalDatabase.dao_global().getAll();          // Записи в виде малых баз данных

        for(int i = 0; i < listGlobal.size(); i++) {                // получение названий
            listOfCollections.add(new CollectionModel(listGlobal.get(i).id,listGlobal.get(i).name, listGlobal.get(i).numberOfColumns));
        }

        setCollectionRecycler(listOfCollections);
    }
    public void setCollectionRecycler(List<CollectionModel> list) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        collectionRecycler = findViewById(R.id.CollectionRecycler);
        collectionRecycler.setLayoutManager(layoutManager);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        collectionAdapter = new CollectionAdapter(this, list, viewGroup);
        collectionRecycler.setAdapter(collectionAdapter);
    }
    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(EditCollections.this, MainActivity.class);
        startActivity(intent);
    }


    public void addName (String name){
        names = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = names.edit();
        editor.putString(name, "taken");
        editor.commit();
    }
    public boolean ifNameTaken (String name){
        names = getPreferences(MODE_PRIVATE);
        String savedText = names.getString(name, "");
        if (savedText.equals("taken")) {
            return true;
        }
        return false;
    }
    public void deleteName (String name){
        names = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = names.edit();
        editor.remove(name);
        editor.commit();
    }

}


