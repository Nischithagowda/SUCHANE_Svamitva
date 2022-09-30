package com.bmc.suchane_svamitva.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.bmc.suchane_svamitva.BR;

public class Item extends BaseObservable {
    private int selectedItemPosition;

    @Bindable
    public int getSelectedItemPosition() {
        return selectedItemPosition;
    }

    public void setSelectedItemPosition(int selectedItemPosition) {
        this.selectedItemPosition = selectedItemPosition;
        notifyPropertyChanged(BR.selectedItemPosition);
    }
}
