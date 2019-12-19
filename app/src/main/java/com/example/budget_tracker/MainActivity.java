package com.example.budget_tracker;

import android.os.Bundle;

import com.example.budget_tracker.ui.home.BudgetFragment;
import com.example.budget_tracker.ui.home.HomeFragment;
import com.example.budget_tracker.ui.home.StockFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get/set bottom navigation view
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

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
            case R.id.stockFragment:
                selectedFragment = new StockFragment();
                break;
        }

        assert selectedFragment != null;
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment)
                .commit();
        return true;
    };
}