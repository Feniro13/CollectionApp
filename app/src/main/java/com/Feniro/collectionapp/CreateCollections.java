package com.Feniro.collectionapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.Feniro.collectionapp.adapter.ColumnAdapter;
import com.Feniro.collectionapp.database.Database;
import com.Feniro.collectionapp.database.GlobalDatabase;
import com.Feniro.collectionapp.database.entities.CollectionEntity;
import com.Feniro.collectionapp.database.entities.DatabaseGlobalEntity;
import com.Feniro.collectionapp.model.ColumnModel;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CreateCollections extends AppCompatActivity {

    String name, numberOfCollections, fileToCheck = "NumberOfCollections.txt";
    int kol = 3;

    Button addColumn, delColumn, create;
    TextView warning;

    Database database;
    GlobalDatabase globalDatabase;
    RecyclerView columnRecycler;
    ColumnAdapter columnAdapter;

    EditCollections editCollections = new EditCollections();

    List<ColumnModel> list = new ArrayList<>();
    List<String> names = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_collections);

        list.add(new ColumnModel(0, "Столбец №1"));
        list.add(new ColumnModel(1, "Столбец №2"));
        list.add(new ColumnModel(2, "Столбец №3"));
        names.add("");
        names.add("");
        names.add("");

        setColumnRecycler(list);

        addColumn = findViewById(R.id.Create_Button_Add);
        delColumn = findViewById(R.id.Create_Button_Delete);
        create = findViewById(R.id.Create_Button_Create);

        warning = findViewById(R.id.Create_TextView_Warner);



        addColumn.setOnClickListener(view -> {
            if(kol == 12) {
                warning.setText("Максимум 12 столбиков");
            }
            else if(kol >= 1 && kol < 12) {
                kol++;
                list.add(new ColumnModel(kol - 1, "Столбец №" + kol));
                names.add("");
                warning.setText("");
            }
            setColumnRecycler(list);
        });

        delColumn.setOnClickListener(view -> {
            if(kol == 1) {
                warning.setText("Минимум                   1 столбик");
            }
            if(kol > 1 && kol <= 12) {
                list.remove(kol - 1);
                names.remove(kol - 1);
                kol--;
                warning.setText("");
            }
            setColumnRecycler(list);
        });

        create.setOnClickListener(view -> {

            database = Room.databaseBuilder(this, Database.class, name)
                    .allowMainThreadQueries()
                    .build();
            CollectionEntity entity = new CollectionEntity();

            // Чтение записей EditText
            {
                if (kol >= 1) {
                    entity.column1 = names.get(0);
                }
                if (kol >= 2) {
                    entity.column2 = names.get(1);
                }
                if (kol >= 3) {
                    entity.column3 = names.get(2);
                }
                if (kol >= 4) {
                    entity.column4 = names.get(3);
                }
                if (kol >= 5) {
                    entity.column5 = names.get(4);
                }
                if (kol >= 6) {
                    entity.column6 = names.get(5);
                }
                if (kol >= 7) {
                    entity.column7 = names.get(6);
                }
                if (kol >= 8) {
                    entity.column8 = names.get(7);
                }
                if (kol >= 9) {
                    entity.column9 = names.get(8);
                }
                if (kol >= 10) {
                    entity.column10 = names.get(9);
                }
                if (kol >= 11) {
                    entity.column11 = names.get(10);
                }
                if (kol >= 12) {
                    entity.column12 = names.get(11);
                }
            }
            name = findViewById(R.id.Create_EditText_Name).toString();


            if(editCollections.ifNameTaken(name)){
                warning.setText("Вы уже создали коллекцию с таким названием");
                return;
            }
            editCollections.addName(name);




            database.dao().insert(entity);

            //Чтение количества коллекций из файла
            try {
                Scanner scanner = new Scanner(new File(fileToCheck));
                numberOfCollections = scanner.next();
            } catch (Exception e) {
                e.printStackTrace();
            }

            //Создание базы данных в глобальной базе данных
            if(numberOfCollections.equals("0")){
                globalDatabase  = Room.databaseBuilder(this,
                        GlobalDatabase.class, "databaseOfDatabases").allowMainThreadQueries().build();
                DatabaseGlobalEntity databaseGlobalEntity =  new DatabaseGlobalEntity();
                databaseGlobalEntity.numberOfColumns = kol;
                databaseGlobalEntity.database = database;
                databaseGlobalEntity.name = name;
                globalDatabase.dao_global().insert(databaseGlobalEntity);
            } else {
                globalDatabase = GlobalDatabaseLoader.getInstance().getDatabase();
                DatabaseGlobalEntity databaseGlobalEntity = new DatabaseGlobalEntity();
                databaseGlobalEntity.database = database;
                databaseGlobalEntity.numberOfColumns = kol;
                databaseGlobalEntity.name = name;
                globalDatabase.dao_global().insert(databaseGlobalEntity);
            }
            try {
                FileWriter fileWriter = new FileWriter(fileToCheck);
                fileWriter.write(numberOfCollections + 1);
                fileWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }



            Intent intent = new Intent(CreateCollections.this, CollectionView.class);
            intent.putExtra("Check", name);
            startActivity(intent);

        });
    }
    public void setColumnRecycler(List<ColumnModel> list) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        columnRecycler = findViewById(R.id.Create_RView_Columns);
        columnRecycler.setLayoutManager(layoutManager);
        columnAdapter = new ColumnAdapter(this, list, names);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CreateCollections.this, MainActivity.class);
        startActivity(intent);
    }
}