package com.bmc.suchane_svamitva.view.callbacks;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

import com.bmc.suchane_svamitva.R;
import com.bmc.suchane_svamitva.database.DBConnection;
import com.bmc.suchane_svamitva.utils.Constant;
import com.bmc.suchane_svamitva.view.interfaces.ProfileInterface;
import com.bmc.suchane_svamitva.view.ui.ProfileActivity;
import com.bmc.suchane_svamitva.view_model.ProfileViewModel;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Nischitha on 23,May,2022
 **/
public class ProfileCallback implements ProfileInterface {
    ProfileActivity activity;

    public ProfileCallback(ProfileActivity activity) {
        this.activity = activity;
    }
}
