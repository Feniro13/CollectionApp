package com.Feniro.collectionapp.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.Feniro.collectionapp.database.Database;

@Entity
public class DatabaseGlobalEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public Database database;

    public int numberOfColumns;

    public String name;
}
