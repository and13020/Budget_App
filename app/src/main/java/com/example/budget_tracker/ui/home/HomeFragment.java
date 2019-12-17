package com.example.budget_tracker.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.budget_tracker.DatabaseC;
import com.example.budget_tracker.DatabaseInitializer;
import com.example.budget_tracker.DatabaseListAdapter;
import com.example.budget_tracker.DatabaseRoom;
import com.example.budget_tracker.DatabaseViewModel;
import com.example.budget_tracker.R;
import com.example.budget_tracker.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    public HomeFragment() {}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentHomeBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        setupRecyclerView(binding);

        View view = binding.getRoot();
        return view;
    }

    private void setupRecyclerView(FragmentHomeBinding binding) {
        RecyclerView recyclerView = binding.recyclerView;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        List<DatabaseC> databaseCList = new ArrayList<>();
        databaseCList.add(new DatabaseC("New Car", 3000));
        databaseCList.add(new DatabaseC("Christmas Presents", 52));

        DatabaseListAdapter adapter = new DatabaseListAdapter(databaseCList);
        recyclerView.setAdapter(adapter);
    }
}