package com.fhl.sistemadedistribucionfh.gastos.view;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.gastos.adapter.adapterGastos;
import com.fhl.sistemadedistribucionfh.gastos.model.gastosV2.GastosOperativo;
import com.fhl.sistemadedistribucionfh.gastos.model.gastosV2.dataGastosOperativos;
import com.fhl.sistemadedistribucionfh.gastos.presenter.presenterGastos;
import com.fhl.sistemadedistribucionfh.gastos.presenter.presenterGastosImpl;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class gastos extends Fragment implements View.OnClickListener,gastosView {
    public static final String TAG = gastos.class.getSimpleName();
    private RecyclerView rv;
    private adapterGastos adapter;
    private SearchView searchView;
    private List<GastosOperativo> data;
    private List<dataGastosOperativos> maindata;
    private presenterGastos presenter;
    private ImageView chart;
    private Integer total=0;
    private Integer dispercion;
    private Integer liquinacion;
    private Integer noLiquidado;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gastos, container, false);
        initView(view);

        return view;
    }
    private void fillGastos(List<dataGastosOperativos> maindata) {
        adapter=new adapterGastos(getContext(),maindata);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);
    }
    private void initView(View view) {
        rv=view.findViewById(R.id.rvgastos);
        chart=view.findViewById(R.id.chart);
        chart.setOnClickListener(this);
        searchView=view.findViewById(R.id.searchViewgastos);
        searchView.setQueryHint("Buscar manifiesto");
        Drawable background= getContext().getDrawable(R.drawable.shape_button);
        searchView.setIconified(false);
        searchView.setBackground(background);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<dataGastosOperativos> filterList =filter(maindata,newText);
                adapter.setFilter(filterList);
                return true;
            }
        });
        presenter= new presenterGastosImpl(this,getContext());
        presenter.getGastos();
    }
    private List<dataGastosOperativos> filter(List<dataGastosOperativos> data, String text) {
        List<dataGastosOperativos> mfilterList= new ArrayList<>();
        text =text.toLowerCase();
        if(data!=null){
            for(dataGastosOperativos gastosList:data)
            {
                String manifestname=gastosList.getFolioDespacho();
                if(manifestname.contains(text)){
                    mfilterList.add(gastosList);
                }
            }
        }
        return mfilterList;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.chart:
                gochart();
                break;
        }
    }
    private void gochart(){
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Chart grafica= new Chart();
        Bundle bundle = new Bundle();
        bundle.putInt("total", total);
        bundle.putInt("Dispersion",dispercion);
        bundle.putInt("Liquidacion",liquinacion);
        grafica.setArguments(bundle);
        transaction.replace(R.id.fragments, grafica, Chart.TAG).commit();
    }

    @Override
    public void setDataGastos(List<dataGastosOperativos> data) {
        this.maindata=data;
        Gson gson = new Gson();
        String jsonString = gson.toJson(maindata);
        Log.e("json",jsonString);
        total=maindata.size();
        calculateMoney(maindata);
        fillGastos(maindata);
    }

    private void calculateMoney(List<dataGastosOperativos> maindata) {
        dispercion=0;//total de balance
        liquinacion=0;//
        noLiquidado=0;//balance-liquidado
        for(dataGastosOperativos alldispercion:maindata){
            for(int i=0;i<alldispercion.getGastosOperativos().size();i++) {
                if (alldispercion.getGastosOperativos().get(i).getDispersion().getMonto()!=null){
                    if(alldispercion.getGastosOperativos().get(i).getDispersion().getMoneda().getId()==2) {
                        Double convertion= (alldispercion.getGastosOperativos().get(i).getDispersion().getMonto()*16.39);
                        Integer round= Math.toIntExact(Math.round(convertion));
                        Log.e("gastos","dolares  dispersion");
                        dispercion = dispercion +round;
                    }else {
                        Log.e("gastos","peso  liquidacion");
                       dispercion = dispercion + alldispercion.getGastosOperativos().get(i).getDispersion().getMonto();
                    }
                }
                if(alldispercion.getGastosOperativos().get(i).getLiquidacion()!=null){
                    if(alldispercion.getGastosOperativos().get(i).getLiquidacion().getMoneda().getId()==2) {//si es dolar
                       Double convertion= (alldispercion.getGastosOperativos().get(i).getLiquidacion().getMonto()*16.39);
                       Integer round= Math.toIntExact(Math.round(convertion));
                        Log.e("gastos","dolares  liquidacion");
                        liquinacion=liquinacion+round;
                    }else {//sso es peso
                        Log.e("gastos","peso liquidacion");
                        liquinacion=liquinacion+alldispercion.getGastosOperativos().get(i).getLiquidacion().getMonto();
                    }

                }
            }
        }
        Log.e("gastos","liquidacion "+liquinacion+" dispersion "+dispercion);
    }
}
