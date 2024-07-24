package com.fhl.sistemadedistribucionfh.mlkit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
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
import androidx.camera.core.Camera;
import androidx.camera.core.CameraControl;
import androidx.camera.core.CameraInfo;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProvider;

import com.fhl.sistemadedistribucionfh.Dialogs.ErrorDialogs.errorCarga;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.escanearCodigosSalida;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.sellosSalida.sellosSalida;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.ticketsSalida.ticketsSalida;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.view.Salida;

import com.fhl.sistemadedistribucionfh.Dialogs.detailManifestTicketsSummary.Tickets.detailTicketsSummary;
import com.fhl.sistemadedistribucionfh.Dialogs.dialogCompleteValidador;
import com.fhl.sistemadedistribucionfh.Dialogs.completeSalida.dialogCompletedSalida;
import com.fhl.sistemadedistribucionfh.Dialogs.validador.ValidadorV2.model.dataValidadorV2;
import com.fhl.sistemadedistribucionfh.Dialogs.validador.ValidadorV2.view.validadorManifest;
import com.fhl.sistemadedistribucionfh.Dialogs.validador.escanearValidador;
import com.fhl.sistemadedistribucionfh.Retrofit.GeneralConstants;
import com.fhl.sistemadedistribucionfh.Sellos.model.Sello;
import com.fhl.sistemadedistribucionfh.mlkit.model.ScannedCode;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.modelV2.dataTicketsManifestV2;
import com.google.gson.Gson;
import com.google.mlkit.common.MlKitException;
import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.databinding.ActivityBarcodeScannerBinding;

