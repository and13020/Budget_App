package com.example.budget_tracker;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

// DatabaseC Table Entity
@Entity(tableName = "budget_table")
public class DatabaseC{
    // Table columns
    @PrimaryKey(autoGenerate =  true)
    private int mId;

    //@ColumnInfo(name = "item_name")
    private String name;

    //@ColumnInfo(name = "item_cost")
    private int cost;

    // Getter/setters for id, mItem, mCost
    void setMId(int mId) {this.mId = mId;} // should not have set
    int getMId()         {return mId;}

    public void setName(String name) {this.name = name;}
    public String getName()          {return name;}

    void setCost(int cost) {this.cost = cost;}
    public int getCost()          {return cost;}

    // Default Constructor
    public DatabaseC(){}

    // Constructor
    @Ignore
    public DatabaseC(String name, int cost){
        setName(name);
        setCost(cost);
    }

}

