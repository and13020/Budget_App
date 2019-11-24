package com.example.budget_tracker;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

// DatabaseC Table Entity
@Entity(tableName = "budget_table")
public class DatabaseC {

    @PrimaryKey(autoGenerate =  true)
    private int mId;

    @NonNull
    @ColumnInfo(name = "item_name")
    private String mItem;

    @NonNull
    @ColumnInfo(name = "item_cost")
    private long mCost;

    // Getter/setters for id, mItem, mCost
    public void setMId(@NonNull int mId) {this.mId = mId;}
    public int getMId() {return this.mId;}

    public void setMItem(@NonNull String mItem) {this.mItem = mItem;}
    public String getMItem() {return this.mItem;}

    public void setMCost(@NonNull long mCost) {this.mCost = mCost;}
    public long getMCost() {return this.mCost;}

    public DatabaseC(){}

    @Ignore
    public DatabaseC(int mId, String mItem, long mCost){
        this.mId = mId;
        this.mItem = mItem;
        this.mCost = mCost;
    }

    @Ignore
    public DatabaseC(String mItem, long mCost){
        this.mItem = mItem;
        this.mCost = mCost;
    }
}

