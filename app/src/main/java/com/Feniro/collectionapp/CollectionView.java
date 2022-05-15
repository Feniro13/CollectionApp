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

    ImageView add, diff, dialogDiff;
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
        button_1 = findViewById(R.id.view_diff_button_selectionMode);
        button_2 = findViewById(R.id.view_diff_button_sort);
        button_3 = findViewById(R.id.view_diff_button_showStats);
        viewGroup = findViewById(android.R.id.content);
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
                            filterPositions.set(column, item);
                        }
                    }
                    else {
                        if(i == column){
                            filterPositions.set(column, null);
                        }
                    }
                }
                getCollectionFiltered();
                itemViewAdapter.notifyDataSetChanged();
                return;
            }
            if(globalPositions.get(position) == 1) {
                globalPositions.set(position, 0); // если итем уже выделен
                itemViewAdapter.notifyItemChanged(position);
            }
            else {
                globalPositions.set(position, 1); // если итем не выделен
            }
            itemViewAdapter.notifyItemChanged(position);
            if(Collections.frequency(globalPositions, 1) == 0) {
                setGoneField();
                selectionMode = false; //отмена выделения
                return;
            }
            else if(Collections.frequency(globalPositions, 1) == 1){ // если выделен 1 элемент
                setItemField();                                         // выкл режим выделения
                selectionMode = false;
            }
            else {
                selectionMode = true; // если выделено >= 2 итемов (вкл режим выделения)
            }

            if(selectionMode) { // режим выделения (несколько итемов)
                itemViewAdapter.notifyItemChanged(position);
                setSelectionField();

                if(wantingMode) {
                    button_1.setText("Добавить");
                }
                // объединить WORKS
                button_1.setOnClickListener(view17 -> {
                    if(wantingMode){
                        for(int i = 0; i < globalPositions.size(); i++){
                            localDatabase.dao().insert(collectionEntities.get(i));
                        }
                        collectionEntities = localDatabase.dao().getWantingListByName(name);
                        collectionEntities.add(0, mainEntity);
                        globalPositions = new ArrayList<>();
                        for(int i = 0; i < collectionEntities.size(); i++){
                            globalPositions.add(0);
                        }
                        getCollectionSorted(sortingMode);
                        getCollectionFiltered();
                        itemViewAdapter.notifyDataSetChanged();
                    }
                    else {
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
                                entity.column1 += collectionEntities.get(i).column1;
                                entity.column1 += " ";
                                if (numberOfColumns >= 2) {
                                    entity.column2 += collectionEntities.get(i).column2;
                                    entity.column2 += " ";
                                }
                                if (numberOfColumns >= 3) {
                                    entity.column3 += collectionEntities.get(i).column3;
                                    entity.column3 += " ";
                                }
                                if (numberOfColumns >= 4) {
                                    entity.column4 += collectionEntities.get(i).column4;
                                    entity.column4 += " ";
                                }
                                if (numberOfColumns >= 5) {
                                    entity.column5 += collectionEntities.get(i).column5;
                                    entity.column5 += " ";
                                }
                                if (numberOfColumns >= 6) {
                                    entity.column6 += collectionEntities.get(i).column6;
                                    entity.column6 += " ";
                                }
                                if (numberOfColumns >= 7) {
                                    entity.column7 += collectionEntities.get(i).column7;
                                    entity.column7 += " ";
                                }
                                if (numberOfColumns >= 8) {
                                    entity.column8 += collectionEntities.get(i).column8;
                                    entity.column8 += " ";
                                }
                                if (numberOfColumns >= 9) {
                                    entity.column9 += collectionEntities.get(i).column9;
                                    entity.column9 += " ";
                                }
                                if (numberOfColumns >= 10) {
                                    entity.column10 += collectionEntities.get(i).column10;
                                    entity.column10 += " ";
                                }
                                if (numberOfColumns >= 11) {
                                    entity.column11 += collectionEntities.get(i).column11;
                                    entity.column11 += " ";
                                }
                                if (numberOfColumns == 12) {
                                    entity.column12 += collectionEntities.get(i).column12;
                                    entity.column12 += " ";
                                }
                            }
                        }
                        for (int i = 0; i < globalPositions.size(); i++) {
                            if (globalPositions.get(i) == 1) {
                                localDatabase.dao().deleteEntityById(collectionEntities.get(i).id);
                            }
                        }
                        localDatabase.dao().insert(entity);
                        List<DatabaseLocalEntities> newEntities = localDatabase.dao().getAllByNameByColumn1(name);
                        newEntities.add(0, mainEntity);
                        while (collectionEntities.size() > newEntities.size()) {
                            collectionEntities.remove(0);
                        }
                        for (int i = 0; i < newEntities.size(); i++) {
                            collectionEntities.set(i, newEntities.get(i));
                        }
                        for (int i = 0; i < collectionEntities.size(); i++) {
                            if (i >= globalPositions.size()) {
                                globalPositions.add(i, 0);
                            } else {
                                globalPositions.set(i, 0);
                            }
                        }
                    }
                });

                // удалить всё WORKS
                button_2.setOnClickListener(view16 -> {
                    for(int i = 0; i < globalPositions.size(); i++) {
                        if(globalPositions.get(i) == 1) {
                            localDatabase.dao().deleteEntityById(collectionEntities.get(i).id);
                            globalPositions.set(i, 0);
                        }
                    }
                    List<DatabaseLocalEntities> newCollectionEntities = localDatabase.dao().getAllByNameByColumn1(name);
                    if(wantingMode){
                        newCollectionEntities = localDatabase.dao().getWantingListByName(name);
                    }
                    newCollectionEntities.add(0, mainEntity);
                    collectionEntities.remove(0);
                    for(int i = 0; i < newCollectionEntities.size(); i++){
                        collectionEntities.set(i, newCollectionEntities.get(i));
                    }
                    for(int i = 0; i < collectionEntities.size(); i++){
                        if(i >= globalPositions.size()){
                            globalPositions.add(i, 0);
                        } else {
                            globalPositions.set(i, 0);
                        }
                    }
                    itemViewAdapter.notifyDataSetChanged();
                    setGoneField();
                    selectionMode = false;
                });

                // Отменить выделение WORKS
                button_3.setOnClickListener(view15 -> {
                    setGoneField();
                    selectionMode = false;
                    for(int i = 0; i < collectionEntities.size(); i++) {
                        globalPositions.set(i, 0);
                    }
                    itemViewAdapter.notifyDataSetChanged();

                });
            }

            // выделен один итем
            else {
                setItemField();

                // Изменить WORKS
                button_1.setOnClickListener(view13 -> {
                    View view11 = LayoutInflater.from(getApplicationContext()).inflate(R.layout.dialog_view_add, viewGroup, false);
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                    builder1.setView(view11);

                    final AlertDialog alertDialog1 = builder1.show();
                    alertDialog1.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
                    ImageView itemAdd = view11.findViewById(R.id.dialog_add_item_button_add);
                    ImageView itemBack = view11.findViewById(R.id.dialog_add_item_button_back);
                    DatabaseLocalEntities localEntity = new DatabaseLocalEntities();
                    localEntity.isFirstLine = 0;
                    localEntity.DatabaseName = name;
                    DatabaseLocalEntities thisEntity = collectionEntities.get(position);

                    setItemDialogRecycler(view11, thisEntity);

                    itemBack.setOnClickListener(view2 -> alertDialog1.dismiss());

                    itemAdd.setOnClickListener(view22 -> {
                        localDatabase.dao().insert(getEntityFromAdapter(localEntity));
                        localDatabase.dao().delete(thisEntity);
                        getCollectionSorted(sortingMode);
                        getCollectionFiltered();
                        setGoneField();
                        itemViewAdapter.notifyDataSetChanged();
                        alertDialog1.dismiss();
                        setGoneField();
                        setNewDateChanged();
                    });
                    alertDialog1.show();
                });

                // удалить WORKS
                button_2.setOnClickListener(view14 -> {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(view.getContext());
                    builder1.setTitle("Вы уверены?");
                    builder1.setPositiveButton("Да", (dialogInterface, i) -> {
                        localDatabase.dao().deleteEntityById(collectionEntities.get(position).id);
                        getCollectionSorted(sortingMode);
                        getCollectionFiltered();
                        setGoneField();
                        itemViewAdapter.notifyDataSetChanged();
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
                });
            }
        };

        // сортировать WORKS
        ItemViewAdapter.onItemSortClickListener itemSortListener = (item) -> {
            item++;
            List<DatabaseLocalEntities> newEntities;
            if(item == 1){
                if(sortingMode == 1){
                    collectionEntities.remove(0);
                    Collections.reverse(collectionEntities);
                    reversedSorting = true;
                }else {
                    newEntities = localDatabase.dao().getAllByNameByColumn1(name);
                    sortingMode = 1;
                    reversedSorting = false;
                    collectionEntities.remove(0);
                    for(int i = 0; i < collectionEntities.size(); i++){
                        collectionEntities.set(i, newEntities.get(i));
                    }
                }
            }
            if(item == 2){
                if(sortingMode == 2){
                    collectionEntities.remove(0);
                    Collections.reverse(collectionEntities);
                    reversedSorting = true;
                }else {
                    sortingMode = 2;
                    newEntities = localDatabase.dao().getAllByNameByColumn2(name);
                    reversedSorting = false;
                    collectionEntities.remove(0);
                    for(int i = 0; i < collectionEntities.size(); i++){
                        collectionEntities.set(i, newEntities.get(i));
                    }
                }
            }
            if(item == 3){
                if(sortingMode == 3){
                    collectionEntities.remove(0);
                    Collections.reverse(collectionEntities);
                    reversedSorting = true;
                }
                else {
                    sortingMode = 3;
                    newEntities = localDatabase.dao().getAllByNameByColumn3(name);
                    reversedSorting = false;
                    collectionEntities.remove(0);
                    for(int i = 0; i < collectionEntities.size(); i++){
                        collectionEntities.set(i, newEntities.get(i));
                    }
                }
            }
            if(item == 4){
                if(sortingMode == 4){
                    collectionEntities.remove(0);
                    Collections.reverse(collectionEntities);
                    reversedSorting = true;
                }
                else {
                    sortingMode = 4;
                    newEntities = localDatabase.dao().getAllByNameByColumn4(name);
                    reversedSorting = false;
                    collectionEntities.remove(0);
                    for(int i = 0; i < collectionEntities.size(); i++){
                        collectionEntities.set(i, newEntities.get(i));
                    }
                }
            }
            if(item == 5){
                if(sortingMode == 5){
                    collectionEntities.remove(0);
                    Collections.reverse(collectionEntities);
                    reversedSorting = true;
                }
                else {
                    sortingMode = 5;
                    newEntities = localDatabase.dao().getAllByNameByColumn5(name);
                    reversedSorting = false;
                    collectionEntities.remove(0);
                    for(int i = 0; i < collectionEntities.size(); i++){
                        collectionEntities.set(i, newEntities.get(i));
                    }
                }
            }
            if(item == 6){
                if(sortingMode == 6){
                    collectionEntities.remove(0);
                    Collections.reverse(collectionEntities);
                    reversedSorting = true;

                }
                else {
                    sortingMode = 6;
                    newEntities = localDatabase.dao().getAllByNameByColumn6(name);
                    reversedSorting = false;
                    collectionEntities.remove(0);
                    for(int i = 0; i < collectionEntities.size(); i++){
                        collectionEntities.set(i, newEntities.get(i));
                    }
                }
            }
            if(item == 7) {
                if(sortingMode == 7){
                    collectionEntities.remove(0);
                    Collections.reverse(collectionEntities);
                    reversedSorting = true;
                }
                else {
                    sortingMode = 7;
                    newEntities = localDatabase.dao().getAllByNameByColumn7(name);
                    reversedSorting = false;
                    collectionEntities.remove(0);
                    for(int i = 0; i < collectionEntities.size(); i++){
                        collectionEntities.set(i, newEntities.get(i));
                    }
                }
            }
            if(item == 8){
                if(sortingMode == 8){
                    collectionEntities.remove(0);
                    Collections.reverse(collectionEntities);
                    reversedSorting = true;
                }
                else {
                    sortingMode = 8;
                    newEntities = localDatabase.dao().getAllByNameByColumn8(name);
                    reversedSorting = false;
                    collectionEntities.remove(0);
                    for(int i = 0; i < collectionEntities.size(); i++){
                        collectionEntities.set(i, newEntities.get(i));
                    }
                }
            }
            if(item == 9){
                if(sortingMode == 9){
                    collectionEntities.remove(0);
                    Collections.reverse(collectionEntities);
                    reversedSorting = true;
                }
                else {
                    sortingMode = 9;
                    newEntities = localDatabase.dao().getAllByNameByColumn9(name);
                    reversedSorting = false;
                    collectionEntities.remove(0);
                    for(int i = 0; i < collectionEntities.size(); i++){
                        collectionEntities.set(i, newEntities.get(i));
                    }
                }
            }
            if(item == 10){
                if(sortingMode == 10){
                    collectionEntities.remove(0);
                    Collections.reverse(collectionEntities);
                    reversedSorting = true;
                }
                else {
                    sortingMode = 10;
                    newEntities = localDatabase.dao().getAllByNameByColumn10(name);
                    reversedSorting = false;
                    collectionEntities.remove(0);
                    for(int i = 0; i < collectionEntities.size(); i++){
                        collectionEntities.set(i, newEntities.get(i));
                    }
                }
            }
            if(item == 11){
                if(sortingMode == 11){
                    collectionEntities.remove(0);
                    Collections.reverse(collectionEntities);
                    reversedSorting = true;
                }
                else {
                    sortingMode = 11;
                    newEntities = localDatabase.dao().getAllByNameByColumn11(name);
                    reversedSorting = false;
                    collectionEntities.remove(0);
                    for(int i = 0; i < collectionEntities.size(); i++){
                        collectionEntities.set(i, newEntities.get(i));
                    }
                }
            }
            if(item == 12){
                if(sortingMode == 12){
                    collectionEntities.remove(0);
                    Collections.reverse(collectionEntities);
                    reversedSorting = true;
                }
                else {
                    sortingMode = 12;
                    newEntities = localDatabase.dao().getAllByNameByColumn12(name);
                    reversedSorting = false;
                    collectionEntities.remove(0);
                    for(int i = 0; i < collectionEntities.size(); i++){
                        collectionEntities.set(i, newEntities.get(i));
                    }
                }
            }
            getCollectionFiltered();
            collectionEntities.add(0, mainEntity);
            itemViewAdapter.notifyDataSetChanged();
        };

        setItemRecycler(collectionEntities, viewGroup, itemClickListener, itemSortListener);

        // добавить WORKS
        add.setOnClickListener(view ->  {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View view1 = LayoutInflater.from(view.getContext()).inflate(R.layout.dialog_view_add, viewGroup, false);
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
                alertDialog.dismiss();
                localDatabase.dao().insert(getEntityFromAdapter(localEntity));
                getCollectionSorted(sortingMode);
                getCollectionFiltered();
                for(int i = 0; i < collectionEntities.size(); i++){
                    if(i >= globalPositions.size()){
                        globalPositions.add(i, 0);
                    } else {
                        globalPositions.set(i, 0);
                    }
                }
                itemViewAdapter.notifyDataSetChanged();
                setNewDateChanged();
            });
        });

        // разное
        diff.setOnClickListener(view -> {
            setViewField();
            if(button_1.getVisibility() == View.GONE) { // если окно закрыто
                setVisibleField();
                // режим выделения
                if(selectionMode){
                    setSelectionField();
                }
                // режим фильтра
                if(filterMode){
                    setFilterField();
                }
                else { // По умолчанию
                    // Фильтр WORKS
                    button_1.setOnClickListener(view27 -> {
                        if(!filterMode) {
                            setFilterField();
                            button_1.setTextColor(getResources().getColor(R.color.selection));
                            filterMode = true;
                        }
                    });

                    button_2.setOnClickListener(view24 -> {
                        if(filterMode) {
                            filterMode = false;
                            filterPositions = new ArrayList<>();
                            for(int i = 0; i < numberOfColumns; i++){
                                filterPositions.add(null);
                            }
                            getCollectionSorted(sortingMode);
                            setViewField();
                            itemViewAdapter.notifyDataSetChanged();
                        }
                        else {

                        }
                    });
                    // Показать статистику WORKS
                    button_3.setOnClickListener(view25 -> {
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        View view2 = LayoutInflater.from(view25.getContext()).inflate(R.layout.dialog_view_diff_stats, viewGroup, false);
                        builder.setView(view2);
                        final AlertDialog alertDialog = builder.show();
                        TextView dataNameEdit = view2.findViewById(R.id.dialog_view_showstats_name_edit);
                        TextView dataSizeEdit = view2.findViewById(R.id.dialog_view_showstats_size_edit);
                        TextView dataDataEdit = view2.findViewById(R.id.dialog_view_showstats_createdate_edit);
                        TextView dataChangeEdit = view2.findViewById(R.id.dialog_view_showstats_change_edit);
                        Button dialogBack = view2.findViewById(R.id.dialog_view_showstats_back);
                        dataNameEdit.setText(name);
                        dataSizeEdit.setText(String.valueOf(collectionEntities.size() - 1));
                        dataDataEdit.setText(globalDatabase.dao_global().getDataCreatedByName(name));
                        dataChangeEdit.setText(globalDatabase.dao_global().getDataChangedByName(name));
                        dialogBack.setOnClickListener(view3 -> alertDialog.dismiss());
                        alertDialog.show();
                    });
                }
            }
            // если окно открыто (закрывает) WORKS
            else {
                globalPositions = new ArrayList<>();
                for(int i = 0; i < collectionEntities.size(); i++) {
                    globalPositions.add(i, 0);
                }
                getCollectionFiltered();
                setItemRecycler(collectionEntities, viewGroup, itemClickListener, itemSortListener);
                selectionMode = false;
                setGoneField();
            }
        });

        // Вкл/Выкл список желаемого
        title.setText(name);
        title.setOnClickListener(view -> {
            if(!wantingMode){
                wantingMode = true;
                collectionEntities = localDatabase.dao().getWantingListByName(name);
                globalPositions = new ArrayList<>();
                collectionEntities.add(0, mainEntity);
                for(int i = 0; i < collectionEntities.size(); i++){
                    globalPositions.add(0);
                }
                title.setText("Ваш список желаемого");
            }else {
                title.setText(name);
                wantingMode = false;
                collectionEntities = localDatabase.dao().getAllByNameByColumn1(name);
                collectionEntities.add(0, mainEntity);
                globalPositions = new ArrayList<>();
                for(int i = 0; i < collectionEntities.size(); i++){
                    globalPositions.add(0);
                }
            }
            getCollectionFiltered();
            setItemRecycler(collectionEntities, viewGroup, itemClickListener, itemSortListener);
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
        button_3.setText("Поиск в интернете");
        button_1.setTextColor(this.getResources().getColor(R.color.main));
        button_2.setTextColor(this.getResources().getColor(R.color.main));
        button_3.setTextColor(this.getResources().getColor(R.color.main));
    }

    public void setViewField(){
        button_1.setText("Фильтр");
        button_1.setTextColor(this.getResources().getColor(R.color.main));
        button_2.setText("Что-то");
        button_2.setTextColor(this.getResources().getColor(R.color.main));
        button_3.setText("Статистика");
        button_3.setTextColor(this.getResources().getColor(R.color.main));
    }

    public void setFilterField(){
        button_1.setText("Фильтр");
        button_1.setTextColor(this.getResources().getColor(R.color.main));
        button_2.setText("Очистить фильтр");
        button_2.setTextColor(this.getResources().getColor(R.color.main));
        button_3.setText("Статистика");
        button_3.setTextColor(this.getResources().getColor(R.color.main));
    }

    public void setSelectionField(){
        button_1.setText("Объединить");
        button_1.setTextColor(this.getResources().getColor(R.color.main));
        button_2.setText("Удалить все");
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

    public void getCollectionSorted(int sortingMode){
        List<DatabaseLocalEntities> newEntities = new ArrayList<>();
        if(sortingMode == 1){
            newEntities = localDatabase.dao().getAllByNameByColumn1(name);
        }
        if(sortingMode == 2){
            newEntities = localDatabase.dao().getAllByNameByColumn2(name);
        }
        if(sortingMode == 3){
            newEntities = localDatabase.dao().getAllByNameByColumn3(name);
        }
        if(sortingMode == 4){
            newEntities = localDatabase.dao().getAllByNameByColumn4(name);
        }
        if(sortingMode == 5){
            newEntities = localDatabase.dao().getAllByNameByColumn5(name);
        }
        if(sortingMode == 6){
            newEntities = localDatabase.dao().getAllByNameByColumn6(name);
        }
        if(sortingMode == 7){
            newEntities = localDatabase.dao().getAllByNameByColumn7(name);
        }
        if(sortingMode == 8){
            newEntities = localDatabase.dao().getAllByNameByColumn8(name);
        }
        if(sortingMode == 9){
            newEntities = localDatabase.dao().getAllByNameByColumn9(name);
        }
        if(sortingMode == 10){
            newEntities = localDatabase.dao().getAllByNameByColumn10(name);
        }
        if(sortingMode == 11){
            newEntities = localDatabase.dao().getAllByNameByColumn11(name);
        }
        if(sortingMode == 12){
            newEntities = localDatabase.dao().getAllByNameByColumn12(name);
        }
        if(reversedSorting) {
            Collections.reverse(newEntities);
        }
        newEntities.add(0, mainEntity);
        for(int i = 0; i < newEntities.size(); i++){
            if(i >= collectionEntities.size()){
                collectionEntities.add(newEntities.get(i));
            } else{
                collectionEntities.set(i, newEntities.get(i));
            }
        }
        for(int i = 0; i < newEntities.size(); i++){
            if(i >= globalPositions.size()){
                globalPositions.add(0);
            } else{
                globalPositions.set(i, 0);
            }
        }
    }

    // WORKS
    public void getCollectionFiltered(){
        List<DatabaseLocalEntities> newEntities = new ArrayList<>();
        newEntities.add(0, mainEntity);
        for(int i = 1; i < collectionEntities.size(); i++){
            if(filterPositions.get(0) != null){
                if(!collectionEntities.get(i).column1.equals(filterPositions.get(0))){
                    continue;
                }
            }
            if(numberOfColumns == 1){
                newEntities.add(collectionEntities.get(i));
                continue;
            }
            if(filterPositions.get(1) != null){
                if(!collectionEntities.get(i).column2.equals(filterPositions.get(1))){
                    continue;
                }
            }
            if(numberOfColumns == 2){
                newEntities.add(collectionEntities.get(i));
                continue;
            }
            if(filterPositions.get(2) != null){
                if(!collectionEntities.get(i).column3.equals(filterPositions.get(2))){
                    continue;
                }
            }
            if(numberOfColumns == 3){
                newEntities.add(collectionEntities.get(i));
                continue;
            }
            if(filterPositions.get(3) != null){
                if(!collectionEntities.get(i).column4.equals(filterPositions.get(3))){
                    continue;
                }
            }
            if(numberOfColumns == 4){
                newEntities.add(collectionEntities.get(i));
                continue;
            }
            if(filterPositions.get(4) != null){
                if(!collectionEntities.get(i).column5.equals(filterPositions.get(4))){
                    continue;
                }
            }
            if(numberOfColumns == 5){
                newEntities.add(collectionEntities.get(i));
                continue;
            }
            if(filterPositions.get(5) != null){
                if(!collectionEntities.get(i).column6.equals(filterPositions.get(5))){
                    continue;
                }
            }
            if(numberOfColumns == 6){
                newEntities.add(collectionEntities.get(i));
                continue;
            }
            if(filterPositions.get(6) != null){
                if(!collectionEntities.get(i).column7.equals(filterPositions.get(6))){
                    continue;
                }
            }
            if(numberOfColumns == 7){
                newEntities.add(collectionEntities.get(i));
                continue;
            }
            if(filterPositions.get(7) != null){
                if(!collectionEntities.get(i).column8.equals(filterPositions.get(7))){
                    continue;
                }
            }
            if(numberOfColumns == 8){
                newEntities.add(collectionEntities.get(i));
                continue;
            }
            if(filterPositions.get(8) != null){
                if(!collectionEntities.get(i).column9.equals(filterPositions.get(8))){
                    continue;
                }
            }
            if(numberOfColumns == 9){
                newEntities.add(collectionEntities.get(i));
                continue;
            }
            if(filterPositions.get(9) != null){
                if(!collectionEntities.get(i).column10.equals(filterPositions.get(9))){
                    continue;
                }
            }
            if(numberOfColumns == 10){
                newEntities.add(collectionEntities.get(i));
                continue;
            }
            if(filterPositions.get(10) != null){
                if(!collectionEntities.get(i).column11.equals(filterPositions.get(10))){
                    continue;
                }
            }
            if(numberOfColumns == 11){
                newEntities.add(collectionEntities.get(i));
                continue;
            }
            if(filterPositions.get(11) != null){
                if(!collectionEntities.get(i).column12.equals(filterPositions.get(11))){
                    continue;
                }
            }
            newEntities.add(collectionEntities.get(i));
        }
        for(int i = 0; i < newEntities.size(); i++){
            collectionEntities.set(i, newEntities.get(i));
            globalPositions.set(i, 0);
        }
        while(collectionEntities.size() > newEntities.size()){
            collectionEntities.remove(collectionEntities.size() - 1);
        }
        while(globalPositions.size() > newEntities.size()){
            globalPositions.remove(globalPositions.size() - 1);
        }
    }
}