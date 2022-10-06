package com.bmc.suchane_svamitva.view.callbacks;

import android.content.Intent;
import android.os.Bundle;

import com.bmc.suchane_svamitva.database.DBConnection;
import com.bmc.suchane_svamitva.model.District;
import com.bmc.suchane_svamitva.model.Hobli;
import com.bmc.suchane_svamitva.model.Taluka;
import com.bmc.suchane_svamitva.model.UserLatLon;
import com.bmc.suchane_svamitva.model.Village;
import com.bmc.suchane_svamitva.utils.Constant;
import com.bmc.suchane_svamitva.view.interfaces.NoticeActivityInterface;
import com.bmc.suchane_svamitva.view.ui.NoticeActivity;
import com.bmc.suchane_svamitva.view_model.NoticeActivityViewModel;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NoticeActivityCallback implements NoticeActivityInterface {
    NoticeActivity activity;

    public NoticeActivityCallback(NoticeActivity activity) {
        this.activity = activity;
    }

    @Override
    public void loadData(NoticeActivityViewModel viewModel){
        Intent intent = activity.getIntent();
        Bundle args = intent.getBundleExtra(Constant.BUNDLE_DATA);
        UserLatLon address = (UserLatLon) args.getSerializable(Constant.LOCATION_DATA);
        viewModel.ownerLat.set(""+address.getLatitude());
        viewModel.ownerLong.set(""+address.getLongitude());
        viewModel.noticeNumber.set(address.getNoticeNo());
        viewModel.addressCode.set(address.getAddressCode());
        String adr=address.getAddress();
        String[] adrs =adr.split(",");
        if(adrs.length>0){
            viewModel.doorNo.set(adrs[0].trim());
        }
        if(adrs.length>1){
            viewModel.building.set(adrs[1].trim());
        }
        if(adrs.length>2){
            viewModel.street.set(adrs[2].trim());
        }
    }

    @Override
    public void getUserDistrict(NoticeActivityViewModel viewModel){

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
    public void getUserTaluk(NoticeActivityViewModel viewModel){

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
    public void getHobli(NoticeActivityViewModel viewModel){
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
    public void getVillage(NoticeActivityViewModel viewModel){

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
                    }
                }, error -> error.printStackTrace());
    }
}
