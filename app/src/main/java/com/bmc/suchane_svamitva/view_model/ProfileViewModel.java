package com.bmc.suchane_svamitva.view_model;

import android.widget.ArrayAdapter;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;

import com.bmc.suchane_svamitva.view.interfaces.ProfileInterface;

/**
 * Created by Nischitha on 23,May,2022
 **/
public class ProfileViewModel {
    ProfileInterface profileInterface;
    public final ObservableField<String> USER_DesignationName = new ObservableField<>("Test");
    public final ObservableField<String> USER_RoleName = new ObservableField<>("Test");
    public final ObservableField<String> USER_MOBILE = new ObservableField<>("Test");
    public final ObservableField<String> USER_DistName = new ObservableField<>("Test");
    public final ObservableField<String> USER_TalukName = new ObservableField<>("Test");

    public ProfileViewModel(ProfileInterface profileInterface) {
        this.profileInterface = profileInterface;
    }
}
