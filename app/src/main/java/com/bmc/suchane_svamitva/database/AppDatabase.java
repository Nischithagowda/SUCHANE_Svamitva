package com.bmc.suchane_svamitva.database;

import com.bmc.suchane_svamitva.model.ApprovedDPRTbl;
import com.bmc.suchane_svamitva.model.DocumentTbl;
import com.bmc.suchane_svamitva.model.Hobli;
import com.bmc.suchane_svamitva.model.Image;
import com.bmc.suchane_svamitva.model.ImageTempTbl;
import com.bmc.suchane_svamitva.model.NoticeDetailsTbl;
import com.bmc.suchane_svamitva.model.PendingDPRTbl;
import com.bmc.suchane_svamitva.model.PendingDPRTbl_Updated;
import com.bmc.suchane_svamitva.model.SvamitvaVillageMaster;
import com.bmc.suchane_svamitva.model.USER_DETAILS;
import com.bmc.suchane_svamitva.model.Village;

import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(entities = {SvamitvaVillageMaster.class, USER_DETAILS.class, Hobli.class, Village.class, NoticeDetailsTbl.class,
        Image.class, PendingDPRTbl.class, PendingDPRTbl_Updated.class, ApprovedDPRTbl.class, DocumentTbl.class, ImageTempTbl.class},
        version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DatabaseDAO getDataBaseDao();
}
