package com.bmc.suchane_svamitva.view.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import com.bmc.suchane_svamitva.R;
import com.bmc.suchane_svamitva.database.DBConnection;
import com.bmc.suchane_svamitva.databinding.ActivityProfileBinding;
import com.bmc.suchane_svamitva.utils.Constant;
import com.bmc.suchane_svamitva.view.callbacks.ProfileCallback;
import com.bmc.suchane_svamitva.view.interfaces.ProfileInterface;
import com.bmc.suchane_svamitva.view_model.ProfileViewModel;

import java.util.Objects;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Nischitha on 23,May,2022
 **/
public class ProfileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityProfileBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_profile);
        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        ProfileInterface profileInterface = new ProfileCallback(this);
        ProfileViewModel viewModel = new ProfileViewModel(profileInterface);
        binding.setViewModel(viewModel);
        setUserDetails(viewModel);
    }

    public void setUserDetails(ProfileViewModel viewModel){

        SharedPreferences sharedPreferences = getSharedPreferences(Constant.MY_SHARED_PREF, Context.MODE_PRIVATE);
        viewModel.USER_MOBILE.set(sharedPreferences.getString(Constant.USER_MOBILE, null));

        Observable
                .fromCallable(() -> DBConnection.getConnection(this)
                        .getDataBaseDao()
                        .getUserDetails())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result ->{
                    viewModel.USER_DistName.set(result.get(0).getBhoomiDistrictName());
                    viewModel.USER_TalukName.set(result.get(0).getBhoomiTalukName());
                    viewModel.USER_DesignationName.set(result.get(0).getDesign_EN());
                    viewModel.USER_RoleName.set(result.get(0).getRole_EN());
                }, error -> error.printStackTrace());
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
