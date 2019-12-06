package com.example.budget_tracker;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

// DatabaseC Table Entity
@Entity(tableName = "budget_table")
public class DatabaseC extends RecyclerView.LayoutManager {

    // Table columns
    @PrimaryKey(autoGenerate =  true)
    private int mId;

    @ColumnInfo(name = "item_name")
    private String mItem;

    @ColumnInfo(name = "item_cost")
    private float mCost;

    // Getter/setters for id, mItem, mCost
    public int getMId() {return this.mId;}

    public void setMItem(String mItem) {this.mItem = mItem;}
    public String getMItem() {return this.mItem;}

    public void setMCost(float mCost) {this.mCost = mCost;}
    public float getMCost() {return this.mCost;}


    // Constructors
    public DatabaseC(){}

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return null;
    }

    @Ignore
    public DatabaseC(int mId, String mItem, float mCost){
        this.mId = mId;
        this.mItem = mItem;
        this.mCost = mCost;
    }

    @Ignore
    public DatabaseC(String mItem, float mCost){
        this.mItem = mItem;
        this.mCost = mCost;
    }

    public DatabaseC(String mItem) {
        this.mItem = mItem;
    }
}

