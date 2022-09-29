package com.bmc.suchane_svamitva.view.ui;

import android.os.Bundle;

import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;

import com.bmc.suchane_svamitva.R;
import com.bmc.suchane_svamitva.databinding.ActivityNoticeMapBinding;
import com.bmc.suchane_svamitva.view.callbacks.NoticeMapsCallback;
import com.bmc.suchane_svamitva.view.interfaces.NoticeMapsInterface;
import com.bmc.suchane_svamitva.view_model.NoticeMapsViewModel;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

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
