package com.example.budget_tracker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DatabaseListAdapter extends RecyclerView.Adapter<DatabaseListAdapter.DatabaseViewHolder> {

    // Variables
    private LayoutInflater mInflater;
    private static List<DatabaseC> databaseC = null;
    private OnItemClickListener mListener;

    // Listener Interface
    public interface OnItemClickListener {
        void onDeleteClick(DatabaseC dbOnClick);
    }

    // Set listener
    public void setOnClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    // Constructor
    public DatabaseListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    static class DatabaseViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameView, costView;

        DatabaseViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            nameView = itemView.findViewById(R.id.listItemTitle);
            costView = itemView.findViewById(R.id.listItemCost);
            Button mDeleteButton = itemView.findViewById(R.id.removeButton);

            mDeleteButton.setOnClickListener(v -> {
                if(listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        DatabaseC dbOnClick = databaseC.get(position);
                        listener.onDeleteClick(dbOnClick);
                        Log.e("Error", "DatabaseViewHolder: end of setClickListener");
                    }
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public DatabaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.list_item, parent, false);
        return new DatabaseViewHolder(itemView, mListener);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull DatabaseViewHolder holder, int position) {
        if (databaseC != null) {
            DatabaseC current = databaseC.get(getItemViewType(position));
            holder.nameView.setText(current.getName());
            holder.costView.setText(Integer.toString(current.getCost()));
            //Log.e("ERROR: ", "onBindViewHolder: current.getName()" + current.getName());
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