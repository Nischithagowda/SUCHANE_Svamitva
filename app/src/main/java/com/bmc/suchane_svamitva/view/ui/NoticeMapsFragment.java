package com.bmc.suchane_svamitva.view.ui;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;

import com.bmc.suchane_svamitva.R;
import com.bmc.suchane_svamitva.databinding.ActivityNoticeMapBinding;
import com.bmc.suchane_svamitva.view.callbacks.NoticeMapsCallback;
import com.bmc.suchane_svamitva.view.interfaces.NoticeMapsInterface;
import com.bmc.suchane_svamitva.view_model.NoticeMapsViewModel;
import com.google.android.gms.maps.model.LatLng;

public class NoticeMapsFragment extends FragmentActivity implements LocationListener {
    NoticeMapsViewModel viewModel;
    protected LocationManager locationManager;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityNoticeMapBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_notice_map);
        NoticeMapsInterface noticeMapsInterface = new NoticeMapsCallback(this);
        viewModel = new NoticeMapsViewModel(noticeMapsInterface);
        binding.setViewModel(viewModel);

        dialog = new ProgressDialog(this);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.setMessage("Loading Please Wait ..");
        dialog.show();

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            viewModel.OffCurrentLocationCoordinates.set(latLng);
            viewModel.accuracy.set(""+location.getAccuracy());
            locationManager.removeUpdates(this);
            viewModel.setUp();
            dialog.dismiss();
        Log.v("TAG", "IN ON LOCATION CHANGE, lat=" + location.getLatitude() + ", lon=" + location.getLongitude());
        Log.d("TAG", "Accuracy: "+location.getAccuracy());
    }
}
