package com.bmc.suchane_svamitva.utils;

import android.app.Activity;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * Created by Nischitha on 17,May,2022
 **/
public class LoadDBFile {
    Activity activity;

    public LoadDBFile(Activity activity) {
        this.activity = activity;
    }

    public String load_svamitva_vill_master_Data() {
        String json = null;
        try {
            InputStream is = activity.getAssets().open("svamitva_vill_master.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
