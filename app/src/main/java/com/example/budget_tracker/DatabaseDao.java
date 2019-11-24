package com.example.budget_tracker;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

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

    // Insert mItem
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertmItem(DatabaseC databaseC);

    // Delete all from budget_table
    @Query("DELETE FROM budget_table")
    void deleteAll();

    // Select all from budget_table
    // Wrap the return type <list> with LiveData to keep it current
    @Query("SELECT item_name FROM budget_table")
    LiveData<List<DatabaseC>> getAllFromBudgetTable();
}
