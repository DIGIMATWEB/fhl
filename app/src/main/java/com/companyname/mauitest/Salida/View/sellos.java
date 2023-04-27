package com.companyname.mauitest.Salida.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.companyname.mauitest.R;
import com.companyname.mauitest.Salida.Adapter.adapterSellos;
import com.companyname.mauitest.Salida.Model.Sello;

import java.util.List;

public class sellos extends AppCompatActivity {

    public static final String TAG = sellos.class.getSimpleName();
    private List<Sello> sellos;
    private RecyclerView rv;
    private adapterSellos adapter;

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

    private void initView() {
        rv=findViewById(R.id.rvsellos);

    }

}
