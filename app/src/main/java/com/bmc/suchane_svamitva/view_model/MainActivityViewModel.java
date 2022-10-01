package com.bmc.suchane_svamitva.view_model;
//
// Created by Nischitha on 22-09-2022.
// Copyright (c) 2022 BMC. All rights reserved.
//

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.bmc.suchane_svamitva.model.District;
import com.bmc.suchane_svamitva.model.Hobli;
import com.bmc.suchane_svamitva.model.Taluka;
import com.bmc.suchane_svamitva.model.Village;
import com.bmc.suchane_svamitva.view.interfaces.MainActivityInterface;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.databinding.ObservableList;

public class MainActivityViewModel {
    MainActivityInterface mainActivityInterface;
    public final ObservableList<District> districtNameList = new ObservableArrayList<>();
    public final ObservableInt selectedItemPosition = new ObservableInt(0);
    public final ObservableField<String> districtCode = new ObservableField<>(null);
    public final ObservableField<String> districtError = new ObservableField<>(null);
    public final ObservableField<String> districtName = new ObservableField<>("");
    public final ObservableList<Taluka> talukNameList = new ObservableArrayList<>();
    public final ObservableField<String> talukCode = new ObservableField<>("");
    public final ObservableField<String> talukError = new ObservableField<>(null);
    public final ObservableField<String> talukName = new ObservableField<>("");
    public final ObservableList<Hobli> hobliNameList = new ObservableArrayList<>();
    public final ObservableField<String> hobliName = new ObservableField<>("");
    public final ObservableField<String> hobliError = new ObservableField<>(null);
    public final ObservableField<String> hobliCode = new ObservableField<>("");
    public final ObservableList<Village> villageNameList = new ObservableArrayList<>();
    public final ObservableField<String> villageName = new ObservableField<>("");
    public final ObservableField<String> villageError = new ObservableField<>(null);
    public final ObservableField<String> villageCode = new ObservableField<>("");

    public MainActivityViewModel(MainActivityInterface mainActivityInterface) {
        this.mainActivityInterface = mainActivityInterface;
        mainActivityInterface.getUserDistrict(this);
    }

    public void onSelectDistrictItem(AdapterView<?> parent, View view, int pos, long id)
    {
        this.districtCode.set(districtNameList.get(pos).getDISTRICT_CODE());
        this.districtName.set(districtNameList.get(pos).getDISTRICT_NAME());
//        Log.d("getDISTRICT_CODE", ""+districtNameList.get(pos).getDISTRICT_CODE());
//        Log.d("getDISTRICT_NAME", ""+districtNameList.get(pos).getDISTRICT_NAME());
//        Log.d("getItem", ""+parent.getAdapter().getItem(pos));
//        Log.d("parent.getSelectedItem", ""+parent.getSelectedItem());

        mainActivityInterface.getUserTaluk(this);

    }

    public void onSelectTalukItem(AdapterView<?> parent, View view, int pos, long id)
    {
        this.talukCode.set(talukNameList.get(pos).getTALUKA_CODE());
        this.talukName.set(talukNameList.get(pos).getTALUKA_NAME());
//        Log.d("getDISTRICT_CODE", ""+talukNameList.get(pos).getDISTRICT_CODE());
//        Log.d("getTALUKA_CODE", ""+talukNameList.get(pos).getTALUKA_CODE());
//        Log.d("getDISTRICT_NAME", ""+talukNameList.get(pos).getTALUKA_NAME());
//        Log.d("getItem", ""+parent.getAdapter().getItem(pos));
//        Log.d("parent.getSelectedItem", ""+parent.getSelectedItem());
        mainActivityInterface.getHobli(this);
    }

    public void onSelectHobliItem(AdapterView<?> parent, View view, int pos, long id)
    {
        this.hobliCode.set(hobliNameList.get(pos).getHOBLI_CODE());
        this.hobliName.set(hobliNameList.get(pos).getHOBLI_NAME());
        mainActivityInterface.getVillage(this);
    }

    public void onSelectVillageItem(AdapterView<?> parent, View view, int pos, long id)
    {
        this.villageCode.set(villageNameList.get(pos).getVILLAGE_CODE());
        this.villageName.set(villageNameList.get(pos).getVILLAGE_NAME());
    }

    public void onClickFetchData(View view) {
        String distCode = this.districtCode.get();
        String talukCode = this.talukCode.get();
        String hobliCode = this.hobliCode.get();
        String villCode = this.villageCode.get();

        boolean status = false;
        if (TextUtils.isEmpty(distCode)) {
            status = true;
            Toast.makeText(view.getContext(), "District is required", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(talukCode)) {
            status = true;
            Toast.makeText(view.getContext(), "Taluk is required", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(hobliCode)) {
            status = true;
            Toast.makeText(view.getContext(), "Hobli is required", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(villCode)) {
            status = true;
            Toast.makeText(view.getContext(), "Village is required", Toast.LENGTH_SHORT).show();
        }

        if (!status) {
            mainActivityInterface.onNavigateToNext(this);
        }

    }
}
