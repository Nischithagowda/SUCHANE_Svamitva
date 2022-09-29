package com.bmc.suchane_svamitva.view.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import com.bmc.suchane_svamitva.R;
import com.bmc.suchane_svamitva.databinding.ActivityNoticeBinding;
import com.bmc.suchane_svamitva.view.callbacks.NoticeActivityCallback;
import com.bmc.suchane_svamitva.view.interfaces.NoticeActivityInterface;
import com.bmc.suchane_svamitva.view_model.NoticeActivityViewModel;

import java.util.Objects;

public class NoticeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityNoticeBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_notice);
        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        NoticeActivityInterface noticeActivityCallback = new NoticeActivityCallback(this);
        NoticeActivityViewModel viewModel = new NoticeActivityViewModel(noticeActivityCallback);
        binding.setViewModel(viewModel);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
