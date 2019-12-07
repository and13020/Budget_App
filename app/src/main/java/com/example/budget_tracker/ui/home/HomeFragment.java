package com.example.budget_tracker.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.budget_tracker.DatabaseListAdapter;
import com.example.budget_tracker.DatabaseViewModel;
import com.example.budget_tracker.R;
import com.example.budget_tracker.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentHomeBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);

        // List adapter to handle the layout manager
        DatabaseListAdapter recyclerViewAdapter =
                new DatabaseListAdapter(new ArrayList<>());

        // Connect to fragment to activity
        binding.recyclerView.setLayoutManager(
                new LinearLayoutManager(getActivity()));


        DatabaseViewModel viewModel =
                ViewModelProviders.of(this).get(DatabaseViewModel.class);

        viewModel.getResults().observe(
                HomeFragment.this, recyclerViewAdapter::setDatabaseCList);

        return binding.getRoot();
    }
}