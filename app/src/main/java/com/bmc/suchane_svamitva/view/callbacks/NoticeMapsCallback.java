package com.bmc.suchane_svamitva.view.callbacks;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CAMERA;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bmc.suchane_svamitva.R;
import com.bmc.suchane_svamitva.model.UserLatLon;
import com.bmc.suchane_svamitva.utils.Constant;
import com.bmc.suchane_svamitva.view.interfaces.NoticeMapsInterface;
import com.bmc.suchane_svamitva.view.ui.DPR_FPR_LaunchActivity;
import com.bmc.suchane_svamitva.view.ui.NoticeActivity;
import com.bmc.suchane_svamitva.view.ui.NoticeMapsFragment;
import com.bmc.suchane_svamitva.view_model.NoticeMapsViewModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class NoticeMapsCallback implements NoticeMapsInterface {
    NoticeMapsFragment activity;

    public NoticeMapsCallback(NoticeMapsFragment activity) {
        this.activity = activity;
    }

    @Override
    public void loadMap(NoticeMapsViewModel viewModel) {
        SupportMapFragment mapFragment = (SupportMapFragment) activity.getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(viewModel);
    }

    @Override
    public void showMap(NoticeMapsViewModel viewModel) {
        try {
            if (checkLocationPermission()) {
                LatLng latLng = new LatLng(12.9716, 77.5946);
                viewModel.choosedLocationMarker.set(Objects.requireNonNull(viewModel.googleMap.get()).addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.fromResource(R.drawable.mpin1))));
                Objects.requireNonNull(viewModel.googleMap.get()).setMapType(GoogleMap.MAP_TYPE_HYBRID);
                Objects.requireNonNull(viewModel.googleMap.get()).moveCamera(CameraUpdateFactory.newLatLng(latLng));
                Objects.requireNonNull(viewModel.googleMap.get()).animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 20));
                Objects.requireNonNull(viewModel.googleMap.get()).setOnCameraMoveStartedListener(viewModel);
                Objects.requireNonNull(viewModel.googleMap.get()).setOnCameraMoveListener(viewModel);
                Objects.requireNonNull(viewModel.googleMap.get()).setOnCameraIdleListener(viewModel);
                Objects.requireNonNull(viewModel.googleMap.get()).setMyLocationEnabled(true);
            }  else {
                ActivityCompat.requestPermissions(activity, new String[]{ACCESS_FINE_LOCATION, CAMERA},
                        Constant.LOCATION_PERMISSION_REQUEST_CODE);
            }
        } catch (NullPointerException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void loadAddress(NoticeMapsViewModel viewModel) {
        Geocoder geocoder = new Geocoder(activity, Locale.getDefault());
        LatLng latLng = Objects.requireNonNull(viewModel.googleMap.get()).getCameraPosition().target;
        viewModel.userLocationCoordinates.set(latLng);
        List<Address> addresses;
        try {
            addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            String address = addresses.get(0).getAddressLine(0);
            viewModel.userLocationAddress.set(address);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onConfirmLocation(NoticeMapsViewModel viewModel) {
        if (checkLocationPermission()) {
            LatLng latLng = new LatLng(12.9716, 77.5946);
            viewModel.choosedLocationMarker.set(Objects.requireNonNull(viewModel.googleMap.get()).addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.fromResource(R.drawable.mpin1))));
            Objects.requireNonNull(viewModel.googleMap.get()).setMapType(GoogleMap.MAP_TYPE_HYBRID);
            Objects.requireNonNull(viewModel.googleMap.get()).moveCamera(CameraUpdateFactory.newLatLng(latLng));
            Objects.requireNonNull(viewModel.googleMap.get()).animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 20));
            Objects.requireNonNull(viewModel.googleMap.get()).setOnCameraMoveStartedListener(viewModel);
            Objects.requireNonNull(viewModel.googleMap.get()).setOnCameraMoveListener(viewModel);
            Objects.requireNonNull(viewModel.googleMap.get()).setOnCameraIdleListener(viewModel);
            Objects.requireNonNull(viewModel.googleMap.get()).setMyLocationEnabled(true);
        }  else {
            ActivityCompat.requestPermissions(activity, new String[]{ACCESS_FINE_LOCATION, CAMERA},
                    Constant.LOCATION_PERMISSION_REQUEST_CODE);
        }
        if (viewModel.userLocationAddress.get() != null & viewModel.userLocationCoordinates.get()!=null) {
            UserLatLon userLatLon=new UserLatLon();
            userLatLon.setLatitude(Objects.requireNonNull(viewModel.userLocationCoordinates.get()).latitude);
            userLatLon.setLongitude(Objects.requireNonNull(viewModel.userLocationCoordinates.get()).longitude);
            userLatLon.setAccuracy(0.0);
            userLatLon.setAddress(viewModel.userLocationAddress.get());
            onNavigateToSelectedAddress(userLatLon);
        } else {
            Toast.makeText(activity, "select address to proceed further", Toast.LENGTH_LONG).show();
        }
    }

    private void onNavigateToSelectedAddress(UserLatLon userLatLon) {
        Bundle args = new Bundle();
        args.putSerializable(Constant.LOCATION_DATA, userLatLon);
        Intent returnIntent = new Intent();
        returnIntent.putExtra(Constant.BUNDLE_DATA, args);
        activity.setResult(Activity.RESULT_OK, returnIntent);
        activity.finish();

        onNavigateToNotice();
    }

    private boolean checkLocationPermission() {
        return ContextCompat.checkSelfPermission(activity, ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onNavigateToNotice(){
        Intent intent = new Intent(activity, NoticeActivity.class);
        activity.startActivity(intent);
    }
}
