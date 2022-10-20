package com.bmc.suchane_svamitva.view.callbacks;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CAMERA;
import static android.content.Context.MODE_PRIVATE;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bmc.suchane_svamitva.R;
import com.bmc.suchane_svamitva.api.APIClient_Suchane;
import com.bmc.suchane_svamitva.api.API_Interface_Suchane;
import com.bmc.suchane_svamitva.model.AddressCodeNoticeNoRequest;
import com.bmc.suchane_svamitva.model.AddressCodeNoticeNoResponse;
import com.bmc.suchane_svamitva.model.TokenRes;
import com.bmc.suchane_svamitva.model.UserLatLon;
import com.bmc.suchane_svamitva.utils.Constant;
import com.bmc.suchane_svamitva.view.interfaces.NoticeMapsInterface;
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
        viewModel.btnColor.set(activity.getResources().getColor(R.color.colorPrimary));

        SupportMapFragment mapFragment = (SupportMapFragment) activity.getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(viewModel);

        Intent intent = activity.getIntent();
        viewModel.districtCode.set(intent.getStringExtra("districtCode"));
        viewModel.districtName.set(intent.getStringExtra("districtName"));
        viewModel.talukCode.set(intent.getStringExtra("talukCode"));
        viewModel.talukName.set(intent.getStringExtra("talukName"));
        viewModel.hobliCode.set(intent.getStringExtra("hobliCode"));
        viewModel.hobliName.set(intent.getStringExtra("hobliName"));
        viewModel.villageCode.set(intent.getStringExtra("villageCode"));
        viewModel.LGD_VILLAGE_CODE.set(intent.getStringExtra("LGD_VILLAGE_CODE"));
        viewModel.villageName.set(intent.getStringExtra("villageName"));
    }

    @Override
    public void showMap(NoticeMapsViewModel viewModel) {
        try {
            if (checkLocationPermission()) {

                LatLng latLng = new LatLng(Objects.requireNonNull(viewModel.OffCurrentLocationCoordinates.get()).latitude,
                        Objects.requireNonNull(viewModel.OffCurrentLocationCoordinates.get()).longitude);

                viewModel.choosedLocationMarker.set(Objects.requireNonNull(viewModel.googleMap.get()).addMarker(new MarkerOptions().anchor(0.5f, 0.5f).position(latLng).icon(BitmapDescriptorFactory.fromResource(R.drawable.gunpoint_red))));
                Objects.requireNonNull(viewModel.googleMap.get()).setMapType(GoogleMap.MAP_TYPE_HYBRID);
                Objects.requireNonNull(viewModel.googleMap.get()).moveCamera(CameraUpdateFactory.newLatLng(latLng));
                Objects.requireNonNull(viewModel.googleMap.get()).animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 20));
                Objects.requireNonNull(viewModel.googleMap.get()).setOnCameraMoveStartedListener(viewModel);
                Objects.requireNonNull(viewModel.googleMap.get()).setOnCameraMoveListener(viewModel);
                Objects.requireNonNull(viewModel.googleMap.get()).setOnCameraIdleListener(viewModel);
                Objects.requireNonNull(viewModel.googleMap.get()).setMyLocationEnabled(true);
            } else {
                ActivityCompat.requestPermissions(activity, new String[]{ACCESS_FINE_LOCATION, CAMERA},
                        Constant.LOCATION_PERMISSION_REQUEST_CODE);
            }
        } catch (NullPointerException ex) {
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

            LocationManager locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
            String locationProvider = LocationManager.NETWORK_PROVIDER;
            if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            Location lastKnownLocation = locationManager.getLastKnownLocation(locationProvider);
            double userCurrLat = lastKnownLocation.getLatitude();
            double userCurrLong = lastKnownLocation.getLongitude();

            LatLng currLatLng = new LatLng(userCurrLat, userCurrLong);
            viewModel.OffCurrentLocationCoordinates.set(currLatLng);

            SharedPreferences sharedPreferences = activity.getSharedPreferences(Constant.MY_SHARED_PREF, MODE_PRIVATE);
            String DistanceRange = sharedPreferences.getString(Constant.DistanceRange, null);
            float FixedBetweenDistance = 60;
            try {
                FixedBetweenDistance = Float.parseFloat(DistanceRange);
            } catch (Exception ex){
                Toast.makeText(activity, ""+ex.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }

            Location locationc = new Location("point c");
            locationc.setLatitude(userCurrLat);
            locationc.setLongitude(userCurrLong);
            Location locationd = new Location("point d");
            locationd.setLatitude(latLng.latitude);
            locationd.setLongitude(latLng.longitude);

            double distToCurr = locationc.distanceTo(locationd);
            Log.d("distToCurr", ""+distToCurr+" Meters");

            if (distToCurr >= FixedBetweenDistance){
                viewModel.btnEnable.set(false);
                viewModel.btnColor.set(activity.getResources().getColor(android.R.color.darker_gray));
                viewModel.noteText.set("Users are restricted to select property within a range of "
                        +FixedBetweenDistance+ " Meters from their current Location");
                viewModel.isTextVisible.set(true);
            } else {
                viewModel.btnEnable.set(true);
                viewModel.btnColor.set(activity.getResources().getColor(R.color.colorPrimary));
                viewModel.noteText.set("");
                viewModel.isTextVisible.set(false);
            }
        }
        catch (IOException e) {
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
                GetAddressCodeNoticeNo(viewModel, userLatLon);
            } else {
                Toast.makeText(activity, "select address to proceed further", Toast.LENGTH_LONG).show();
            }
        } catch (Exception ex){
            ex.printStackTrace();
            Toast.makeText(activity, ""+ex.getLocalizedMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void GetAddressCodeNoticeNo(NoticeMapsViewModel viewModel, UserLatLon userLatLon) {
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

                        SharedPreferences sharedPreferences = activity.getSharedPreferences(Constant.MY_SHARED_PREF, MODE_PRIVATE);
                        String mobNum = sharedPreferences.getString(Constant.USER_MOBILE, null);

                        AddressCodeNoticeNoRequest addressCodeNoticeNoRequest = new AddressCodeNoticeNoRequest();
                        addressCodeNoticeNoRequest.setLAT(""+userLatLon.getLatitude());
                        addressCodeNoticeNoRequest.setLONG(""+userLatLon.getLongitude());
                        addressCodeNoticeNoRequest.setLGD_VILLAGECODE(""+viewModel.LGD_VILLAGE_CODE.get());
                        addressCodeNoticeNoRequest.setUSER_ID(""+mobNum);

                        Retrofit client1 = APIClient_Suchane.getClientWithoutToken(activity.getString(R.string.api_url));
                        API_Interface_Suchane apiService1 = client1.create(API_Interface_Suchane.class);
                        Observable<AddressCodeNoticeNoResponse> responseObservable = apiService1.FnGenerateAddressCode_NoticeNo(accessToken, addressCodeNoticeNoRequest);
                        responseObservable.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe((result1) -> {
                                    dialog.dismiss();
                                    if (result1.getRESPONSE_CODE().contains("200") && result1.getNOTICE_NO()!=null && result1.getBHM_GPLUS_ADDRESS_CODE()!=null) {
                                        userLatLon.setNoticeNo(result1.getNOTICE_NO());
                                        userLatLon.setAddressCode(result1.getBHM_GPLUS_ADDRESS_CODE());
                                        userLatLon.setVirtualID(result1.getVIRTUAL_ID());
                                        onNavigateToSelectedAddress(viewModel, userLatLon);
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

    private void onNavigateToSelectedAddress(NoticeMapsViewModel viewModel, UserLatLon userLatLon) {
        Bundle args = new Bundle();
        args.putSerializable(Constant.LOCATION_DATA, userLatLon);
        Intent intent = new Intent(activity, NoticeActivity.class);
        intent.putExtra(Constant.BUNDLE_DATA, args);
        intent.putExtra("districtCode", ""+viewModel.districtCode.get());
        intent.putExtra("districtName", ""+viewModel.districtName.get());
        intent.putExtra("talukCode", ""+viewModel.talukCode.get());
        intent.putExtra("talukName", ""+viewModel.talukName.get());
        intent.putExtra("hobliCode", ""+viewModel.hobliCode.get());
        intent.putExtra("hobliName", ""+viewModel.hobliName.get());
        intent.putExtra("villageCode", ""+viewModel.villageCode.get());
        intent.putExtra("LGD_VILLAGE_CODE", ""+viewModel.LGD_VILLAGE_CODE.get());
        intent.putExtra("villageName", ""+viewModel.villageName.get());
        intent.putExtra("accuracy", ""+viewModel.accuracy.get());
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
