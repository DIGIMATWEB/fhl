package com.fhl.sistemadedistribucionfh.Visor.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.Visor.adapter.adapterVisor;

public class visorViewImpl extends Fragment implements  visorView {
    public static final String TAG = visorViewImpl.class.getSimpleName();
    private adapterVisor adapter;
    private RecyclerView rv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_visor, container, false);
        //initView(view);
        rv=view.findViewById(R.id.rvVisor);
        fillAdapter();
        return view;
    }

    private void fillAdapter() {
        adapter=new adapterVisor(getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);
    }
}
