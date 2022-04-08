package com.Feniro.collectionapp.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.Feniro.collectionapp.database.entities.DatabaseGlobalEntity;

import java.util.List;

@Dao
public interface DAO_Global {
    @Query("SELECT * FROM DatabaseGlobalEntity")
    List<DatabaseGlobalEntity> getAll();

    @Query("SELECT * FROM DatabaseGlobalEntity WHERE id = :id")
    DatabaseGlobalEntity getById(long id);

    @Query("SELECT * FROM DatabaseGlobalEntity WHERE name = :name")
    DatabaseGlobalEntity getByName(String name);

    @Insert
    void insert (DatabaseGlobalEntity collectionEntity);

    @Update
    void update (DatabaseGlobalEntity collectionEntity);

    @Delete
    void delete (DatabaseGlobalEntity collectionEntity);
}
