package com.fhl.sistemadedistribucionfh.mlkit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import com.fhl.sistemadedistribucionfh.Dialogs.EmpaquesValidador.validadorEmpaques;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.ticketsSalida.model.ticketsScanned;
import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.databinding.ActivityBarcodeScannerBinding;
import com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus.Paquete;
import com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus.dataTicketsDetailsendtrip;
import com.google.mlkit.common.MlKitException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BarcodeScannerActivity2 extends AppCompatActivity
        implements ActivityCompat.OnRequestPermissionsResultCallback, ExchangeScannedData, View.OnClickListener {

    private static final String TAG = "BarcodeScannerActivity2";
    private static final int PERMISSION_REQUESTS = 1;

    private ActivityBarcodeScannerBinding binding;
    private MediaPlayer mediaPlayer;
    @Nullable
    private ProcessCameraProvider cameraProvider;
    @Nullable
    private Preview previewUseCase;
    @Nullable
    private ImageAnalysis analysisUseCase;
    @Nullable
    private VisionImageProcessor imageProcessor;
    private boolean needUpdateGraphicOverlayImageSourceInfo;

    private int lensFacing = CameraSelector.LENS_FACING_BACK;
    private CameraSelector cameraSelector;

    private static final String STATE_SELECTED_MODEL = "selected_model";
    private static final String STATE_LENS_FACING = "lens_facing";

    public String typeScanner="";
    private List<dataTicketsDetailsendtrip> data;
    private String currentManifest;
    private validadorEmpaques bottonSheetv;
    private  List<Paquete> lotes=new ArrayList<>();
    private List<ticketsScanned> fresult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mediaPlayer=new MediaPlayer();

        binding = ActivityBarcodeScannerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mediaPlayer= MediaPlayer.create(getApplicationContext(), R.raw.beep);
        Bundle bundle;
        bundle = getIntent().getExtras();//detailOrderB
        if(bundle!=null) {
            data = (List<dataTicketsDetailsendtrip>) bundle.getSerializable("dataTcikets");
            currentManifest = bundle.getString("currentManifest");
            fresult=(List<ticketsScanned>)bundle.getSerializable("lotes");
        }

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
        Bundle fbundle = new Bundle();
        fbundle.putString("currentmanifest", currentManifest);
        fbundle.putString("typeScanner", "Lotes");
        fbundle.putSerializable("tickets",(Serializable) data);
        if(fresult!=null){
            fbundle.putSerializable("lotes",(Serializable) fresult);
        }

        bottonSheetv = new validadorEmpaques();
        bottonSheetv.setArguments(fbundle);
        bottonSheetv.show(getSupportFragmentManager(), "validadorEmpaques");
        //Paquete
        if(data!=null){
            data.get(0).getFolioTicket();//string
            data.get(0).getSendtripPlus().getPaquetes().size();//numero de paquetes
            lotes.clear(); ;
            for(Paquete packages:data.get(0).getSendtripPlus().getPaquetes()){
                lotes.add(packages);//referencia
            }
        }
    }

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
        cameraProvider.bindToLifecycle(/* lifecycleOwner= */ BarcodeScannerActivity2.this, cameraSelector, previewUseCase);
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
            //Log.e(TAG, "Can not create image processor.", e);
            // Toast.makeText(getApplicationContext(),"Can not create image processor: " + e.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
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

    @Override
    public void sendScannedCode(String code) {
        Handler handler = new Handler(Looper.getMainLooper());

        handler.post(new Runnable() {
            @Override
            public void run() {

                if (code != null && !code.isEmpty()) {
                    barcodesCollection(code);
                    binding.resultContainer.setVisibility(View.VISIBLE);
                    Log.e("codescann",""+code);
                }
            }
        });
    }

    public void restartCameraProcess() {
        if (allPermissionsGranted()) {
            bindAllCameraUseCases();
            if(typeScanner.equals("Salida")) {


            }else if(typeScanner.equals("Validador")){

            }else{

            }

        } else {
            getRuntimePermissions();
        }
    }
    public void returnResult(List<ticketsScanned> result) {
        Intent intent = new Intent();
        intent.putExtra("result_key",(Serializable) result);
        setResult(this.RESULT_OK, intent);
        finish();
    }

    private void barcodesCollection(String code)
    {
        Log.e("qrs",code);
        mediaPlayer.start();
        stopCameraProcess();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                restartCameraProcess();
                for(Paquete pac:lotes){
                    if(code.equals(pac.getNombre())){
                        bottonSheetv.sendToast(code);
                    }
                }
//                if(code.equals("700")){
//                    bottonSheetv.sendToast("700");
//                  //  returnResult("1234");
//                    // onBackPressed();
//                }
            }
        }, 1500);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iconchecklist:

                    break;

        }
    }

}
