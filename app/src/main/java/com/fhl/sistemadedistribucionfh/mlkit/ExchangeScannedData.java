package com.fhl.sistemadedistribucionfh.mlkit;

import android.graphics.Rect;

public interface ExchangeScannedData {
    public void sendScannedCode(String code);

    void sendScannedCodewithBounding(String rawValue, Rect boundingBox);
}
