package com.fhl.sistemadedistribucionfh.gastos.view;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.gastos.adapter.adapterGastos;
import com.fhl.sistemadedistribucionfh.gastos.model.dataGastos;
import com.fhl.sistemadedistribucionfh.gastos.presenter.presenterGastos;
import com.fhl.sistemadedistribucionfh.gastos.presenter.presenterGastosImpl;

import java.util.ArrayList;
import java.util.List;

public class gastos extends Fragment implements View.OnClickListener,gastosView {
    public static final String TAG = gastos.class.getSimpleName();
    private RecyclerView rv;
    private adapterGastos adapter;
    private SearchView searchView;
    private List<dataGastos> data;
    private presenterGastos presenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gastos, container, false);
        initView(view);

        return view;
    }
    private void fillSellos( List<dataGastos> data) {
        adapter=new adapterGastos(data,getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);
    }
    private void initView(View view) {
        rv=view.findViewById(R.id.rvgastos);
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
                List<dataGastos> filterList =filter(data,newText);
                adapter.setFilter(filterList);
                return true;
            }
        });
        presenter= new presenterGastosImpl(this,getContext());
        presenter.getGastos();
    }
    private List<dataGastos> filter(List<dataGastos> data, String text) {
        List<dataGastos> mfilterList= new ArrayList<>();
        text =text.toLowerCase();
        if(data!=null){
            for(dataGastos gastosList:data)
            {
                String manifestname=gastosList.getManifestGastos().toLowerCase();
                if(manifestname.contains(text)){
                    mfilterList.add(gastosList);
                }
            }
        }
        return mfilterList;
    }
    @Override
    public void onClick(View v) {

    }

    @Override
    public void setDataGastos(List<dataGastos> data) {
        this.data=data;
        fillSellos(data);
    }
}