import java.io.Serializable;
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
    public String typeScanner="";
    private Integer currentStatus=0;
    private String cortinaDestination,mQR,mcodigoAnden,currentmanifest;
    private  ticketsSalida botonsheettickets;
    private sellosSalida botonsheetsellos;
    private List<dataTicketsManifestV2> dataTickets;
    private List<Sello> dataSellos;
    private String vehiclebarcode,rfcBarcode;
    private String vehiclebarcodeVal,rfcBarcodeVal;
    private Integer claveVehicleID;
    private String codigoValidador="";
    private Boolean isMotorola=false;
    private boolean isTorchOn = false;
    private CameraControl cameraControl;
    private List<ScannedCode> scannedCodes = new ArrayList<>();
    private long startTime;
    private  errorCarga errorD2;
    private escanearValidador escanValidador;
    private escanearCodigosSalida   escanearSalida;
    private Boolean isBegin=true;
    private Boolean isErrorShowed=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mediaPlayer=new MediaPlayer();

        binding = ActivityBarcodeScannerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mediaPlayer= MediaPlayer.create(getApplicationContext(), R.raw.beep);
        Bundle bndl;
        bndl = getIntent().getExtras();//detailOrderB
            if(bndl!=null){
                typeScanner=bndl.getString("scannerType");
                if(typeScanner!=null){

                    binding.mtypeScanner.setText(typeScanner);
                    if (typeScanner.equals("Validador")) {
                        escanValidador =new escanearValidador();
                        escanValidador.show(getSupportFragmentManager(), "escanearValidador");
                        stopCameraProcess();
                    }else if(typeScanner.equals("Recolectar")){
                        isBegin=false;
                      Bundle bundle = new Bundle();
                      List<dataTicketsManifestV2> codigoValidador= (List<dataTicketsManifestV2>) bndl.getSerializable("tickets");
                        List<Sello> sellos =(List<Sello>) bndl.getSerializable("sellos");
                       currentmanifest=bndl.getString("manifest");
                        this.dataTickets=codigoValidador;
                      bundle.putSerializable("tickets", (Serializable) codigoValidador);
                      bundle.putSerializable("sellos", (Serializable) sellos);
                      bundle.putString("typeScanner",typeScanner);
                      bundle.putString("currentmanifest",currentmanifest);
                        botonsheettickets = new ticketsSalida();
                        botonsheettickets.setArguments(bundle);
                        botonsheettickets.show(getSupportFragmentManager(), "ticketsSalida");
                    }else{
                        escanearSalida= new escanearCodigosSalida();
                        escanearSalida.show(getSupportFragmentManager(), "escanearCodigosSalida");
                        stopCameraProcess();
                    }

                }else{
                    typeScanner=bndl.getString("scannerType2");
                }
         }else{
                typeScanner="Validador";
                new escanearCodigosSalida().show(getSupportFragmentManager(), "escanearCodigosSalida");
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
        binding.lamp2.setVisibility(View.VISIBLE);
        binding.lamp2.setOnClickListener(this);
        if(typeScanner!=null) {
            if (typeScanner.equals("Validador")) {
                binding.lamp2.setVisibility(View.VISIBLE);
            }else if(typeScanner.equals("Recolectar")){
                binding.lamp2.setVisibility(View.GONE);
            }else if(typeScanner.equals("Salida")){
                binding.lamp2.setVisibility(View.VISIBLE);
            }
        }
        binding.inputmanual.setOnClickListener(this);
        binding.inputcamara.setOnClickListener(this);
        binding.captureCode.setOnClickListener(this);
        binding.iconchecklist.setOnClickListener(this);
        //binding.inputmanual.setVisibility(View.GONE);
        //binding.inputcamara.setVisibility(View.GONE);
        //binding.captureCode.setVisibility(View.GONE);
        //binding.iconchecklist.setVisibility(View.GONE);

        //todo se receteara de momento collectedBarcode
        //collectedBarCodes.clear();
        if(collectedBarCodes.isEmpty()) {  //todo esto es para comprobar si el arreglo viene lleno o vacio

        }else
        {
            binding.cardviewnumber.setVisibility(View.VISIBLE);
            binding.textdimens.setText(String.valueOf(collectedBarCodes.size()));

        }

       // binding.escribircodigo.setInputType(InputType.TYPE_CLASS_NUMBER);//todo se quita la restriccion de solo numeros
       // binding.escribircodigo.setFilters(new InputFilter[]{new InputFilter.LengthFilter(8)});//todo se quita la restriccion de 8 digitos
        binding.clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetShared();
            }
        });
        binding.escribircodigo.setOnEditorActionListener(new TextView.OnEditorActionListener() {


            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    String textleng= binding.escribircodigo.getText().toString();
                    if(textleng.length()<8) {
                       // Toast.makeText(getApplicationContext(), "el numero de digitos es incorrecto intenta de nuevo", Toast.LENGTH_SHORT).show();
                        binding.escribircodigo.setText("");
                    }else {
                        binding.escribircodigo.setText("");

                    }

                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(),
                            InputMethodManager.RESULT_UNCHANGED_SHOWN);
                    return true;
                }
                return false;
            }
        });
        }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt(STATE_LENS_FACING, lensFacing);
    }
    public void stopCameraProcess() {
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
            if (isTorchOn) {
                binding.lamp2c.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lamparaon));
            } else {

                binding.lamp2c.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lamparaoff));
            }
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
                        //      TAG, "Failed to process image. Error: " + e.getLocalizedMessage());
                        Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            cameraProvider.bindToLifecycle(/* lifecycleOwner= */ this, cameraSelector, analysisUseCase);
        } else {
            Log.w(TAG, "Cannot bind use case. Activity is in a destroyed state.");
        }
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
        Log.i("motorola", "Permission "+requestCode+"  "+permissions+"  "+grantResults);
        if (allPermissionsGranted()) {
            bindAllCameraUseCases();
            Log.i("motorola", "Permission granted!");

        }else {
            if(requestCode==1){
                if(isMotorola) {
                    checkIfMotorola();
                    isMotorola=false;
                }
            }
            Log.i("motorola", "not all Permission granted!" +currentStatus);
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
//        Handler handler = new Handler(Looper.getMainLooper());
//
//        handler.post(new Runnable() {
//            @Override
//            public void run() {
//
//                if (code != null && !code.isEmpty()) {
//                    barcodesCollection(code);
//                    binding.resultContainer.setVisibility(View.VISIBLE);
//                    lastCode=code;
//                }
//            }
//        });
    }

    public void beginScreen() {
        isBegin=false;
    }
    @Override
    public void sendScannedCodewithBounding(String code, Rect boundingBox) {
        if(!isErrorShowed) {
            if (!isBegin) {
                if (scannedCodes.isEmpty()) {
                    startTime = System.currentTimeMillis();
                    Log.e("beginningScanner", typeScanner);
                    new Handler().postDelayed(this::processScannedCodes, 2000);
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
            } else {
                // Toast.makeText(this, "Pantalla inicial", Toast.LENGTH_SHORT).show();
            }
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
            Log.e("centeredCode", "Most centered code: " + mostCenteredCode.code +" "+centerPoint);
           // Toast.makeText(this, "" + mostCenteredCode.code, Toast.LENGTH_SHORT).show();
            // You can handle the most centered code here, e.g., show it in the UI or trigger further processing
        }

        // Clear the scanned codes list for future scans
        scannedCodes.clear();
        mediaPlayer.start();
        stopCameraProcess();
        newCollection(mostCenteredCode.code);
       // new Handler().postDelayed(this::restartCameraProcess, 1500);
    }
    private float calculateDistance(PointF p1, PointF p2) {//centerPoint, scannedCode.position
        //todo definir el orden de la operacion dependiendo de la posicion del punto
        float dx = p1.x - p2.x;
        float dy = p1.y - p2.y;
        return (float) Math.sqrt(dx * dx + dy * dy);
    }
    public void restartCameraProcesswithNoChanges() {
        Log.e("carga"," restartCameraProcesswithNoChanges typeScanner "+typeScanner);
        binding.barcodeRawValue.setText("");
        if (allPermissionsGranted()) {
            bindAllCameraUseCases();
            if(typeScanner.equals("Salida")) {
                if (currentStatus == 3) {
                    binding.barcodeRawValue.setText("escanea los tickets");
                    if (getSupportFragmentManager().findFragmentByTag("ticketsSalida") == null) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("tickets", (Serializable) dataTickets);
                        botonsheettickets = new ticketsSalida();
                        botonsheettickets.setArguments(bundle);
                        botonsheettickets.show(getSupportFragmentManager(), "ticketsSalida");
                    }

                }else if (currentStatus == 4) {
                    binding.barcodeRawValue.setText("escanea los sellos");
                    if (getSupportFragmentManager().findFragmentByTag("sellosSalida") == null) {
                        Bundle bundle = new Bundle();
                        bundle.putString("currentManifest", currentmanifest);
                        bundle.putSerializable("sellos", (Serializable) dataSellos);
                        bundle.putString("flowSellos","2");
                        botonsheetsellos = new sellosSalida();
                        botonsheetsellos.setArguments(bundle);
                        botonsheetsellos.show(getSupportFragmentManager(), "sellosSalida");
                    }
                } else if (currentStatus == 5) {
                    binding.barcodeRawValue.setText("escanea los sellos");
                    if (getSupportFragmentManager().findFragmentByTag("sellosSalida") == null) {
//                        Bundle bundle = new Bundle();
//                        bundle.putSerializable("sellos", (Serializable) dataSellos);
//                        botonsheetsellos = new sellosSalida();
//                        botonsheetsellos.setArguments(bundle);
//                        botonsheetsellos.show(getSupportFragmentManager(), "sellosSalida");
                    }
                }
            }else if(typeScanner.equals("Validador")){

            }
        } else {
            getRuntimePermissions();
        }
    }
    public void restartCameraProcessfromerror() {
        if (allPermissionsGranted()) {
            bindAllCameraUseCases();
        }else {
            getRuntimePermissions();
        }
    }
    public void checkIfMotorola(){
        Log.e("motorola","checkIfMotorola typeScanner "+typeScanner + " currentStatus "+currentStatus+" currentManifest "+currentmanifest);
        if(typeScanner.equals("Salida")) {
            if (currentStatus != 5 && currentStatus != 4) {
                SharedPreferences preferences = getApplicationContext().getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(GeneralConstants.STATUS_SALIDA, String.valueOf(currentStatus));
                editor.commit();
            }
            if (currentStatus == 3) {
                binding.lamp2.setVisibility(View.GONE);
                binding.barcodeRawValue.setText("escanea los tickets");
                if (getSupportFragmentManager().findFragmentByTag("ticketsSalida") == null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("typeScanner",typeScanner);
                    bundle.putSerializable("tickets", (Serializable) dataTickets);
                    bundle.putString("currentmanifest", currentmanifest);
                    botonsheettickets = new ticketsSalida();
                    botonsheettickets.setArguments(bundle);
                    botonsheettickets.show(getSupportFragmentManager(), "ticketsSalida");
                }

            }else if (currentStatus == 4) {
                binding.barcodeRawValue.setText("escanea los sellos");
                if (getSupportFragmentManager().findFragmentByTag("sellosSalida") == null) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("sellos", (Serializable) dataSellos);
                    bundle.putString("flowSellos","2");
                    botonsheetsellos = new sellosSalida();
                    botonsheetsellos.setArguments(bundle);
                    botonsheetsellos.show(getSupportFragmentManager(), "sellosSalida");
                }
            } else if (currentStatus == 5) {
                binding.barcodeRawValue.setText("escanea los sellos");
                botonsheettickets.dismiss();
                if (getSupportFragmentManager().findFragmentByTag("sellosSalida") == null) {
                   Bundle bundle = new Bundle();
                    bundle.putString("typeScanner",typeScanner);
                    bundle.putString("currentManifest", currentmanifest);
                    bundle.putSerializable("sellos", (Serializable) dataSellos);
                    bundle.putSerializable("dataTcikets", (Serializable) dataTickets);
                    bundle.putString("flowSellos","2");
                    botonsheetsellos = new sellosSalida();
                    botonsheetsellos.setArguments(bundle);
                    botonsheetsellos.show(getSupportFragmentManager(), "sellosSalida");
                }
                SharedPreferences preferences = getApplicationContext().getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(GeneralConstants.STATUS_SALIDA, String.valueOf(currentStatus));
                editor.commit();
            }else {
            }
        }else if(typeScanner.equals("Validador")){
            if(currentStatus<4) {
                currentStatus = currentStatus + 1;
                if (currentStatus == 4) {
                    Bundle bundle = new Bundle();
                    bundle.putString("currentManifest", codigoValidador);
                    bundle.putInt("claveVehicleID",claveVehicleID);
                    bundle.putString("vehicleVin", vehiclebarcodeVal);
                    bundle.putString("RFCUser", rfcBarcodeVal);
                    dialogCompleteValidador bottonSheetv = new dialogCompleteValidador();
                    bottonSheetv.setArguments(bundle);
                    bottonSheetv.show(getSupportFragmentManager(), "dialogCompleteValidador");
                    stopCameraProcess();
                }
            }              //aqui solo se debe visivilizar el escaner ya que no hay ventanas emergentes
        }else if(typeScanner.equals("Recolectar")){
            Log.e("dataSellosE","motorola currentStatus "+currentStatus);
            if (currentStatus == 5) {
                binding.barcodeRawValue.setText("escanea los sellos");
                botonsheettickets.dismiss();
                if (getSupportFragmentManager().findFragmentByTag("sellosSalida") == null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("typeScanner",typeScanner);
                    bundle.putString("currentManifest", currentmanifest);
                    bundle.putSerializable("sellos", (Serializable) dataSellos);
                    bundle.putSerializable("dataTcikets", (Serializable) dataTickets);
                    bundle.putString("flowSellos", "1");
                    botonsheetsellos = new sellosSalida();
                    botonsheetsellos.setArguments(bundle);
                    botonsheetsellos.show(getSupportFragmentManager(), "sellosSalida");
                }
                SharedPreferences preferences = getApplicationContext().getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(GeneralConstants.STATUS_SALIDA, String.valueOf(currentStatus));
                editor.commit();
            }
        }
    }
    public void restartCameraProcess() {
        if (allPermissionsGranted()) {
            bindAllCameraUseCases();
            Log.e("carga","restartCameraProcess typeScanner "+typeScanner);
          if(typeScanner.equals("Salida")) {
            if (currentStatus != 3 && currentStatus != 5 && currentStatus != 4) {
                currentStatus = currentStatus + 1;
                SharedPreferences preferences = getApplicationContext().getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(GeneralConstants.STATUS_SALIDA, String.valueOf(currentStatus));
                editor.commit();
            }

            if (currentStatus == 3) {
                binding.barcodeRawValue.setText("escanea los tickets");
                binding.lamp2.setVisibility(View.GONE);
                if (getSupportFragmentManager().findFragmentByTag("ticketsSalida") == null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("typeScanner",typeScanner);
                    bundle.putSerializable("tickets", (Serializable) dataTickets);
                    bundle.putString("currentmanifest", currentmanifest);
                    botonsheettickets = new ticketsSalida();
                    botonsheettickets.setArguments(bundle);
                    botonsheettickets.show(getSupportFragmentManager(), "ticketsSalida");
                }

            }else if (currentStatus == 4) {
                binding.barcodeRawValue.setText("escanea los sellos");
                if (getSupportFragmentManager().findFragmentByTag("sellosSalida") == null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("currentManifest", currentmanifest);
                    bundle.putSerializable("sellos", (Serializable) dataSellos);
                    bundle.putString("flowSellos","2");
                    botonsheetsellos = new sellosSalida();
                    botonsheetsellos.setArguments(bundle);
                    botonsheetsellos.show(getSupportFragmentManager(), "sellosSalida");
                }
                SharedPreferences preferences = getApplicationContext().getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(GeneralConstants.STATUS_SALIDA, String.valueOf(currentStatus));
                editor.commit();
            }else if (currentStatus == 5) {
                binding.barcodeRawValue.setText("escanea los sellos");
                if (getSupportFragmentManager().findFragmentByTag("sellosSalida") == null) {
//                    Bundle bundle = new Bundle();
//                    bundle.putSerializable("sellos", (Serializable) dataSellos);
//                    botonsheetsellos = new sellosSalida();
//                    botonsheetsellos.setArguments(bundle);
//                    botonsheetsellos.show(getSupportFragmentManager(), "sellosSalida");
                }
            }
         }else if(typeScanner.equals("Validador")){
              if(currentStatus<4) {
                  currentStatus = currentStatus + 1;
                  if (currentStatus == 4) {
                      Bundle bundle = new Bundle();
                      bundle.putString("currentManifest", codigoValidador);
                      bundle.putInt("claveVehicleID",claveVehicleID);
                      bundle.putString("vehicleVin", vehiclebarcodeVal);
                      bundle.putString("RFCUser", rfcBarcodeVal);
                      dialogCompleteValidador bottonSheetv = new dialogCompleteValidador();
                      bottonSheetv.setArguments(bundle);
                      bottonSheetv.show(getSupportFragmentManager(), "dialogCompleteValidador");
                      stopCameraProcess();
                  }
              }              //aqui solo se debe visivilizar el escaner ya que no hay ventanas emergentes
          }else{
              Log.e("dataSellosE","restartCameraProcess currentStatus "+currentStatus);
              if (currentStatus == 4) {
                  binding.barcodeRawValue.setText("escanea los sellos");
                  if (getSupportFragmentManager().findFragmentByTag("sellosSalida") == null) {
                      Bundle bundle = new Bundle();
                      bundle.putString("typeScanner",typeScanner);
                      bundle.putString("currentManifest", currentmanifest);
                      bundle.putSerializable("dataTcikets", (Serializable) dataTickets);
                      bundle.putSerializable("sellos", (Serializable) dataSellos);
                      bundle.putString("flowSellos", "1");
                      botonsheetsellos = new sellosSalida();
                      botonsheetsellos.setArguments(bundle);
                      botonsheetsellos.show(getSupportFragmentManager(), "sellosSalida");
                  }
                  SharedPreferences preferences = getApplicationContext().getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
                  SharedPreferences.Editor editor = preferences.edit();
                  editor.putString(GeneralConstants.STATUS_SALIDA, String.valueOf(currentStatus));
                  editor.commit();
              }
          }

        } else {
            if (currentStatus != 3 && currentStatus != 5) {
                if(typeScanner.equals("Validador")){

                }else {
                    currentStatus = currentStatus + 1;
                }
            }
            isMotorola=true;

            getRuntimePermissions();
        }
    }
    public void godialogCheck(){
        dismissSellos();
        currentStatus = 6;
        botonsheetsellos.dismiss();
        dialogCompletedSalida bottonSheetv=new dialogCompletedSalida();
        Bundle bundle = new Bundle();
        bundle.putString("manifest", currentmanifest);
        bundle.putSerializable("tickets", (Serializable) dataTickets);
        bundle.putSerializable("sellos", (Serializable) dataSellos);
        bottonSheetv.setArguments(bundle);
        bottonSheetv.show(getSupportFragmentManager(),"dialogCompletedSalidaImp");
    }
    public void resumeValidador(){
        dismissSellos();
        dialogCompletedSalida bottonSheetv=new dialogCompletedSalida();
        bottonSheetv.show(getSupportFragmentManager(),"dialogCompletedSalidaImp");
    }
    public void dismissTickets(){
        if(botonsheettickets!=null) {
            botonsheettickets.closeDialog();
            currentStatus = 4;
        }
    }
    public void dismissSellos(){
        if(botonsheetsellos!=null) {
            botonsheetsellos.closeDialog();
            currentStatus = 5;
        }

    }

    public void resetShared(){
        currentStatus = 1;
        SharedPreferences preferences =getApplicationContext().getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString(GeneralConstants.STATUS_SALIDA,String.valueOf(currentStatus));
        editor.commit();
    }

    public void goResumenValidador(){
        currentStatus = 3;
        SharedPreferences preferences =getApplicationContext().getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString(GeneralConstants.STATUS_VALIDADOR,String.valueOf(currentStatus));
        editor.commit();
    }
    public void goVehicle(){
        currentStatus = 2;
        SharedPreferences preferences =getApplicationContext().getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString(GeneralConstants.STATUS_VALIDADOR,String.valueOf(currentStatus));
        editor.commit();
    }
    public void goTickets(){
        currentStatus = 3;
        SharedPreferences preferences =getApplicationContext().getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString(GeneralConstants.STATUS_SALIDA,String.valueOf(currentStatus));
        editor.commit();
    }
    public void resetError() {
        errorD2=null;
        isErrorShowed=false;
    }
    public void errorTicket(String value) {
        stopCameraProcess();
        if(errorD2==null) {
            isErrorShowed = true;
            errorD2 = new errorCarga();
            Bundle args = new Bundle();
            args.putString("error_value", value);
            errorD2.setArguments(args);
            errorD2.show(getSupportFragmentManager(), "errorCarga");
        }

    }
    public void errorCarga(String value) {
        stopCameraProcess();
        if(errorD2==null) {
            isErrorShowed = true;
            errorD2 = new errorCarga();
            Bundle args = new Bundle();
            args.putString("error_value", value);
            errorD2.setArguments(args);
            errorD2.show(getSupportFragmentManager(), "errorCarga");
        }
    }
    public void detalManifestTicketsSummary(String mcurrentmanifest, List<dataTicketsManifestV2> codigoValidador, List<Sello> sellos){
        stopCameraProcess();
        Log.e("motorola","motorola tickets Sumary");
        Bundle bundle = new Bundle();
        bundle.putString("currentManifest", mcurrentmanifest);
        bundle.putSerializable("dataTcikets",(Serializable) codigoValidador);
        bundle.putSerializable("sellos",(Serializable) sellos);
        detailTicketsSummary bottonSheetv = new detailTicketsSummary();
        bottonSheetv.setArguments(bundle);
        bottonSheetv.show(getSupportFragmentManager(), "detailTicketsSummary");
        currentStatus = 4;
        dataTickets=codigoValidador;
    }
    public void goTicketsSummary(){
        stopCameraProcess();
        Bundle bundle = new Bundle();
        //bundle.putString("qrCode", code);
        String statusrecepcion="3";
        if(dataSellos!=null){
           statusrecepcion="4";
            //Toast.makeText(this, "No tienes sellos", Toast.LENGTH_SHORT).show();
         //   dismissSellos();
        }else{
            statusrecepcion="4";
            //Toast.makeText(this, "No tienes sellos", Toast.LENGTH_SHORT).show();
          //|  dismissSellos();
        }
//        botonsheetsellos = new sellosSalida();
//        // botonsheetsellos.setArguments(bundle);
//        botonsheetsellos.show(getSupportFragmentManager(), "sellosSalida");

        // bundle.putString("cortinaDestino", cortinaDestination);
        //bundle.putString("mQR", mQR);
        bundle.putString("statusRecepcion", statusrecepcion);
        bundle.putString("currentManifest", currentmanifest);
        Salida bottonSheetv = new Salida();
        bottonSheetv.setArguments(bundle);
        bottonSheetv.show(getSupportFragmentManager(), "Salida");
//
//        Bundle bundle1 = new Bundle();
//        bundle1.putString("currentManifest", currentmanifest);
//        bundle1.putSerializable("dataTcikets", (Serializable) dataTickets);
//        bundle1.putSerializable("sellos", (Serializable) dataSellos);
//        bundle1.putString("flowSellos", "2");
//        sellosSummary bottomSheet = new sellosSummary();
//        bottomSheet.setArguments(bundle1);
//        bottomSheet.show(getSupportFragmentManager(), "sellosSummary");

    }
    public void goSellosSummary() {
        stopCameraProcess();
       // botonsheetsellos.dismiss();
        Bundle bundle = new Bundle();
        //bundle.putString("qrCode", code);
        bundle.putString("statusRecepcion", "5");
        // bundle.putString("cortinaDestino", cortinaDestination);
        //bundle.putString("mQR", mQR);
        bundle.putString("currentManifest", currentmanifest);
        Salida bottonSheetv = new Salida();
        bottonSheetv.setArguments(bundle);
        bottonSheetv.show(getSupportFragmentManager(), "Salida");

    }
    public void setCurrentManifestSellos(String currentManifest){
        if(currentManifest!=null){
        this.currentmanifest=currentManifest;
        }
    }
    public void setTicketsArray(List<dataTicketsManifestV2> data) {
        this.dataTickets=data;
        Gson gson =new Gson();
        String json =gson.toJson(dataTickets);

    }
    public void setSellosNull() {
        this.typeScanner="Salida";
    }
    public void setSellosArray(List<Sello> response) {
        this.dataSellos=response;
    }
    public void setCortina(String destino, String qrCodigo, String codigoAnden, String codigoValidador) {
        this.currentmanifest=codigoValidador;
        cortinaDestination=destino;
        mQR=qrCodigo;
        mcodigoAnden=codigoAnden;
        currentStatus = 1;
        SharedPreferences preferences =getApplicationContext().getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString(GeneralConstants.STATUS_SALIDA,String.valueOf(currentStatus));
        editor.commit();
    }

    public void setVehicleandDriver(List<dataValidadorV2> data) {
    }
    public void setVehicleandDriverBarcodes(String vehiclebarcode, String rfcBarcode, String vehiclebarcodeVal, String rfcBarcodeVal, Integer claveVehicleID){
        this.vehiclebarcode=vehiclebarcode;
        this.rfcBarcode=rfcBarcode;
        this.vehiclebarcodeVal=vehiclebarcodeVal;
        this.rfcBarcodeVal=rfcBarcodeVal;
        this.claveVehicleID=claveVehicleID;
    }
    public void newCollection(String code) {
            barcodesCollection(code);
    }
    private void barcodesCollection(String code)
    {
        if(collectedBarCodes.contains(code))
        {
            if(typeScanner.equals("Salida")){//para salida recepccion4 si esta en la lista preguntar por el estatus del escaneo
                SharedPreferences preferences = getApplicationContext().getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
                String status = preferences.getString(GeneralConstants.STATUS_SALIDA, null);

                if(status.equals("0")){
                    status="1";
                }
                stopCameraProcess();
                Bundle bundle = new Bundle();
                bundle.putString("qrCode", code);
                bundle.putString("statusRecepcion", status);
                Salida bottonSheetv=new Salida();
                bottonSheetv.setArguments(bundle);
                bottonSheetv.show(getSupportFragmentManager(),"Salida");
                //Toast.makeText(this, "verificar estatus de la salida y escaneo", Toast.LENGTH_SHORT).show();
            }
        }else
        {
            if(!typeScanner.equals("Validador")) {
            }
            mediaPlayer.start();
            if(!collectedBarCodes.isEmpty())//esto oculta el numero de codigos escaneados
            {
                binding.cardviewnumber.setVisibility(View.VISIBLE);
                binding.textdimens.setText(String.valueOf( collectedBarCodes.size()));
            }


            if(typeScanner.equals("Salida")){ //esto es CARGA y RECOLECCION si no esta en la lista el primer bottom sheet debe ser el de manifiesto

                SharedPreferences preferences = getApplicationContext().getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
                String status = preferences.getString(GeneralConstants.STATUS_SALIDA, null);
                if(status == null){

                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putString(GeneralConstants.STATUS_SALIDA,"1");
                    editor.commit();
                    binding.barcodeRawValue.setText(code);
                    Bundle bundle = new Bundle();
                    bundle.putString("qrCode", code);
                    bundle.putString("statusRecepcion", "1");
                    Salida bottonSheetv=new Salida();
                    bottonSheetv.setArguments(bundle);
                    bottonSheetv.show(getSupportFragmentManager(),"Salida");
                    stopCameraProcess();
                }else if(status.equals("1")){//esto muestra el sumary ed manifiestos
                    binding.barcodeRawValue.setText(code);
                    Bundle bundle = new Bundle();
                    bundle.putString("qrCode", code);
                    bundle.putString("statusRecepcion", status);
                    Salida bottonSheetv=new Salida();
                    bottonSheetv.setArguments(bundle);
                    bottonSheetv.show(getSupportFragmentManager(),"Salida");
                    stopCameraProcess();
                }else if(status.equals("2")){//esto muestra el sumary de cortinas
                    if(mcodigoAnden!=null) {
               //todo el anden ya da igual
                            Bundle bundle = new Bundle();
                            bundle.putString("qrCode", code);
                            bundle.putString("statusRecepcion", status);
                            bundle.putString("cortinaDestino", code);
                            bundle.putString("mQR", mQR);
                            bundle.putString("currentManifest", currentmanifest);
                            Salida bottonSheetv = new Salida();
                            bottonSheetv.setArguments(bundle);
                            bottonSheetv.show(getSupportFragmentManager(), "Salida");
                            stopCameraProcess();

                    }else{
                        if(errorD2==null) {
                            isErrorShowed = true;
                            errorD2 = new errorCarga();
                            Bundle args = new Bundle();
                            args.putString("error_value", "Cortina no valida");
                            errorD2.setArguments(args);
                            errorD2.show(getSupportFragmentManager(), "errorCarga");
                        }
                    }
                }else if(status.equals("3")){//esto muestra el bottomsheet de tickets

                    if (getSupportFragmentManager().findFragmentByTag("ticketsSalida") == null) {//si el estatus es tres se crea el bottomsheet siempre y cuando no exista
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("tickets", (Serializable) dataTickets);
                        bundle.putString("currentmanifest", currentmanifest);
                        botonsheettickets = new ticketsSalida();
                        botonsheettickets.setArguments(bundle);
                        botonsheettickets.show(getSupportFragmentManager(), "ticketsSalida");//de existir el botomsheet
                    } else {
                        botonsheettickets.sendToast(code);
                    }
                    stopCameraProcess();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            restartCameraProcess();
                        }
                    }, 1500);
                }else if(status.equals("4")) {
                    if(getSupportFragmentManager().findFragmentByTag("sellosSalida")==null){
                        Bundle bundle= new Bundle();
                        bundle.putString("typeScanner",typeScanner);
                        bundle.putString("currentManifest", currentmanifest);
                        bundle.putSerializable("sellos",(Serializable) dataSellos);
                        botonsheetsellos = new sellosSalida();
                        botonsheetsellos.setArguments(bundle);
                        botonsheetsellos.show(getSupportFragmentManager(),"sellosSalida");
                    } else {
                        botonsheetsellos.sendToast(code);
                    }
                    stopCameraProcess();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            restartCameraProcess();
                        }
                    }, 1500);
                }else if(status.equals("5")) {
                    Log.e("motorola",""+status);
                    botonsheettickets.dismiss();
                    //botonsheetsellos
                    if(getSupportFragmentManager().findFragmentByTag("sellosSalida")==null){
//                        Bundle bundle= new Bundle();
//                        bundle.putSerializable("sellos",(Serializable) dataSellos);
//                        botonsheetsellos = new sellosSalida();
//                        botonsheetsellos.setArguments(bundle);
//                        botonsheetsellos.show(getSupportFragmentManager(),"sellosSalida");
                    } else {
                        botonsheetsellos.sendToast(code);
                    }
                    stopCameraProcess();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            restartCameraProcess();
                        }
                    }, 1500);
                }else {
                    binding.barcodeRawValue.setText("");
                    Bundle bundle = new Bundle();
                    bundle.putString("qrCode", code);
                    bundle.putString("statusRecepcion", status);
                    Salida bottonSheetv=new Salida();
                    bottonSheetv.setArguments(bundle);
                    bottonSheetv.show(getSupportFragmentManager(),"Salida");
                    stopCameraProcess();
                }

            }else if (typeScanner.equals("Validador")) {

                SharedPreferences preferences = getApplicationContext().getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
                String status = preferences.getString(GeneralConstants.STATUS_VALIDADOR, null);

                if (status == null) {
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString(GeneralConstants.STATUS_VALIDADOR, "1");
                    editor.commit();
                    currentStatus = 1;
                } else if (currentStatus == 0) {
                    currentStatus = Integer.valueOf(status);
                }
                String image = "";
                if(currentStatus==1){
                    this.codigoValidador=code;
                    Bundle bundle = new Bundle();
                    bundle.putString("currentManifest", code);
                    bundle.putString("qrCode", image);
                    bundle.putString("statusValidador", String.valueOf(currentStatus));
                    validadorManifest validador=new validadorManifest();
                    validador.setArguments(bundle);
                    validador.show(getSupportFragmentManager(),"validadorManifest");
                }else if(currentStatus==2){//vin
                    if(vehiclebarcodeVal.equals(code)) {
                        image = vehiclebarcode;
                        Bundle bundle = new Bundle();
                        bundle.putString("currentManifest", code);
                        bundle.putString("qrCode", image);
                        bundle.putString("statusValidador", String.valueOf(currentStatus));
                        validadorManifest validador = new validadorManifest();
                        validador.setArguments(bundle);
                        validador.show(getSupportFragmentManager(), "validadorManifest");
                    }else{
                        if(errorD2==null) {
                            isErrorShowed = true;
                            errorD2 = new errorCarga();
                            Bundle args = new Bundle();
                            args.putString("error_value", "Código vin incorrecto");
                            errorD2.setArguments(args);
                            errorD2.show(getSupportFragmentManager(), "errorCarga");
                        }
                    }

                }else if(currentStatus==3)//rfc
                {
                    if(rfcBarcodeVal.equals(code)) {
                        image = rfcBarcode;
                        Bundle bundle = new Bundle();
                        bundle.putString("currentManifest", code);
                        bundle.putString("qrCode", image);
                        bundle.putString("statusValidador", String.valueOf(currentStatus));
                        validadorManifest validador = new validadorManifest();
                        validador.setArguments(bundle);
                        validador.show(getSupportFragmentManager(), "validadorManifest");
                    }else{
                        if(errorD2==null) {
                            isErrorShowed = true;
                            errorD2 = new errorCarga();
                            Bundle args = new Bundle();
                            args.putString("error_value", "RFC incorrecto");
                            errorD2.setArguments(args);
                            errorD2.show(getSupportFragmentManager(), "errorCarga");
                        }
                    }
                }                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        else {
                    if(errorD2==null) {
                        isErrorShowed = true;
                        errorD2 = new errorCarga();
                        Bundle args = new Bundle();
                        args.putString("error_value", "");
                        errorD2.setArguments(args);
                        errorD2.show(getSupportFragmentManager(), "errorCarga");
                    }
                }
                stopCameraProcess();



            }else if(typeScanner.equals("Recolectar")) {
                Log.e("dataSellosE", " currentStatus Recolectar "+currentStatus);
                binding.lamp2.setVisibility(View.GONE);
                if (currentStatus == 4) {
                    if (dataTickets != null) {
                    } else {
                    }
                    botonsheettickets.dismiss();
                    if(getSupportFragmentManager().findFragmentByTag("sellosSalida")==null){
                        Bundle bundle= new Bundle();
                        bundle.putString("typeScanner",typeScanner);
                        bundle.putString("currentManifest", currentmanifest);
                        bundle.putSerializable("dataTcikets", (Serializable) dataTickets);
                        bundle.putSerializable("sellos",(Serializable) dataSellos);
                        botonsheetsellos = new sellosSalida();
                        botonsheetsellos.setArguments(bundle);
                        botonsheetsellos.show(getSupportFragmentManager(),"sellosSalida");
                    } else {
                        botonsheetsellos.sendToast(code);
                        botonsheetsellos.setTickets(dataTickets);
                        botonsheetsellos.setSellos(dataSellos);
                        botonsheetsellos.currentManifst(currentmanifest);
                    }
                    stopCameraProcess();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            restartCameraProcess();
                        }
                    }, 1500);
                }else if(currentStatus == 5) {
                    if (dataTickets != null) {
                    } else {
                    }
                    botonsheettickets.dismiss();
                    if(getSupportFragmentManager().findFragmentByTag("sellosSalida")==null){
                        Bundle bundle= new Bundle();
                        bundle.putString("typeScanner",typeScanner);
                        bundle.putString("currentManifest", currentmanifest);
                        bundle.putSerializable("dataTcikets", (Serializable) dataTickets);
                        bundle.putSerializable("sellos",(Serializable) dataSellos);
                        botonsheetsellos = new sellosSalida();
                        botonsheetsellos.setArguments(bundle);
                        botonsheetsellos.show(getSupportFragmentManager(),"sellosSalida");
                    } else {
                        botonsheetsellos.sendToast(code);
                        botonsheetsellos.setTickets(dataTickets);
                        botonsheetsellos.setSellos(dataSellos);
                        botonsheetsellos.currentManifst(currentmanifest);
                    }
                    stopCameraProcess();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            restartCameraProcess();
                        }
                    }, 1500);
                }else{
                    if (dataTickets != null) {
                    } else {
                    }
                    if (getSupportFragmentManager().findFragmentByTag("ticketsSalida") == null) {//si el estatus es tres se crea el bottomsheet siempre y cuando no exista
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("tickets", (Serializable) dataTickets);
                        botonsheettickets = new ticketsSalida();
                        botonsheettickets.setArguments(bundle);
                        botonsheettickets.show(getSupportFragmentManager(), "ticketsSalida");//de existir el botomsheet
                    } else {
                        botonsheettickets.sendToast(code);
                    }
                    stopCameraProcess();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            restartCameraProcess();
                        }
                    }, 1500);
                }
            }
        }

    }

    @Override
    public void onBackPressed() {
        resetShared();
        super.onBackPressed();

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iconchecklist:
                if(collectedBarCodes.isEmpty())
                {
                }else {
                    gotoListBarcode = "ready";
                }
            break;
            case R.id.lamp2:
                toggleFlash();

                break;
            case R.id.inputcamara:
                //Toast.makeText(this, "camara click", Toast.LENGTH_SHORT).show();
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
              //  Toast.makeText(this, "Obtener el codigo del InputText", Toast.LENGTH_SHORT).show();
                //binding.escribircodigo.getText().toString();
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
