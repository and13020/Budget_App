package com.example.budget_tracker;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/***********************************************
 * Room DatabaseC:
 * Allows the DAO to query to database
 * Runs query threads asynchronously
 **********************************************/

// Turn it into a ROOM database,
// declare what entities belong in the database
@Database(entities = {DatabaseC.class}, version = 1, exportSchema = false)
public abstract class DatabaseRoom extends RoomDatabase {

    public abstract DatabaseDao databaseDao();

    // Created a singleton of DatabaseRoom type
    private static volatile DatabaseRoom INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    // Returns the single of DatabaseRoom type
    // ...will create it on first access
    static DatabaseRoom getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DatabaseRoom.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DatabaseRoom.class, "my_database").build();
                }
            }
        }
        return INSTANCE;
    }

}
