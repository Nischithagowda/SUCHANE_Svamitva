package com.bmc.suchane_svamitva.view.callbacks;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CAMERA;
import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bmc.suchane_svamitva.R;
import com.bmc.suchane_svamitva.api.APIClient_Suchane;
import com.bmc.suchane_svamitva.api.API_Interface_Suchane;
import com.bmc.suchane_svamitva.model.AddressCodeNoticeNoRequest;
import com.bmc.suchane_svamitva.model.AddressCodeNoticeNoResponse;
import com.bmc.suchane_svamitva.model.FnSVM_UploadDocumentResponse;
import com.bmc.suchane_svamitva.model.SMS_Request;
import com.bmc.suchane_svamitva.model.SMS_Response;
import com.bmc.suchane_svamitva.model.TokenRes;
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

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

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
        try {
            if (viewModel.userLocationAddress.get() != null & viewModel.userLocationCoordinates.get() != null) {
                UserLatLon userLatLon = new UserLatLon();
                userLatLon.setLatitude(Objects.requireNonNull(viewModel.userLocationCoordinates.get()).latitude);
                userLatLon.setLongitude(Objects.requireNonNull(viewModel.userLocationCoordinates.get()).longitude);
                userLatLon.setAccuracy(0.0);
                userLatLon.setAddress(viewModel.userLocationAddress.get());
                GetAddressCodeNoticeNo(userLatLon);
            } else {
                Toast.makeText(activity, "select address to proceed further", Toast.LENGTH_LONG).show();
            }
        } catch (Exception ex){
            ex.printStackTrace();
            Toast.makeText(activity, ""+ex.getLocalizedMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void GetAddressCodeNoticeNo(UserLatLon userLatLon) {
        if (isNetworkAvailable()) {
            ProgressDialog dialog = new ProgressDialog(activity);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setCancelable(false);
            dialog.setMessage("Loading Please Wait ..");
            dialog.show();

            Retrofit client = APIClient_Suchane.getClientWithoutToken(activity.getString(R.string.api_url));
            API_Interface_Suchane apiService = client.create(API_Interface_Suchane.class);
            Observable<TokenRes> serviceToken = apiService.getToken(activity.getString(R.string.api_user_id), activity.getString(R.string.api_password), activity.getString(R.string.grant_type));
            serviceToken.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe((result) -> {
                        SharedPreferences.Editor editor = activity.getSharedPreferences(activity.getString(R.string.Auth), MODE_PRIVATE).edit();
                        editor.putString(activity.getString(R.string.token), result.getAccessToken());
                        editor.putString(activity.getString(R.string.token_type), result.getTokenType());
                        editor.putString(activity.getString(R.string.refresh_tkn), result.getRefreshToken());
                        editor.apply();

                        String accessToken = result.getTokenType() + " " + result.getAccessToken();

                        AddressCodeNoticeNoRequest addressCodeNoticeNoRequest = new AddressCodeNoticeNoRequest();
                        addressCodeNoticeNoRequest.setLAT(""+userLatLon.getLatitude());
                        addressCodeNoticeNoRequest.setLONG(""+userLatLon.getLongitude());

                        Retrofit client1 = APIClient_Suchane.getClientWithoutToken(activity.getString(R.string.api_url));
                        API_Interface_Suchane apiService1 = client1.create(API_Interface_Suchane.class);
                        Observable<AddressCodeNoticeNoResponse> responseObservable = apiService1.FnGenerateAddressCode_NoticeNo(accessToken, addressCodeNoticeNoRequest);
                        responseObservable.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe((result1) -> {
                                    dialog.dismiss();
                                    if (result1.getRESPONSE_CODE().contains("200") && result1.getNOTICE_NO()!=null && result1.getADDRESS_CODE()!=null) {
                                        userLatLon.setNoticeNo(result1.getNOTICE_NO());
                                        userLatLon.setAddressCode(result1.getADDRESS_CODE());
                                        onNavigateToSelectedAddress(userLatLon);
                                    } else {
                                        Toast.makeText(activity, "" + result1.getRESPONSE_MESSAGE(), Toast.LENGTH_SHORT).show();
                                    }
                                }, (error) -> {
                                    dialog.dismiss();
                                    Toast.makeText(activity, error.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                                });
                    }, (error) -> {
                        Toast.makeText(activity, error.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    });
        } else {
            Toast.makeText(activity, activity.getString(R.string.please_switch_on_the_internet), Toast.LENGTH_SHORT).show();
        }
    }

    private void onNavigateToSelectedAddress(UserLatLon userLatLon) {
        Bundle args = new Bundle();
        args.putSerializable(Constant.LOCATION_DATA, userLatLon);
        Intent intent = new Intent(activity, NoticeActivity.class);
        intent.putExtra(Constant.BUNDLE_DATA, args);
        activity.startActivity(intent);
        activity.finish();
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

    private boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
