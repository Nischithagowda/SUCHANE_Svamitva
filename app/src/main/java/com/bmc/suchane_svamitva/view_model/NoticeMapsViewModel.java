package com.bmc.suchane_svamitva.view_model;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.bmc.suchane_svamitva.view.interfaces.NoticeMapsInterface;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import java.util.Objects;

public class NoticeMapsViewModel implements OnMapReadyCallback, GoogleMap.OnCameraMoveStartedListener, GoogleMap.OnCameraIdleListener, GoogleMap.OnCameraMoveListener {
    NoticeMapsInterface noticeMapsInterface;
    public final ObservableField<String> userLocationAddress = new ObservableField<>("");
    public final ObservableField<LatLng> userLocationCoordinates = new ObservableField<>();
    public final ObservableField<GoogleMap> googleMap = new ObservableField<>();
    public final ObservableField<Marker> choosedLocationMarker = new ObservableField<>();
    public final ObservableField<String> districtCode = new ObservableField<>();
    public final ObservableField<String> districtName = new ObservableField<>("");
    public final ObservableField<String> talukCode = new ObservableField<>();
    public final ObservableField<String> talukName = new ObservableField<>("");
    public final ObservableField<String> hobliCode = new ObservableField<>();
    public final ObservableField<String> hobliName = new ObservableField<>("");
    public final ObservableField<String> villageCode = new ObservableField<>();
    public final ObservableField<String> villageName = new ObservableField<>("");

    public NoticeMapsViewModel(NoticeMapsInterface noticeMapsInterface) {
        this.noticeMapsInterface = noticeMapsInterface;
        setUp();
    }

    public void setUp() {
        noticeMapsInterface.loadMap(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.googleMap.set(googleMap);
        noticeMapsInterface.showMap(this);

    }

    @Override
    public void onCameraMoveStarted(int reason) {

    }

    @Override
    public void onCameraIdle() {
        noticeMapsInterface.loadAddress(this);

    }

    @Override
    public void onCameraMove() {
        LatLng latLng = Objects.requireNonNull(googleMap.get()).getCameraPosition().target;
        Objects.requireNonNull(choosedLocationMarker.get()).setPosition(latLng);
    }

    public void onConfirmLocation(View view){
        noticeMapsInterface.onConfirmLocation(this);
    }
}
