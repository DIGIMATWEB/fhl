package com.fhl.sistemadedistribucionfh.Dialogs.validador.ValidadorV2.view;

import android.app.AlertDialog;
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
import com.fhl.sistemadedistribucionfh.Dialogs.Loader.view.loaderFH;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.model.responseManifestSalidaV2data;
import com.fhl.sistemadedistribucionfh.Dialogs.validador.ValidadorV2.model.dataValidadorV2;
import com.fhl.sistemadedistribucionfh.Dialogs.validador.ValidadorV2.presenter.presenterValidadorDetail;
import com.fhl.sistemadedistribucionfh.Dialogs.validador.ValidadorV2.presenter.presenterValidadorImplements;
import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.Retrofit.GeneralConstants;
import com.fhl.sistemadedistribucionfh.login.model.modelProfile.profileResponse;
import com.fhl.sistemadedistribucionfh.mlkit.BarcodeScannerActivity;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.modelV2.dataTicketsManifestV2;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
    private String vehiclebarcode,rfcBarcode="";
    private String vehiclebarcodeVal,rfcBarcodeVal;
    private Integer claveVehicleID;
    private  List<dataValidadorV2> mdata=new ArrayList<>();
    private List<dataTicketsManifestV2> tdata;
    private Boolean ticketsEntregaExist=false;
    private Boolean loadVin=false;
    private loaderFH progress;
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
            mdata.clear();
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
        progress = new loaderFH();
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
                textView29.setText("Escanear código del vehículo");
                Gson gson = new Gson();
                SharedPreferences preferences = getContext().getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
                String token2 = preferences.getString(GeneralConstants.USER_VALUES, null);
                profileResponse profileData = gson.fromJson(token2, profileResponse.class);
                int idEmpleado = profileData.getUsuarioId();
                Log.e("validador","validator: "+idEmpleado+" manifest: "+currentManifest);
                presenter.requestManifestDetail( idEmpleado,currentManifest);
                presenter.getTicketByManifest(currentManifest);
                presenter.showProgress();
                break;
            case "2":/** aqui pedimos los manifiestos y las cortinas*/
                //aqui visible el vehiculo
                constrainCard.setVisibility(View.GONE);
                cortina.setVisibility(View.VISIBLE);
                SharedPreferences preferences1 = getContext().getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
                String vehicle = preferences1.getString(GeneralConstants.VIN_CURRENT_VALIDADOR, null);
                Log.e("barcodereader","barcode vehicle "+ vehicle);
                try {
                    byte[] decodedBytes = Base64.decode(vehicle, Base64.DEFAULT);
                    Glide.with(getContext())
                            .load(decodedBytes)
                            .error(R.drawable.okwarning)
                            .into(qrsalida);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                constrainCard.setVisibility(View.GONE);
                textView29.setText("Escanear código de la identificación");
                break;
            case "3":/***aqui se pide rfc*/

                constrainCard.setVisibility(View.GONE);
                cortina.setVisibility(View.VISIBLE);
                SharedPreferences preferences2 = getContext().getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
                String rfc = preferences2.getString(GeneralConstants.RFC_CURRENT_VALIDADOR, null);
                Log.e("barcodereader","barcode rfc     "+ rfc);
                try {
                    byte[] decodedBytes = Base64.decode(rfc, Base64.DEFAULT);
                    Glide.with(getContext())
                            .load(decodedBytes)
                            .error(R.drawable.okwarning)
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
        barcodeScannerActivity.errorTicket("Manifiesto no valido");


    }
    @Override
    public void hideProgress() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (progress != null && this != null)
                    if(progress.isAdded()) {
                        progress.dismiss();
                    }
            }
        }, 300);
    }

    @Override
    public void showProgress() {
        if (progress != null && !progress.isVisible()) {
            Bundle bundle = new Bundle();
            bundle.putBoolean("HAS_TITLE", false);
            bundle.putString("title","Cargando detalles");
            progress.setArguments(bundle);
            progress.show(getParentFragmentManager(), loaderFH.TAG);
        }
    }
    @Override
    public void setDetailTickets(List<dataTicketsManifestV2> data) {//solicita todos los tickets del manifiesto
        this.tdata=data;
        if(data!=null){
            Boolean isAtListOne=true;
            for(dataTicketsManifestV2 ticket:data){
                if(ticket.getTipoEntregaId()==2){
                    //Log.e("SendTicket", "Datos: " + this.data.get(0).getValidador().getEstatus());
                    if (ticket.getEstatus().equals("correcto")) {
                        //isAtListOne=true;
                    } else {
                        isAtListOne=false;
                    }
                    break;
                }else {

                }

            }
            if(!isAtListOne){//si al menos har un ticket en entrega ()
               // showToast();
                if(mdata!=null) {

                    ticketsEntregaExist=true;
                }
                //adapter.updateManifest(this.data);
            }else{
                //gotoTicketsDetail();
                ticketsEntregaExist=false;
            }
        }else {
           // Toast.makeText(getContext(), "No hay ningun ticket", Toast.LENGTH_SHORT).show();

        }
    }
    private void setCurrentFlow(String rfcBarcode, String barcodeVehicle) {
        SharedPreferences preferencias = getContext().getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencias.edit();
        editor.putString(GeneralConstants.RFC_CURRENT_VALIDADOR, rfcBarcode);
        editor.putString(GeneralConstants.VIN_CURRENT_VALIDADOR, barcodeVehicle);
        // editor.putString(GeneralConstants.CVE_EMPLOYE,String.valueOf(data.getCve_employee()));
        editor.commit();
    }
    @Override
    public void setManifestVehicleandDriver(List<dataValidadorV2> data) {
        this.mdata=data;

        fulllayout.setVisibility(View.VISIBLE);
        numberManifestsalida.setText(""+data.get(0).getFolioDespacho());
        cedissalida.setText(""+data.get(0).getOrigen());
        vehiculosalida.setText(""+data.get(0).getVehiculo().getEconomico());
        String fechaSalida = "";
        if(data.get(0).getFechaCreacion()!=null){
            fechaSalida="" +convertDate(data.get(0).getFechaCreacion());
        }
        datesalida.setText(""+fechaSalida);
        placasalida.setText(""+data.get(0).getVehiculo().getPlaca());
        regresosalida.setText(""+data.get(0).getTiempoEntrega());
        Log.e("validador",""+data.get(0).getVehiculo().getVin()+" "+data.get(0).getOperador().getRfc()+" ");
        vehiclebarcode=data.get(0).getVehiculo().getBarcodeVehicle();
        rfcBarcode=data.get(0).getOperador().getRfcBarcode(); // Es el StringBase 64 de la imagen QR del RFC
        vehiclebarcodeVal=data.get(0).getVehiculo().getVin();
        rfcBarcodeVal=data.get(0).getOperador().getRfc(); // Esto es el String del RFC
        claveVehicleID=data.get(0).getVehiculoId();
        if(codigoValidador1.equals("1")){
            setCurrentFlow(data.get(0).getOperador().getRfcBarcode(),data.get(0).getVehiculo().getBarcodeVehicle());
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (codigoValidador1.equals("1")) {
                    BarcodeScannerActivity barcodeScannerActivity = (BarcodeScannerActivity) getActivity();
                    if (data.get(0).getEstatus().getId() == 2) { // si el estatus es confirmado verde y no en progreso
                        if (tdata != null) {
                            if (ticketsEntregaExist) {
                                barcodeScannerActivity.errorCarga("No se ha realizado la carga");
                                dismiss();
                            } else {
                                barcodeScannerActivity.errorCarga("No hay tickets de entrega para el manifiesto");
                                dismiss();
                            }
                        } else {
                            barcodeScannerActivity.errorCarga("No se ha realizado la carga..");
                            dismiss();
                        }
                    } else {
                        if (data.get(0).getValidador().getEstatus().equals("correcto")) {
                            barcodeScannerActivity.errorCarga("El manifiesto ya fue validado");
                            dismiss();
                        } else { // si es en progreso
                            if (tdata != null) {
                                if (ticketsEntregaExist) {
                                    barcodeScannerActivity.setVehicleandDriverBarcodes(vehiclebarcode,
                                            rfcBarcode,
                                            vehiclebarcodeVal,
                                            rfcBarcodeVal,
                                            claveVehicleID);
                                } else {
                                    barcodeScannerActivity.errorCarga("No hay tickets de entrega para el manifiesto");
                                    dismiss();
                                }
                            } else {
                                barcodeScannerActivity.setVehicleandDriverBarcodes(vehiclebarcode, rfcBarcode, vehiclebarcodeVal, rfcBarcodeVal, claveVehicleID);
                            }

                        }
                    }
                }
                if(mdata!=null){
                    loadVin=true;
                    presenter.hideProgress();
                }
            }
        }, 500); // 2000 milliseconds = 2 seconds
        //barcodes data.get(0).getOperador().getRfcBarcode()+data.get(0).getVehiculo().getBarcodeVehicle()
