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
import android.widget.TextView;

import com.Feniro.collectionapp.adapter.ItemAddAdapter;
import com.Feniro.collectionapp.adapter.ItemSortAdapter;
import com.Feniro.collectionapp.adapter.ItemViewAdapter;
import com.Feniro.collectionapp.database.LocalDatabase;
import com.Feniro.collectionapp.database.GlobalDatabase;
import com.Feniro.collectionapp.database.entities.DatabaseLocalEntities;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CollectionView extends AppCompatActivity {

    int numberOfColumns;
    String name;

    LocalDatabase localDatabase;
    GlobalDatabase globalDatabase;

    File file;


    List<DatabaseLocalEntities> collectionEntities;

    RecyclerView mainRecycler, itemRecycler, sortRecycler;
    ItemViewAdapter itemViewAdapter;
    ItemAddAdapter itemAddAdapter;
    ItemSortAdapter itemSortAdapter;

    Button add, diff;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_view);

        DatabaseLocalEntities mainEntity;
        name = getIntent().getStringExtra("check");

        title = findViewById(R.id.view_textview_title);
        title.setText(name);

        add = findViewById(R.id.view_button_add);
        diff = findViewById(R.id.view_button_diff);

        globalDatabase = GlobalDatabase.getDatabase(this);
        localDatabase = LocalDatabase.getDatabase(this);

        numberOfColumns = globalDatabase.dao_global().getNumberOfColumnsByName(name);
        collectionEntities = localDatabase.dao().getAllByNameByColumn1(name);
        mainEntity = localDatabase.dao().getNamesOfColumnsByName(name);

        ViewGroup viewGroup = findViewById(android.R.id.content);

        collectionEntities.add(0, mainEntity);
        setItemRecycler(collectionEntities, numberOfColumns, viewGroup);

        add.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false);
            View view1 = LayoutInflater.from(view.getContext()).inflate(R.layout.dialog_view_add_item, viewGroup, false);

            builder.setView(view1);

            Button itemAdd = view1.findViewById(R.id.dialog_add_item_button_add);
            Button itemBack = view1.findViewById(R.id.dialog_add_item_button_back);
            TextView warning = view1.findViewById(R.id.dialog_add_item_textview_warning);

            DatabaseLocalEntities localEntity = new DatabaseLocalEntities();
            localEntity.isFirstLine = 0;
            localEntity.DatabaseName = name;

            setItemDialogRecycler(view1, mainEntity, localEntity);


            final AlertDialog alertDialog = builder.create();

            itemBack.setOnClickListener(view2 -> alertDialog.dismiss());

            itemAdd.setOnClickListener(view22 -> {
                try {
                    List<String> names = itemAddAdapter.entityList;
                    int kol = 1;
                    boolean isKolBigger = false;
                    localEntity.column1 = names.get(0);
                    if (kol + 1 > numberOfColumns) {
                        isKolBigger = true;
                    }
                    kol++;
                    localEntity.column1 = names.get(1);
                    if (kol + 1 > numberOfColumns && !isKolBigger) {
                        isKolBigger = true;
                    }
                    kol++;
                    localEntity.column1 = names.get(2);
                    if (kol + 1 > numberOfColumns && !isKolBigger) {
                        isKolBigger = true;
                    }
                    kol++;
                    localEntity.column1 = names.get(3);
                    if (kol + 1 > numberOfColumns && !isKolBigger) {
                        isKolBigger = true;
                    }
                    kol++;
                    localEntity.column1 = names.get(4);
                    if (kol + 1 > numberOfColumns && !isKolBigger) {
                        isKolBigger = true;
                    }
                    kol++;
                    localEntity.column1 = names.get(5);
                    if (kol + 1 > numberOfColumns && !isKolBigger) {
                        isKolBigger = true;
                    }
                    kol++;
                    localEntity.column1 = names.get(6);
                    if (kol + 1 > numberOfColumns && !isKolBigger) {
                        isKolBigger = true;
                    }
                    kol++;
                    localEntity.column1 = names.get(7);
                    if (kol + 1 > numberOfColumns && !isKolBigger) {
                        isKolBigger = true;
                    }
                    kol++;
                    localEntity.column1 = names.get(8);
                    if (kol + 1 > numberOfColumns && !isKolBigger) {
                        isKolBigger = true;
                    }
                    kol++;
                    localEntity.column1 = names.get(9);
                    if (kol + 1 > numberOfColumns && !isKolBigger) {
                        isKolBigger = true;
                    }
                    kol++;
                    localEntity.column1 = names.get(10);
                    if (kol + 1 > numberOfColumns && !isKolBigger) {
                        isKolBigger = true;
                    }
                    if (!isKolBigger) {
                        localEntity.column1 = names.get(11);
                    }
                }catch (Exception E){
                    warning.setText("Все поля должны быть заполнены");
                }
            });
            alertDialog.show();
        });

        diff.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View view1 = LayoutInflater.from(view.getContext()).inflate(R.layout.dialog_view_diff, viewGroup, false);

            builder.setView(view1);
            AlertDialog alertDialog = builder.create();

            Button download = view1.findViewById(R.id.view_diff_button_download);
            Button sort = view1.findViewById(R.id.view_diff_button_sort);

            download.setOnClickListener(view23 -> {

            });

            sort.setOnClickListener(view24 -> {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                View view2 = LayoutInflater.from(view24.getContext()).inflate(R.layout.dialog_view_diff_sort, viewGroup, false);

                builder1.setView(view2);
                AlertDialog alertDialog1 = builder1.create();

                setItemDialogSortRecycler(view2, ConverterToList(mainEntity, numberOfColumns));


                builder1.show();
            });

            builder.show();
        });


    }

    public void setItemRecycler(List<DatabaseLocalEntities> listOfItems, int numberOfColumns, ViewGroup viewGroup) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        mainRecycler = findViewById(R.id.View_RecyclerView);
        mainRecycler.setLayoutManager(layoutManager);
        itemViewAdapter = new ItemViewAdapter(listOfItems, numberOfColumns, this, viewGroup);
        mainRecycler.setHasFixedSize(true);
        mainRecycler.setAdapter(itemViewAdapter);
    }

    public void setItemDialogRecycler(View view, DatabaseLocalEntities mainEntity, DatabaseLocalEntities localEntity) {
        itemRecycler = view.findViewById(R.id.dialog_add_item_recyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        itemRecycler.setLayoutManager(layoutManager);
        ItemAddAdapter itemAddAdapter = new ItemAddAdapter(this, mainEntity, localEntity, numberOfColumns);
        itemRecycler.setAdapter(itemAddAdapter);
    }

    public void setItemDialogSortRecycler(View view, List<String> items){
        sortRecycler = view.findViewById(R.id.dialog_view_diff_sort_rview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        itemSortAdapter = new ItemSortAdapter(items, this);
        sortRecycler.setLayoutManager(layoutManager);
        sortRecycler.setAdapter(itemSortAdapter);

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CollectionView.this, ActivityStart.class);
        startActivity(intent);
    }

    List<String> ConverterToList(DatabaseLocalEntities entity, int numberOfColumns) {
        List<String> returnList = new ArrayList<>();
        int kol = 1;
        if(entity.column1 == null) {
            returnList.add("");
        }
        else {returnList.add(entity.column1); }

        if(kol + 1 > numberOfColumns) {
            return  returnList;
        }
        kol++;

        if(entity.column2 == null) {
            returnList.add("");
        }
        else {returnList.add(entity.column2); }

        if(kol + 1 > numberOfColumns) {
            return  returnList;
        }
        kol++;

        if(entity.column3 == null) {
            returnList.add("");
        }
        else {returnList.add(entity.column3); }

        if(kol + 1 > numberOfColumns) {
            return  returnList;
        }
        kol++;

        if(entity.column4 == null) {
            returnList.add("");
        }
        else {returnList.add(entity.column4); }

        if(kol + 1 > numberOfColumns) {
            return  returnList;
        }
        kol++;

        if(entity.column5 == null) {
            returnList.add("");
        }
        else {returnList.add(entity.column5); }

        if(kol + 1 > numberOfColumns) {
            return  returnList;
        }
        kol++;

        if(entity.column6 == null) {
            returnList.add("");
        }
        else {returnList.add(entity.column6); }

        if(kol + 1 > numberOfColumns) {
            return  returnList;
        }
        kol++;

        if(entity.column7 == null) {
            returnList.add("");
        }
        else {returnList.add(entity.column7); }

        if(kol + 1 > numberOfColumns) {
            return  returnList;
        }
        kol++;

        if(entity.column8 == null) {
            returnList.add("");
        }
        else {returnList.add(entity.column8); }

        if(kol + 1 > numberOfColumns) {
            return  returnList;
        }
        kol++;

        if(entity.column9 == null) {
            returnList.add("");
        }
        else {returnList.add(entity.column9); }

        if(kol + 1 > numberOfColumns) {
            return  returnList;
        }
        kol++;

        if(entity.column10 == null) {
            returnList.add("");
        }
        else {returnList.add(entity.column10); }
        if(kol + 1 > numberOfColumns) {
            return  returnList;
        }
        kol++;

        if(entity.column11 == null) {
            returnList.add("");
        }
        else {returnList.add(entity.column11); }
        if(kol + 1 > numberOfColumns) {
            return  returnList;
        }
        kol++;

        if(entity.column12 == null) {
            returnList.add("");
        }
        else {returnList.add(entity.column12); }
        return  returnList;
    }

}