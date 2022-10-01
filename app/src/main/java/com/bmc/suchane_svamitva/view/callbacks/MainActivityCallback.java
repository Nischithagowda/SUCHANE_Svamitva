package com.bmc.suchane_svamitva.view.callbacks;
//
// Created by Nischitha on 22-09-2022.
// Copyright (c) 2022 BMC. All rights reserved.
//

import static android.content.Context.MODE_PRIVATE;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.bmc.suchane_svamitva.R;
import com.bmc.suchane_svamitva.api.APIClient_Suchane;
import com.bmc.suchane_svamitva.api.API_Interface_Suchane;
import com.bmc.suchane_svamitva.database.DBConnection;
import com.bmc.suchane_svamitva.model.District;
import com.bmc.suchane_svamitva.model.Hobli;
import com.bmc.suchane_svamitva.model.HobliRequest;
import com.bmc.suchane_svamitva.model.HobliResponse;
import com.bmc.suchane_svamitva.model.Taluka;
import com.bmc.suchane_svamitva.model.USER_DETAILS;
import com.bmc.suchane_svamitva.model.Village;
import com.bmc.suchane_svamitva.model.VillageRequest;
import com.bmc.suchane_svamitva.model.VillageResponse;
import com.bmc.suchane_svamitva.view.interfaces.MainActivityInterface;
import com.bmc.suchane_svamitva.view.ui.MainActivity;
import com.bmc.suchane_svamitva.view.ui.NoticeMapsFragment;
import com.bmc.suchane_svamitva.view.ui.SelectActivity;
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
                        District district = new District();
                        district.setDISTRICT_CODE("0");
                        district.setDISTRICT_NAME("Select District");
                        result.add(0, district);
                        viewModel.districtNameList.addAll(result);

                    } else {
                        District district = new District();
                        district.setDISTRICT_CODE("0");
                        district.setDISTRICT_NAME("Select District");
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
                        Taluka taluka = new Taluka();
                        taluka.setDISTRICT_CODE("0");
                        taluka.setTALUKA_CODE("0");
                        taluka.setTALUKA_NAME("Select Taluk");
                        result.add(0, taluka);
                        viewModel.talukNameList.addAll(result);

                    } else {
                        Taluka taluka = new Taluka();
                        taluka.setDISTRICT_CODE("0");
                        taluka.setTALUKA_CODE("0");
                        taluka.setTALUKA_NAME("Select Taluk");
                        viewModel.talukNameList.add(taluka);
                        Toast.makeText(activity, "Select District", Toast.LENGTH_SHORT).show();
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
                        Hobli hobli = new Hobli();
                        hobli.setDISTRICT_CODE("0");
                        hobli.setTALUKA_CODE("0");
                        hobli.setHOBLI_CODE("0");
                        hobli.setHOBLI_NAME("Select Hobli");
                        result.add(0, hobli);
                        viewModel.hobliNameList.addAll(result);

                    } else {
                        Hobli hobli = new Hobli();
                        hobli.setDISTRICT_CODE("0");
                        hobli.setTALUKA_CODE("0");
                        hobli.setHOBLI_CODE("0");
                        hobli.setHOBLI_NAME("Select Hobli");
                        viewModel.hobliNameList.add(hobli);
                        Toast.makeText(activity, "Select Taluk", Toast.LENGTH_SHORT).show();
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
                        Village village = new Village();
                        village.setDISTRICT_CODE("0");
                        village.setTALUK_CODE("0");
                        village.setHOBLI_CODE("0");
                        village.setVILLAGE_CODE("0");
                        village.setVILLAGE_NAME("Select Village");
                        result.add(0, village);
                        viewModel.villageNameList.addAll(result);

                    } else {
                        Village village = new Village();
                        village.setDISTRICT_CODE("0");
                        village.setTALUK_CODE("0");
                        village.setHOBLI_CODE("0");
                        village.setVILLAGE_CODE("0");
                        village.setVILLAGE_NAME("Select Village");
                        viewModel.villageNameList.add(village);
                        //Toast.makeText(activity, "Select Hobli", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(activity, "Please Switch on the Internet", Toast.LENGTH_SHORT).show();
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
                            Village village = new Village();
                            village.setDISTRICT_CODE("0");
                            village.setTALUK_CODE("0");
                            village.setHOBLI_CODE("0");
                            village.setVILLAGE_CODE("0");
                            village.setVILLAGE_NAME("Select Village");
                            villageList.add(0, village);
                            viewModel.villageNameList.addAll(villageList);
                        }, error -> {
                            error.printStackTrace();
                            dialog.dismiss();
                        }
                );
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    @Override
    public void onNavigateToNext(MainActivityViewModel viewModel){
        Intent intent = new Intent(activity, SelectActivity.class);
        intent.putExtra("districtCode", ""+viewModel.districtCode);
        intent.putExtra("districtName", ""+viewModel.districtName);
        intent.putExtra("talukCode", ""+viewModel.talukCode);
        intent.putExtra("talukName", ""+viewModel.talukName);
        intent.putExtra("hobliCode", ""+viewModel.hobliCode);
        intent.putExtra("hobliName", ""+viewModel.hobliName);
        intent.putExtra("villageCode", ""+viewModel.villageCode);
        intent.putExtra("villageName", ""+viewModel.villageName);
        activity.startActivity(intent);
    }
}
