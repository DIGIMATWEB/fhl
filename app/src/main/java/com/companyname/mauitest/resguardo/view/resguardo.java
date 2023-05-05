package com.companyname.mauitest.resguardo.view;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.companyname.mauitest.Profile.view.viewProfile;
import com.companyname.mauitest.R;
import com.companyname.mauitest.Salida.Adapter.adapterSellos;
import com.companyname.mauitest.Salida.Model.Sello;
import com.companyname.mauitest.resguardo.adapter.adapterResguardo;

import java.util.List;

public class resguardo extends Fragment implements View.OnClickListener {
    public static final String TAG = resguardo.class.getSimpleName();
    private RecyclerView rv;
    private adapterResguardo adapter;
    private SearchView searchView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_resguardo, container, false);
        initView(view);
        fillSellos();
        return view;
    }
    private void fillSellos() {
        adapter=new adapterResguardo(getContext());
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
    }
    @Override
    public void onClick(View v) {

    }
}
