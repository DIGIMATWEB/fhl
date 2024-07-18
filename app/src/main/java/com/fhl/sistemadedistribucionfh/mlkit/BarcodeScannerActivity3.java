package com.fhl.sistemadedistribucionfh.mlkit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraControl;
import androidx.camera.core.CameraInfo;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import com.fhl.sistemadedistribucionfh.Dialogs.Planeacion.view.validadorPlaneacion;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.ErrorSalida.errorRecepcion;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.ticketsSalida.model.ticketsScanned;
import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.databinding.ActivityBarcodeRecepcionBinding;
import com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus.Paquete;
import com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus.dataTicketsDetailsendtrip;
import com.fhl.sistemadedistribucionfh.mlkit.model.ScannedCode;
import com.google.mlkit.common.MlKitException;

import java.util.ArrayList;
import java.util.List;

public class BarcodeScannerActivity3 extends AppCompatActivity
        implements ActivityCompat.OnRequestPermissionsResultCallback, ExchangeScannedData, View.OnClickListener {
    private static final String TAG = "BarcodeScannerActivity3";
    private static final int PERMISSION_REQUESTS = 1;

    private @NonNull ActivityBarcodeRecepcionBinding binding;//needed
    private MediaPlayer mediaPlayer; //needed
    @Nullable
    private ProcessCameraProvider cameraProvider;//needed
    @Nullable
    private Preview previewUseCase;//needed
    @Nullable
    private ImageAnalysis analysisUseCase;//needed
    @Nullable
    private VisionImageProcessor imageProcessor;//needed
    private boolean needUpdateGraphicOverlayImageSourceInfo;//needed

    private int lensFacing = CameraSelector.LENS_FACING_BACK;//needed
    private CameraSelector cameraSelector;//needed

    private static final String STATE_SELECTED_MODEL = "selected_model";//needed
    private static final String STATE_LENS_FACING = "lens_facing";//needed

   // public String typeScanner="";
    private List<dataTicketsDetailsendtrip> data;//no needed
    private String currentManifest;//no needed
    private validadorPlaneacion bottonSheetv;//no needed
    private  List<Paquete> lotes=new ArrayList<>();//no needed
    private List<ticketsScanned> fresult;//no needed
    private errorRecepcion errorD;
    private boolean isTorchOn = false;
    private CameraControl cameraControl;
    private List<ScannedCode> scannedCodes = new ArrayList<>();
    private long startTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mediaPlayer=new MediaPlayer();
        binding = ActivityBarcodeRecepcionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mediaPlayer= MediaPlayer.create(getApplicationContext(), R.raw.beep);

        if (savedInstanceState != null) {
            lensFacing = savedInstanceState.getInt(STATE_LENS_FACING, CameraSelector.LENS_FACING_BACK);
        }
        cameraSelector = new CameraSelector.Builder().requireLensFacing(lensFacing).build();



        new ViewModelProvider(this, (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()))
                .get(CameraXViewModel.class)
                .getProcessCameraProvider()
                .observe(
                        this,
                        provider -> {
                            cameraProvider = provider;
                            if (allPermissionsGranted()) {
                                bindAllCameraUseCases();
                            }
                        });

        if (!allPermissionsGranted()) {
            getRuntimePermissions();
        }
        binding.inputmanual.setVisibility(View.GONE);
        binding.inputcamara.setVisibility(View.GONE);
//        binding.inputmanual.setOnClickListener(this);
//        binding.inputcamara.setOnClickListener(this);
//        binding.captureCode.setOnClickListener(this);
        binding.constraintLayout5.setVisibility(View.GONE);
        binding.constraintLayout6.setVisibility(View.GONE);
        binding.constraintLayout8.setVisibility(View.GONE);
       // binding.iconchecklist.setOnClickListener(this);
        bottonSheetv = new validadorPlaneacion();
        bottonSheetv.show(getSupportFragmentManager(), "validadorPlaneacion");

    }
