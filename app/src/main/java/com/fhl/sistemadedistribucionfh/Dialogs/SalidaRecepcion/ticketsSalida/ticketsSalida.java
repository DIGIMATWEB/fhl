package com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.ticketsSalida;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fhl.sistemadedistribucionfh.Dialogs.Reasons.adapter.adapterReasons;
import com.fhl.sistemadedistribucionfh.Dialogs.Reasons.model.dataReasons;
import com.fhl.sistemadedistribucionfh.Dialogs.Reasons.presenter.dialogReasonsPresenter;
import com.fhl.sistemadedistribucionfh.Dialogs.Reasons.presenter.dialogReasonsPresenterImpl;
import com.fhl.sistemadedistribucionfh.Dialogs.Reasons.view.dialogReasons;
import com.fhl.sistemadedistribucionfh.Dialogs.Reasons.view.dialogReasonsView;
import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.modelV2.dataTicketsManifestV2;

import java.util.List;

public class ticketsSalida extends DialogFragment implements View.OnClickListener {
    public static final String TAG = ticketsSalida.class.getSimpleName();
    private RecyclerView rvReasons;
//    private adapterReasons adapter;
//    private dialogReasonsPresenter presenter;
    private ImageView closeReasons;
    private  List<dataTicketsManifestV2> codigoValidador;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_DeviceDefault_Light_NoActionBar);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_tickets, container, false);
        getDialog().getWindow().setBackgroundDrawableResource(R.color.alfa);
        setCancelable(true);
        Bundle args = getArguments();
        if (args != null) {
            codigoValidador= (List<dataTicketsManifestV2>) args.getSerializable("tickets");
        }
        initDialog(view);
        if(codigoValidador!=null) {
            Log.e("ticketsArray", "adapter size" + codigoValidador.size());
        }
        //setFonts();
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogAnimationBottonSheet;
    }
    private void initDialog(View view) {
        rvReasons=view.findViewById(R.id.rvTickets);
        //presenter= new dialogReasonsPresenterImpl(this,getContext());


        //presenter.requestMReasons();
    }

    private void fillAdapter(List<dataTicketsManifestV2> data, Context context) {
//        adapter = new adapterReasons(data,context);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
//        rvReasons.setLayoutManager(linearLayoutManager);
//        rvReasons.setAdapter(adapter);
    }

    public void closeDialog() {
        this.dismiss();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.closeReasons:
//                dismiss();
//                break;
        }
    }

    public void sendToast() {
        Toast.makeText(getContext(), "ticket escaneado", Toast.LENGTH_SHORT).show();
    }
}
