package com.example.budget_tracker.ui.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.budget_tracker.R;
import com.example.budget_tracker.databinding.FragmentBudgetBinding;

public class StockFragment extends Fragment {

    public StockFragment() {
        // Required empty public constructor
    }
    private FragmentBudgetBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_stock, container, false);

        return binding.getRoot();
    }
}
