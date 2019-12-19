package com.example.budget_tracker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DatabaseListAdapter extends RecyclerView.Adapter<DatabaseListAdapter.DatabaseViewHolder> {

    static class DatabaseViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameView, costView;

        DatabaseViewHolder(@NonNull View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.listItemTitle);
            costView = itemView.findViewById(R.id.listItemCost);
        }
    }

    private final LayoutInflater mInflater;
    private List<DatabaseC> databaseC;

    public DatabaseListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public DatabaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.list_item, parent, false);
        return new DatabaseViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull DatabaseViewHolder holder, int position) {
        if (databaseC != null) {
            DatabaseC current = databaseC.get(position);
            holder.nameView.setText(current.getName());
            holder.costView.setText(current.getCost());
        } else {
            // Otherwise initialize data
            holder.nameView.setText("Nothing here yet");
            holder.costView.setText("123");
        }
    }

    public void setData(List<DatabaseC> mData) {
        databaseC = mData;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (databaseC != null) {
            return databaseC.size();
        } else {
            return 0;
        }
    }

}