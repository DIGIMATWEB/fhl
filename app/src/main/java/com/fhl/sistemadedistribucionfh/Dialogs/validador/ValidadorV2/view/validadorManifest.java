package com.fhl.sistemadedistribucionfh.Dialogs.validador.ValidadorV2.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;

import com.bumptech.glide.Glide;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.model.responseManifestSalidaV2data;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.presenter.salidaViewPresenter;
import com.fhl.sistemadedistribucionfh.Dialogs.validador.ValidadorV2.model.dataValidadorV2;
import com.fhl.sistemadedistribucionfh.Dialogs.validador.ValidadorV2.presenter.presenterValidadorDetail;
import com.fhl.sistemadedistribucionfh.Dialogs.validador.ValidadorV2.presenter.presenterValidadorImplements;
import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.Retrofit.GeneralConstants;
import com.fhl.sistemadedistribucionfh.login.model.modelProfile.profileResponse;
import com.fhl.sistemadedistribucionfh.mlkit.BarcodeScannerActivity;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.gson.Gson;

import java.util.List;

public class validadorManifest extends DialogFragment implements View.OnClickListener ,validadorViewV2{
    //todo nota esto debe verificarse
    public static final String TAG = validadorManifest.class.getSimpleName();
    private presenterValidadorDetail presenter;
    private BottomSheetBehavior bottomSheetBehavior;
    private ConstraintLayout bottomSheet,cortina,fulllayout;
    private CardView constrainCard;
    private String codigoValidador,codigoValidador1,cortinaDestino,mQR,currentManifest;
    private ImageButton imageButton;
    private Button clear;
    private ImageView imageView24,qrsalida;
    private TextView textView23,textView29 ,textsalida;
    private CardView gonext;
    private List<responseManifestSalidaV2data> data;
    private TextView numberManifestsalida,cedissalida,vehiculosalida,datesalida,placasalida,regresosalida;
    private Boolean isCanceled =true;
    private String vehiclebarcode,rfcBarcode;
    private String vehiclebarcodeVal,rfcBarcodeVal;
    private Integer claveVehicleID;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_DeviceDefault_Light_NoActionBar);

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_salida_manifest, container, false);
        getDialog().getWindow().setBackgroundDrawableResource(R.color.customTransparent2);
        setCancelable(true);
        Bundle args = getArguments();
        if (args != null) {
            codigoValidador= args.getString("qrCode");
            codigoValidador1= args.getString("statusValidador");
            cortinaDestino= args.getString("cortinaDestino");
            mQR= args.getString("mQR");
            currentManifest = args.getString("currentManifest");
        }
        Log.e("datadecortina",""+cortinaDestino+"  "+ codigoValidador1);
        initDialog(view);
        setUpDialog(codigoValidador1);
        //setFonts();
        return view;
    }
    private void initDialog(View view) {
        presenter= new presenterValidadorImplements(this,getContext());
        constrainCard =view.findViewById(R.id.constrainCard);
        cortina=view.findViewById(R.id. cortina);
        imageButton=view.findViewById(R.id.imageButton);
        imageButton.setOnClickListener(this);
        qrsalida =view.findViewById(R.id.qrsalida);
        textsalida =view.findViewById(R.id.textsalida);
        clear=view.findViewById(R.id.clear);
        clear.setOnClickListener(this);
        imageView24 =view.findViewById(R.id.imageView24);
        textView23=view.findViewById(R.id.textView23);
        textView29=view.findViewById(R.id.textView29);

        gonext=view.findViewById(R.id.gonext);
        gonext.setOnClickListener(this);

        bottomSheet=view.findViewById(R.id.bottomSheetZones);

        //region detailmanifest
        numberManifestsalida=view.findViewById(R.id.numberManifestsalida);
        cedissalida=view.findViewById(R.id.cedissalida);
        vehiculosalida=view.findViewById(R.id.vehiculosalida);
        datesalida=view.findViewById(R.id.datesalida);
        placasalida=view.findViewById(R.id.placasalida);
        regresosalida=view.findViewById(R.id.regresosalida);
        fulllayout=view.findViewById(R.id.fulllayout);
        //endregion
      //  Toast.makeText(getContext(), "qrCode: "+codigoValidador+" status de recepcion: "+codigoValidador1, Toast.LENGTH_SHORT).show();

    }
    private void setUpDialog(String codigoValidador1) {
        switch (codigoValidador1) {
            case "1":/** aqui pedimos los manifiestos y las cortinas*/
                fulllayout.setVisibility(View.GONE);
                textView29.setText("escanear codigo del vehiculo");
                Gson gson = new Gson();
                SharedPreferences preferences = getContext().getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
                String token2 = preferences.getString(GeneralConstants.USER_VALUES, null);
                profileResponse profileData = gson.fromJson(token2, profileResponse.class);
                int idEmpleado = profileData.getUsuarioId();
                Log.e("validador","validator: "+idEmpleado+" manifest: "+currentManifest);
               presenter.requestManifestDetail( idEmpleado,currentManifest);
                break;
            case "2":/** aqui pedimos los manifiestos y las cortinas*/
                //aqui visible el vehiculo
                constrainCard.setVisibility(View.GONE);
                cortina.setVisibility(View.VISIBLE);
                Log.e("barcodereader","barcode vehicle "+ codigoValidador);
                try {
                    byte[] decodedBytes = Base64.decode(codigoValidador, Base64.DEFAULT);
                    Glide.with(getContext())
                            .load(decodedBytes)
                            .into(qrsalida);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                constrainCard.setVisibility(View.GONE);
                textView29.setText("escanear codigo de la identificacion");
                break;
            case "3":
                Log.e("barcodereader","barcode rfc     "+ codigoValidador);
                constrainCard.setVisibility(View.GONE);
                cortina.setVisibility(View.VISIBLE);
                try {
                    byte[] decodedBytes = Base64.decode(codigoValidador, Base64.DEFAULT);
                    Glide.with(getContext())
                            .load(decodedBytes)
                            .into(qrsalida);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                textView23.setVisibility(View.GONE);
                textView29.setText("Resumen");
                break;

        }
    }
    private void bottomSheetSettings() {
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {

            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_DRAGGING:
                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        bottomSheet.setVisibility(View.GONE);
                        closeDialog();
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        break;

                }
            }
            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }
    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);

        // Handle the dismissal of the Salida dialog here
        // You can perform any actions or checks you need

        // For example, you can restart the camera process in BarcodeScannerActivity
        if (getActivity() instanceof BarcodeScannerActivity) {
            if(isCanceled==false) {
                BarcodeScannerActivity barcodeScannerActivity = (BarcodeScannerActivity) getActivity();
                barcodeScannerActivity.restartCameraProcess();
            }else {
                BarcodeScannerActivity barcodeScannerActivity = (BarcodeScannerActivity) getActivity();
                barcodeScannerActivity.restartCameraProcesswithNoChanges();
            }
        }
    }
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogAnimationBottonSheet;
//    }


    public void closeDialog() {
        this.dismiss();

    }
    @Override
    public void errorCode(){
        fulllayout.setVisibility(View.GONE);
        isCanceled=true;
        closeDialog();
        BarcodeScannerActivity barcodeScannerActivity = (BarcodeScannerActivity) getActivity();
        barcodeScannerActivity.errorTicket();


    }
    @Override
    public void setManifestVehicleandDriver(List<dataValidadorV2> data) {
        fulllayout.setVisibility(View.VISIBLE);
        numberManifestsalida.setText(""+data.get(0).getFolioDespacho());
        cedissalida.setText(""+data.get(0).getOrigen());
        vehiculosalida.setText(""+data.get(0).getVehiculoId());
        datesalida.setText(""+data.get(0).getFechaCreacion());
        placasalida.setText(""+data.get(0).getVehiculo().getPlaca());
        regresosalida.setText(""+data.get(0).getTiempoEntrega());
        Log.e("validador",""+data.get(0).getVehiculo().getVin()+" "+data.get(0).getOperador().getRfc()+" ");
        vehiclebarcode=data.get(0).getVehiculo().getBarcodeVehicle();
        rfcBarcode=data.get(0).getOperador().getRfcBarcode();
        vehiclebarcodeVal=data.get(0).getVehiculo().getVin();
        rfcBarcodeVal=data.get(0).getOperador().getRfc();
        claveVehicleID=data.get(0).getVehiculoId();
        if(codigoValidador1.equals("1")){
            BarcodeScannerActivity barcodeScannerActivity = (BarcodeScannerActivity) getActivity();
            barcodeScannerActivity.setVehicleandDriverBarcodes(vehiclebarcode,rfcBarcode,vehiclebarcodeVal,rfcBarcodeVal,claveVehicleID);
        }
        //barcodes data.get(0).getOperador().getRfcBarcode()+data.get(0).getVehiculo().getBarcodeVehicle()
//        BarcodeScannerActivity barcodeScannerActivity1 = (BarcodeScannerActivity) getActivity();
//        barcodeScannerActivity1.setVehicleandDriver(data.get(0).getVehiculo().getEconomico(),data.get(0).getOperador().getId());

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.closeReasons://este fues desabilitado para este bottom sheet
                dismiss();
                break;
            case R.id.imageButton:
                isCanceled=true;
                dismiss();
                break;
            case R.id.clear://este boton resetea los shared de estatus hardcode boton purpura

                break;
            case R.id.gonext:
                isCanceled=false;
                Log.e("validador","gonext "+codigoValidador1);
                BarcodeScannerActivity barcodeScannerActivity = (BarcodeScannerActivity) getActivity();
                if(codigoValidador1.equals(1)){

                    barcodeScannerActivity.goVehicle();
                }else if(codigoValidador1.equals(2)){
                    barcodeScannerActivity.goResumenValidador();
                }else  if(codigoValidador1.equals(3)){
                    barcodeScannerActivity.resumeValidador();
                }

                dismiss();
                break;
        }

    }



}