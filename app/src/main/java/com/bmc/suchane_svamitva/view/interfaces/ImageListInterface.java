package com.bmc.suchane_svamitva.view.interfaces;

import android.widget.CompoundButton;

import com.bmc.suchane_svamitva.model.ImageTempTbl;
import com.bmc.suchane_svamitva.model.OwnerTbl;

public interface ImageListInterface {
    void onCheckedChangeListener(ImageTempTbl dataVariable, int position, boolean isChecked);
}
