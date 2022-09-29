package com.bmc.suchane_svamitva.database;

import com.bmc.suchane_svamitva.model.SvamitvaVillageMaster;

import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(entities = {SvamitvaVillageMaster.class},
        version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DatabaseDAO getDataBaseDao();
}
