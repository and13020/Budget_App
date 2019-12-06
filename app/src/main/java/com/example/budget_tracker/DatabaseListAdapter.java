package com.example.budget_tracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.budget_tracker.databinding.FragmentHomeBinding;

import java.util.List;

public class DatabaseListAdapter extends RecyclerView.Adapter
        <DatabaseListAdapter.DatabaseViewHolder> {

    //private final LayoutInflater dInflater;
    private List<DatabaseC> databaseCList; // Cached copy of item info

    // Constructor, takes one database list
    public DatabaseListAdapter(List<DatabaseC> databaseCList) {
        this.databaseCList = databaseCList;
    }

    // Passes custom view layout to inflate with LayoutInflater
    // Returns a view object
    @Override
    public DatabaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FragmentHomeBinding itemBinding = FragmentHomeBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);

        return new DatabaseViewHolder(itemBinding);
    }

    // Performs actions on views here, just prefix holder. to view name
    @Override
    public void onBindViewHolder(DatabaseViewHolder holder, int position) {
        DatabaseC myData = databaseCList.get(position);
        holder.bindStuff(myData);
    }

    // Returns number of items (times to inflate custom view)
    @Override
    public int getItemCount() {
        return databaseCList.size();
    }

    public void setDatabaseCList(List<DatabaseC> databaseCList) {
        this.databaseCList = databaseCList;
        notifyDataSetChanged();
    }

    // DataBindingUtil allows binding object to access views directly
    static class DatabaseViewHolder extends RecyclerView.ViewHolder {

        FragmentHomeBinding binding;

        DatabaseViewHolder(FragmentHomeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bindStuff(DatabaseC myData) {
            binding.recyclerView.setLayoutManager(myData);
            binding.executePendingBindings();
        }
    }

}
