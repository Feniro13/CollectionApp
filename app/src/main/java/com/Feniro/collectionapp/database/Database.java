package com.Feniro.collectionapp.database;

import androidx.room.RoomDatabase;

import com.Feniro.collectionapp.database.entities.CollectionEntity;
import com.Feniro.collectionapp.database.dao.DAO;

@androidx.room.Database(entities = {CollectionEntity.class}, version = 1)
public abstract class Database extends RoomDatabase {
    public abstract DAO dao();
}
