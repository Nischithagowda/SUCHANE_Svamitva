package com.bmc.suchane_svamitva.database;

import android.content.Context;

import androidx.room.Room;

public class DBConnection {
    private static AppDatabase db;

    private DBConnection() {
    }

    public static AppDatabase getConnection(Context context) {
        if (db == null) {
            db = Room.databaseBuilder(context,
                    AppDatabase.class, "SvamitvaDB")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return db;
    }
}
