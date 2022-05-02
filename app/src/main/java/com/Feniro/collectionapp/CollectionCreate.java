package com.Feniro.collectionapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.Feniro.collectionapp.adapter.ColumnAdapter;
import com.Feniro.collectionapp.database.LocalDatabase;
import com.Feniro.collectionapp.database.GlobalDatabase;
import com.Feniro.collectionapp.database.entities.DatabaseGlobalEntities;
import com.Feniro.collectionapp.database.entities.DatabaseLocalEntities;
import com.Feniro.collectionapp.model.ColumnModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CollectionCreate extends AppCompatActivity {

    String name;
    int kol = 3;

    Button addColumn, delColumn, create;
    TextView warning;
    EditText editText;

    LocalDatabase localDatabase;
    GlobalDatabase globalDatabase;
    RecyclerView columnRecycler;
    ColumnAdapter columnAdapter;

    List<ColumnModel> list = new ArrayList<>();
    List<String> names = new ArrayList<>();
    List<DatabaseGlobalEntities> databases = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_collections);

        try {
            editText = findViewById(R.id.Create_EditText_Name);
            Bundle args = getIntent().getExtras();
            editText.setText(args.get("check").toString());

        } catch (Exception E){}

        list.add(new ColumnModel(0, "Столбец №1"));
        list.add(new ColumnModel(1, "Столбец №2"));
        list.add(new ColumnModel(2, "Столбец №3"));
        names.add("");
        names.add("");
        names.add("");

        setColumnRecycler(list, names);

        addColumn = findViewById(R.id.Create_Button_Add);
        delColumn = findViewById(R.id.Create_Button_Delete);
        create = findViewById(R.id.Create_Button_Create);

        warning = findViewById(R.id.Create_TextView_Warner);


        // Кнопка "добавить столбик"
        addColumn.setOnClickListener(view -> {
            if(kol == 12) {
                warning.setText(R.string.maxColumns);
            }
            else if(kol >= 1 && kol < 12) {
                kol++;
                list.add(new ColumnModel(kol - 1, "Столбец №" + kol));
                names.add("");
                warning.setText(R.string.empty);
            }
            names = columnAdapter.names;
            setColumnRecycler(list, names);
        });

        // Кнопка "убрать столбик"
        delColumn.setOnClickListener(view -> {
            if(kol == 1) {
                warning.setText(R.string.minColumns);
            }
            if(kol > 1 && kol <= 12) {
                list.remove(kol - 1);
                names.remove(kol - 1);
                kol--;
                warning.setText(R.string.empty);
            }
            names = columnAdapter.names;
            setColumnRecycler(list, names);
        });

        // Кнопка "создать коллекцию"
        create.setOnClickListener(view -> {
            warning = findViewById(R.id.Create_TextView_Warner);

            names = columnAdapter.names;
            editText = findViewById(R.id.Create_EditText_Name);
            name = editText.getText().toString();

            localDatabase = LocalDatabase.getDatabase(this);
            DatabaseLocalEntities localEntity = new DatabaseLocalEntities();

            {
                if (kol >= 1) {
                    if(names.get(0).equals("")){
                        warning.setText("Все столбцы должны иметь название");
                        return;
                    }
                    localEntity.column1 = names.get(0);
                }
                if (kol >= 2) {
                    if(names.get(1).equals("")){
                        warning.setText("Все столбцы должны иметь название");
                        return;
                    }
                    localEntity.column2 = names.get(1);
                }
                if (kol >= 3) {
                    if(names.get(2).equals("")){
                        warning.setText("Все столбцы должны иметь название");
                        return;
                    }
                    localEntity.column3 = names.get(2);
                }
                if (kol >= 4) {
                    if(names.get(3).equals("")){
                        warning.setText("Все столбцы должны иметь название");
                        return;
                    }
                    localEntity.column4 = names.get(3);
                }
                if (kol >= 5) {
                    if(names.get(4).equals("")){
                        warning.setText("Все столбцы должны иметь название");
                        return;
                    }
                    localEntity.column5 = names.get(4);
                }
                if (kol >= 6) {
                    if(names.get(5).equals("")){
                        warning.setText("Все столбцы должны иметь название");
                        return;
                }
                    localEntity.column6 = names.get(5);
                }
                if (kol >= 7) {
                    if(names.get(6).equals("")){
                        warning.setText("Все столбцы должны иметь название");
                        return;
                }
                    localEntity.column7 = names.get(6);
                }
                if (kol >= 8) {
                    if(names.get(7).equals("")){
                        warning.setText("Все столбцы должны иметь название");
                        return;
                    }
                    localEntity.column8 = names.get(7);
                }
                if (kol >= 9) {
                    if(names.get(8).equals("")){
                        warning.setText("Все столбцы должны иметь название");
                        return;
                    }
                    localEntity.column9 = names.get(8);
                }
                if (kol >= 10) {
                    if(names.get(9).equals("")){
                        warning.setText("Все столбцы должны иметь название");
                        return;
                    }
                    localEntity.column10 = names.get(9);
                }
                if (kol >= 11) {
                    if(names.get(10).equals("")){
                        warning.setText("Все столбцы должны иметь название");
                        return;
                    }
                    localEntity.column11 = names.get(10);
                }
                if (kol >= 12) {
                    if(names.get(11).equals("")){
                        warning.setText("Все столбцы должны иметь название");
                        return;
                    }
                    localEntity.column12 = names.get(11);
                }
            }
            localEntity.isFirstLine = 1;
            localEntity.DatabaseName = name;

            //Создание базы данных в глобальной базе данных
            globalDatabase = GlobalDatabase.getDatabase(this);
            DatabaseGlobalEntities databaseGlobalEntities = new DatabaseGlobalEntities();

            databases = globalDatabase.dao_global().getAll();
            for(int i = 0; i < databases.size(); i++) {
                if(databases.get(i).name.equals(name)) {
                    warning.setText("Вы уже создали коллекцию с таким названием");
                    return;
                }
            }

            Date currentDate = new Date();
            DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
            String dateText = dateFormat.format(currentDate);

            databaseGlobalEntities.dataCreated = dateText;
            databaseGlobalEntities.dataChanged = dateText;
            databaseGlobalEntities.numberOfColumns = kol;
            databaseGlobalEntities.name = name;

            globalDatabase.dao_global().insert(databaseGlobalEntities);
            localDatabase.dao().insert(localEntity);

            Intent intent = new Intent(CollectionCreate.this, CollectionView.class);
            intent.putExtra("check", name);
            startActivity(intent);

        });
    }
    public void setColumnRecycler(List<ColumnModel> list, List<String> names) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        columnRecycler = findViewById(R.id.Create_RView_Columns);
        columnRecycler.setLayoutManager(layoutManager);
        columnAdapter = new ColumnAdapter(this, list, names);
        columnRecycler.setAdapter(columnAdapter);
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(CollectionCreate.this, ActivityStart.class);
        EditText editText = findViewById(R.id.Create_EditText_Name);
        String arg = editText.getText().toString();
        intent.putExtra("check", arg);
        startActivity(intent);
    }
}