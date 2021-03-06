package com.example.budget_tracker;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/***********************************************
* DAO: Data access object
* This class contains query's for the database
* *DAO can be an interface or abstract class*
*
* A wrapper "LiveData<>" keeps content up to date
* without having to continually observe
************************************************/
@Dao
public interface DatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertItem(DatabaseC databaseC);

    @Delete
    void deleteItem(DatabaseC databaseC);

    @Query("DELETE FROM budget_table")
    void deleteAll();

    @Query("SELECT * FROM budget_table ORDER BY name ASC")
    LiveData<List<DatabaseC>> getAllFromBudgetTable();
}
