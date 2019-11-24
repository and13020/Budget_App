package com.example.budget_tracker;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

/*****************************************
 * ViewModel: provides data to UI and survives config changes
 * Acts as communication between Repository and UI
 * Can also share data between fragments
 *****************************************/
public class DatabaseViewModel extends AndroidViewModel {
    private DatabaseRepository dRepository;
    private LiveData<List<DatabaseC>> myResults;

    public DatabaseViewModel (Application application) {
        super(application);
        dRepository = new DatabaseRepository(application);
        myResults = dRepository.getResults();
    }

    LiveData<List<DatabaseC>> getResults() { return myResults; }

    // Wrapper 'insert' calls repository's insert
    public void insert(DatabaseC databaseC) { dRepository.insert(databaseC);}
}
