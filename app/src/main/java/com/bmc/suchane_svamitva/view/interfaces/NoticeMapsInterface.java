package com.bmc.suchane_svamitva.view.interfaces;

import com.bmc.suchane_svamitva.view_model.NoticeMapsViewModel;

public interface NoticeMapsInterface {

    void showMap(NoticeMapsViewModel viewModel);
    void loadMap(NoticeMapsViewModel viewModel);
    void loadAddress(NoticeMapsViewModel viewModel);
    void onConfirmLocation(NoticeMapsViewModel viewModel);
    void onNavigateToNotice();
}
