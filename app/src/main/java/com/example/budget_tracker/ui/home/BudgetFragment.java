package com.example.budget_tracker.ui.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.budget_tracker.DatabaseC;
import com.example.budget_tracker.DatabaseViewModel;
import com.example.budget_tracker.R;

import java.util.Objects;

public class BudgetFragment extends Fragment {

    public BudgetFragment() {
    }

    private DatabaseViewModel mDatabaseViewModel;
    private EditText mTitleEditView, mCostEditView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mDatabaseViewModel = ViewModelProviders.of(this).get(DatabaseViewModel.class);
        return inflater.inflate(R.layout.fragment_budget, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        // Set in onViewCreated, otherwise getView does not work
        mTitleEditView = Objects.requireNonNull(getView()).findViewById(R.id.titleEditView);
        mCostEditView = Objects.requireNonNull(getView()).findViewById(R.id.costEditView);
        final Button sButton = getView().findViewById(R.id.button_save);

        if (!mCostEditView.getText().toString().equals("")) {
            try {
                Integer.parseInt(mCostEditView.getText().toString());
            } catch (NumberFormatException e) {
                System.out.println("Number field is blank");
                System.out.println(e.getMessage());
            }
        }

        // Listener to save button
        sButton.setOnClickListener(v -> {
            DatabaseC dbOnClick = new DatabaseC(
                    mTitleEditView.toString(),
                    Integer.parseInt(mCostEditView.getText().toString()));

            mDatabaseViewModel.insert(dbOnClick);
        });
    }
}