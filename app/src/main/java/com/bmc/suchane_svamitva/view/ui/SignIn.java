package com.bmc.suchane_svamitva.view.ui;

import android.content.Intent;
import android.os.Bundle;

import com.bmc.suchane_svamitva.R;
import com.bmc.suchane_svamitva.databinding.ActivitySignInBinding;
import com.bmc.suchane_svamitva.view.callbacks.SignInCallback;
import com.bmc.suchane_svamitva.view.interfaces.SignInInterface;
import com.bmc.suchane_svamitva.view_model.SignInViewModel;

import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

/**
 * Created by Nischitha on 28,April,2022
 **/
public class SignIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivitySignInBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in);
        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        SignInInterface signInInterface = new SignInCallback(this);
        SignInViewModel viewModel = new SignInViewModel(signInInterface);
        binding.setViewModel(viewModel);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
