package com.fhl.sistemadedistribucionfh.gastos.view;

import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.fhl.sistemadedistribucionfh.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

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
    private Integer totalManifest,todalBalance,liquidacion=0;
    private TextView textTotal,liquidaciontext,textViewnL2;
    private Integer noLiquidado=0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chart, container, false);
        Bundle bundle = getArguments();
        if (bundle != null) {
            totalManifest = bundle.getInt("total"); // Retrieve the data using the key
            todalBalance= bundle.getInt("Dispersion");
            liquidacion=bundle.getInt("Liquidacion");
            noLiquidado=todalBalance-liquidacion;
            // Do whatever you need with the data
        }
       // strings.add("No entregado");
        initView(view);

        return view;
    }

    private void initView(View view) {
        textTotal=view.findViewById(R.id.textTotal);
        liquidaciontext=view.findViewById(R.id.liquidacion);

        liquidaciontext.setText(""+liquidacion);
        textViewnL2=view.findViewById(R.id.textViewnL2);
        textViewnL2.setText(""+noLiquidado);
        textTotal.setText("Total de manifiestos: "+totalManifest);
        chart=view.findViewById(R.id.pieChart2);
        chartback=view.findViewById(R.id.chartback);
        chartback.setOnClickListener(this);

        strings.add("Liquidado");
        strings.add("No liquidado");
        strings.add("No liquidado");

        showPieChart();
    }

    private void showPieChart() {
        int total = strings.size(); // total count of elements

        int terminados = Collections.frequency(strings, "Liquidado");
        int cancelados = Collections.frequency(strings, "No liquidado");

        float terminadosPercentage = ((float) liquidacion / todalBalance) * 100;
        float canceladosPercentage = ((float) noLiquidado / todalBalance) * 100;

        ArrayList<PieEntry> pieEntries = new ArrayList<>();

        // Add entries with their respective percentages
        pieEntries.add(new PieEntry(terminadosPercentage, "%"));
        pieEntries.add(new PieEntry(canceladosPercentage, "%"));

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#04BFDA")); // azul
        colors.add(Color.parseColor("#9B88ED")); // morado

        PieDataSet pieDataSet = new PieDataSet(pieEntries, null);
        pieDataSet.setValueTextSize(12f);
        pieDataSet.setValueTextColor(Color.WHITE);
        pieDataSet.setColors(colors);

        // Set a custom value formatter to add "%" symbol
        pieDataSet.setValueFormatter(new PercentFormatter());

        PieData pieData = new PieData(pieDataSet);
        pieData.setDrawValues(true);

        chart.getDescription().setEnabled(false);
        chart.setRotationEnabled(false);
        chart.setCenterText(""+todalBalance);//"Total: " + total); Display total count
        chart.setCenterTextSize(19);
        chart.setHoleRadius(70f);
        chart.getLegend().setEnabled(false);
        chart.setData(pieData);
        chart.animateXY(500, 500);
        chart.invalidate();
    }
    /***
     *  private void showPieChart(){
     *
     *
     *         int terminados = Collections.frequency(strings,"Liquidado");
     *         int cancelados = Collections.frequency(strings,"No liquidado");
     *
     *
     *         ArrayList<PieEntry> pieEntries = new ArrayList<>();
     *         String label = "type";
     *
     *         //initializing data
     *         Map<String, Integer> typeAmountMap = new HashMap<>();
     *         typeAmountMap.put("Liquidado",terminados);//todo este vaalor es en porcentaje  //("terminados",1)
     *         //typeAmountMap.put("No entregado",noentregados);
     *         typeAmountMap.put("No liquidado",cancelados);
     *
     *
     *         //initializing colors for the entries
     *         ArrayList<Integer> colors = new ArrayList<>();
     *         colors.add(Color.parseColor("#04BFDA"));//azul
     *         colors.add(Color.parseColor("#9B88ED"));//morado
     *
     *
     *         //input data and fit data into pie chart entry
     *         for(String type: typeAmountMap.keySet()){
     *             pieEntries.add(new PieEntry(typeAmountMap.get(type)));
     *         }
     *
     *         //collecting the entries with label name
     *         PieDataSet pieDataSet = new PieDataSet(pieEntries,null);
     *         //setting text size of the value
     *         pieDataSet.setValueTextSize(12f);
     *         //providing color list for coloring different entries
     *         Log.e("colorespiechart",""+colors);
     *         pieDataSet.setColors(colors);
     *         //grouping the data set from entry to chart
     *         PieData pieData = new PieData(pieDataSet);
     *         //showing the value of the entries, default true if not set
     *         chart.getDescription().setEnabled(false);
     *
     *         chart.setRotationEnabled(false);
     *         chart.setCenterText(2+" de "+String.valueOf(12));
     *         pieData.setDrawValues(true);
     *         chart.setHoleRadius(70f);
     *         chart.getLegend().setEnabled(false);
     *         chart.setData(pieData);
     *         chart.animateXY(500,500);
     *         chart.invalidate();
     *     }*/
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
