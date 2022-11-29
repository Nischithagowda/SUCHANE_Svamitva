package com.bmc.suchane_svamitva.view.interfaces;

import com.bmc.suchane_svamitva.model.DocumentTbl;

public interface DocumentListInterface {
    void onClickViewPDF(DocumentTbl documentTbl);
    void onClickDeletePDF(DocumentTbl documentTbl);
}