//region simple config
    @Override
    protected void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt(STATE_LENS_FACING, lensFacing);
    }
    private void stopCameraProcess() {
        if (cameraProvider != null) {
            if (previewUseCase != null) {
                cameraProvider.unbind(previewUseCase);
            }
            if (analysisUseCase != null) {
                cameraProvider.unbind(analysisUseCase);
            }
            if (imageProcessor != null) {
                imageProcessor.stop();
            }
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        bindAllCameraUseCases();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (imageProcessor != null) {
            imageProcessor.stop();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (imageProcessor != null) {
            imageProcessor.stop();
        }
    }

    private void bindAllCameraUseCases() {
        bindPreviewUseCase();

        bindAnalysisUseCase();
    }

    private void bindPreviewUseCase() {

        if (cameraProvider == null) {
            return;
        }
        if (previewUseCase != null) {
            cameraProvider.unbind(previewUseCase);
        }

        previewUseCase = new Preview.Builder().build();
        previewUseCase.setSurfaceProvider(binding.previewView.getSurfaceProvider());
        try {
            Camera camera = cameraProvider.bindToLifecycle(this, cameraSelector, previewUseCase);
            CameraInfo cameraInfo = camera.getCameraInfo();
            cameraControl = camera.getCameraControl();
        } catch (Exception e) {
            Log.e(TAG, "Failed to bind camera use cases", e);
        }
    }
    public void toggleFlash() {
        if (cameraControl != null) {
            isTorchOn = !isTorchOn;
            cameraControl.enableTorch(isTorchOn);
        }
    }

    private void bindAnalysisUseCase() {
        if (cameraProvider == null) {
            return;
        }
        if (analysisUseCase != null) {
            cameraProvider.unbind(analysisUseCase);
        }
        if (imageProcessor != null) {
            imageProcessor.stop();
        }

        try {
            Log.i(TAG, "Using Barcode Detector Processor");
            imageProcessor = new BarcodeScannerProcessor(this, this);
        } catch (Exception e) {
            return;
        }

        ImageAnalysis.Builder builder = new ImageAnalysis.Builder();
        analysisUseCase = builder.build();

        needUpdateGraphicOverlayImageSourceInfo = true;
        analysisUseCase.setAnalyzer(
                // imageProcessor.processImageProxy will use another thread to run the detection underneath,
                // thus we can just runs the analyzer itself on main thread.
                ContextCompat.getMainExecutor(this),
                imageProxy -> {
                    if (needUpdateGraphicOverlayImageSourceInfo) {
                        boolean isImageFlipped = lensFacing == CameraSelector.LENS_FACING_FRONT;
                        int rotationDegrees = imageProxy.getImageInfo().getRotationDegrees();
                        if (rotationDegrees == 0 || rotationDegrees == 180) {
                            binding.graphicOverlay.setImageSourceInfo(
                                    imageProxy.getWidth(), imageProxy.getHeight(), isImageFlipped);
                        } else {
                            binding.graphicOverlay.setImageSourceInfo(
                                    imageProxy.getHeight(), imageProxy.getWidth(), isImageFlipped);
                        }
                        needUpdateGraphicOverlayImageSourceInfo = false;
                    }
                    try {
                        imageProcessor.processImageProxy(imageProxy, binding.graphicOverlay);
                    } catch (   MlKitException e) {
                        //      Log.e(TAG, "Failed to process image. Error: " + e.getLocalizedMessage());
                        Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

        cameraProvider.bindToLifecycle(/* lifecycleOwner= */ this, cameraSelector, analysisUseCase);
    }

    private String[] getRequiredPermissions() {
        try {
            PackageInfo info =
                    this.getPackageManager()
                            .getPackageInfo(this.getPackageName(), PackageManager.GET_PERMISSIONS);
            String[] ps = info.requestedPermissions;
            if (ps != null && ps.length > 0) {
                return ps;
            } else {
                return new String[0];
            }
        } catch (Exception e) {
            return new String[0];
        }
    }
    private boolean allPermissionsGranted() {
        for (String permission : getRequiredPermissions()) {
            if (!isPermissionGranted(this, permission)) {
                return false;
            }
        }
        return true;
    }

    private void getRuntimePermissions() {
        List<String> allNeededPermissions = new ArrayList<>();
        for (String permission : getRequiredPermissions()) {
            if (!isPermissionGranted(this, permission)) {
                allNeededPermissions.add(permission);
            }
        }
        if (!allNeededPermissions.isEmpty()) {
            ActivityCompat.requestPermissions(
                    this, allNeededPermissions.toArray(new String[0]), PERMISSION_REQUESTS);
        }
    }
    @Override
    public void onRequestPermissionsResult(
            int requestCode, String[] permissions, int[] grantResults) {
        Log.i(TAG, "Permission granted!");
        if (allPermissionsGranted()) {
            bindAllCameraUseCases();
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private static boolean isPermissionGranted(Context context, String permission) {
        if (ContextCompat.checkSelfPermission(context, permission)
                == PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG, "Permission granted: " + permission);
            return true;
        }
        Log.i(TAG, "Permission NOT granted: " + permission);
        return false;
    }
//endregion
    @Override
    public void sendScannedCode(String code) {

    }

    @Override
    public void sendScannedCodewithBounding(String code, Rect boundingBox) {
        if (scannedCodes.isEmpty()) {
            startTime = System.currentTimeMillis();
            new Handler().postDelayed(this::processScannedCodes, 4000);
        }

        // Convert bounding box to a center point
        PointF position = getCenterPoint(boundingBox);

        // Store the scanned code with its position and timestamp
        boolean codeExists = false;
        for (ScannedCode mcode : scannedCodes) {
            if (mcode.code.equals(code)) {
                codeExists = true;
                break;
            }
        }

        // If the code is not in the list, add it
        if (!codeExists) {
            scannedCodes.add(new ScannedCode(code, position, System.currentTimeMillis()));
        }
    }
    private PointF getCenterPoint(Rect boundingBox) {
        float centerX = boundingBox.centerX();
        float centerY = boundingBox.centerY();
        return new PointF(centerX, centerY);
    }
    private void processScannedCodes() {
        if (scannedCodes.isEmpty()) {
            return;
        }

        // Calculate the center of the camera view
        int viewWidth = binding.graphicOverlay.getWidth()/2;
        int viewHeight = binding.graphicOverlay.getHeight()/2;
        PointF centerPoint = new PointF(viewWidth / 2.0f, viewHeight / 2.0f);//getCodePosition();//

        // Find the code closest to the center of the view
        ScannedCode mostCenteredCode = null;
        float minDistance = Float.MAX_VALUE;
        for (ScannedCode scannedCode : scannedCodes) {
            float distance = calculateDistance(centerPoint, scannedCode.position);
             Log.e("fcenteredCode", "all " + scannedCode.code + " distance code " + scannedCode.position);
             Log.e("centeredCodea", "" + scannedCode.code); //+ " " + scannedCode.position.x+" "+scannedCode.position.y);
             Log.e("centeredCodex", "" + scannedCode.position.x);
             Log.e("centeredCodey", "" + scannedCode.position.y);
             Log.e("centeredCodef", "distance: " + distance+" min: "+minDistance);
            if (distance < minDistance) {

                minDistance = distance;
                mostCenteredCode = scannedCode;
            }
        }

        if (mostCenteredCode != null) {
            // Process the most centered code
            Log.e("centeredCodeff", "Most centered code: " + mostCenteredCode.code +" "+centerPoint);
            //Toast.makeText(this, "" + mostCenteredCode.code, Toast.LENGTH_SHORT).show();
            // You can handle the most centered code here, e.g., show it in the UI or trigger further processing
        }

        // Clear the scanned codes list for future scans
        scannedCodes.clear();
        mediaPlayer.start();
        stopCameraProcess();
        newCollection(mostCenteredCode.code);
        new Handler().postDelayed(this::restartCameraProcess, 1500);
    }
    private float calculateDistance(PointF p1, PointF p2) {//centerPoint, scannedCode.position
        //todo definir el orden de la operacion dependiendo de la posicion del punto
        float dx = p1.x - p2.x;
        float dy = p1.y - p2.y;
        return (float) Math.sqrt(dx * dx + dy * dy);
    }
    private PointF getCodePosition() {
        // You need to implement this method to return the position of the code in the view
        // For demonstration purposes, let's assume the center of the view
        int viewWidth = binding.previewView.getWidth();
        int viewHeight = binding.previewView.getHeight();
        return new PointF(viewWidth / 2.0f, viewHeight / 2.0f);
    }
    public void restartCameraProcess() {
        if (allPermissionsGranted()) {
            bindAllCameraUseCases();

        } else {
            getRuntimePermissions();
        }
    }
    public void returnResult(String result) {
        Toast.makeText(this, ""+result, Toast.LENGTH_SHORT).show();
    }

    private void newCollection(String code) {
        barcodesCollection(code);
        //        Handler handler = new Handler(Looper.getMainLooper());
//
//        handler.post(new Runnable() {
//            @Override
//            public void run() {
//
//                if (code != null && !code.isEmpty()) {
//                    barcodesCollection(code);
//                    binding.resultContainer.setVisibility(View.VISIBLE);
//                    Log.e("codescann",""+code);
//                }
//            }
//        });
    }

    public void barcodesCollection(String code)
    {
        Log.e("qrs",code);
        mediaPlayer.start();
        stopCameraProcess();
        bottonSheetv.sendToast(code);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
            restartCameraProcess();
            }
        }, 1500);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
    public void resetError(){
        errorD=null;
    }
    public void errorTicket() {
        stopCameraProcess();
        if(errorD==null) {
            errorD = new errorRecepcion();
            Bundle args = new Bundle();
            args.putString("error_value", "Este codigo ya fue escaneado o no corresponde a la secuencia");
            errorD.setArguments(args);
            errorD.show(getSupportFragmentManager(), "errorDialog");
        }
    }
    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.inputcamara:
                binding.inputkeyscode.setVisibility(View.GONE);
                binding.inputcamara.setBackgroundResource(R.drawable.ic_scannercam);
                binding.inputmanual.setBackgroundResource(R.drawable.ic_keys);
                binding.headerText.setTextColor(Color.WHITE);
                break;

            case R.id.inputmanual:
                //Toast.makeText(this, "Input manual", Toast.LENGTH_SHORT).show();
                binding.inputkeyscode.setVisibility(View.VISIBLE);
                binding.inputcamara.setBackgroundResource(R.drawable.icscannercamblack);
                binding.inputmanual.setBackgroundResource(R.drawable.ic_keys_black);
                binding.headerText.setTextColor(Color.BLACK);
                break;
            case R.id.captureCode:
                if(!binding.escribircodigo.getText().toString().equals("")) {
                    barcodesCollection(binding.escribircodigo.getText().toString());
                    binding.escribircodigo.setText("");
                    binding.inputkeyscode.setVisibility(View.GONE);
                }else{
                    Toast.makeText(this, "Debes resgistrar datos", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}