package com.fhl.sistemadedistribucionfh.nmanifest.viewV2;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SearchView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.Retrofit.GeneralConstants;
import com.fhl.sistemadedistribucionfh.login.view.login;
import com.fhl.sistemadedistribucionfh.nmanifest.adapterV2.manifestAdapterV2;
import com.fhl.sistemadedistribucionfh.nmanifest.modelV2.dataManifestV2;
import com.fhl.sistemadedistribucionfh.nmanifest.presenterV2.manifestImplV2;
import com.fhl.sistemadedistribucionfh.nmanifest.presenterV2.presentermanifestV2;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.view.manifestDetailV2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class mmanifestV2 extends Fragment implements View.OnClickListener, viewManifestV2 {
    public static final String TAG = mmanifestV2.class.getSimpleName();
    private RecyclerView rv;
    private manifestAdapterV2 adapter;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private presentermanifestV2 presenter;
    private ImageView finder;
    private SearchView searchViewv2;
    private List<dataManifestV2> data;
    private ProgressDialog mprogres;
    @SuppressLint("NewApi")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        View view = inflater.inflate(R.layout.fragment_manifest, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        rv = view.findViewById(R.id.rvmanifest);
        finder = view.findViewById(R.id.finder);
        finder.setOnClickListener(this);
        mprogres = new ProgressDialog(getActivity());
        searchViewv2 = view.findViewById(R.id.searchViewManifest);
        presenter = new manifestImplV2(this, getContext());
        presenter.getmanifestV2();
    }

    private void setAdapter(List<dataManifestV2> data) {
        adapter = new manifestAdapterV2(this, data, data.size(), getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);
    }
    public void gotoTickets(int position, String folioDespacho, String vehiculoModelo, String vehiculoPlaca, String cedis) {
        //TODO Agregar los demas datos al Bundle cuando se tengan
        Bundle bundle = new Bundle();
        bundle.putString("folioDespachoId",folioDespacho);
        bundle.putString("vehiculoModeloId", vehiculoModelo);
        bundle.putString("vehiculoPlacaId", vehiculoPlaca);
        bundle.putString("cedisId", cedis);

        manager = getActivity().getSupportFragmentManager();
        transaction = manager.beginTransaction();
        //tickets ticketsf= new tickets();
        manifestDetailV2 manifestdetail = new manifestDetailV2();
        manifestdetail.setArguments(bundle);
        transaction.replace(R.id.fragments, manifestdetail, manifestDetailV2.TAG).commit();
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.finder:
                if (searchViewv2.getVisibility() == View.GONE) {
                    searchViewv2.setVisibility(View.VISIBLE);
                    searchViewv2.setQueryHint("Buscar manifiesto");
                    Drawable background = getContext().getDrawable(R.drawable.shape_button);
                    searchViewv2.setIconified(false);
                    searchViewv2.setBackground(background);
                    searchViewv2.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                        @Override
                        public boolean onQueryTextSubmit(String query) {
                            return false;
                        }

                        @Override
                        public boolean onQueryTextChange(String newText) {
                            List<dataManifestV2> filterList = filter(data, newText);
                            adapter.setFilterV2(filterList);
                            return true;
                        }
                    });
                    searchViewv2.setOnCloseListener(new SearchView.OnCloseListener() {
                        @Override
                        public boolean onClose() {
                            searchViewv2.setVisibility(View.GONE);
                            return false;
                        }
                    });
                } else {
                    searchViewv2.setVisibility(View.GONE);
                }
                break;
        }
    }

    private List<dataManifestV2> filter(List<dataManifestV2> data, String text) {
        List<dataManifestV2> mfilterList = new ArrayList<>();
        text = text.toLowerCase();
        if (data!=null) {
            for(dataManifestV2 manifestV2:data){
                //TODO cambiar al valor corecto
                String manifestname = String.valueOf( manifestV2.getFolioDespacho());
                if(manifestname.contains(text)) {
                    mfilterList.add(manifestV2);
                }
            }
        }
        return mfilterList;
    }

    @Override
    public void setAllmanifestV2(List<dataManifestV2> data) {
        this.data = data;
        setAdapter(data);
    }

    @Override
    public void returnTologin() {
        deleteCache(getContext());
        SharedPreferences preferences = getContext().getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        preferences.edit().clear().commit();
        Intent intent = new Intent(getContext(), login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_CLEAR_TOP);//
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void hideProgress() {
        //mprogres.hide();
    }

    @Override
    public void showProgress() {
//        mprogres.setMessage("Cargando manifiestos");
//        mprogres.setCancelable(false);
//        mprogres.show();
    }

    private void deleteCache(Context context) {
        try {
            File dir = context.getCacheDir();
            deleteDir(dir);
        } catch (Exception e) { e.printStackTrace();}
    }
    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
            return dir.delete();
        } else if(dir!= null && dir.isFile()) {
            return dir.delete();
        } else {
            return false;
        }
    }
}
