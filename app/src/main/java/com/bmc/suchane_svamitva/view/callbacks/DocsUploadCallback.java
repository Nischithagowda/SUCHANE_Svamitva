package com.bmc.suchane_svamitva.view.callbacks;

import android.app.ProgressDialog;
import android.content.Intent;
import android.widget.Toast;

import com.bmc.suchane_svamitva.R;
import com.bmc.suchane_svamitva.database.DBConnection;
import com.bmc.suchane_svamitva.view.interfaces.DocsUploadInterface;
import com.bmc.suchane_svamitva.view.ui.DPR_FPR_LaunchApprovedFragment;
import com.bmc.suchane_svamitva.view.ui.DPR_FPR_LaunchPendingFragment;
import com.bmc.suchane_svamitva.view.ui.DocsUploadActivity;
import com.bmc.suchane_svamitva.view_model.DPR_FPR_LaunchActivityViewModel;
import com.bmc.suchane_svamitva.view_model.DocsUploadViewModel;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DocsUploadCallback implements DocsUploadInterface {
    DocsUploadActivity activity;

    public DocsUploadCallback(DocsUploadActivity activity) {
        this.activity = activity;
    }

    @Override
    public void loadOwnerList(DocsUploadViewModel viewModel){
        ProgressDialog dialog = new ProgressDialog(activity);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.setMessage("Loading Data Wait ..");
        dialog.show();

        try {
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

            Observable
                    .fromCallable(() -> DBConnection.getConnection(activity)
                            .getDataBaseDao()
                            .getPendingDPRList(viewModel.LGD_VILLAGE_CODE.get()))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(result -> {
                                dialog.dismiss();
                                viewModel.ownerPendingList.clear();
                                viewModel.ownerPendingList.addAll(result);
                                if (result.size()<=0){
                                    viewModel.isNoPendingDataAvailable.set(true);
                                } else {
                                    viewModel.isNoPendingDataAvailable.set(false);
                                }
                            }, error -> {
                                error.printStackTrace();
                                dialog.dismiss();
                            }
                    );

            Observable
                    .fromCallable(() -> DBConnection.getConnection(activity)
                            .getDataBaseDao()
                            .getApprovedDPRDetails(viewModel.LGD_VILLAGE_CODE.get()))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(result -> {
                                dialog.dismiss();
                                viewModel.ownerApprovedList.clear();
                                viewModel.ownerApprovedList.addAll(result);
                                if (result.size()<=0){
                                    viewModel.isNoApprovedDataAvailable.set(true);
                                } else {
                                    viewModel.isNoApprovedDataAvailable.set(false);
                                }
                            }, error -> {
                                error.printStackTrace();
                                dialog.dismiss();
                            }
                    );


        } catch (Exception ex) {
            ex.printStackTrace();
            Toast.makeText(activity, ""+ex.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
