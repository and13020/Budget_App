package com.example.budget_tracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DatabaseListAdapter extends RecyclerView.Adapter<DatabaseListAdapter.DatabaseViewHolder> {

    class DatabaseViewHolder extends RecyclerView.ViewHolder {
        private final TextView databaseItemView;

        private DatabaseViewHolder(View itemView) {
            super(itemView);
            databaseItemView = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater dInflater;
    private List<DatabaseC> databaseCList; // Cached copy of item info

    DatabaseListAdapter(Context context) { dInflater = LayoutInflater.from(context); }

    @Override
    public DatabaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = dInflater.inflate(R.layout.fragment_home, parent, false);
        return new DatabaseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DatabaseViewHolder holder, int position) {
        if (databaseCList != null) {
            DatabaseC current = databaseCList.get(position);
            holder.databaseItemView.setText(current.getMItem());
        } else {
            // Covers the case of data not being ready yet
            holder.databaseItemView.setText("Missing");
        }
    }

    void setDatabase(List<DatabaseC> db) {
        databaseCList = db;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (databaseCList != null)
            return databaseCList.size();
        else return 0;
    }

}