//        BarcodeScannerActivity barcodeScannerActivity1 = (BarcodeScannerActivity) getActivity();
//        barcodeScannerActivity1.setVehicleandDriver(data.get(0).getVehiculo().getEconomico(),data.get(0).getOperador().getId());

    }

    public static String convertDate(String inputDate) {
        // Step 1: Parse the input date string
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS", Locale.ENGLISH);
        Date date = null;
        try {
            date = inputFormat.parse(inputDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

        // Step 2: Format the date to the desired output format
        SimpleDateFormat outputFormat = new SimpleDateFormat("EEE | dd/MM/yy | HH:mm", new Locale("es", "ES"));
        String formattedDate = outputFormat.format(date);

        // Step 3: Capitalize the first letter of the formatted date string
        if (formattedDate != null && !formattedDate.isEmpty()) {
            formattedDate = formattedDate.substring(0, 1).toUpperCase() + formattedDate.substring(1);
        }
        return formattedDate;
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
                Log.e("validadorftest","gonext "+codigoValidador1);
                BarcodeScannerActivity barcodeScannerActivity = (BarcodeScannerActivity) getActivity();
                if(codigoValidador1.equals(1)){

                    if(loadVin){
                        barcodeScannerActivity.goVehicle();
                        dismiss();
                    }else {
                        //Toast.makeText(getContext(), "Se estan cargando datos espera un momento", Toast.LENGTH_SHORT).show();
                    }
                }else if(codigoValidador1.equals(2)){
                    barcodeScannerActivity.goResumenValidador();
                    dismiss();
                }else  if(codigoValidador1.equals(3)){
                    barcodeScannerActivity.resumeValidador();
                    dismiss();
                }else{
                    dismiss();
                }


                break;
        }

    }



}