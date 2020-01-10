package com.example.budget_tracker;

import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

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

    public void insertItem(DatabaseC databaseC) {
        mRepository.insertItem(databaseC);
        Toast.makeText(getApplication(),"Successfully added", Toast.LENGTH_SHORT).show();
    }

    public void deleteItem(DatabaseC databaseC) {
        mRepository.deleteItem(databaseC);
        Toast.makeText(getApplication(), "Deleted", Toast.LENGTH_SHORT).show();
    }
}
