package com.fhl.sistemadedistribucionfh.resguardo.view;

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
import com.fhl.sistemadedistribucionfh.resguardo.adapter.adapterResguardo;
import com.fhl.sistemadedistribucionfh.resguardo.model.dataResguardo;
import com.fhl.sistemadedistribucionfh.resguardo.presenter.presenterResguardo;
import com.fhl.sistemadedistribucionfh.resguardo.presenter.presenterResguardosImpl;

import java.util.ArrayList;
import java.util.List;

public class resguardo extends Fragment implements View.OnClickListener , resguardoView {
    public static final String TAG = resguardo.class.getSimpleName();
    private RecyclerView rv;
    private adapterResguardo adapter;
    private SearchView searchView;
    private List<dataResguardo> data;
    private presenterResguardo presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_resguardo, container, false);
        initView(view);

        return view;
    }
    private void fillSellos(List<dataResguardo> data) {
        adapter=new adapterResguardo(data,getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);
    }
    private void initView(View view) {
        rv=view.findViewById(R.id.rvresguardo);
        searchView=view.findViewById(R.id.searchViewResguardo);
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
                List<dataResguardo> filterList =filter(data,newText);
                adapter.setFilter(filterList);
                return true;
            }
        });
        presenter= new presenterResguardosImpl(this,getContext());
        presenter.getResguardos();

    }

    private List<dataResguardo> filter(List<dataResguardo> data, String text) {
        List<dataResguardo> mfilterList= new ArrayList<>();
        text =text.toLowerCase();
        if(data!=null){
            for(dataResguardo resguardoList:data)
            {
                String manifestname=resguardoList.getArticulo().toLowerCase();
                if(manifestname.contains(text)){
                    mfilterList.add(resguardoList);
                }
            }
        }
        return mfilterList;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void setResguardos(List<dataResguardo> data) {
        this.data=data;
        fillSellos(data);
    }
}
