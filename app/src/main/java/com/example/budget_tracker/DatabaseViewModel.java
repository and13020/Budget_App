package com.example.budget_tracker;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*****************************************
 * ViewModel: provides data to UI and survives config changes
 * Acts as communication between Repository and UI
 * Can also share data between fragments
 *****************************************/
public class DatabaseViewModel extends AndroidViewModel {

    private DatabaseRepository mRepository;
    private LiveData<List<DatabaseC>> mAllData;

    public DatabaseViewModel (Application application) {
        super(application);
        mRepository = new DatabaseRepository(application);
        mAllData = mRepository.getAllData();
    }

    public LiveData<List<DatabaseC>> getAllData() {
        return mAllData;
    }

    public void insert(DatabaseC databaseC) {
        mRepository.insert(databaseC);
        Toast.makeText(getApplication(),"Successfully added", Toast.LENGTH_LONG).show();
    }
}
