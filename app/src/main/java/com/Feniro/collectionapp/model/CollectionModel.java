package com.Feniro.collectionapp.model;

import androidx.room.Database;

public class CollectionModel {

    int id;
    String name;
    Database database;

    public CollectionModel(int id, String name, Database database) {
        this.name = name;
        this.database = database;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
