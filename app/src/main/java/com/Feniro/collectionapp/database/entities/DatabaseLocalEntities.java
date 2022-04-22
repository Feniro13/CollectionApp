package com.Feniro.collectionapp.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
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

        @Ignore
        public String column4;

        @Ignore
        public String column5;

        @Ignore
        public String column6;

        public String column7;

        @Ignore
        public String column8;

        @Ignore
        public String column9;

        @Ignore
        public String column10;

        @Ignore
        public String column11;

        @Ignore
        public String column12;
}
