package com.fhl.sistemadedistribucionfh.Salida.View;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.Salida.Adapter.adapterSellos;
import com.fhl.sistemadedistribucionfh.Salida.Model.test.Sello;

import java.util.ArrayList;
import java.util.List;

public class sellos extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = sellos.class.getSimpleName();
    private List<Sello> sellos;
    private RecyclerView rv;
    private adapterSellos adapter;
    private ImageButton continuarSalida;
    private SearchView searchViewSello ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sellos);
        initView();
        Bundle bndl;
        bndl = getIntent().getExtras();//detailOrderB
        if(bndl!=null) {
          sellos= (List<Sello>) bndl.getSerializable("sellos");
          fillSellos(sellos);
        }

    }

    private void fillSellos(List<Sello> sellos) {
          adapter=new adapterSellos(this,sellos,getApplicationContext());
          LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
          rv.setLayoutManager(layoutManager);
          rv.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void initView() {
        rv=findViewById(R.id.rvsellos);
        continuarSalida=findViewById(R.id.continuarSalida);
        continuarSalida.setOnClickListener(this);
        searchViewSello=findViewById(R.id.searchViewSello);
        searchViewSello.setQueryHint("Buscar manifiesto");
        Drawable background= getApplicationContext().getDrawable(R.drawable.shape_button);
        searchViewSello.setIconified(false);
        searchViewSello.setBackground(background);
        searchViewSello.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<Sello> filterList =filter(sellos,newText);
                adapter.setFilter(filterList);
                return true;
            }
        });

    }
    private List<Sello> filter(List<Sello> data, String text) {
        List<Sello> mfilterList= new ArrayList<>();
        text =text.toLowerCase();
        if(data!=null){
            for(Sello resguardoList:data)
            {
                String manifestname=resguardoList.getNombreSello().toLowerCase();
                if(manifestname.contains(text)){
                    mfilterList.add(resguardoList);
                }
            }
        }
        return mfilterList;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.continuarSalida:
                onBackPressed();
                break;
        }
    }
}
