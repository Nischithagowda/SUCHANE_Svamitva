package com.bmc.suchane_svamitva.view.interfaces;

import com.bmc.suchane_svamitva.model.OwnerTbl;
import com.bmc.suchane_svamitva.view_model.DocsUploadLaunchViewModel;

public interface DocsUploadLaunchInterface {
    void loadOwnerList(DocsUploadLaunchViewModel viewModel);
    void onNavigateToDocsUploadFinal(DocsUploadLaunchViewModel viewModel, OwnerTbl ownerTbl, String NoticeNo);
    void onTypeCheckChanged(DocsUploadLaunchViewModel viewModel, int checkedId);
    void loadDefaultFragment();
}
