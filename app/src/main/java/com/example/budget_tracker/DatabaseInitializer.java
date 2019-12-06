package com.example.budget_tracker;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

class DatabaseInitializer {

    static void populateAsync(final DatabaseRoom databaseRoom) {
        new PopulateDbAsync(databaseRoom).execute();
    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private final DatabaseRoom databaseRoom;

        PopulateDbAsync(DatabaseRoom databaseRoom) {
            this.databaseRoom = databaseRoom;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // If empty, add initial data
            if (databaseRoom.databaseDao().rowCount() == 0) {
                List<DatabaseC> data = new ArrayList<>();
                data.add(new DatabaseC("Item #1"));
                data.add(new DatabaseC("Item #2"));
                data.add(new DatabaseC("Item #3"));

                databaseRoom.databaseDao().insertItem(
                        data.toArray(new DatabaseC[data.size()]));
            }

            return null;
        }
    }
}
