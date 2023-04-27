package com.companyname.mauitest.Salida.View;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.companyname.mauitest.Dialogs.mainMenu;
import com.companyname.mauitest.R;
import com.companyname.mauitest.Salida.Adapter.adapterTicketsSalida;
import com.companyname.mauitest.mainContainer.mainContainer;
import com.companyname.mauitest.mainContainer.model.dataMenuItems;
import com.companyname.mauitest.mainContainer.presenter.presentermainContainer;
import com.companyname.mauitest.mainContainer.tabBar;
import com.companyname.mauitest.nmanifest.mmanifest;

import java.util.List;

public class salidaContainer extends AppCompatActivity  {
        public static final String TAG = salidaContainer.class.getSimpleName();
        private FragmentManager manager;
        private FragmentTransaction transaction;
        private adapterTicketsSalida adapter;
        private RecyclerView rv;
        private String typeScanner;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_salida_qr);
            Bundle bndl;
            bndl = getIntent().getExtras();//detailOrderB
            if(bndl!=null) {
                typeScanner = bndl.getString("qrValue");
            }
            salida(bndl);

        }
    private void salida(Bundle bndl) {
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        salidaView salida= new salidaView();
        salida.setArguments(bndl);
        transaction.replace(R.id.qrFragment, salida, salidaView.TAG).commit();
    }
}