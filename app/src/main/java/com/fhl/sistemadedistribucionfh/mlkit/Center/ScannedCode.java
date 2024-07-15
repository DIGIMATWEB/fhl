package com.fhl.sistemadedistribucionfh.mlkit.Center;

import android.graphics.PointF;

public class ScannedCode {
    public String code;
    public PointF position;
    long timestamp;

    public ScannedCode(String code, PointF position, long timestamp) {
        this.code = code;
        this.position = position;
        this.timestamp = timestamp;
    }
}
