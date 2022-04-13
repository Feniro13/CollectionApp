package com.Feniro.collectionapp.database.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "DatabaseGlobalEntity")
public class DatabaseGlobalEntity {

    @ColumnInfo(name = "numberOfColumns")
    public int numberOfColumns;

    @ColumnInfo(name = "name")
    @PrimaryKey
    @NonNull
    public String name;

}
