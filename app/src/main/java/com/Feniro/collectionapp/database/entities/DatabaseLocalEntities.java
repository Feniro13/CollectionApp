package com.Feniro.collectionapp.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.Query;

@Entity(tableName = "DatabaseLocalEntities")
public class DatabaseLocalEntities {

        @PrimaryKey(autoGenerate = true)
        public int id;

        @ColumnInfo(name = "isFirstLine")
        public boolean isFirstLine;

        @ColumnInfo(name = "DatabaseName")
        public int DatabaseName;

        public String column1;

        public String column2;

        public String column3;

        public String column4;

        public String column5;

        public String column6;

        public String column7;

        public String column8;

        public String column9;

        public String column10;

        public String column11;

        public String column12;
}
