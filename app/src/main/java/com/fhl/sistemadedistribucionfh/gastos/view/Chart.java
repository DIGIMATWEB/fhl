package com.fhl.sistemadedistribucionfh.gastos.view;

import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.fhl.sistemadedistribucionfh.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Chart extends Fragment implements View.OnClickListener {
    public static final String TAG = Chart.class.getSimpleName();
    private List<String> strings = new ArrayList<>();
    private PieChart chart;
    private ImageView chartback;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chart, container, false);
        strings.add("Liquidado");
        strings.add("No liquidado");
       // strings.add("No entregado");
        initView(view);

        return view;
    }

    private void initView(View view) {
        chart=view.findViewById(R.id.pieChart2);
        chartback=view.findViewById(R.id.chartback);
        chartback.setOnClickListener(this);
        showPieChart();
    }

    private void showPieChart(){
//
//        int terminados = Collections.frequency(strings,"Terminado");
//        int cancelados = Collections.frequency(strings,"Cancelado");
//        int noentregados = Collections.frequency(strings,"No entregado");

        int terminados = Collections.frequency(strings,"Liquidado");
        int cancelados = Collections.frequency(strings,"No liquidado");


        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        String label = "type";

        //initializing data
        Map<String, Integer> typeAmountMap = new HashMap<>();
        typeAmountMap.put("terminados",terminados);//todo este vaalor es en porcentaje  //("terminados",1)
        //typeAmountMap.put("No entregado",noentregados);
        typeAmountMap.put("cancelados",cancelados);


        //initializing colors for the entries
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#04BFDA"));//rojo
        //colors.add(Color.parseColor("#8064667A"));//gris
        colors.add(Color.parseColor("#9B88ED"));//Verde




/*
        if (terminados != 0){
            binding.botonentregados.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.shapecolors1));
            binding.botonentregados.setTextColor(Color.parseColor("#ffffff"));
        }else {
            binding.botonentregados.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.shapecolorse));
            binding.botonentregados.setTextColor(Color.parseColor("#000000"));
        }

        if (noentregados != 0){
            binding.botonnoentregados.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.shapecolor4));
            binding.botonnoentregados.setTextColor(Color.parseColor("#ffffff"));
        }else {
            binding.botonnoentregados.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.shapecolorsne));
            binding.botonnoentregados.setTextColor(Color.parseColor("#000000"));
        }

        if (cancelados != 0){
            binding.cancelados.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.shapecolor5));
            binding.cancelados.setTextColor(Color.parseColor("#ffffff"));
        }else {
            binding.cancelados.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.shapecolorsc));
            binding.cancelados.setTextColor(Color.parseColor("#000000"));
        }
*/

        //input data and fit data into pie chart entry
        for(String type: typeAmountMap.keySet()){
            pieEntries.add(new PieEntry(typeAmountMap.get(type)));
        }

        //collecting the entries with label name
        PieDataSet pieDataSet = new PieDataSet(pieEntries,null);
        //setting text size of the value
        pieDataSet.setValueTextSize(12f);
        //providing color list for coloring different entries
        Log.e("colorespiechart",""+colors);
        pieDataSet.setColors(colors);
        //grouping the data set from entry to chart
        PieData pieData = new PieData(pieDataSet);
        //showing the value of the entries, default true if not set
        chart.getDescription().setEnabled(false);

        chart.setRotationEnabled(true);
        chart.setCenterText(2+" de "+String.valueOf(12));
        pieData.setDrawValues(true);
        chart.setHoleRadius(70f);
        chart.getLegend().setEnabled(false);
        chart.setData(pieData);
        chart.animateXY(500,500);
        chart.invalidate();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.chartback:
                lastFragment();
                break;
        }
    }
    private void lastFragment(){

        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        gastos mgastos= new gastos();
        transaction.replace(R.id.fragments, mgastos, gastos.TAG).commit();

    }
}
