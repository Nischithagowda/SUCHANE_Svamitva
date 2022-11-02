package com.bmc.suchane_svamitva.view.callbacks;
//
// Created by Nischitha on 22-09-2022.
// Copyright (c) 2022 BMC. All rights reserved.
//

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CAMERA;
import static android.content.Context.MODE_PRIVATE;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bmc.suchane_svamitva.R;
import com.bmc.suchane_svamitva.api.APIClient_Suchane;
import com.bmc.suchane_svamitva.api.API_Interface_Suchane;
import com.bmc.suchane_svamitva.database.DBConnection;
import com.bmc.suchane_svamitva.model.ApprovedDPRTbl;
import com.bmc.suchane_svamitva.model.District;
import com.bmc.suchane_svamitva.model.FnGetApprovedDPRResponse;
import com.bmc.suchane_svamitva.model.FnGetPendingDPRRequest;
import com.bmc.suchane_svamitva.model.FnGetPendingDPRResponse;
import com.bmc.suchane_svamitva.model.Hobli;
import com.bmc.suchane_svamitva.model.HobliRequest;
import com.bmc.suchane_svamitva.model.HobliResponse;
import com.bmc.suchane_svamitva.model.LogoutRequest;
import com.bmc.suchane_svamitva.model.LogoutResponse;
import com.bmc.suchane_svamitva.model.PendingDPRTbl;
import com.bmc.suchane_svamitva.model.Taluka;
import com.bmc.suchane_svamitva.model.TokenRes;
import com.bmc.suchane_svamitva.model.USER_DETAILS;
import com.bmc.suchane_svamitva.model.Village;
import com.bmc.suchane_svamitva.model.VillageRequest;
import com.bmc.suchane_svamitva.model.VillageResponse;
import com.bmc.suchane_svamitva.utils.Constant;
import com.bmc.suchane_svamitva.view.interfaces.MainActivityInterface;
import com.bmc.suchane_svamitva.view.ui.MainActivity;
import com.bmc.suchane_svamitva.view.ui.NoticeMapsFragment;
import com.bmc.suchane_svamitva.view.ui.SelectActivity;
import com.bmc.suchane_svamitva.view.ui.SignIn;
import com.bmc.suchane_svamitva.view_model.MainActivityViewModel;
import com.bmc.suchane_svamitva.view_model.OTPVerifyViewModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MainActivityCallback implements MainActivityInterface {
    MainActivity activity;

    public MainActivityCallback(MainActivity activity) {
        this.activity = activity;
        if (!checkLocationPermission()){
            ActivityCompat.requestPermissions(activity, new String[]{ACCESS_FINE_LOCATION, CAMERA},
                    Constant.LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    private boolean checkLocationPermission() {
        return ContextCompat.checkSelfPermission(activity, ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void getUserDistrict(MainActivityViewModel viewModel){

        Observable
                .fromCallable(() -> DBConnection.getConnection(activity)
                        .getDataBaseDao()
                        .getUserDistrict())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result ->{
                    viewModel.districtNameList.clear();
                    if (result.size()>0) {
                        viewModel.districtNameList.addAll(result);
                    } else {
                        District district = new District();
                        district.setDISTRICT_CODE("0");
                        district.setDISTRICT_NAME("No District");
                        viewModel.districtNameList.add(district);
                    }
                }, error -> error.printStackTrace());
    }

    @Override
    public void getUserTaluk(MainActivityViewModel viewModel){

        Observable
                .fromCallable(() -> DBConnection.getConnection(activity)
                        .getDataBaseDao()
                        .getUserTaluk(viewModel.districtCode.get()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result ->{
                    viewModel.talukNameList.clear();
                    if (result.size()>0) {
                        viewModel.talukNameList.addAll(result);

                    } else {
                        Taluka taluka = new Taluka();
                        taluka.setDISTRICT_CODE("0");
                        taluka.setTALUKA_CODE("0");
                        taluka.setTALUKA_NAME("No Taluk");
                        viewModel.talukNameList.add(taluka);
                    }
                }, error -> error.printStackTrace());
    }

    @Override
    public void getHobli(MainActivityViewModel viewModel){
        Observable
                .fromCallable(() -> DBConnection.getConnection(activity)
                        .getDataBaseDao()
                        .getHobliDetails(viewModel.districtCode.get(), viewModel.talukCode.get()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result ->{
                    viewModel.hobliNameList.clear();
                    if (result.size()>0) {
                        viewModel.hobliNameList.addAll(result);
                    } else {
                        Hobli hobli = new Hobli();
                        hobli.setDISTRICT_CODE("0");
                        hobli.setTALUKA_CODE("0");
                        hobli.setHOBLI_CODE("0");
                        hobli.setHOBLI_NAME("No Hobli");
                        viewModel.hobliNameList.add(hobli);
                    }
                }, error -> error.printStackTrace());
    }

    @Override
    public void getVillage(MainActivityViewModel viewModel){

        Observable
                .fromCallable(() -> DBConnection.getConnection(activity)
                        .getDataBaseDao()
                        .getVillageDetails(viewModel.districtCode.get(), viewModel.talukCode.get(), viewModel.hobliCode.get()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result ->{
                    viewModel.villageNameList.clear();
                    if (result.size()>0) {
                        viewModel.villageNameList.addAll(result);
                    } else {
                        Village village = new Village();
                        village.setDISTRICT_CODE("0");
                        village.setTALUK_CODE("0");
                        village.setHOBLI_CODE("0");
                        village.setVILLAGE_CODE("0");
                        village.setVILLAGE_NAME("No Village");
                        viewModel.villageNameList.add(village);
                        getVillageFromServer(viewModel);
                    }
                }, error -> error.printStackTrace());
    }

    public void getVillageFromServer(MainActivityViewModel viewModel) {
        if (isNetworkAvailable()) {
            ProgressDialog dialog = new ProgressDialog(activity);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setCancelable(false);
            dialog.setMessage("Loading Please Wait ..");
            dialog.show();

            SharedPreferences sharedPreferences1 = activity.getSharedPreferences(activity.getString(R.string.Auth), MODE_PRIVATE);
            String token = sharedPreferences1.getString(activity.getString(R.string.token), null);
            String tokenType = sharedPreferences1.getString(activity.getString(R.string.token_type), null);
            String accessToken = tokenType + " " + token;

            String distCode = viewModel.districtCode.get();
            String talukCode = viewModel.talukCode.get();
            String hobliCode = viewModel.hobliCode.get();

            VillageRequest villageRequest = new VillageRequest();
            villageRequest.setDISTRICT_CODE(distCode);
            villageRequest.setTALUK_CODE(talukCode);
            villageRequest.setHOBLI_CODE(hobliCode);

            Retrofit client = APIClient_Suchane.getClientWithoutToken(activity.getString(R.string.api_url));
            API_Interface_Suchane apiService = client.create(API_Interface_Suchane.class);
            Observable<VillageResponse> responseObservable = apiService.FnGetVillage(accessToken, villageRequest);
            responseObservable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe((result) -> {
                        dialog.dismiss();
                        if (result.getRESPONSE_CODE().contains("200")) {
                            if (result.getVillageList().size() > 0) {
                                List<Village> villageList;
                                for (int i = 0; i < result.getVillageList().size(); i++) {
                                    Log.d("result.i", "" + i);
                                    result.getVillageList().get(i).setDISTRICT_CODE(distCode);
                                    result.getVillageList().get(i).setTALUK_CODE(talukCode);
                                    result.getVillageList().get(i).setHOBLI_CODE(hobliCode);
                                    if (i == result.getVillageList().size() - 1) {
                                        villageList = result.getVillageList();
                                        deleteVillageDetails(viewModel, villageList);
                                    }
                                }

                            } else {
                                Toast.makeText(activity, "" + result.getRESPONSE_MESSAGE(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(activity, "" + result.getRESPONSE_MESSAGE(), Toast.LENGTH_SHORT).show();
                        }
                    }, (error) -> {
                        dialog.dismiss();
                        error.printStackTrace();
                        Toast.makeText(activity, "" + error.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    });
        } else {
            Toast.makeText(activity, activity.getString(R.string.please_switch_on_the_internet), Toast.LENGTH_SHORT).show();
        }

    }

    public void deleteVillageDetails(MainActivityViewModel viewModel, List<Village> villageList){
        ProgressDialog dialog = new ProgressDialog(activity);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.setMessage("Loading Data Wait ..");
        dialog.show();
        Observable
                .fromCallable(() -> DBConnection.getConnection(activity)
                        .getDataBaseDao()
                        .deleteVillageDetails(viewModel.districtCode.get(), viewModel.talukCode.get(), viewModel.hobliCode.get()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                            dialog.dismiss();
                            InsertVillageDetails(viewModel, villageList);
                        }, error -> {
                            error.printStackTrace();
                            dialog.dismiss();
                        }
                );
    }

    public void InsertVillageDetails(MainActivityViewModel viewModel, List<Village> villageList){
        ProgressDialog dialog = new ProgressDialog(activity);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.setMessage("Loading Data Wait ..");
        dialog.show();

        Observable
                .fromCallable(() -> DBConnection.getConnection(activity)
                        .getDataBaseDao()
                        .InsertVillageDetails(villageList))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    dialog.dismiss();
                    viewModel.villageNameList.clear();
                    viewModel.villageNameList.addAll(villageList);
                    }, error -> {
                    error.printStackTrace();
                    dialog.dismiss();
                });
    }

    @Override
    public void logoutFromServer(){
        ProgressDialog dialog = new ProgressDialog(activity);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.setMessage("Loading, Please Wait ..");
        dialog.show();

        SharedPreferences sharedPreferences = activity.getSharedPreferences(Constant.MY_SHARED_PREF, MODE_PRIVATE);
        String mobileNo = sharedPreferences.getString(Constant.USER_MOBILE, null);

        Retrofit client = APIClient_Suchane.getClientWithoutToken(activity.getString(R.string.api_url));
        API_Interface_Suchane apiService = client.create(API_Interface_Suchane.class);
        Observable<TokenRes> serviceToken = apiService.getToken(activity.getString(R.string.api_user_id), activity.getString(R.string.api_password), activity.getString(R.string.grant_type));
        serviceToken.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((result) -> {
                    SharedPreferences.Editor editor = activity.getSharedPreferences(activity.getString(R.string.Auth), MODE_PRIVATE).edit();
                    editor.putString(activity.getString(R.string.token), result.getAccessToken());
                    editor.putString(activity.getString(R.string.token_type),result.getTokenType());
                    editor.putString(activity.getString(R.string.refresh_tkn), result.getRefreshToken());
                    editor.apply();

                    LogoutRequest logoutRequest = new LogoutRequest();
                    logoutRequest.setMOBILE_NO(mobileNo);

                    String accessToken = result.getTokenType() + " " + result.getAccessToken();
                    Retrofit client1 = APIClient_Suchane.getClientWithoutToken(activity.getString(R.string.api_url));
                    API_Interface_Suchane apiService1 = client1.create(API_Interface_Suchane.class);
                    Observable<LogoutResponse> responseObservable = apiService1.FnLogout(accessToken, logoutRequest);
                    responseObservable.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe((result1) -> {
                                dialog.dismiss();
                                if (result1.getRESPONSE_CODE().contains("200")) {
                                    clearLocalDataBase();
                                } else {
                                    Toast.makeText(activity, ""+result1.getRESPONSE_MESSAGE(), Toast.LENGTH_SHORT).show();
                                }
                            }, (error) -> {
                                dialog.dismiss();
                                Toast.makeText(activity, error.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                            });
                }, (error) -> {
                    Toast.makeText(activity, error.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                });
    }

    private void clearLocalDataBase() {
        ProgressDialog dialog = new ProgressDialog(activity);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.setMessage("Loading, Please Wait ..");
        dialog.show();

        Observable
                .fromCallable(() -> DBConnection.getConnection(activity)
                        .getDataBaseDao()
                        .deleteUserDetails())
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result ->
                {
                    DBConnection.getConnection(activity).getDataBaseDao().deleteHobliDetails();
                    DBConnection.getConnection(activity).getDataBaseDao().deleteFullVillageDetails();
                    DBConnection.getConnection(activity).getDataBaseDao().deleteFullNoticeDetails();
                    DBConnection.getConnection(activity).getDataBaseDao().deleteFullImageDetails();
                    DBConnection.getConnection(activity).getDataBaseDao().deleteFullPendingDPRDetails();
                    DBConnection.getConnection(activity).getDataBaseDao().deleteFullApprovedDPRDetails();

                    dialog.dismiss();
                }, error -> {
                    dialog.dismiss();
                    error.printStackTrace();
                });

        SharedPreferences sharedPreferences = activity.getSharedPreferences(Constant.MY_SHARED_PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        SharedPreferences.Editor editor_Auth = activity.getSharedPreferences(activity.getString(R.string.Auth), MODE_PRIVATE).edit();
        editor_Auth.clear();
        editor_Auth.apply();

        Intent intent = new Intent(activity, SignIn.class);
        activity.startActivity(intent);
        activity.finish();
        Toast.makeText(activity, "User Logout Successfully", Toast.LENGTH_SHORT).show();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    @Override
    public void getPendingDPRDetails(MainActivityViewModel viewModel){
        if (isNetworkAvailable()) {
            ProgressDialog dialog = new ProgressDialog(activity);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setCancelable(false);
            dialog.setMessage("Fetching, Please Wait ..");
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

                        FnGetPendingDPRRequest fnGetPendingDPRRequest = new FnGetPendingDPRRequest();
                        fnGetPendingDPRRequest.setLGD_VILLAGECODE(viewModel.LGD_VILLAGE_CODE.get());
//                        fnGetPendingDPRRequest.setLGD_VILLAGECODE("598473");

                        String accessToken = result.getTokenType() + " " + result.getAccessToken();
                        Retrofit client1 = APIClient_Suchane.getClientWithoutToken(activity.getString(R.string.api_url));
                        API_Interface_Suchane apiService1 = client1.create(API_Interface_Suchane.class);
                        Observable<FnGetPendingDPRResponse> responseObservable = apiService1.FnGetPendingDPR(accessToken, fnGetPendingDPRRequest);
                        responseObservable.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe((result1) -> {
                                    dialog.dismiss();
                                    if (result1.getRESPONSE_CODE().contains("200") && result1.getPendingDPRTblList().size() > 0) {
                                        deletePendingDPRDetails(viewModel, result1.getPendingDPRTblList());
                                    } else {
                                        onNavigateToNext(viewModel);
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
            onNavigateToNext(viewModel);
        }
    }

    public void deletePendingDPRDetails(MainActivityViewModel viewModel, List<PendingDPRTbl> pendingDPRTblList){
        ProgressDialog dialog = new ProgressDialog(activity);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.setMessage("Loading Data Wait ..");
        dialog.show();
        Observable
                .fromCallable(() -> DBConnection.getConnection(activity)
                        .getDataBaseDao()
                        .deletePendingDPRDetails(viewModel.LGD_VILLAGE_CODE.get()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                            dialog.dismiss();
                            InsertPendingDPRDetails(viewModel, pendingDPRTblList);
                        }, error -> {
                            error.printStackTrace();
                            dialog.dismiss();
                        }
                );
    }

    public void InsertPendingDPRDetails(MainActivityViewModel viewModel, List<PendingDPRTbl> pendingDPRTblList){
        ProgressDialog dialog = new ProgressDialog(activity);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.setMessage("Loading Data Wait ..");
        dialog.show();

        Observable
                .fromCallable(() -> DBConnection.getConnection(activity)
                        .getDataBaseDao()
                        .InsertPendingDPRDetails(pendingDPRTblList))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    dialog.dismiss();
                    getApprovedDPRDetails(viewModel);
                }, error -> {
                    error.printStackTrace();
                    dialog.dismiss();
                });
    }

    public void getApprovedDPRDetails(MainActivityViewModel viewModel){
        ProgressDialog dialog = new ProgressDialog(activity);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.setMessage("Fetching, Please Wait ..");
        dialog.show();

        FnGetPendingDPRRequest fnGetPendingDPRRequest = new FnGetPendingDPRRequest();
        fnGetPendingDPRRequest.setLGD_VILLAGECODE(viewModel.LGD_VILLAGE_CODE.get());

        SharedPreferences sharedPreferences1 = activity.getSharedPreferences(activity.getString(R.string.Auth), MODE_PRIVATE);
        String token = sharedPreferences1.getString(activity.getString(R.string.token), null);
        String tokenType = sharedPreferences1.getString(activity.getString(R.string.token_type), null);
        String accessToken = tokenType + " " + token;

        Retrofit client1 = APIClient_Suchane.getClientWithoutToken(activity.getString(R.string.api_url));
        API_Interface_Suchane apiService1 = client1.create(API_Interface_Suchane.class);
        Observable<FnGetApprovedDPRResponse> responseObservable = apiService1.FnGetApprovedDPR(accessToken, fnGetPendingDPRRequest);
        responseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((result1) -> {
                    dialog.dismiss();
                    if (result1.getRESPONSE_CODE().contains("200") && result1.getApprovedDPRTblList().size()>0) {
                        deleteApprovedDPRDetails(viewModel, result1.getApprovedDPRTblList());
                    } else {
                        onNavigateToNext(viewModel);
                        //Toast.makeText(activity, ""+result1.getRESPONSE_MESSAGE(), Toast.LENGTH_SHORT).show();
                    }
                }, (error) -> {
                    dialog.dismiss();
                    Toast.makeText(activity, error.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                });
    }

    public void deleteApprovedDPRDetails(MainActivityViewModel viewModel, List<ApprovedDPRTbl> approvedDPRTblList){
        ProgressDialog dialog = new ProgressDialog(activity);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.setMessage("Loading Data Wait ..");
        dialog.show();
        Observable
                .fromCallable(() -> DBConnection.getConnection(activity)
                        .getDataBaseDao()
                        .deleteApprovedDPRDetails(viewModel.LGD_VILLAGE_CODE.get()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                            dialog.dismiss();
                            InsertApprovedDPRDetails(viewModel, approvedDPRTblList);
                        }, error -> {
                            error.printStackTrace();
                            dialog.dismiss();
                        }
                );
    }

    public void InsertApprovedDPRDetails(MainActivityViewModel viewModel, List<ApprovedDPRTbl> approvedDPRTblList){
        ProgressDialog dialog = new ProgressDialog(activity);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.setMessage("Loading Data Wait ..");
        dialog.show();

        Observable
                .fromCallable(() -> DBConnection.getConnection(activity)
                        .getDataBaseDao()
                        .InsertApprovedDPRDetails(approvedDPRTblList))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    dialog.dismiss();
                    onNavigateToNext(viewModel);
                }, error -> {
                    error.printStackTrace();
                    dialog.dismiss();
                });
    }

    @Override
    public void onNavigateToNext(MainActivityViewModel viewModel){
        Intent intent = new Intent(activity, SelectActivity.class);
        intent.putExtra("districtCode", ""+viewModel.districtCode.get());
        intent.putExtra("districtName", ""+viewModel.districtName.get());
        intent.putExtra("talukCode", ""+viewModel.talukCode.get());
        intent.putExtra("talukName", ""+viewModel.talukName.get());
        intent.putExtra("hobliCode", ""+viewModel.hobliCode.get());
        intent.putExtra("hobliName", ""+viewModel.hobliName.get());
        intent.putExtra("villageCode", ""+viewModel.villageCode.get());
        intent.putExtra("LGD_VILLAGE_CODE", ""+viewModel.LGD_VILLAGE_CODE.get());
        intent.putExtra("villageName", ""+viewModel.villageName.get());
        activity.startActivity(intent);
    }
}
