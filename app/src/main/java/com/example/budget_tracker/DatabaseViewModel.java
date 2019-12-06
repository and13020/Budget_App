package com.example.budget_tracker;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

/*****************************************
 * ViewModel: provides data to UI and survives config changes
 * Acts as communication between Repository and UI
 * Can also share data between fragments
 *****************************************/
public class DatabaseViewModel extends AndroidViewModel {
    private LiveData<List<DatabaseC>> myResults;

    public DatabaseViewModel (@NonNull Application application) {
        super(application);

        myResults = DatabaseRoom
                .getInstance(getApplication())
                .databaseDao().getAllFromBudgetTable();
    }

    public LiveData<List<DatabaseC>> getResults() {
        return myResults;
    }
}
