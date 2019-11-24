package com.example.budget_tracker;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

/*******************************************
 * Repository class: used as a 'middleman'
 * making management easier between using
 * DAO and other backends (which we don't have currently)
 ******************************************/
class DatabaseRepository {
    private DatabaseDao mDatabaseDao;
    private LiveData<List<DatabaseC>> results;

    // Takes data from the DAO and ROOM
    DatabaseRepository(Application application) {
        DatabaseRoom db = DatabaseRoom.getDatabase(application);
        mDatabaseDao = db.databaseDao();
        results = mDatabaseDao.getAllFromBudgetTable();
    }

    // Observed LiveData will notify the observer when the data has changed
    LiveData<List<DatabaseC>> getResults() {
        return results;
    }

    // Asynchronous, calls on non-UI thread using our ExecutorService
    void insert(DatabaseC myDatabaseC) {
        DatabaseRoom.databaseWriteExecutor.execute(() -> {
            mDatabaseDao.insertmItem(myDatabaseC);
        });
    }
}
