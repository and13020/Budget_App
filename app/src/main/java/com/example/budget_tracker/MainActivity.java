package com.example.budget_tracker;

import android.os.Bundle;

import com.example.budget_tracker.ui.home.BudgetFragment;
import com.example.budget_tracker.ui.home.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get/set bottom navigation view
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        // Set the recycler view
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        final DatabaseListAdapter adapter = new DatabaseListAdapter((List<DatabaseC>) this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Connect viewModel to this activity
        DatabaseViewModel viewModel = ViewModelProviders
                .of(this).get(DatabaseViewModel.class);

        // Add observer for liveData
        viewModel.getResults().observe(this, databaseCS -> adapter.setDatabaseCList(databaseCS));

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