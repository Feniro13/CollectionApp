package com.Feniro.collectionapp.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RenameTable;
import androidx.room.Update;

import com.Feniro.collectionapp.database.entities.DatabaseGlobalEntities;

import java.util.List;

@Dao
public interface DAO_Global {
    @Query("SELECT * FROM DatabaseGlobalEntities")
    List<DatabaseGlobalEntities> getAll();

    @Query("SELECT * FROM DatabaseGlobalEntities WHERE name = :name")
    DatabaseGlobalEntities getByName(String name);

    @Query("SELECT NumberOfColumns FROM DatabaseGlobalEntities WHERE name = :name")
    int getNumberOfColumnsByName(String name);

    @Query("SELECT dataCreated FROM DatabaseGlobalEntities WHERE name = :name")
    String getDataCreatedByName(String name);

    @Query("SELECT dataChanged FROM DatabaseGlobalEntities WHERE name = :name")
    String getDataChangedByName(String name);



    @Query("DELETE FROM DatabaseGlobalEntities WHERE name = :name")
    void deleteCollectionByName(String name);

    @Insert
    void insert (DatabaseGlobalEntities collectionEntity);

    @Update
    void update (DatabaseGlobalEntities collectionEntity);

    @Delete
    void delete (DatabaseGlobalEntities collectionEntity);
}
