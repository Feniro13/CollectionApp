package com.Feniro.collectionapp.database.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "DatabaseGlobalEntities")
public class DatabaseGlobalEntities {

    @ColumnInfo(name = "name")
    @PrimaryKey
    @NonNull
    public String name;

    @ColumnInfo(name = "numberOfColumns")
    public int numberOfColumns;

}
