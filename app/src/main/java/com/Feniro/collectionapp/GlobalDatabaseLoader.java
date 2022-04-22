package com.Feniro.collectionapp;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.room.Room;

import com.Feniro.collectionapp.database.GlobalDatabase;

public class GlobalDatabaseLoader extends Application {


    public static GlobalDatabaseLoader instance;

    private static GlobalDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, GlobalDatabase.class, "database").build();
    }

    public static GlobalDatabaseLoader getInstance() {
        return instance;
    }

    public GlobalDatabase getDatabase(){
        return database;
    }

}

