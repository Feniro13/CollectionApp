package com.Feniro.collectionapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.Feniro.collectionapp.database.dao.DAO_Global;
import com.Feniro.collectionapp.database.entities.DatabaseGlobalEntities;

@Database(entities = {DatabaseGlobalEntities.class}, version = 1)
public abstract class GlobalDatabase extends RoomDatabase {
    public static GlobalDatabase INSTANCE;
    public abstract DAO_Global dao_global();
    public static GlobalDatabase getDatabase(Context context) {
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), GlobalDatabase.class, "globalDatabase").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }
}
