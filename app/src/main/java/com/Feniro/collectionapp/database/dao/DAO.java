package com.Feniro.collectionapp.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.Feniro.collectionapp.database.entities.CollectionEntity;

import java.util.List;

@Dao
public interface DAO {

    @Query("SELECT * FROM CollectionEntity")
    List<CollectionEntity> getAll();

    @Query("SELECT * FROM CollectionEntity WHERE id = :id")
    CollectionEntity getPersonById(long id);

    @Insert
    void insert (CollectionEntity collectionEntity);

    @Update
    void update (CollectionEntity collectionEntity);

    @Delete
    void delete (CollectionEntity collectionEntity);
}
