package com.example.budget_tracker.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.budget_tracker.DatabaseListAdapter;
import com.example.budget_tracker.DatabaseViewModel;
import com.example.budget_tracker.R;

import java.util.Objects;

public class HomeFragment extends Fragment {

    private DatabaseViewModel mDatabaseViewModel;

    public HomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mDatabaseViewModel = ViewModelProviders.of(this).get(DatabaseViewModel.class);
        //return super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        // Set in onViewCreated, otherwise getView does not work
        RecyclerView recyclerView = Objects.requireNonNull(getView()).findViewById(R.id.recyclerView);
        final DatabaseListAdapter adapter = new DatabaseListAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        mDatabaseViewModel.getAllData().observe(this, adapter::setData);
                //adapter::setData);

    }
}