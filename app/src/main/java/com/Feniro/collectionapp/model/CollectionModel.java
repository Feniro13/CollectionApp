package com.Feniro.collectionapp.model;

public class CollectionModel {

    int id;
    String name;
    int numberOfColumns;

    public CollectionModel(int id, String name, int numberOfColumns) {
        this.name = name;
        this.numberOfColumns = numberOfColumns;
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
