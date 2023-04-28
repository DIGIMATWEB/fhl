package com.companyname.mauitest.checkList.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.companyname.mauitest.Profile.view.viewProfile;
import com.companyname.mauitest.R;
import com.companyname.mauitest.Salida.Adapter.adapterSellos;
import com.companyname.mauitest.Salida.Model.Sello;
import com.companyname.mauitest.checkList.adapter.adapterChecklist;
import com.companyname.mauitest.resguardo.adapter.adapterResguardo;

import java.util.List;

public class checkList extends Fragment implements View.OnClickListener{

    public static final String TAG = checkList.class.getSimpleName();
    private RecyclerView rv;
    private adapterChecklist adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_checklist, container, false);
        initView(view);
        fillSellos();
        return view;
    }
    private void fillSellos() {
        adapter=new adapterChecklist(getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);
    }
    private void initView(View view) {
        rv=view.findViewById(R.id.rvchecklist);
    }

    @Override
    public void onClick(View v) {

    }
}
