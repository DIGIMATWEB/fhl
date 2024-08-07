package com.fhl.sistemadedistribucionfh.mlkit;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.barcode.BarcodeScanner;
import com.google.mlkit.vision.barcode.BarcodeScannerOptions;
import com.google.mlkit.vision.barcode.BarcodeScanning;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.common.InputImage;

import java.util.List;

public class BarcodeScannerProcessor extends VisionProcessorBase<List<Barcode>> {

    private static final String TAG = "BarcodeProcessor";
    private static final String MANUAL_TESTING_LOG = "BarcodeProcessor_LOG";
    private final BarcodeScanner barcodeScanner;
    private ExchangeScannedData exchangeScannedData;

    public BarcodeScannerProcessor(Context context, ExchangeScannedData exchangeScannedData) {
        super(context);

        // Comment this code if you want to allow open Barcode format.
        BarcodeScannerOptions options = new BarcodeScannerOptions.Builder()
                .setBarcodeFormats(Barcode.FORMAT_CODE_128, Barcode.FORMAT_QR_CODE, Barcode.FORMAT_CODE_39)
                .build();

        barcodeScanner = BarcodeScanning.getClient(options);
        this.exchangeScannedData = exchangeScannedData;
    }

    @Override
    public void stop() {
        super.stop();
        barcodeScanner.close();
    }

    @Override
    protected Task<List<Barcode>> detectInImage(InputImage image) {
        return barcodeScanner.process(image);
    }

    @Override
    protected void onSuccess(@NonNull List<Barcode> barcodes, @NonNull GraphicOverlay graphicOverlay) {
        if (barcodes.isEmpty()) {
            // Log.v(MANUAL_TESTING_LOG, "No barcode has been detected");
            return;
        }

        PointF graphicOverlayCenter = getGraphicOverlayCenter(graphicOverlay);

        for (Barcode barcode : barcodes) {
            graphicOverlay.add(new BarcodeGraphic(graphicOverlay, barcode));

            if (barcode != null && barcode.getRawValue() != null && !barcode.getRawValue().isEmpty()) {
                Rect boundingBox = barcode.getBoundingBox();

                if (boundingBox != null) {
                    PointF centerPoint = getCenterPoint(boundingBox);
                    exchangeScannedData.sendScannedCodewithBounding(barcode.getRawValue(), boundingBox);
                } else {
                    exchangeScannedData.sendScannedCode(barcode.getRawValue());
                }
            }
        }
    }

    private PointF getCenterPoint(Rect boundingBox) {
        float centerX = boundingBox.centerX();
        float centerY = boundingBox.centerY();
        return new PointF(centerX, centerY);
    }

    private PointF getGraphicOverlayCenter(GraphicOverlay graphicOverlay) {
        int centerX = graphicOverlay.getWidth() / 2;
        int centerY = graphicOverlay.getHeight() / 2;
        return new PointF(centerX, centerY);
    }

    @Override
    protected void onFailure(@NonNull Exception e) {
        Log.e(TAG, "Barcode detection failed " + e);
    }

}