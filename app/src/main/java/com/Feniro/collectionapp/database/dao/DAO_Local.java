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

    @Query("SELECT * FROM DatabaseLocalEntities WHERE DatabaseName IS :name and isFirstLine = 2 ORDER BY column1")
    List<DatabaseLocalEntities> getWantingListByNameByColumn1(String name);
    @Query("SELECT * FROM DatabaseLocalEntities WHERE DatabaseName IS :name and isFirstLine = 2 ORDER BY column2")
    List<DatabaseLocalEntities> getWantingListByNameByColumn2(String name);
    @Query("SELECT * FROM DatabaseLocalEntities WHERE DatabaseName IS :name and isFirstLine = 2 ORDER BY column3")
    List<DatabaseLocalEntities> getWantingListByNameByColumn3(String name);
    @Query("SELECT * FROM DatabaseLocalEntities WHERE DatabaseName IS :name and isFirstLine = 2 ORDER BY column4")
    List<DatabaseLocalEntities> getWantingListByNameByColumn4(String name);
    @Query("SELECT * FROM DatabaseLocalEntities WHERE DatabaseName IS :name and isFirstLine = 2 ORDER BY column5")
    List<DatabaseLocalEntities> getWantingListByNameByColumn5(String name);
    @Query("SELECT * FROM DatabaseLocalEntities WHERE DatabaseName IS :name and isFirstLine = 2 ORDER BY column6")
    List<DatabaseLocalEntities> getWantingListByNameByColumn6(String name);
    @Query("SELECT * FROM DatabaseLocalEntities WHERE DatabaseName IS :name and isFirstLine = 2 ORDER BY column7")
    List<DatabaseLocalEntities> getWantingListByNameByColumn7(String name);
    @Query("SELECT * FROM DatabaseLocalEntities WHERE DatabaseName IS :name and isFirstLine = 2 ORDER BY column8")
    List<DatabaseLocalEntities> getWantingListByNameByColumn8(String name);
    @Query("SELECT * FROM DatabaseLocalEntities WHERE DatabaseName IS :name and isFirstLine = 2 ORDER BY column9")
    List<DatabaseLocalEntities> getWantingListByNameByColumn9(String name);
    @Query("SELECT * FROM DatabaseLocalEntities WHERE DatabaseName IS :name and isFirstLine = 2 ORDER BY column10")
    List<DatabaseLocalEntities> getWantingListByNameByColumn10(String name);
    @Query("SELECT * FROM DatabaseLocalEntities WHERE DatabaseName IS :name and isFirstLine = 2 ORDER BY column11")
    List<DatabaseLocalEntities> getWantingListByNameByColumn11(String name);
    @Query("SELECT * FROM DatabaseLocalEntities WHERE DatabaseName IS :name and isFirstLine = 2 ORDER BY column12")
    List<DatabaseLocalEntities> getWantingListByNameByColumn12(String name);

    // сортировки
    @Query("SELECT * FROM DatabaseLocalEntities WHERE DatabaseName IS :name and isFirstLine = 0 ORDER BY column1")
    List<DatabaseLocalEntities> getAllByNameByColumn1(String name);

    @Query("SELECT * FROM DatabaseLocalEntities WHERE DatabaseName IS :name and isFirstLine = 0 ORDER BY column2")
    List<DatabaseLocalEntities> getAllByNameByColumn2(String name);

    @Query("SELECT * FROM DatabaseLocalEntities WHERE DatabaseName IS :name and isFirstLine = 0 ORDER BY column3")
    List<DatabaseLocalEntities> getAllByNameByColumn3(String name);

    @Query("SELECT * FROM DatabaseLocalEntities WHERE DatabaseName IS :name and isFirstLine = 0 ORDER BY column4")
    List<DatabaseLocalEntities> getAllByNameByColumn4(String name);

    @Query("SELECT * FROM DatabaseLocalEntities WHERE DatabaseName IS :name and isFirstLine = 0 ORDER BY column5")
    List<DatabaseLocalEntities> getAllByNameByColumn5(String name);

    @Query("SELECT * FROM DatabaseLocalEntities WHERE DatabaseName IS :name and isFirstLine = 0  ORDER BY column6")
    List<DatabaseLocalEntities> getAllByNameByColumn6(String name);

    @Query("SELECT * FROM DatabaseLocalEntities WHERE DatabaseName IS :name and isFirstLine = 0  ORDER BY column7")
    List<DatabaseLocalEntities> getAllByNameByColumn7(String name);

    @Query("SELECT * FROM DatabaseLocalEntities WHERE DatabaseName IS :name and isFirstLine = 0  ORDER BY column8")
    List<DatabaseLocalEntities> getAllByNameByColumn8(String name);

    @Query("SELECT * FROM DatabaseLocalEntities WHERE DatabaseName IS :name and isFirstLine = 0  ORDER BY column9")
    List<DatabaseLocalEntities> getAllByNameByColumn9(String name);

    @Query("SELECT * FROM DatabaseLocalEntities WHERE DatabaseName IS :name and isFirstLine = 0  ORDER BY column10")
    List<DatabaseLocalEntities> getAllByNameByColumn10(String name);

    @Query("SELECT * FROM DatabaseLocalEntities WHERE DatabaseName IS :name and isFirstLine = 0  ORDER BY column11")
    List<DatabaseLocalEntities> getAllByNameByColumn11(String name);

    @Query("SELECT * FROM DatabaseLocalEntities WHERE DatabaseName IS :name and isFirstLine = 0  ORDER BY column12")
    List<DatabaseLocalEntities> getAllByNameByColumn12(String name);

    // Названия столбцов
    @Query("SELECT * FROM DatabaseLocalEntities WHERE DatabaseName Is :name and isFirstLine is 1")
    DatabaseLocalEntities getNamesOfColumnsByName(String name);

    // удаление
    @Query("DELETE FROM DatabaseLocalEntities WHERE DatabaseName Is :name") // всей датабазы
    void deleteEntitiesByName(String name);

    @Query("DELETE FROM DatabaseLocalEntities WHERE Id Is :id")            // одной entity
    void deleteEntityById(int id);


    @Insert
    void insert (DatabaseLocalEntities databaseLocalEntities);

    @Update
    void update (DatabaseLocalEntities databaseLocalEntities);

    @Delete
    void delete (DatabaseLocalEntities databaseLocalEntities);
}
