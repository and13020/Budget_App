package com.example.budget_tracker;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

class DatabaseRepository {

    private DatabaseDao databaseDao;
    private LiveData<List<DatabaseC>> mAllData;

    DatabaseRepository(Application application) {
        DatabaseRoom db = DatabaseRoom.getInstance(application);
        databaseDao = db.databaseDao();
        mAllData = databaseDao.getAllFromBudgetTable();
    }

    LiveData<List<DatabaseC>> getAllData() {
        return mAllData;
    }

    void insert(DatabaseC databaseC) {
        DatabaseRoom.databaseWriteExecutor.execute(() -> databaseDao.insertItem(databaseC));
    }
}