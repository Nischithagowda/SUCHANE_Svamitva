package com.bmc.suchane_svamitva.view_model;

import android.view.View;
import androidx.databinding.ObservableField;

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

    public void onClickCancel(View view){
        profileInterface.onClickCancel();
    }
}
