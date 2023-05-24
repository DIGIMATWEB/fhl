package com.fhl.sistemadedistribucionfh.cerrarViaje.view;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.cerrarViaje.adapter.adapterNoCompletado;
import com.fhl.sistemadedistribucionfh.evidence.documents.documents;
import com.fhl.sistemadedistribucionfh.nmanifest.adapter.manifestAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class cerrarViaje  extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = cerrarViaje.class.getSimpleName();
    private RecyclerView rv;
    private adapterNoCompletado adapter;
    private ImageView trashicon;
    private List<String> vcurrentSelected,moldlist;
    private int sizeAfterErase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cerrar_viaje);
        initView();
        fillAdapter(getApplicationContext());
    }

    private void fillAdapter(Context applicationContext) {
        adapter=new adapterNoCompletado(this,getApplicationContext());
        LinearLayoutManager linearLayoutManager = new GridLayoutManager(applicationContext,3);
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(adapter);
    }

    private void initView() {
        rv=findViewById(R.id.carrusel);
        trashicon=findViewById(R.id.trashicon);
        trashicon.setOnClickListener(this);
    }
    /**este metodo muestra el icono de eliminar y guarda el arreglo de items a eliminar*/
    public void sawTrash(List<String> currentSelected) { //este metodo recibe dos listas la primera es de items seleccionados la segunda deberia ser data
        List<String> oldList=new ArrayList<>();
        oldList.add("1");
        oldList.add("2");
        oldList.add("3");
        oldList.add("4");
        oldList.add("5");
        oldList.add("6");
        oldList.add("7");
        oldList.add("8");
        oldList.add("9");
        this.moldlist=oldList;
        this.vcurrentSelected=currentSelected;
        for(String value:currentSelected){
            Log.e("borrarFotos",""+value);
        }
        if(!currentSelected.isEmpty()){
            trashicon.setVisibility(View.VISIBLE);
        }
    }
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.trashicon:

                adapter.updateSize(moldlist.size(),true);
               //todo esto arreglo remueve los indexes de forma incorre
                break;
        }
    }


}
