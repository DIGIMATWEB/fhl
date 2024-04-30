package com.fhl.sistemadedistribucionfh.evidence.photos.model;

import android.graphics.Bitmap;

import java.util.List;

public class bitmapArange {
    private Bitmap imageBitmap;
    private Integer position;
            public bitmapArange(Bitmap imageBitmap,Integer position){
                this.imageBitmap=imageBitmap;
                this.position=position;
            }

    public Bitmap getImageBitmap() {
        return imageBitmap;
    }

    public void setImageBitmap(Bitmap imageBitmap) {
        this.imageBitmap = imageBitmap;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}
