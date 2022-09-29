package com.bmc.suchane_svamitva.view.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import com.bmc.suchane_svamitva.R;
import com.bmc.suchane_svamitva.databinding.ActivityOtpVerifyBinding;
import com.bmc.suchane_svamitva.view.callbacks.OTPVerifyCallback;
import com.bmc.suchane_svamitva.view.interfaces.OTPVerifyInterface;
import com.bmc.suchane_svamitva.view_model.OTPVerifyViewModel;

/**
 * Created by Nischitha on 18,May,2022
 **/
public class OTPVerify extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityOtpVerifyBinding binding= DataBindingUtil.setContentView(this, R.layout.activity_otp_verify);
        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        OTPVerifyInterface otpVerifyInterface=new OTPVerifyCallback(this);
        OTPVerifyViewModel viewModel=new OTPVerifyViewModel(otpVerifyInterface);
        binding.setViewModel(viewModel);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
