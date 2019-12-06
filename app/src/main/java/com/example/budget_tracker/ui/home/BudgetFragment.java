package com.example.budget_tracker.ui.home;


import android.os.AsyncTask;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.budget_tracker.DatabaseC;
import com.example.budget_tracker.DatabaseRoom;
import com.example.budget_tracker.R;
import com.example.budget_tracker.databinding.FragmentBudgetBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class BudgetFragment extends Fragment {

    public BudgetFragment() {
    }
    FragmentBudgetBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_budget, container, false);

        binding.buttonSave.setOnClickListener((view) -> {
            String titleEditTextContent = binding
                    .addTitleText.getText().toString().trim();

            if (!titleEditTextContent.isEmpty()){
                new AddTitleTask(titleEditTextContent).execute();
                getActivity().finish();
            }
        });

        return binding.getRoot();
    }

    private class AddTitleTask extends AsyncTask<Void, Void, Void> {
        String title;

        public AddTitleTask(String title) {
            this.title = title;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            DatabaseRoom.getInstance(getActivity().getApplication())
                    .databaseDao().insertItem(new DatabaseC(title));
            return null;
        }
    }
}
