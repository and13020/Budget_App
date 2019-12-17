package com.example.budget_tracker;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/***********************************************
 * Room DatabaseC:
 * Allows the DAO to query to database
 * Run query threads asynchronously
 **********************************************/

// Turn it into a ROOM database,
// declare what entities belong in the database
@Database(entities = {DatabaseC.class}, version = 1, exportSchema = false)
public abstract class DatabaseRoom extends RoomDatabase {

    private static DatabaseRoom INSTANCE;

    static public DatabaseRoom getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
                    context.getApplicationContext(),
                    DatabaseRoom.class,
                    "DatabaseRoom").build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    public abstract DatabaseDao databaseDao();
}
