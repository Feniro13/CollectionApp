package com.Feniro.collectionapp.model;

import android.widget.EditText;
import android.widget.TextView;

public class ColumnModel {
    int id;
    String name;

    public ColumnModel(int id, String name) {
        this.name = name;
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
