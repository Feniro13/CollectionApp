package com.Feniro.collectionapp.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.Feniro.collectionapp.database.entities.DatabaseLocalEntities;

import java.util.List;

@Dao
public interface DAO_Local {

    @Query("SELECT * FROM DatabaseLocalEntities WHERE DatabaseName Is :name")
    List<DatabaseLocalEntities> getAllByName(String name);

    @Query("DELETE FROM DatabaseLocalEntities WHERE DatabaseName Is :name")
    void deleteEntitiesByName(String name);

    @Query("DELETE FROM DatabaseLocalEntities WHERE Id Is :id")
    void deleteEntityById(int id);



    @Insert
    void insert (DatabaseLocalEntities databaseLocalEntities);

    @Update
    void update (DatabaseLocalEntities databaseLocalEntities);

    @Delete
    void delete (DatabaseLocalEntities databaseLocalEntities);
}
