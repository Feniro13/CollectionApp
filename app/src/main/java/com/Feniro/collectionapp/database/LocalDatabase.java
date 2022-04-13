package com.Feniro.collectionapp.database;

import androidx.room.RoomDatabase;

import com.Feniro.collectionapp.database.dao.DAO_Local;
import com.Feniro.collectionapp.database.entities.DatabaseLocalEntities;

@androidx.room.Database(entities = {DatabaseLocalEntities.class}, version = 1)
public abstract class LocalDatabase extends RoomDatabase {
    public abstract DAO_Local dao();
}
