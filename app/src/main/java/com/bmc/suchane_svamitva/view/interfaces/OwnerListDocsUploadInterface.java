package com.bmc.suchane_svamitva.view.interfaces;

import android.view.View;

import com.bmc.suchane_svamitva.model.OwnerTbl;

public interface OwnerListDocsUploadInterface {
    void onClickOwner(int position, OwnerTbl ownerTbl, String noticeNo, View view);
}
