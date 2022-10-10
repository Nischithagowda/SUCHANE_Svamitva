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

public class NoticeMapsFragment extends FragmentActivity implements LocationListener {
    NoticeMapsViewModel viewModel;
    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityNoticeMapBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_notice_map);
        NoticeMapsInterface noticeMapsInterface = new NoticeMapsCallback(this);
        viewModel = new NoticeMapsViewModel(noticeMapsInterface);
        binding.setViewModel(viewModel);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
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
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
    }

    @Override
    public void onLocationChanged(Location location) {
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        viewModel.CurrentLocationCoordinates.set(latLng);
        Log.d("Latitude",""+latLng.latitude);
        Log.d("Longitude",""+latLng.longitude);
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("Latitude","disable");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("Latitude","enable");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("Latitude","status");
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
