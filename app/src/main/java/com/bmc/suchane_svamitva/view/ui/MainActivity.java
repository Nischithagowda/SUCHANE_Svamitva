package com.bmc.suchane_svamitva.view.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.bmc.suchane_svamitva.R;
import com.bmc.suchane_svamitva.databinding.ActivityMainBinding;
import com.bmc.suchane_svamitva.view.callbacks.MainActivityCallback;
import com.bmc.suchane_svamitva.view.interfaces.MainActivityInterface;
import com.bmc.suchane_svamitva.view_model.MainActivityViewModel;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        MainActivityInterface mainActivityInterface = new MainActivityCallback(this);
        MainActivityViewModel viewModel = new MainActivityViewModel(mainActivityInterface);
        binding.setViewModel(viewModel);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}