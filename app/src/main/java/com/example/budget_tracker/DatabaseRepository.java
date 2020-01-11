package com.example.budget_tracker;

import android.app.Application;
import android.os.AsyncTask;

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

    void insertItem(DatabaseC databaseC) { new insertAsyncTask(databaseDao).execute(databaseC); }
    private static class insertAsyncTask extends AsyncTask<DatabaseC, Void, Void> {
        private DatabaseDao mAsyncTaskDao;
        insertAsyncTask(DatabaseDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final DatabaseC... params) {
            mAsyncTaskDao.insertItem(params[0]);
            return null;
        }
    }

    void deleteItem(DatabaseC databaseC) { new deleteItemAsyncTask(databaseDao).execute(databaseC); }
    private static class deleteItemAsyncTask extends AsyncTask<DatabaseC, Void, Void> {
        private DatabaseDao mAsyncTaskDao;
        deleteItemAsyncTask(DatabaseDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final DatabaseC... params) {
            mAsyncTaskDao.deleteItem(params[0]);
            return null;
        }
    }

}
