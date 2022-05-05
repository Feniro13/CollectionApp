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
import android.widget.ImageView;
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

    ImageView add, diff;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_collection);

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
        setItemRecycler(collectionEntities, numberOfColumns, viewGroup, mainEntity);

        add.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View view1 = LayoutInflater.from(view.getContext()).inflate(R.layout.dialog_view_add, viewGroup, false);
            builder.setView(view1);

            Button itemAdd = view1.findViewById(R.id.dialog_add_item_button_add);
            Button itemBack = view1.findViewById(R.id.dialog_add_item_button_back);
            TextView warning = view1.findViewById(R.id.dialog_add_item_textview_warning);

            warning.setText(name);
            DatabaseLocalEntities localEntity = new DatabaseLocalEntities();
            localEntity.isFirstLine = 0;
            localEntity.DatabaseName = name;

            final AlertDialog alertDialog = builder.create();
            alertDialog.show();

            setItemDialogRecycler(view1, mainEntity, localEntity);

            itemBack.setOnClickListener(view2 -> alertDialog.dismiss());

            itemAdd.setOnClickListener(view22 -> {
                localDatabase.dao().insert(getEntityFromAdapter(localEntity));
                alertDialog.dismiss();
                collectionEntities = localDatabase.dao().getAllByNameByColumn1(name);
                collectionEntities.add(0, mainEntity);
                setItemRecycler(collectionEntities, numberOfColumns, viewGroup, mainEntity);

            });
        });

        diff.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View view1 = LayoutInflater.from(view.getContext()).inflate(R.layout.dialog_view_diff, viewGroup, false);

            builder.setView(view1);
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

            Button download = view1.findViewById(R.id.view_diff_button_download);
            Button sort = view1.findViewById(R.id.view_diff_button_sort);
            Button showStats = view1.findViewById(R.id.view_diff_button_showstats);

            download.setOnClickListener(view23 -> {

            });

            showStats.setOnClickListener(view25 -> {
                alertDialog.dismiss();
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                View view2 = LayoutInflater.from(view25.getContext()).inflate(R.layout.dialog_view_diff_stats, viewGroup, false);

                builder1.setView(view2);
                final AlertDialog alertDialog1 = builder1.show();

                TextView dataNameEdit = view2.findViewById(R.id.dialog_view_showstats_name_edit);
                TextView dataSizeEdit = view2.findViewById(R.id.dialog_view_showstats_size_edit);
                TextView dataDataEdit = view2.findViewById(R.id.dialog_view_showstats_createdate_edit);
                TextView dataChangeEdit = view2.findViewById(R.id.dialog_view_showstats_change_edit);
                Button dialogBack = view2.findViewById(R.id.dialog_view_showstats_back);

                dataNameEdit.setText(name);
                dataSizeEdit.setText(String.valueOf(collectionEntities.size()-1));
                dataDataEdit.setText(globalDatabase.dao_global().getDataCreatedByName(name));
                dataChangeEdit.setText(globalDatabase.dao_global().getDataChangedByName(name));

                dialogBack.setOnClickListener(view3 -> alertDialog1.dismiss());
                alertDialog1.show();
            });

            sort.setOnClickListener(view24 -> {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                View view2 = LayoutInflater.from(view24.getContext()).inflate(R.layout.dialog_view_diff_sort, viewGroup, false);

                builder1.setView(view2);
                alertDialog.dismiss();
                AlertDialog alertDialog1 = builder1.create();

                setItemDialogSortRecycler(view2, ConverterToList(mainEntity, numberOfColumns));


                alertDialog1.show();
            });

        });
    }

    public void setItemRecycler(List<DatabaseLocalEntities> listOfItems, int numberOfColumns, ViewGroup viewGroup, DatabaseLocalEntities mainEntity) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        mainRecycler = findViewById(R.id.View_RecyclerView);
        mainRecycler.setLayoutManager(layoutManager);
        itemViewAdapter = new ItemViewAdapter(listOfItems, numberOfColumns, this, viewGroup, mainEntity, name);
        itemViewAdapter.notifyDataSetChanged();
        mainRecycler.setAdapter(itemViewAdapter);
    }

    public void setItemDialogRecycler(View view, DatabaseLocalEntities mainEntity, DatabaseLocalEntities localEntity) {
        itemRecycler = view.findViewById(R.id.dialog_add_item_recyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        itemRecycler.setLayoutManager(layoutManager);
        itemAddAdapter = new ItemAddAdapter(this, mainEntity, localEntity, numberOfColumns);
        itemRecycler.setAdapter(itemAddAdapter);
    }

    public void setItemDialogSortRecycler(View view, List<String> items){
        sortRecycler = view.findViewById(R.id.dialog_view_diff_sort_rview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        itemSortAdapter = new ItemSortAdapter(items, this);
        sortRecycler.setLayoutManager(layoutManager);
        sortRecycler.setAdapter(itemSortAdapter);

    }

    public DatabaseLocalEntities getEntityFromAdapter(DatabaseLocalEntities localEntity){
        List<String> names = itemAddAdapter.entityList;
        int kol = 1;
        localEntity.column1 = names.get(0);
        if (kol + 1 > numberOfColumns) {
            return localEntity;
        }
        kol++;
        localEntity.column2 = names.get(1);
        if (kol + 1 > numberOfColumns) {
            return localEntity;
        }
        kol++;
        localEntity.column3 = names.get(2);
        if (kol + 1 > numberOfColumns) {
            return localEntity;
        }
        kol++;
        localEntity.column4 = names.get(3);
        if (kol + 1 > numberOfColumns) {
            return localEntity;
        }
        kol++;
        localEntity.column5 = names.get(4);
        if (kol + 1 > numberOfColumns) {
            return localEntity;
        }
        kol++;
        localEntity.column6 = names.get(5);
        if (kol + 1 > numberOfColumns) {
            return localEntity;
        }
        kol++;
        localEntity.column7 = names.get(6);
        if (kol + 1 > numberOfColumns) {
            return localEntity;
        }
        kol++;
        localEntity.column8 = names.get(7);
        if (kol + 1 > numberOfColumns) {
            return localEntity;
        }
        kol++;
        localEntity.column9 = names.get(8);
        if (kol + 1 > numberOfColumns) {
            return localEntity;
        }
        kol++;
        localEntity.column10 = names.get(9);
        if (kol + 1 > numberOfColumns) {
            return localEntity;
        }
        kol++;
        localEntity.column11 = names.get(10);
        if (kol + 1 > numberOfColumns) {
            return localEntity;
        }
        localEntity.column12 = names.get(11);
        return localEntity;
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

        if(entity.column12 == null) {
            returnList.add("");
        }
        else {returnList.add(entity.column12); }
        return  returnList;
    }

}