package com.example.budget_tracker;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/***********************************************
 * Room DatabaseC:
 * Allows the DAO to query to database
 * Run query threads asynchronously
 **********************************************/

@Database(entities = {DatabaseC.class}, version = 1, exportSchema = false)
public abstract class DatabaseRoom extends RoomDatabase {

    public abstract DatabaseDao databaseDao();
    private  static volatile DatabaseRoom INSTANCE;
    private  static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static DatabaseRoom getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (DatabaseRoom.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            DatabaseRoom.class, "budget_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            //To delete data between app restarts..
            databaseWriteExecutor.execute(()-> {
                DatabaseDao dao = INSTANCE.databaseDao();
                dao.deleteAll();


                /*DatabaseC databaseC = new DatabaseC("Apartment", 1250);
                dao.insertItem(databaseC);
                databaseC = new DatabaseC("Gas Monies", 30);
                dao.insertItem(databaseC);*/
            });
        }
    };
}
