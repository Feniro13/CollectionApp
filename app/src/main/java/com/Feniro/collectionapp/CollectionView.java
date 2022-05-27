package com.Feniro.collectionapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.Feniro.collectionapp.adapter.ItemAddAdapter;
import com.Feniro.collectionapp.adapter.ItemViewAdapter;
import com.Feniro.collectionapp.database.LocalDatabase;
import com.Feniro.collectionapp.database.GlobalDatabase;
import com.Feniro.collectionapp.database.entities.DatabaseGlobalEntities;
import com.Feniro.collectionapp.database.entities.DatabaseLocalEntities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@SuppressLint("NotifyDataSetChanged")
public class CollectionView extends AppCompatActivity {
    int numberOfColumns, sortingMode;
    String name;
    Boolean selectionMode, wantingMode, filterMode, reversedSorting;
    LocalDatabase localDatabase;
    GlobalDatabase globalDatabase;
    DatabaseLocalEntities mainEntity;
    List<DatabaseLocalEntities> collectionEntities;
    List<Integer> globalPositions;
    List<String> filterPositions;
    RecyclerView mainRecycler, itemRecycler;
    ItemViewAdapter itemViewAdapter;
    ItemAddAdapter itemAddAdapter;
    ImageView add, diff, dialogDiff, iconDiff;
    Button button_1, button_2, button_3;
    TextView title;
    ViewGroup viewGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_collection);
        name = getIntent().getStringExtra("check");
        title = findViewById(R.id.view_textview_title);
        add = findViewById(R.id.view_button_add);
        diff = findViewById(R.id.view_button_diff);
        dialogDiff = findViewById(R.id.view_imageview_diff_buttons);
        iconDiff = findViewById(R.id.different_stuff);
        button_1 = findViewById(R.id.view_diff_button_selectionMode);
        button_2 = findViewById(R.id.view_diff_button_sort);
        button_3 = findViewById(R.id.view_diff_button_showStats);
        viewGroup = findViewById(android.R.id.content);
        title.setText(name);
        selectionMode = false;
        wantingMode = false;
        filterMode = false;
        reversedSorting = false;
        sortingMode = 1;
        globalDatabase = GlobalDatabase.getDatabase(this);
        localDatabase = LocalDatabase.getDatabase(this);
        numberOfColumns = globalDatabase.dao_global().getNumberOfColumnsByName(name);
        collectionEntities = localDatabase.dao().getAllByNameByColumn1(name);
        mainEntity = localDatabase.dao().getNamesOfColumnsByName(name);
        collectionEntities.add(0, mainEntity);
        globalPositions = new ArrayList<>();
        filterPositions = new ArrayList<>();
        for(int i = 0; i < numberOfColumns; i++){
            filterPositions.add(null);
        }
        for(int i = 0; i < collectionEntities.size(); i++) {
            globalPositions.add(i, 0);
        }
        setGoneField();

        // нажатие на итем
        ItemViewAdapter.onItemClickListener itemClickListener = (position, view, item, column) -> {
            setVisibleField();
            if(filterMode){
                for(int i = 0; i < filterPositions.size(); i++){
                    if(filterPositions.get(i) == null){
                        if(i == column){
                            Toast.makeText(view.getContext(), "Добавлено в фильтр",Toast.LENGTH_SHORT).show();
                            filterPositions.set(column, item);
                        }
                    }
                    else {
                        if(i == column){
                            Toast.makeText(view.getContext(), "Убрано из фильтра",Toast.LENGTH_SHORT).show();
                            filterPositions.set(column, null);
                        }
                    }
                }
                getCollectionSortedAndFiltered();
                return;
            }

            if(globalPositions.get(position) == 1) {
                globalPositions.set(position, 0);
            }
            else {
                globalPositions.set(position, 1);
            }
            itemViewAdapter.notifyItemChanged(position);
            selectionMode = false;
            if(Collections.frequency(globalPositions, 1) == 0) {
                setGoneField();
                return;
            }
            else if(Collections.frequency(globalPositions, 1) == 1){
                setItemField();
            }
            else {
                selectionMode = true;
            }

            itemViewAdapter.notifyItemChanged(position);
            if(selectionMode) {
                setSelectionField();

                if(wantingMode) {
                    button_1.setText("Очистить выбор");
                    button_3.setText("Добавить");
                }

                button_1.setOnClickListener(viewUnite -> {
                    Toast.makeText(viewUnite.getContext(), "Объекты были изменены",Toast.LENGTH_SHORT).show();
                    if(!wantingMode){
                        DatabaseLocalEntities entity = new DatabaseLocalEntities();
                        entity.DatabaseName = name;
                        entity.isFirstLine = 0;
                        if (wantingMode) {
                            entity.isFirstLine = 2;
                        }
                        entity.column1 = "";
                        if (numberOfColumns >= 2) {
                            entity.column2 = "";
                        }
                        if (numberOfColumns >= 3) {
                            entity.column3 = "";
                        }
                        if (numberOfColumns >= 4) {
                            entity.column4 = "";
                        }
                        if (numberOfColumns >= 5) {
                            entity.column5 = "";
                        }
                        if (numberOfColumns >= 6) {
                            entity.column6 = "";
                        }
                        if (numberOfColumns >= 7) {
                            entity.column7 = "";
                        }
                        if (numberOfColumns >= 8) {
                            entity.column8 = "";
                        }
                        if (numberOfColumns >= 9) {
                            entity.column9 = "";
                        }
                        if (numberOfColumns >= 10) {
                            entity.column10 = "";
                        }
                        if (numberOfColumns >= 11) {
                            entity.column11 = "";
                        }
                        if (numberOfColumns == 12) {
                            entity.column12 = "";
                        }

                        for (int i = 0; i < globalPositions.size(); i++) {
                            if (globalPositions.get(i) == 1) {
                                if(!(entity.column1 + " ").contains(collectionEntities.get(i).column1)){
                                    entity.column1 += collectionEntities.get(i).column1;
                                    if(i + 1 != globalPositions.size()){
                                        entity.column1 += " ";
                                    }
                                }
                                if (numberOfColumns >= 2) {
                                    if(!(entity.column2 + " ").contains(collectionEntities.get(i).column2) ){
                                        entity.column2 += collectionEntities.get(i).column2;
                                        if(i + 1 != globalPositions.size()){
                                            entity.column2 += " ";
                                        }                                    }
                                }
                                if (numberOfColumns >= 3) {
                                    if(!(entity.column3 + " ").contains(collectionEntities.get(i).column3)){
                                        entity.column3 += collectionEntities.get(i).column3;
                                        if(i + 1 != globalPositions.size()){
                                            entity.column3 += " ";
                                        }                                    }
                                }
                                if (numberOfColumns >= 4) {
                                    if(!(entity.column4 + " ").contains(collectionEntities.get(i).column4)){
                                        entity.column4 += collectionEntities.get(i).column4;
                                        if(i + 1 != globalPositions.size()){
                                            entity.column4 += " ";
                                        }                                    }
                                }
                                if (numberOfColumns >= 5) {
                                    if(!(entity.column5 + " ").contains(collectionEntities.get(i).column5)){
                                        entity.column5 += collectionEntities.get(i).column5;
                                        if(i + 1 != globalPositions.size()){
                                            entity.column5 += " ";
                                        }                                    }
                                }
                                if (numberOfColumns >= 6) {
                                    if(!(entity.column6 + " ").contains(collectionEntities.get(i).column6)){
                                        entity.column6 += collectionEntities.get(i).column6;
                                        if(i + 1 != globalPositions.size()){
                                            entity.column6 += " ";
                                        }                                    }
                                }
                                if (numberOfColumns >= 7) {
                                    if(!(entity.column7 + " ").contains(collectionEntities.get(i).column7)){
                                        entity.column7 += collectionEntities.get(i).column7;
                                        if(i + 1 != globalPositions.size()){
                                            entity.column7+= " ";
                                        }                                    }
                                }
                                if (numberOfColumns >= 8) {
                                    if(!(entity.column8 + " ").contains(collectionEntities.get(i).column8)){
                                        entity.column8 += collectionEntities.get(i).column8;
                                        if(i + 1 != globalPositions.size()){
                                            entity.column8 += " ";
                                        }                                    }
                                }
                                if (numberOfColumns >= 9) {
                                    if(!(entity.column9 + " ").contains(collectionEntities.get(i).column9)){
                                        entity.column9 += collectionEntities.get(i).column9;
                                        if(i + 1 != globalPositions.size()){
                                            entity.column9 += " ";
                                        }                                    }
                                }
                                if (numberOfColumns >= 10) {
                                    if(!(entity.column10 + " ").contains(collectionEntities.get(i).column10)){
                                        entity.column10 += collectionEntities.get(i).column10;
                                        if(i + 1 != globalPositions.size()){
                                            entity.column10 += " ";
                                        }                                    }
                                }
                                if (numberOfColumns >= 11) {
                                    if(!(entity.column11 + " ").contains(collectionEntities.get(i).column11)){
                                        entity.column11 += collectionEntities.get(i).column11;
                                        if(i + 1 != globalPositions.size()){
                                            entity.column11 += " ";
                                        }
                                    }
                                }
                                if (numberOfColumns == 12) {
                                    if(!(entity.column12 + " ").contains(collectionEntities.get(i).column11)){
                                        entity.column12 += collectionEntities.get(i).column12;
                                        if(i + 1 != globalPositions.size()){
                                            entity.column12 += " ";
                                        }
                                    }
                                }
                            }
                        }
                        for (int i = 0; i < globalPositions.size(); i++) {
                            if (globalPositions.get(i) == 1) {
                                localDatabase.dao().deleteEntityById(collectionEntities.get(i).id);
                            }
                        }
                        localDatabase.dao().insert(entity);
                    }
                    getCollectionSortedAndFiltered();
                    setItemField();
                    setGoneField();
                });
                button_2.setOnClickListener(viewDeleteAll -> {
                    Toast.makeText(viewDeleteAll.getContext(), "Объекты были удалены",Toast.LENGTH_SHORT).show();
                    for(int i = 0; i < globalPositions.size(); i++) {
                        if(globalPositions.get(i) == 1) {
                            localDatabase.dao().deleteEntityById(collectionEntities.get(i).id);
                        }
                    }
                    getCollectionSortedAndFiltered();
                    setGoneField();
                    selectionMode = false;
                });
                button_3.setOnClickListener(viewSearch -> {
                    if(wantingMode){
                        for(int i = 0; i < globalPositions.size(); i++){
                            DatabaseLocalEntities newEntity;
                            if(globalPositions.get(i) == 1) {
                                newEntity = collectionEntities.get(i);
                                newEntity.isFirstLine = 0;
                                localDatabase.dao().deleteEntityById(collectionEntities.get(i).id);
                                localDatabase.dao().insert(newEntity);
                            }
                        }
                        getCollectionSortedAndFiltered();
                    }else {
                        selectionMode = false;
                        for (int i = 0; i < collectionEntities.size(); i++) {
                            globalPositions.set(i, 0);
                        }
                        itemViewAdapter.notifyDataSetChanged();
                    }
                    setGoneField();
                });
            }
            // выделен один итем
            else {
                setItemField();

                // Изменить WORKS
                button_1.setOnClickListener(viewChange -> {
                    View view11 = LayoutInflater.from(viewChange.getContext()).inflate(R.layout.dialog_add, viewGroup, false);
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                    builder1.setView(view11);
                    final AlertDialog alertDialog1 = builder1.show();
                    ImageView itemAdd = view11.findViewById(R.id.dialog_add_item_button_add);
                    ImageView itemBack = view11.findViewById(R.id.dialog_add_item_button_back);
                    alertDialog1.show();
                    alertDialog1.getWindow().setDimAmount(0.5f);
                    alertDialog1.getWindow().setGravity(Gravity.CENTER);
                    alertDialog1.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
                    DatabaseLocalEntities localEntity = new DatabaseLocalEntities();
                    localEntity.isFirstLine = 0;
                    if(wantingMode){
                        localEntity.isFirstLine = 2;
                    }
                    localEntity.DatabaseName = name;
                    DatabaseLocalEntities thisEntity = collectionEntities.get(position);
                    setItemDialogRecycler(view11, thisEntity);
                    itemBack.setOnClickListener(view2 -> alertDialog1.dismiss());
                    itemAdd.setOnClickListener(view22 -> {
                        Toast.makeText(viewChange.getContext(), "Объект был изменен",Toast.LENGTH_SHORT).show();
                        localDatabase.dao().insert(getEntityFromAdapter(localEntity));
                        localDatabase.dao().delete(thisEntity);
                        getCollectionSortedAndFiltered();
                        setGoneField();
                        alertDialog1.dismiss();
                        setGoneField();
                        setNewDateChanged();
                    });

                });

                // удалить WORKS
                button_2.setOnClickListener(viewDelete -> {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(view.getContext());
                    builder1.setTitle("Вы уверены?");
                    builder1.setPositiveButton("Да", (dialogInterface, i) -> {
                        Toast.makeText(viewDelete.getContext(), "Объект был удален",Toast.LENGTH_SHORT).show();
                        localDatabase.dao().deleteEntityById(collectionEntities.get(position).id);
                        getCollectionSortedAndFiltered();
                        setGoneField();
                        setNewDateChanged();
                    });
                    builder1.setNegativeButton("Назад", (dialogInterface, i) -> {
                    });
                    final AlertDialog alertDialog = builder1.show();
                    alertDialog.show();
                });

                // поиск в интернете WORKS / добавить из списка желаемого
                if(wantingMode){
                    button_3.setText("Добавить");
                }
                button_3.setOnClickListener(view12 -> {
                    if(wantingMode){
                        DatabaseLocalEntities newEntity = collectionEntities.get(position);
                        newEntity.isFirstLine = 0;
                        localDatabase.dao().deleteEntityById(collectionEntities.get(position).id);
                        localDatabase.dao().insert(newEntity);
                        collectionEntities.remove(position);
                        globalPositions.remove(position);
                        itemViewAdapter.notifyItemRemoved(position);
                        setGoneField();
                    }
                    else {
                        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                        intent.putExtra(SearchManager.QUERY, item);
                        setGoneField();
                        this.startActivity(intent);
                    }
                    globalPositions.set(position, 0);
                    itemViewAdapter.notifyItemChanged(position);
                });
            }
        };

        ItemViewAdapter.onItemSortClickListener itemSortListener = (item) -> {
            item++;
            if(item == sortingMode){
                reversedSorting = !reversedSorting;
            }
            else {
                reversedSorting = false;
            }
            sortingMode = item;
            getCollectionSortedAndFiltered();
        };

        setItemRecycler(collectionEntities, viewGroup, itemClickListener, itemSortListener);
        if(savedInstanceState != null){
            wantingMode = savedInstanceState.getBoolean("WantingMode");
            reversedSorting = savedInstanceState.getBoolean("ReversedSorting");
            sortingMode = savedInstanceState.getInt("SortingMode");
            boolean idk = false;
            if(wantingMode){
                getCollectionSortedAndFiltered();
            }
            for(int i = 0; i < globalPositions.size(); i++){
                globalPositions.set(i, savedInstanceState.getInt(Integer.toString(i)));
                if(savedInstanceState.getInt(Integer.toString(i)) == 1){
                    if(idk){
                        selectionMode = true;
                        setVisibleField();
                        setSelectionField();
                    } else{
                        idk = true;
                    }
                }
            }
            itemViewAdapter.notifyDataSetChanged();
        }

        add.setOnClickListener(viewAdd -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View view1 = LayoutInflater.from(viewAdd.getContext()).inflate(R.layout.dialog_add, viewGroup, false);
            builder.setView(view1);
            ImageView itemAdd = view1.findViewById(R.id.dialog_add_item_button_add);
            ImageView itemBack = view1.findViewById(R.id.dialog_add_item_button_back);
            DatabaseLocalEntities localEntity = new DatabaseLocalEntities();
            localEntity.isFirstLine = 0;
            localEntity.DatabaseName = name;
            if(wantingMode){
                localEntity.isFirstLine = 2;
            }
            final AlertDialog alertDialog = builder.create();
            alertDialog.show();
            alertDialog.getWindow().setDimAmount(0.5f);
            alertDialog.getWindow().setGravity(Gravity.CENTER);
            alertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
            setItemDialogRecycler(view1, localEntity);
            itemBack.setOnClickListener(view2 -> alertDialog.dismiss());
            itemAdd.setOnClickListener(view22 -> {
                Toast.makeText(viewAdd.getContext(), "Объект был добавлен",Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
                localDatabase.dao().insert(getEntityFromAdapter(localEntity));
                getCollectionSortedAndFiltered();
                setNewDateChanged();
            });
        });
        diff.setOnClickListener(view -> {
            setViewField();
            if(button_1.getVisibility() == View.GONE) {
                setVisibleField();
                if(selectionMode){
                    setSelectionField();
                }
                if(filterMode){
                    button_1.setTextColor(getResources().getColor(R.color.selection));
                }
                else {
                    // вкл выкл фильтр
                    button_1.setOnClickListener(view27 -> {
                        if(!filterMode) {                          
                            button_1.setTextColor(getResources().getColor(R.color.selection));
                        }
                        if(filterMode) {
                            filterPositions = new ArrayList<>();
                            button_1.setTextColor(getResources().getColor(R.color.second_main));
                            for(int i = 0; i < numberOfColumns; i++){
                                filterPositions.add(null);
                            }
                            getCollectionSortedAndFiltered();
                            setViewField();
                        }
                        filterMode = !filterMode;
                    });

                    // статистика
                    button_2.setOnClickListener(view24 -> {
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        View view2 = LayoutInflater.from(view24.getContext()).inflate(R.layout.dialog_statistics, viewGroup, false);
                        builder.setView(view2);
                        final AlertDialog alertDialog = builder.show();
                        TextView dataNameEdit = view2.findViewById(R.id.dialog_view_showstats_name_edit);
                        TextView dataSizeEdit = view2.findViewById(R.id.dialog_view_showstats_size_edit);
                        TextView dataDataEdit = view2.findViewById(R.id.dialog_view_showstats_createdate_edit);
                        TextView dataChangeEdit = view2.findViewById(R.id.dialog_view_showstats_change_edit);
                        ImageView dialogBack = view2.findViewById(R.id.dialog_view_showstats_back);
                        dataNameEdit.setText(name);
                        dataSizeEdit.setText(String.valueOf(collectionEntities.size() - 1));
                        dataDataEdit.setText(globalDatabase.dao_global().getDataCreatedByName(name));
                        dataChangeEdit.setText(globalDatabase.dao_global().getDataChangedByName(name));
                        dialogBack.setOnClickListener(view3 -> alertDialog.dismiss());
                        alertDialog.show();
                    });

                    // Скрыть интерфейс
                    button_3.setOnClickListener(view25 -> {
                        setGoneField();
                        Toast.makeText(view.getContext(), "Интерфейс скрыт",Toast.LENGTH_SHORT).show();
                        add.setVisibility(View.GONE);
                        diff.setVisibility(View.GONE);
                        iconDiff.setVisibility(View.GONE);
                    });
                }
            }
            else {
                getCollectionSortedAndFiltered();
                selectionMode = false;
                setGoneField();
            }
        });
        title.setOnClickListener(view -> {
            if(!wantingMode){
                title.setText(getResources().getText(R.string.yourWantingList));
            }
            else {
                title.setText(name);
            }
            setGoneField();
            wantingMode = !wantingMode;
            getCollectionSortedAndFiltered();
        });
    }

    public void setItemRecycler(List<DatabaseLocalEntities> listOfItems, ViewGroup viewGroup, ItemViewAdapter.onItemClickListener itemClickListener, ItemViewAdapter.onItemSortClickListener itemSortListener) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        mainRecycler = findViewById(R.id.View_RecyclerView);
        mainRecycler.setLayoutManager(layoutManager);
        itemViewAdapter = new ItemViewAdapter(listOfItems, numberOfColumns, this, viewGroup, name, itemClickListener, itemSortListener, globalPositions);
        mainRecycler.setAdapter(itemViewAdapter);
    }

    public void setItemDialogRecycler(View view, DatabaseLocalEntities localEntity) {
        itemRecycler = view.findViewById(R.id.dialog_add_item_recyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        itemRecycler.setLayoutManager(layoutManager);
        itemAddAdapter = new ItemAddAdapter(this, mainEntity, localEntity, numberOfColumns);
        itemRecycler.setAdapter(itemAddAdapter);
    }

    public void setVisibleField(){
        add.setVisibility(View.VISIBLE);
        diff.setVisibility(View.VISIBLE);
        iconDiff.setVisibility(View.VISIBLE);
        button_3.setVisibility(View.VISIBLE);
        button_1.setVisibility(View.VISIBLE);
        button_2.setVisibility(View.VISIBLE);
        dialogDiff.setVisibility(View.VISIBLE);
    }

    public void setGoneField(){
        button_3.setVisibility(View.GONE);
        button_1.setVisibility(View.GONE);
        button_2.setVisibility(View.GONE);
        dialogDiff.setVisibility(View.GONE);
    }

    public void setItemField(){
        button_1.setText("Изменить");
        button_2.setText("Удалить");
        button_3.setText("Найти");
        button_1.setTextColor(this.getResources().getColor(R.color.main));
        button_2.setTextColor(this.getResources().getColor(R.color.main));
        button_3.setTextColor(this.getResources().getColor(R.color.main));
    }

    public void setViewField(){
        button_1.setText("Фильтр");
        button_1.setTextColor(this.getResources().getColor(R.color.main));
        button_2.setText("Статистика");
        button_2.setTextColor(this.getResources().getColor(R.color.main));
        button_3.setText("Скрыть");
        button_3.setTextColor(this.getResources().getColor(R.color.main));
    }

    public void setSelectionField(){
        button_1.setText("Объединить");
        button_1.setTextColor(this.getResources().getColor(R.color.main));
        button_2.setText("Удалить");
        button_2.setTextColor(this.getResources().getColor(R.color.main));
        button_3.setText("Очистить выбор");
        button_3.setTextColor(this.getResources().getColor(R.color.main));
    }

    public void setNewDateChanged(){
        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        String dateText = dateFormat.format(currentDate);
        DatabaseGlobalEntities databaseGlobalEntities = new DatabaseGlobalEntities();
        databaseGlobalEntities.dataCreated = globalDatabase.dao_global().getDataCreatedByName(name);
        databaseGlobalEntities.dataChanged = dateText;
        databaseGlobalEntities.numberOfColumns = globalDatabase.dao_global().getNumberOfColumnsByName(name);
        databaseGlobalEntities.name = name;
        globalDatabase.dao_global().deleteCollectionByName(name);
        globalDatabase.dao_global().insert(databaseGlobalEntities);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CollectionView.this, ActivityStart.class);
        startActivity(intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putBoolean("WantingMode", wantingMode);
        savedInstanceState.putBoolean("ReversedSorting", reversedSorting);
        savedInstanceState.putInt("SortingMode", sortingMode);
        for(int i = 0; i < globalPositions.size(); i++){
            savedInstanceState.putInt(Integer.toString(i), globalPositions.get(i));
        }
        super.onSaveInstanceState(savedInstanceState);
    }

    public DatabaseLocalEntities getEntityFromAdapter(DatabaseLocalEntities localEntity) {
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

    public void getCollectionSortedAndFiltered(){
        List<DatabaseLocalEntities> sortedEntities = new ArrayList<>();
        if(sortingMode == 1){
            sortedEntities = localDatabase.dao().getAllByNameByColumn1(name);
            if(wantingMode){
                sortedEntities = localDatabase.dao().getWantingListByNameByColumn1(name);
            }
        }
        if(sortingMode == 2){
            sortedEntities = localDatabase.dao().getAllByNameByColumn2(name);
            if(wantingMode){
                sortedEntities = localDatabase.dao().getWantingListByNameByColumn2(name);
            }
        }
        if(sortingMode == 3){
            sortedEntities = localDatabase.dao().getAllByNameByColumn3(name);
            if(wantingMode){
                sortedEntities = localDatabase.dao().getWantingListByNameByColumn3(name);
            }
        }
        if(sortingMode == 4){
            sortedEntities = localDatabase.dao().getAllByNameByColumn4(name);
            if(wantingMode){
                sortedEntities = localDatabase.dao().getWantingListByNameByColumn4(name);
            }
        }
        if(sortingMode == 5){
            sortedEntities = localDatabase.dao().getAllByNameByColumn5(name);
            if(wantingMode){
                sortedEntities = localDatabase.dao().getWantingListByNameByColumn5(name);
            }
        }
        if(sortingMode == 6){
            sortedEntities = localDatabase.dao().getAllByNameByColumn6(name);
            if(wantingMode){
                sortedEntities = localDatabase.dao().getWantingListByNameByColumn6(name);
            }
        }
        if(sortingMode == 7){
            sortedEntities = localDatabase.dao().getAllByNameByColumn7(name);
            if(wantingMode){
                sortedEntities = localDatabase.dao().getWantingListByNameByColumn7(name);
            }
        }
        if(sortingMode == 8){
            sortedEntities = localDatabase.dao().getAllByNameByColumn8(name);
            if(wantingMode){
                sortedEntities = localDatabase.dao().getWantingListByNameByColumn8(name);
            }
        }
        if(sortingMode == 9){
            sortedEntities = localDatabase.dao().getAllByNameByColumn9(name);
            if(wantingMode){
                sortedEntities = localDatabase.dao().getWantingListByNameByColumn9(name);
            }
        }
        if(sortingMode == 10){
            sortedEntities = localDatabase.dao().getAllByNameByColumn10(name);
            if(wantingMode){
                sortedEntities = localDatabase.dao().getWantingListByNameByColumn10(name);
            }
        }
        if(sortingMode == 11){
            sortedEntities = localDatabase.dao().getAllByNameByColumn11(name);
            if(wantingMode){
                sortedEntities = localDatabase.dao().getWantingListByNameByColumn11(name);
            }
        }
        if(sortingMode == 12){
            sortedEntities = localDatabase.dao().getAllByNameByColumn12(name);
            if(wantingMode){
                sortedEntities = localDatabase.dao().getWantingListByNameByColumn12(name);
            }
        }
        if(reversedSorting) {
            Collections.reverse(sortedEntities);
        }
        sortedEntities.add(0, mainEntity);

        List<DatabaseLocalEntities> filteredEntities = new ArrayList<>();
        for(int i = 1; i < sortedEntities.size(); i++){
            if(filterPositions.get(0) != null){
                if(!sortedEntities.get(i).column1.equals(filterPositions.get(0))){
                    continue;
                }
            }
            if(numberOfColumns == 1){
                filteredEntities.add(sortedEntities.get(i));
                continue;
            }
            if(filterPositions.get(1) != null){
                if(!sortedEntities.get(i).column2.equals(filterPositions.get(1))){
                    continue;
                }
            }
            if(numberOfColumns == 2){
                filteredEntities.add(sortedEntities.get(i));
                continue;
            }
            if(filterPositions.get(2) != null){
                if(!sortedEntities.get(i).column3.equals(filterPositions.get(2))){
                    continue;
                }
            }
            if(numberOfColumns == 3){
                filteredEntities.add(sortedEntities.get(i));
                continue;
            }
            if(filterPositions.get(3) != null){
                if(!sortedEntities.get(i).column4.equals(filterPositions.get(3))){
                    continue;
                }
            }
            if(numberOfColumns == 4){
                filteredEntities.add(sortedEntities.get(i));
                continue;
            }
            if(filterPositions.get(4) != null){
                if(!sortedEntities.get(i).column5.equals(filterPositions.get(4))){
                    continue;
                }
            }
            if(numberOfColumns == 5){
                filteredEntities.add(collectionEntities.get(i));
                continue;
            }
            if(filterPositions.get(5) != null){
                if(!sortedEntities.get(i).column6.equals(filterPositions.get(5))){
                    continue;
                }
            }
            if(numberOfColumns == 6){
                filteredEntities.add(sortedEntities.get(i));
                continue;
            }
            if(filterPositions.get(6) != null){
                if(!sortedEntities.get(i).column7.equals(filterPositions.get(6))){
                    continue;
                }
            }
            if(numberOfColumns == 7){
                filteredEntities.add(sortedEntities.get(i));
                continue;
            }
            if(filterPositions.get(7) != null){
                if(!sortedEntities.get(i).column8.equals(filterPositions.get(7))){
                    continue;
                }
            }
            if(numberOfColumns == 8){
                filteredEntities.add(sortedEntities.get(i));
                continue;
            }
            if(filterPositions.get(8) != null){
                if(!sortedEntities.get(i).column9.equals(filterPositions.get(8))){
                    continue;
                }
            }
            if(numberOfColumns == 9){
                filteredEntities.add(sortedEntities.get(i));
                continue;
            }
            if(filterPositions.get(9) != null){
                if(!sortedEntities.get(i).column10.equals(filterPositions.get(9))){
                    continue;
                }
            }
            if(numberOfColumns == 10){
                filteredEntities.add(sortedEntities.get(i));
                continue;
            }
            if(filterPositions.get(10) != null){
                if(!sortedEntities.get(i).column11.equals(filterPositions.get(10))){
                    continue;
                }
            }
            if(numberOfColumns == 11){
                filteredEntities.add(sortedEntities.get(i));
                continue;
            }
            if(filterPositions.get(11) != null){
                if(!sortedEntities.get(i).column12.equals(filterPositions.get(11))){
                    continue;
                }
            }
            filteredEntities.add(collectionEntities.get(i));
        }
        filteredEntities.add(0, mainEntity);
        while(collectionEntities.size() > filteredEntities.size()){
            collectionEntities.remove(collectionEntities.size() - 1);
        }
        while(globalPositions.size() > filteredEntities.size()){
            globalPositions.remove(globalPositions.size() - 1);
        }
        for(int i = 0; i < filteredEntities.size(); i++){
            if(collectionEntities.size() <= i){
                collectionEntities.add(filteredEntities.get(i));
            }
            collectionEntities.set(i, filteredEntities.get(i));
            if(globalPositions.size() <= i){
                globalPositions.add(0);
            }
            globalPositions.set(i, 0);
        }
        itemViewAdapter.notifyDataSetChanged();
    }
}
