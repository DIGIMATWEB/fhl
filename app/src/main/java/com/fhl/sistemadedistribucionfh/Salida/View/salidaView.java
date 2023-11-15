package com.fhl.sistemadedistribucionfh.Salida.View;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.Salida.Adapter.adapterTicketsSalida;
import com.fhl.sistemadedistribucionfh.Salida.Model.test.Ticket;
import com.fhl.sistemadedistribucionfh.Salida.Model.v2.ResponseSalida;
import com.fhl.sistemadedistribucionfh.Salida.Presenter.presenterSalida;
import com.fhl.sistemadedistribucionfh.Salida.Presenter.presenterSalidaImpl;
import com.fhl.sistemadedistribucionfh.mainContainer.mainContainer;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.modelV2.dataTicketsManifestV2;

import java.util.List;

import retrofit2.Response;

public class salidaView extends Fragment implements salidaViewinterface, View.OnClickListener {
    public static final String TAG = salidaView.class.getSimpleName();
    private adapterTicketsSalida adapter;
    private RecyclerView rv;
    private presenterSalida presenter;
    private ImageView qrImage;
    private TextView textEnvio;
    private ImageButton continuarSalida;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_salida, container, false);
        initView(view);

        return view;
    }

    private void initView(View view) {
        rv=view.findViewById(R.id.rvTicketsSalida);
        qrImage=view.findViewById(R.id.qrImage);
        textEnvio=view.findViewById(R.id.textEnvio);
        continuarSalida=view.findViewById(R.id.continuarSalida);
        continuarSalida.setOnClickListener(this);
        presenter=new presenterSalidaImpl(this,getContext());
        String code=getArguments().getString("qrValue");
        presenter.requestSalida(code);
    }

    private void fillTickets(List<dataTicketsManifestV2> data) {
        adapter=new adapterTicketsSalida(this,data,getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);
    }

    @Override
    public void setQR(String qr) {
        //Log.e("typeScanner",""+qr);
        byte[] imageByteArray = Base64.decode(qr, Base64.DEFAULT);
        Glide.with(getContext())
                .asBitmap()
                .load(imageByteArray)
                .into(qrImage);
    }

    @Override
    public void setTickets(List<Ticket> data) {


    }
    @Override
    public void setTicketsList(List<dataTicketsManifestV2> data) {
        fillTickets(data);
    }
    @Override
    public void setDireccion(String direccionEntrega) {
        this.textEnvio.setText(direccionEntrega);
    }

    @Override
    public void setSalida(Response<ResponseSalida> response) {
        byte[] decodedBytes = Base64.decode(response.body().getData().get(0).getQrCodigo(), Base64.DEFAULT);
        Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);

// Load the Bitmap using Glide
        Glide.with(getContext())
                .asBitmap()
                .load(decodedBitmap)
                .into(qrImage);
        textEnvio.setText( response.body().getData().get(0).getDespacho().getDestino());
      
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.continuarSalida:
                Intent intent = new Intent(getActivity(), mainContainer.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_CLEAR_TOP);//
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            break;
        }
    }
}
