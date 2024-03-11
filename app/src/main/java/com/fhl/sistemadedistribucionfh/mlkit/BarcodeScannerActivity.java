package com.fhl.sistemadedistribucionfh.mlkit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
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

import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.ErrorSalida.errorDialog;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.escanearCodigosSalida;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.sellosSalida.sellosSalida;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.ticketsSalida.ticketsSalida;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.view.Salida;
import com.fhl.sistemadedistribucionfh.Dialogs.detailManifestTicketsSummary.Tickets.detailTicketsSummary;
import com.fhl.sistemadedistribucionfh.Dialogs.dialogCompleteValidador;
import com.fhl.sistemadedistribucionfh.Dialogs.dialogCompletedSalida;
import com.fhl.sistemadedistribucionfh.Dialogs.validador.ValidadorV2.model.dataValidadorV2;
import com.fhl.sistemadedistribucionfh.Dialogs.validador.ValidadorV2.view.validadorManifest;
import com.fhl.sistemadedistribucionfh.Dialogs.validador.escanearValidador;
import com.fhl.sistemadedistribucionfh.Retrofit.GeneralConstants;
import com.fhl.sistemadedistribucionfh.Salida.Model.v2.dataSalida;
import com.fhl.sistemadedistribucionfh.Sellos.model.Sello;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.modelV2.dataTicketsManifestV2;
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
    private String codigoValidador="";
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
                    Log.e("typeScanner","1 "+typeScanner);
                    binding.mtypeScanner.setText(typeScanner);
                    if (typeScanner.equals("Validador")) {
                        new escanearValidador().show(getSupportFragmentManager(), "escanearValidador");
                        stopCameraProcess();
                    }else if(typeScanner.equals("Recolectar")){
                      Bundle bundle = new Bundle();
                      List<dataTicketsManifestV2> codigoValidador= (List<dataTicketsManifestV2>) bndl.getSerializable("tickets");
                        List<Sello> sellos =(List<Sello>) bndl.getSerializable("sellos");
                       currentmanifest=bndl.getString("manifest");
                      bundle.putSerializable("tickets", (Serializable) codigoValidador);
                      bundle.putSerializable("sellos", (Serializable) sellos);
                      bundle.putString("typeScanner",typeScanner);
                      bundle.putString("currentmanifest",currentmanifest);
                        botonsheettickets = new ticketsSalida();
                        botonsheettickets.setArguments(bundle);
                        botonsheettickets.show(getSupportFragmentManager(), "ticketsSalida");
                    }else{
                        new escanearCodigosSalida().show(getSupportFragmentManager(), "escanearCodigosSalida");
                        stopCameraProcess();
                    }

                }else{
                    typeScanner=bndl.getString("scannerType2");
                    Log.e("typeScanner","2 "+typeScanner);
                }
         }else{
                typeScanner="Validador";
                Log.e("","3 "+typeScanner);
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
       // binding.inputmanual.setOnClickListener(this);
       // binding.inputcamara.setOnClickListener(this);
       // binding.iconchecklist.setOnClickListener(this);
        //todo se receteara de momento collectedBarcode
        //collectedBarCodes.clear();
        if(collectedBarCodes.isEmpty()) {  //todo esto es para comprobar si el arreglo viene lleno o vacio

        }else
        {
            binding.cardviewnumber.setVisibility(View.VISIBLE);
            binding.textdimens.setText(String.valueOf(collectedBarCodes.size()));

        }

        binding.escribircodigo.setInputType(InputType.TYPE_CLASS_NUMBER);
        binding.escribircodigo.setFilters(new InputFilter[]{new InputFilter.LengthFilter(8)});
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
                    lastCode=code;
                    Log.e("codescann",""+lastCode);
                }
            }
        });
    } public void restartCameraProcesswithNoChanges() {
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

                } else if (currentStatus == 5) {
                    binding.barcodeRawValue.setText("escanea los sellos");
                    if (getSupportFragmentManager().findFragmentByTag("sellosSalida") == null) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("sellos", (Serializable) dataSellos);
                        botonsheetsellos = new sellosSalida();
                        botonsheetsellos.setArguments(bundle);
                        botonsheetsellos.show(getSupportFragmentManager(), "sellosSalida");
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
            Log.e("validador","last status "+currentStatus);
        }else {
            getRuntimePermissions();
        }
    }
    public void restartCameraProcess() {
        if (allPermissionsGranted()) {
            bindAllCameraUseCases();
          if(typeScanner.equals("Salida")) {
            if (currentStatus != 3 && currentStatus != 5) {
                currentStatus = currentStatus + 1;
                SharedPreferences preferences = getApplicationContext().getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(GeneralConstants.STATUS_SALIDA, String.valueOf(currentStatus));
                editor.commit();
            }
            if (currentStatus == 3) {
                binding.barcodeRawValue.setText("escanea los tickets");
                if (getSupportFragmentManager().findFragmentByTag("ticketsSalida") == null) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("tickets", (Serializable) dataTickets);
                    botonsheettickets = new ticketsSalida();
                    botonsheettickets.setArguments(bundle);
                    botonsheettickets.show(getSupportFragmentManager(), "ticketsSalida");
                }

            } else if (currentStatus == 5) {
                binding.barcodeRawValue.setText("escanea los sellos");
                if (getSupportFragmentManager().findFragmentByTag("sellosSalida") == null) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("sellos", (Serializable) dataSellos);
                    botonsheetsellos = new sellosSalida();
                    botonsheetsellos.setArguments(bundle);
                    botonsheetsellos.show(getSupportFragmentManager(), "sellosSalida");
                }
            }
         }else if(typeScanner.equals("Validador")){
              Log.e("validador","ir a validador "+currentStatus);
              if(currentStatus<4) {
                  currentStatus = currentStatus + 1;
                  if (currentStatus == 4) {
                      Bundle bundle = new Bundle();
                      bundle.putString("currentManifest", codigoValidador);
                      dialogCompleteValidador bottonSheetv = new dialogCompleteValidador();
                      bottonSheetv.setArguments(bundle);
                      bottonSheetv.show(getSupportFragmentManager(), "dialogCompleteValidador");
                      stopCameraProcess();
                  }
              }              //aqui solo se debe visivilizar el escaner ya que no hay ventanas emergentes
          }else{

          }

        } else {
            getRuntimePermissions();
        }
    }
    public void godialogCheck(){
        dialogCompletedSalida bottonSheetv=new dialogCompletedSalida();
        bottonSheetv.show(getSupportFragmentManager(),"dialogCompletedSalida");
    }
    public void dismissTickets(){
        botonsheettickets.closeDialog();
        currentStatus=4;
        Log.e("salida","ir a sellos");
    }
    public void dismissSellos(){
        botonsheetsellos.closeDialog();
    }

    public void resetShared(){
        currentStatus = 1;
        SharedPreferences preferences =getApplicationContext().getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString(GeneralConstants.STATUS_SALIDA,String.valueOf(currentStatus));
        editor.commit();
    }
    public void resumeValidador(){
        dialogCompletedSalida bottonSheetv=new dialogCompletedSalida();
        bottonSheetv.show(getSupportFragmentManager(),"dialogCompletedSalida");
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
    public void errorTicket() {
        errorDialog errorD = new errorDialog();
        errorD.show(getSupportFragmentManager(),"errorDialog");
        stopCameraProcess();

    }
    public void detalManifestTicketsSummary(String mcurrentmanifest, List<dataTicketsManifestV2> codigoValidador, List<Sello> sellos){
        Bundle bundle = new Bundle();
        bundle.putString("currentManifest", mcurrentmanifest);
        bundle.putSerializable("dataTcikets",(Serializable) codigoValidador);
        bundle.putSerializable("sellos",(Serializable) sellos);
        detailTicketsSummary bottonSheetv = new detailTicketsSummary();
        bottonSheetv.setArguments(bundle);
        bottonSheetv.show(getSupportFragmentManager(), "detailTicketsSummary");
        stopCameraProcess();

    }
    public void goTicketsSummary(){
        Bundle bundle = new Bundle();
        //bundle.putString("qrCode", code);
        bundle.putString("statusRecepcion", "3");
        // bundle.putString("cortinaDestino", cortinaDestination);
        //bundle.putString("mQR", mQR);
        bundle.putString("currentManifest", currentmanifest);
        Salida bottonSheetv = new Salida();
        bottonSheetv.setArguments(bundle);
        bottonSheetv.show(getSupportFragmentManager(), "Salida");
        stopCameraProcess();
    }
    public void goSellosSummary() {
        Bundle bundle = new Bundle();
        //bundle.putString("qrCode", code);
        bundle.putString("statusRecepcion", "5");
        // bundle.putString("cortinaDestino", cortinaDestination);
        //bundle.putString("mQR", mQR);
        bundle.putString("currentManifest", currentmanifest);
        Salida bottonSheetv = new Salida();
        bottonSheetv.setArguments(bundle);
        bottonSheetv.show(getSupportFragmentManager(), "Salida");
        stopCameraProcess();
    }
    public void setTicketsArray(List<dataTicketsManifestV2> data) {
        this.dataTickets=data;

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
    public void setVehicleandDriverBarcodes(String vehiclebarcode, String rfcBarcode, String vehiclebarcodeVal, String rfcBarcodeVal){
        this.vehiclebarcode=vehiclebarcode;
        this.rfcBarcode=rfcBarcode;
        this.vehiclebarcodeVal=vehiclebarcodeVal;
        this.rfcBarcodeVal=rfcBarcodeVal;
    }
    private void barcodesCollection(String code)
    {
        Log.e("qrs",code);
        if(collectedBarCodes.contains(code))
        {

            if(typeScanner.equals("Salida")){//para salida recepccion4 si esta en la lista preguntar por el estatus del escaneo

                SharedPreferences preferences = getApplicationContext().getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
                String status = preferences.getString(GeneralConstants.STATUS_SALIDA, null);
                Log.e("typeScanner","collected status: "+status);
                if(status.equals("0")){
                    status="1";
                }
                Bundle bundle = new Bundle();
                bundle.putString("qrCode", code);
                bundle.putString("statusRecepcion", status);
                Salida bottonSheetv=new Salida();
                bottonSheetv.setArguments(bundle);
                bottonSheetv.show(getSupportFragmentManager(),"Salida");
                Toast.makeText(this, "verificar estatus de la salida y escaneo", Toast.LENGTH_SHORT).show();
                stopCameraProcess();
            }
        }else
        {

            Log.e("typeScanner","codigos escaneados: "+collectedBarCodes);
            if(!typeScanner.equals("Validador")) {
            }
            mediaPlayer.start();
            if(!collectedBarCodes.isEmpty())//esto oculta el numero de codigos escaneados
            {
                binding.cardviewnumber.setVisibility(View.VISIBLE);
                binding.textdimens.setText(String.valueOf( collectedBarCodes.size()));
            }


            if(typeScanner.equals("Salida")){ //si no esta en la lista el primer bottom sheet debe ser el de manifiesto

                SharedPreferences preferences = getApplicationContext().getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
                String status = preferences.getString(GeneralConstants.STATUS_SALIDA, null);
                if(status == null){

                    Log.e("typeScanner","null status: 1");
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
                    Log.e("typeScanner","1 status: 1");
                    Bundle bundle = new Bundle();
                    bundle.putString("qrCode", code);
                    bundle.putString("statusRecepcion", status);
                    Salida bottonSheetv=new Salida();
                    bottonSheetv.setArguments(bundle);
                    bottonSheetv.show(getSupportFragmentManager(),"Salida");
                    stopCameraProcess();
                }else if(status.equals("2")){//esto muestra el sumary de cortinas
                    if(mcodigoAnden!=null) {
                        if (mcodigoAnden.equals(code)) {
                            Log.e("typeScanner", "1 status: " + status);
                            Bundle bundle = new Bundle();
                            bundle.putString("qrCode", code);
                            bundle.putString("statusRecepcion", status);
                            bundle.putString("cortinaDestino", cortinaDestination);
                            bundle.putString("mQR", mQR);
                            bundle.putString("currentManifest", currentmanifest);
                            Salida bottonSheetv = new Salida();
                            bottonSheetv.setArguments(bundle);
                            bottonSheetv.show(getSupportFragmentManager(), "Salida");
                            stopCameraProcess();
                        } else {
                            errorDialog errorD = new errorDialog();
                        }
                    }else{
                        errorDialog errorD = new errorDialog();
                    }
                }else if(status.equals("3")){//esto muestra el bottomsheet de tickets

                    if (getSupportFragmentManager().findFragmentByTag("ticketsSalida") == null) {//si el estatus es tres se crea el bottomsheet siempre y cuando no exista
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("tickets", (Serializable) dataTickets);
                        botonsheettickets = new ticketsSalida();
                        botonsheettickets.setArguments(bundle);
                        botonsheettickets.show(getSupportFragmentManager(), "ticketsSalida");//de existir el botomsheet
                        Log.e("ticketsArray", "adapter tickets inicio nulo"  );
                    } else {
                        botonsheettickets.sendToast(code);
                        Log.e("ticketsArray", "se envia el codigo al adapter "+code  );
                    }
                    stopCameraProcess();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            restartCameraProcess();
                        }
                    }, 1500);
                }else if(status.equals("4")) {

                }else if(status.equals("5")) {
                    //botonsheetsellos
                    if(getSupportFragmentManager().findFragmentByTag("sellosSalida")==null){
                        Bundle bundle= new Bundle();
                        bundle.putSerializable("sellos",(Serializable) dataSellos);
                        botonsheetsellos = new sellosSalida();
                        botonsheetsellos.setArguments(bundle);
                        botonsheetsellos.show(getSupportFragmentManager(),"sellosSalida");
                    } else {
                        botonsheetsellos.sendToast(code);
                        Log.e("ticketsArray", "se envia el codigo al adapter "+code  );
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
                    Log.e("typeScanner","else estatus: "+status+" cortina: "+cortinaDestination);
                    Bundle bundle = new Bundle();
                    bundle.putString("qrCode", code);
                    bundle.putString("statusRecepcion", status);
                    Salida bottonSheetv=new Salida();
                    bottonSheetv.setArguments(bundle);
                    bottonSheetv.show(getSupportFragmentManager(),"Salida");
                    stopCameraProcess();
                }

            }else if (typeScanner.equals("Validador")) {

                Log.e("Validador", "Escanned");
                SharedPreferences preferences = getApplicationContext().getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
                String status = preferences.getString(GeneralConstants.STATUS_VALIDADOR, null);
                Log.e("validador", "escanerActivity  " + status);

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
                    Log.e("validador","last status "+currentStatus+"  "+code);
                    Bundle bundle = new Bundle();
                    bundle.putString("currentManifest", code);
                    bundle.putString("qrCode", image);
                    bundle.putString("statusValidador", String.valueOf(currentStatus));
                    validadorManifest validador=new validadorManifest();
                    validador.setArguments(bundle);
                    validador.show(getSupportFragmentManager(),"validadorManifest");
                }else if(currentStatus==2){
                    Log.e("validador","last status "+currentStatus+"  "+code +"  "+vehiclebarcodeVal);
                    if(vehiclebarcodeVal.equals(code)) {
                        image = vehiclebarcode;
                        Log.e("validador", "escanerActivity current " + currentStatus + " estatus " + status);
                        Bundle bundle = new Bundle();
                        bundle.putString("currentManifest", code);
                        bundle.putString("qrCode", image);
                        bundle.putString("statusValidador", String.valueOf(currentStatus));
                        validadorManifest validador = new validadorManifest();
                        validador.setArguments(bundle);
                        validador.show(getSupportFragmentManager(), "validadorManifest");
                    }else{
                        errorDialog errorD = new errorDialog();
                        errorD.show(getSupportFragmentManager(),"errorDialog");
                    }

                }else if(currentStatus==3)
                {
                    Log.e("validador","last status "+currentStatus+"  "+code+"  "+rfcBarcodeVal);
                    if(rfcBarcodeVal.equals(code)) {
                        image = rfcBarcode;
                        Log.e("validador", "escanerActivity current " + currentStatus + " estatus " + status);
                        Bundle bundle = new Bundle();
                        bundle.putString("currentManifest", code);
                        bundle.putString("qrCode", image);
                        bundle.putString("statusValidador", String.valueOf(currentStatus));
                        validadorManifest validador = new validadorManifest();
                        validador.setArguments(bundle);
                        validador.show(getSupportFragmentManager(), "validadorManifest");
                    }else{
                        errorDialog errorD = new errorDialog();
                        errorD.show(getSupportFragmentManager(),"errorDialog");
                    }
                }                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        else {
                    errorDialog errorD = new errorDialog();
                    errorD.show(getSupportFragmentManager(),"errorDialog");
                }
                stopCameraProcess();



            }else if(typeScanner.equals("Recolectar")){
                if (getSupportFragmentManager().findFragmentByTag("ticketsSalida") == null) {//si el estatus es tres se crea el bottomsheet siempre y cuando no exista
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("tickets", (Serializable) dataTickets);
                    botonsheettickets = new ticketsSalida();
                    botonsheettickets.setArguments(bundle);
                    botonsheettickets.show(getSupportFragmentManager(), "ticketsSalida");//de existir el botomsheet
                    Log.e("ticketsArray", "adapter tickets inicio nulo"  );
                } else {
                    botonsheettickets.sendToast(code);
                    Log.e("ticketsArray", "se envia el codigo al adapter "+code  );
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

    @Override
    public void onBackPressed() {
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
                    break;
                }
        }
    }

}
