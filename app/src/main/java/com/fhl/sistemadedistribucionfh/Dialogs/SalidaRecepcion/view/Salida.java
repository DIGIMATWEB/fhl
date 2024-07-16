package com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.view;

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
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.model.cortina.dataCortina;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.model.responseManifestSalidaV2data;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.presenter.salidaViewPresenter;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.presenter.salidaViewPresenterImplements;
import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.Retrofit.GeneralConstants;
import com.fhl.sistemadedistribucionfh.Sellos.model.Sello;
import com.fhl.sistemadedistribucionfh.mlkit.BarcodeScannerActivity;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.modelV2.dataTicketsManifestV2;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Salida extends DialogFragment implements View.OnClickListener, salidaView {
    //todo nota esto debe verificarse
    public static final String TAG = Salida.class.getSimpleName();
    private salidaViewPresenter presenter;
    private BottomSheetBehavior bottomSheetBehavior;
    private ConstraintLayout bottomSheet,cortina;
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
    private Boolean loadCortina=false;
    private loaderFH progress;
    private List<dataTicketsManifestV2> dataTickets;
    private dataCortina dataC;
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
            codigoValidador1= args.getString("statusRecepcion");
            cortinaDestino= args.getString("cortinaDestino");
            mQR= args.getString("mQR");
            currentManifest = args.getString("currentManifest");
        }
        Log.e("motorola",""+cortinaDestino+"  "+ codigoValidador1);
        initDialog(view);
        setUpDialog(codigoValidador1);
        //setFonts();
        return view;
    }
    private void initDialog(View view) {
        // presenter= new dialogReasonsPresenterImpl(this,getContext());
        progress = new loaderFH();
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
        //endregion
       // Toast.makeText(getContext(), "qrCode: "+codigoValidador+" status de recepcion: "+codigoValidador1, Toast.LENGTH_SHORT).show();
        presenter= new salidaViewPresenterImplements(this,getContext());
    }
    private void setUpDialog(String codigoValidador1) {
        Log.e("motorola","setUpDialog:  "+codigoValidador1);
        switch (codigoValidador1) {

            case "1":/** aqui pedimos los manifiestos y las cortinas*/
                textView23.setText("Siguiente paso");
                textView29.setText("Escanear código de la cortina");
                cortina.setVisibility(View.GONE);
                presenter.requestManifest(codigoValidador);
                Log.e("motorola","manifest "+codigoValidador);
                break;
            case "2":/** aqui pedimos los manifiestos y las cortinas*/
                constrainCard.setVisibility(View.GONE);
                cortina.setVisibility(View.VISIBLE);
                try {
                    byte[] decodedBytes = Base64.decode(mQR, Base64.DEFAULT);
                    Glide.with(getContext())
                            .load(decodedBytes)
                            .into(qrsalida);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                textsalida.setText("   "+cortinaDestino);
                textView23.setText("Siguiente paso");
                textView29.setText("Escanea el código de los tickets");
                presenter.requestTickets(currentManifest);
                Log.e("motorola","ticketsM:  "+currentManifest);
                break;
            case "3":
                constrainCard.setVisibility(View.GONE);
                cortina.setVisibility(View.VISIBLE);
                textView23.setText("Siguiente paso");
                textView29.setText("Escanea el código de los sellos");
                presenter.getsellos(currentManifest);
                break;
            case "4"://sellos
                constrainCard.setVisibility(View.GONE);
                cortina.setVisibility(View.VISIBLE);
                textView23.setText("Siguiente paso ");
                textView29.setText("Escanea el código de los sellos");
                Glide.with(getContext())
                        .load(qrsalida)
                        .error(R.drawable.okwarning)
                        .into(qrsalida);
                presenter.getsellos(currentManifest);
                break;
            case "5":
                constrainCard.setVisibility(View.GONE);
                textView23.setVisibility(View.GONE);
                cortina.setVisibility(View.VISIBLE);
                Log.e("fcarga","salida ");
                Glide.with(getContext())
                        .load(qrsalida)
                        .error(R.drawable.okwarning)
                        .into(qrsalida);
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
                Log.e("motorola","isCanceledf:  "+isCanceled);
                BarcodeScannerActivity barcodeScannerActivity = (BarcodeScannerActivity) getActivity();
                barcodeScannerActivity.restartCameraProcess();
            }else {
                Log.e("motorola","isCanceledt:  "+isCanceled);
                BarcodeScannerActivity barcodeScannerActivity = (BarcodeScannerActivity) getActivity();
                barcodeScannerActivity.restartCameraProcesswithNoChanges();
            }
        }
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogAnimationBottonSheet;
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
            progress.show(getChildFragmentManager(), loaderFH.TAG);
        }
    }

    @Override
    public void setManifestCard(List<responseManifestSalidaV2data> data) {
        this.data=data;
        Log.e("datademanifiesto",""+data);
        fillmanifest(data);
        presenter.requestCortinas(codigoValidador);
        presenter.showProgress();
        Log.e("motorola","cortinasM:  "+codigoValidador);
    }

    @Override
    public void setdataCortina(dataCortina data) {
       // Log.e("datadecortina",""+data.getFolioDespacho());
        this.dataC=data;
        BarcodeScannerActivity barcodeScannerActivity1 = (BarcodeScannerActivity) getActivity();
        Log.e("salidaSentrip","destino cortina "+data.getDestino());
        Log.e("salidaSentrip",""+data.getAnden().getQrCodigo());
        Log.e("salidaSentrip",""+data.getAnden().getCodigoAnden());
        Log.e("salidaSentrip",""+codigoValidador);
        barcodeScannerActivity1.setCortina(data.getDestino(),
                data.getAnden().getQrCodigo(),
                data.getAnden().getCodigoAnden(),
                codigoValidador);
        if(data!=null){
            loadCortina=true;
            presenter.hideProgress();
        }
    }

    @Override
    public void goticketsifNull() {
        if(codigoValidador1.equals("2")) {
            BarcodeScannerActivity barcodeScannerActivity1 = (BarcodeScannerActivity) getActivity();
            barcodeScannerActivity1.goTickets();
        }
    }

    @Override
    public void setTickets(List<dataTicketsManifestV2> data) {
        this.dataTickets=data;
        Log.e("ticketsArray","tickets: "+data.size()+" testfirst:" + data.get(0).getFolioTicket());
        BarcodeScannerActivity barcodeScannerActivity1 = (BarcodeScannerActivity) getActivity();
        barcodeScannerActivity1.setTicketsArray(data);

    }

    @Override
    public void setSellos(List<Sello> response) {
        if(response!=null&&!response.isEmpty()) {
            Log.e("ticketsArray", "sellos: " + response.size() + " testfirst:" + response.get(0).getNumeroSello());
            BarcodeScannerActivity barcodeScannerActivity1 = (BarcodeScannerActivity) getActivity();
            barcodeScannerActivity1.setSellosArray(response);
        }else {
//            BarcodeScannerActivity barcodeScannerActivity1 = (BarcodeScannerActivity) getActivity();
//            barcodeScannerActivity1.setSellosNull();

        }
    }

    private void fillmanifest(List<responseManifestSalidaV2data> data) {
        numberManifestsalida.setText(""+data.get(0).getFolioDespacho());
        cedissalida.setText(""+data.get(0).getOrigen());
        vehiculosalida.setText(""+data.get(0).getVehiculo().getEconomico());
        if(data.get(0).getFechaCreacion()!=null){
            convertDate(data.get(0).getFechaCreacion());
            datesalida.setText(""+convertDate(data.get(0).getFechaCreacion()));
        }else{
            datesalida.setText("");
        }
        placasalida.setText(""+data.get(0).getVehiculo().getPlaca());
        regresosalida.setText(""+data.get(0).getTiempoEntrega());
    }
    public static String convertDate(String inputDate) {
        // Step 1: Parse the input date string
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.ENGLISH);
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
    public void closeDialog() {
        this.dismiss();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.closeReasons://este fues desabilitado para este bottom sheet
                dismiss();
                break;
            case R.id.imageButton:
                Log.e("salida","cancelar");
                dismiss();
                isCanceled=true;
                break;
            case R.id.clear://este boton resetea los shared de estatus hardcode boton purpura
                BarcodeScannerActivity barcodeScannerActivity = (BarcodeScannerActivity) getActivity();
                barcodeScannerActivity.resetShared();
                Log.e("salida","reset");
               // Toast.makeText(getContext(), "reset ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.gonext:
                isCanceled=false;//indicador si es que fue cancelado el flujo del escanner
                Log.e("salidaftest",""+codigoValidador1);
                if(codigoValidador1.equals("3")){
                    SharedPreferences preferences = getContext().getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString(GeneralConstants.STATUS_SALIDA, String.valueOf(4));
                    editor.commit();
                    BarcodeScannerActivity barcodeScannerActivity1 = (BarcodeScannerActivity) getActivity();
                    barcodeScannerActivity1.dismissTickets();
                    closeDialog();
                }else if(codigoValidador1.equals("4")) {
                    BarcodeScannerActivity barcodeScannerActivity1 = (BarcodeScannerActivity) getActivity();
                    barcodeScannerActivity1.dismissTickets();
                    barcodeScannerActivity1.setCurrentManifestSellos(currentManifest);
                    //barcodeScannerActivity1.dismissSellos();
                    //barcodeScannerActivity1.godialogCheck();
                    closeDialog();
                }else if(codigoValidador1.equals("5")) {
                    BarcodeScannerActivity barcodeScannerActivity1 = (BarcodeScannerActivity) getActivity();
                    barcodeScannerActivity1.dismissTickets();
                    barcodeScannerActivity1.dismissSellos();
                    barcodeScannerActivity1.godialogCheck();
                    closeDialog();
                }else if(codigoValidador1.equals("1")){
                    SharedPreferences preferences = getContext().getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString(GeneralConstants.STATUS_SALIDA, "2");
                    editor.commit();
                    if(loadCortina){
                        BarcodeScannerActivity barcodeScannerActivity1 = (BarcodeScannerActivity) getActivity();
                        barcodeScannerActivity1.setCortina(dataC.getDestino(),
                                dataC.getAnden().getQrCodigo(),
                                dataC.getAnden().getCodigoAnden(),
                                codigoValidador);
                        closeDialog();
                    }else {
                       // Toast.makeText(getContext(), "Se estan cargando datos espera un momento", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    if(dataTickets!=null){
                        BarcodeScannerActivity barcodeScannerActivity1 = (BarcodeScannerActivity) getActivity();
                        barcodeScannerActivity1.setTicketsArray(dataTickets);
                        Log.e("dataticketsSizeE","manifiesto en sellos "+currentManifest);
                        barcodeScannerActivity1.setCurrentManifestSellos(currentManifest);
                    }
                    closeDialog();
                }


                break;
        }

    }


}
