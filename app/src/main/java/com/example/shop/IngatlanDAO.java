package com.example.shop;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface IngatlanDAO {

    @Insert
     void insert(Ingatlan ing);

    @Query("DELETE FROM ingatlan_table")
     void deleteAll();

     @Delete
     void delete(Ingatlan ing);

     @Query("SELECT * FROM ingatlan_table ORDER BY location ASC")
     LiveData<List<Ingatlan>> getIngatlanList();

}
