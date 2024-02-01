package com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.view;

import android.content.DialogInterface;
import android.os.Bundle;
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
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.model.cortina.dataCortina;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.model.responseManifestSalidaV2data;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.presenter.salidaViewPresenter;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.presenter.salidaViewPresenterImplements;
import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.Sellos.model.Sello;
import com.fhl.sistemadedistribucionfh.mlkit.BarcodeScannerActivity;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.modelV2.dataTicketsManifestV2;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.List;

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
        Log.e("datadecortina",""+cortinaDestino);
        initDialog(view);
        setUpDialog(codigoValidador1);
        //setFonts();
        return view;
    }
    private void initDialog(View view) {
        // presenter= new dialogReasonsPresenterImpl(this,getContext());
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
        Toast.makeText(getContext(), "qrCode: "+codigoValidador+" status de recepcion: "+codigoValidador1, Toast.LENGTH_SHORT).show();
        presenter= new salidaViewPresenterImplements(this,getContext());
    }
    private void setUpDialog(String codigoValidador1) {
        switch (codigoValidador1) {
            case "1":/** aqui pedimos los manifiestos y las cortinas*/
                textView23.setText("siguiente paso");
                textView29.setText("escanear codigo de la cortina");
                cortina.setVisibility(View.GONE);
                presenter.requestManifest(codigoValidador);
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
                textView23.setText("siguiente paso");
                textView29.setText("escanea el codigo de los tickets");
                presenter.requestTickets(currentManifest);
                break;
            case "3":
                textView23.setText("siguiente paso");
                textView29.setText("escanea el codigo de los sellos");
                break;
            case "4":
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
            BarcodeScannerActivity barcodeScannerActivity = (BarcodeScannerActivity) getActivity();
            barcodeScannerActivity.restartCameraProcess();
        }
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogAnimationBottonSheet;
    }


    @Override
    public void hideProgress() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void setManifestCard(List<responseManifestSalidaV2data> data) {
        this.data=data;
        Log.e("datademanifiesto",""+data);
        fillmanifest(data);
        presenter.requestCortinas(codigoValidador);
    }

    @Override
    public void setdataCortina(dataCortina data) {
        Log.e("datadecortina",""+data.getFolioDespacho());
        BarcodeScannerActivity barcodeScannerActivity1 = (BarcodeScannerActivity) getActivity();
        barcodeScannerActivity1.setCortina(data.getDestino(),data.getAnden().getQrCodigo(),data.getAnden().getCodigoAnden(),codigoValidador);

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
        Log.e("ticketsArray","tickets: "+data.size()+" testfirst:" + data.get(0).getFolioTicket());
        BarcodeScannerActivity barcodeScannerActivity1 = (BarcodeScannerActivity) getActivity();
        barcodeScannerActivity1.setTicketsArray(data);
        presenter.getsellos(currentManifest);
    }

    @Override
    public void setSellos(List<Sello> response) {
        Log.e("ticketsArray","sellos: "+response.size()+" testfirst:" + response.get(0).getNumeroSello());
        BarcodeScannerActivity barcodeScannerActivity1 = (BarcodeScannerActivity) getActivity();
        barcodeScannerActivity1.setSellosArray(response);
    }

    private void fillmanifest(List<responseManifestSalidaV2data> data) {
        numberManifestsalida.setText(""+data.get(0).getFolioDespacho());
        cedissalida.setText(""+data.get(0).getOrigen());
        vehiculosalida.setText(""+data.get(0).getVehiculoId());
        datesalida.setText(""+data.get(0).getFechaCreacion());
        placasalida.setText(""+data.get(0).getVehiculo().getPlaca());
        regresosalida.setText(""+data.get(0).getTiempoEntrega());
    }

    public void closeDialog() {
        this.dismiss();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.closeReasons:
                dismiss();
                break;
            case R.id.imageButton:
                closeDialog();
                break;
            case R.id.clear:
                BarcodeScannerActivity barcodeScannerActivity = (BarcodeScannerActivity) getActivity();
                barcodeScannerActivity.resetShared();
                Toast.makeText(getContext(), "reset ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.gonext:
                if(codigoValidador1.equals("4")) {
                BarcodeScannerActivity barcodeScannerActivity1 = (BarcodeScannerActivity) getActivity();
                barcodeScannerActivity1.godialogCheck();
                }
        }

    }


}
