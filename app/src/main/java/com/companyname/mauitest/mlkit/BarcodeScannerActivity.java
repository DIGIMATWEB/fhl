package com.companyname.mauitest.mlkit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
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

import com.google.mlkit.common.MlKitException;
import com.companyname.mauitest.Dialogs.employeBottomSheet;
import com.companyname.mauitest.Dialogs.manifestBottomSheet;
import com.companyname.mauitest.Dialogs.vehicleBottomSheet;
import com.companyname.mauitest.R;
import com.companyname.mauitest.databinding.ActivityBarcodeScannerBinding;

import java.util.ArrayList;
import java.util.List;

public class BarcodeScannerActivity extends AppCompatActivity
        implements ActivityCompat.OnRequestPermissionsResultCallback, ExchangeScannedData, View.OnClickListener {

    private static final String TAG = "BarcodeScannerActivity";
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
    public static List<String> collectedBarCodes=new ArrayList<>();
    private  String lastCode="";
    public static  String gotoListBarcode;
   // private BottomSheetBehavior bottomSheetBehavior;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mediaPlayer=new MediaPlayer();

//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

//       this.getWindow().getDecorView().setSystemUiVisibility(
//                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
//                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
//                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
        mediaPlayer= MediaPlayer.create(getApplicationContext(), R.raw.beep);

        Log.d(TAG, "onCreate");

        if (savedInstanceState != null) {
            lensFacing = savedInstanceState.getInt(STATE_LENS_FACING, CameraSelector.LENS_FACING_BACK);
        }
        cameraSelector = new CameraSelector.Builder().requireLensFacing(lensFacing).build();

        binding = ActivityBarcodeScannerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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
        binding.inputmanual.setOnClickListener(this);
        binding.inputcamara.setOnClickListener(this);
        binding.iconchecklist.setOnClickListener(this);
        if(collectedBarCodes.isEmpty()) {

        }else
        {
            binding.cardviewnumber.setVisibility(View.VISIBLE);
            binding.textdimens.setText(String.valueOf(collectedBarCodes.size()));

        }

        binding.escribircodigo.setInputType(InputType.TYPE_CLASS_NUMBER);
        binding.escribircodigo.setFilters(new InputFilter[]{new InputFilter.LengthFilter(7)});
        binding.escribircodigo.setOnEditorActionListener(new TextView.OnEditorActionListener() {


            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    String textleng= binding.escribircodigo.getText().toString();
                    if(textleng.length()<7) {
                        Toast.makeText(getApplicationContext(), "el numero de digitos es incorrecto intenta de nuevo", Toast.LENGTH_SHORT).show();
                        binding.escribircodigo.setText("");
                    }else {

                        binding.escribircodigo.setText("");
                        /** if(!collectedBarCodes.contains(String.valueOf(textleng))) {
                         //TODO volver a poner
                         if (MensajeroenProceso.barcodes.contains(String.valueOf(textleng))) {
                         collectedBarCodes.add(String.valueOf(textleng));
                         mediaPlayer.start();
                         Toast.makeText(getApplicationContext(), "Codigo Agregado", Toast.LENGTH_SHORT).show();
                         if (!collectedBarCodes.isEmpty()) {
                         binding.cardviewnumber.setVisibility(View.VISIBLE);
                         binding.textdimens.setText(String.valueOf(collectedBarCodes.size()));
                         }
                         }
                         }else{
                         Toast.makeText(getApplicationContext(), "Codigo Incorrecto", Toast.LENGTH_SHORT).show();
                         }*/
                    }

                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(),
                            InputMethodManager.RESULT_UNCHANGED_SHOWN);
                    return true;
                }
                return false;
            }
        });
       /* bottomSheetBehavior =BottomSheetBehavior.from( binding.bottomSheetZones);
       // bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_DRAGGING:

                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:

                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });*/
        }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt(STATE_LENS_FACING, lensFacing);
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
        cameraProvider.bindToLifecycle(/* lifecycleOwner= */ BarcodeScannerActivity.this, cameraSelector, previewUseCase);
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
            Log.e(TAG, "Can not create image processor.", e);
            Toast.makeText(
                            getApplicationContext(),
                            "Can not create image processor: " + e.getLocalizedMessage(),
                            Toast.LENGTH_LONG)
                    .show();
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
                        //            .show();
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
                    lastCode=code;
                }
            }
        });
    }

    private void barcodesCollection(String code)
    {
        if(collectedBarCodes.contains(code))
        {

        }else
        {

            Log.e("folios","codigos escaneados: "+collectedBarCodes);
            //   if(MensajeroenProceso.barcodes.contains(code)) {
            collectedBarCodes.add(code);
            binding.barcodeRawValue.setText(code);
            mediaPlayer.start();
            if(!collectedBarCodes.isEmpty())
            {
                binding.cardviewnumber.setVisibility(View.VISIBLE);
                binding.textdimens.setText(String.valueOf( collectedBarCodes.size()));
            }
            if(code.equals("123456"))
            {
                Toast.makeText(this, "Vehiculo", Toast.LENGTH_SHORT).show();
                new vehicleBottomSheet().show(getSupportFragmentManager(),"dialogBottomSheet");
            }else if(code.equals("1234567"))
            {
                Toast.makeText(this, "empleado", Toast.LENGTH_SHORT).show();
                new employeBottomSheet().show(getSupportFragmentManager(),"employeBottomSheet");
            }else if(code.equals("12345678"))
            {
                Toast.makeText(this, "manifiesto", Toast.LENGTH_SHORT).show();
                new manifestBottomSheet().show(getSupportFragmentManager(),"manifestBottomSheet");
            }
            //showDialog();
            //    }
            // else
            //   {
            //     binding.barcodeRawValue.setText("Codigo no permitido");
            // }
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //Intent intent = new Intent(this, menuViewImpl.class);
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //startActivity(intent);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.inputmanual:// esto es cuando se introduce el codigo de foma manual
                imageProcessor.stop();//esto detiene el proceso de escaneo
                if( binding.inputkeyscode.getVisibility()==View.VISIBLE)
                {

                }else {

                    binding.inputkeyscode.setVisibility(View.VISIBLE);
                    binding.inputcamara.setImageDrawable(getResources().getDrawable( R.drawable.ic_icon));
                    binding.inputmanual.setImageDrawable(getResources().getDrawable( R.drawable.ic_icon));
                    binding.iconchecklist.setImageDrawable(getResources().getDrawable( R.drawable.ic_icon));
                    binding.headerText.setTextColor(Color.BLACK);
                    binding.resultContainer.setVisibility(View.GONE);
                    binding.barcodeimage.setVisibility(View.VISIBLE);
                }
                break;
            case  R.id.inputcamara:// esto es cuando se introduce el codigo de con la camara
                bindAllCameraUseCases();//esto retoma el proceso de escaneo
                if( binding.inputkeyscode.getVisibility()==View.VISIBLE)
                {
                    binding.inputkeyscode.setVisibility(View.GONE);
                    binding.inputcamara.setImageDrawable(getResources().getDrawable( R.drawable.ic_icon));
                    binding.inputmanual.setImageDrawable(getResources().getDrawable( R.drawable.ic_icon));
                    binding.iconchecklist.setImageDrawable(getResources().getDrawable( R.drawable.ic_icon));
                    binding.headerText.setTextColor(Color.WHITE);
                    binding.resultContainer.setVisibility(View.VISIBLE);
                    binding.barcodeimage.setVisibility(View.GONE);
                }else {

                }
                break;
            case R.id.iconchecklist:
                //Toast.makeText( getApplicationContext(), "vamos ala pantalla de recycler", Toast.LENGTH_SHORT).show();
                if(collectedBarCodes.isEmpty())
                {
                    Toast.makeText(this, "No hay paquetes escaneados", Toast.LENGTH_SHORT).show();
                }else {
                    gotoListBarcode = "ready";
                   // Intent intent = new Intent(this, menuViewImpl.class);
                   // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    //startActivity(intent);
                    break;
                }
        }
    }
}