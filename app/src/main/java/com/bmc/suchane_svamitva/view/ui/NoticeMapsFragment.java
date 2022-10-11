package com.bmc.suchane_svamitva.view.ui;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;

import com.bmc.suchane_svamitva.R;
import com.bmc.suchane_svamitva.databinding.ActivityNoticeMapBinding;
import com.bmc.suchane_svamitva.view.callbacks.NoticeMapsCallback;
import com.bmc.suchane_svamitva.view.interfaces.NoticeMapsInterface;
import com.bmc.suchane_svamitva.view_model.NoticeMapsViewModel;
import com.google.android.gms.maps.model.LatLng;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class NoticeMapsFragment extends FragmentActivity {
    NoticeMapsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityNoticeMapBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_notice_map);
        NoticeMapsInterface noticeMapsInterface = new NoticeMapsCallback(this);
        viewModel = new NoticeMapsViewModel(noticeMapsInterface);
        binding.setViewModel(viewModel);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

}
