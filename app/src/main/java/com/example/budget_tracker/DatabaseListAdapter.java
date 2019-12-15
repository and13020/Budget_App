package com.example.budget_tracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.budget_tracker.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class DatabaseListAdapter extends RecyclerView.Adapter
        <DatabaseListAdapter.DatabaseViewHolder> {

    private List<DatabaseC> databaseCList;

    // Constructor, takes one database list
    public DatabaseListAdapter(List<DatabaseC> databaseCList) {
        this.databaseCList = databaseCList;
    }

    // Passes custom view layout to inflate with LayoutInflater
    @Override
    public DatabaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FragmentHomeBinding itemBinding = FragmentHomeBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);

        TextView itemTextView = parent.findViewById(R.id.listItemTitle);
        Button removeButton = parent.findViewById(R.id.removeButton);

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
            binding.listItemTitle.setText(myData.getMItem());
            binding.executePendingBindings();
        }
    }

}




/*
public class DatabaseListAdapter extends
            ListAdapter<DatabaseC, DatabaseListAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView itemTextView;
        public Button deleteButton;

        public ViewHolder(View itemView) {
            super(itemView);

            itemTextView = itemView.findViewById(R.id.listItemTitle);
            deleteButton = itemView.findViewById(R.id.removeButton);
        }
    }
    private ArrayList<DatabaseC> databaseCS;

    public static final DiffUtil.ItemCallback<DatabaseC> DIFF_CALLBACK =
        new DiffUtil.ItemCallback<DatabaseC>() {
            @Override
            public boolean areItemsTheSame(@NonNull DatabaseC oldItem, @NonNull DatabaseC newItem) {
                return oldItem.getMId() == newItem.getMId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull DatabaseC oldItem, @NonNull DatabaseC newItem) {
                return oldItem.getMItem().equals(newItem.getMItem());
            }
        };

    // Constructor
    public DatabaseListAdapter(ArrayList<DatabaseC> databaseCS) {
        super(DIFF_CALLBACK);
    }

    @Override
    public DatabaseListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View dbView = inflater.inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(dbView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DatabaseListAdapter.ViewHolder viewHolder, int position) {
        DatabaseC databaseC = getItem(position);

        TextView textView = viewHolder.itemTextView;
        textView.setText(databaseC.getMItem());

        Button button = viewHolder.deleteButton;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // add function to button to remove current position item
            }
        });
    }

    public void addItemToArray(ArrayList<DatabaseC> newDB) {
        databaseCS.addAll(newDB);
        submitList(databaseCS);
    }

    public void setDatabaseCList(ArrayList<DatabaseC> databaseCList) {
        this.databaseCS = databaseCList;
        notifyDataSetChanged();
    }

}*/

