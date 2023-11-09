package com.fhl.sistemadedistribucionfh.nmanifest.viewV2;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.nmanifest.adapterV2.manifestAdapterV2;
import com.fhl.sistemadedistribucionfh.nmanifest.model.dataManifest;
import com.fhl.sistemadedistribucionfh.nmanifest.modelV2.dataManifestV2;
import com.fhl.sistemadedistribucionfh.nmanifest.presenterV2.manifestImplV2;
import com.fhl.sistemadedistribucionfh.nmanifest.presenterV2.presentermanifestV2;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class mmanifestV2 extends Fragment implements View.OnClickListener, viewManifestV2 {
    public static final String TAG = mmanifestV2.class.getSimpleName();
    private RecyclerView rv;
    private manifestAdapterV2 adapter;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private presentermanifestV2 presenter;
    private ImageView finder;
    private SearchView searchView;
    private List<dataManifestV2> data;

    @SuppressLint("NewApi")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        View view = inflater.inflate(R.layout.fragment_manifest, container, false);
        initView(view);
        /*presenter = new manifestImplV2(this, getContext());
        presenter.getmanifestV2();*/
        return view;
    }

    private void initView(View view) {
        rv = view.findViewById(R.id.rvmanifest);
        finder = view.findViewById(R.id.finder);
        finder.setOnClickListener(this);
        //searchView = view.findViewById(R.id.searchViewManifest);
        presenter = new manifestImplV2(this, getContext());
        presenter.getmanifestV2();
    }

    private void setAdapter(List<dataManifestV2> data) {
        adapter = new manifestAdapterV2(this, data, data.size(), getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        /*switch (view.getId()) {
            case R.id.finder:
                if (searchView.getVisibility() == View.GONE) {
                    searchView.setVisibility(View.VISIBLE);
                    searchView.setQueryHint("Buscar manifiesto");
                    Drawable background = getContext().getDrawable(R.drawable.shape_button);
                    searchView.setIconified(false);
                    searchView.setBackground(background);
                    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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
                    searchView.setOnCloseListener(new SearchView.OnCloseListener() {
                        @Override
                        public boolean onClose() {
                            searchView.setVisibility(View.GONE);
                            return false;
                        }
                    });
                } else {
                    searchView.setVisibility(View.GONE);
                }
                break;
        }*/
    }

    private List<dataManifestV2> filter(List<dataManifestV2> data, String text) {
        List<dataManifestV2> mfilterList = new ArrayList<>();
        text = text.toLowerCase();
        if (data!=null) {
            for(dataManifestV2 manifestV2:data){
                //TODO cambiar al valor corecto
                String manifestname = manifestV2.getAnden().toLowerCase();
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
}
