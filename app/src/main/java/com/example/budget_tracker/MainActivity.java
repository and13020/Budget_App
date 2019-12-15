package com.example.budget_tracker;

import android.os.Bundle;

import com.example.budget_tracker.ui.home.BudgetFragment;
import com.example.budget_tracker.ui.home.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DatabaseListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get/set bottom navigation view
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        // Set the recycler view
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        adapter = new DatabaseListAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Connect viewModel to this activity
        DatabaseViewModel viewModel = ViewModelProviders.of(this).get(DatabaseViewModel.class);

        // Add observer for liveData
        viewModel.getResults().observe(this, databaseCS -> adapter.setDatabaseCList((ArrayList<DatabaseC>) databaseCS));

        // Add initial data
        DatabaseInitializer.populateAsync(DatabaseRoom.getInstance(getApplicationContext()));

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new HomeFragment()).commit();
    }

    // Select fragment
    private BottomNavigationView.OnNavigationItemSelectedListener navListener
            = menuItem -> {
        Fragment selectedFragment = null;

        switch (menuItem.getItemId()) {
            case R.id.homeFragment:
                selectedFragment = new HomeFragment();
                break;
            case R.id.budgetFragment:
                selectedFragment = new BudgetFragment();
                break;
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment)
                .commit();
        return true;
    };
}