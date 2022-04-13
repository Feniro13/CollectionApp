package com.Feniro.collectionapp.database;

import androidx.room.RoomDatabase;

import com.Feniro.collectionapp.database.dao.DAO_Global;
import com.Feniro.collectionapp.database.entities.DatabaseGlobalEntity;

@androidx.room.Database(entities = {DatabaseGlobalEntity.class}, version = 1)
public abstract class GlobalDatabase extends RoomDatabase {
    public abstract DAO_Global dao_global();
}
