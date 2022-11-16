package com.bmc.suchane_svamitva.view.callbacks;

import android.app.ProgressDialog;
import android.content.Intent;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.bmc.suchane_svamitva.R;
import com.bmc.suchane_svamitva.database.DBConnection;
import com.bmc.suchane_svamitva.model.OwnerTbl;
import com.bmc.suchane_svamitva.view.interfaces.DocsUploadLaunchInterface;
import com.bmc.suchane_svamitva.view.ui.DocsUploadFinalActivity;
import com.bmc.suchane_svamitva.view.ui.DocsUploadLaunchCompletedFragment;
import com.bmc.suchane_svamitva.view.ui.DocsUploadLaunchPendingFragment;
import com.bmc.suchane_svamitva.view_model.DocsUploadLaunchViewModel;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DocsUploadLaunchCallback implements DocsUploadLaunchInterface {
    FragmentActivity activity;

    public DocsUploadLaunchCallback(FragmentActivity activity) {
        this.activity = activity;
    }

    @Override
    public void loadOwnerList(DocsUploadLaunchViewModel viewModel){
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

    @Override
    public void loadDefaultFragment() {
        androidx.fragment.app.FragmentTransaction fragmentTransaction =
                activity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.docs_upload_frame_layout, new DocsUploadLaunchPendingFragment());
        fragmentTransaction.commit();
    }

    @Override
    public void onTypeCheckChanged(DocsUploadLaunchViewModel viewModel, int checkedId) {
        if (checkedId == R.id.pending){
            androidx.fragment.app.FragmentTransaction fragmentTransaction1 =
                    activity.getSupportFragmentManager().beginTransaction();
            fragmentTransaction1.replace(R.id.docs_upload_frame_layout, new DocsUploadLaunchPendingFragment());
            fragmentTransaction1.commit();
            viewModel.isDocsUploadBtnVisible.set(true);
        } else if (checkedId == R.id.completed){
            androidx.fragment.app.FragmentTransaction fragmentTransaction2 =
                    activity.getSupportFragmentManager().beginTransaction();
            fragmentTransaction2.replace(R.id.docs_upload_frame_layout, new DocsUploadLaunchCompletedFragment());
            fragmentTransaction2.commit();
            viewModel.isDocsUploadBtnVisible.set(false);
        }
    }

    @Override
    public void onNavigateToDocsUploadFinal(DocsUploadLaunchViewModel viewModel, OwnerTbl ownerTbl, String NoticeNo) {
        Intent intent = new Intent(activity, DocsUploadFinalActivity.class);
        intent.putExtra("districtCode", ""+viewModel.districtCode.get());
        intent.putExtra("districtName", ""+viewModel.districtName.get());
        intent.putExtra("talukCode", ""+viewModel.talukCode.get());
        intent.putExtra("talukName", ""+viewModel.talukName.get());
        intent.putExtra("hobliCode", ""+viewModel.hobliCode.get());
        intent.putExtra("hobliName", ""+viewModel.hobliName.get());
        intent.putExtra("villageCode", ""+viewModel.villageCode.get());
        intent.putExtra("LGD_VILLAGE_CODE", ""+viewModel.LGD_VILLAGE_CODE.get());
        intent.putExtra("villageName", ""+viewModel.villageName.get());
        intent.putExtra("NoticeNo", ""+NoticeNo);
        activity.startActivity(intent);
    }
}
