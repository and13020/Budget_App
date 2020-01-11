package com.example.budget_tracker.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.budget_tracker.DatabaseListAdapter;
import com.example.budget_tracker.DatabaseViewModel;
import com.example.budget_tracker.R;

import java.util.Objects;

public class HomeFragment extends Fragment {

    private DatabaseViewModel mDatabaseViewModel;
    private DividerItemDecoration mItemDecoration;
    private RecyclerView mRecyclerView;
    private DatabaseListAdapter mAdapter;

    public HomeFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mDatabaseViewModel = ViewModelProviders.of(this).get(DatabaseViewModel.class);
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        // Set in onViewCreated, otherwise getView does not work
        mRecyclerView = Objects.requireNonNull(getView()).findViewById(R.id.recyclerView);

        createDivider();
        setAdapter();
        setRecyclerView();

        mDatabaseViewModel.getAllData().observe(this, mAdapter::setData);
    }

    private void createDivider(){
        mItemDecoration = new DividerItemDecoration(
                mRecyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        mItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(
                Objects.requireNonNull(getContext()),
                R.drawable.list_item_divider)));
    }
    private void setAdapter() {
        mAdapter = new DatabaseListAdapter(getActivity());
        mAdapter.setOnClickListener(position -> mDatabaseViewModel.deleteItem(position));
    }
    private void setRecyclerView() {
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(mItemDecoration);
    }
}