package com.fhl.sistemadedistribucionfh.nmanifest.view;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.nmanifest.adapter.manifestAdapter;
import com.fhl.sistemadedistribucionfh.nmanifest.model.dataManifest;
import com.fhl.sistemadedistribucionfh.nmanifest.presenter.manifestImpl;
import com.fhl.sistemadedistribucionfh.nmanifest.presenter.presentermanifest;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.view.manifestDetailV2;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class mmanifest extends Fragment implements View.OnClickListener ,viewManifest {
    public static final String TAG = mmanifest.class.getSimpleName();
    private RecyclerView rv;
    private manifestAdapter adapter;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private presentermanifest presenter;
    private ImageView finder;
    private SearchView searchView;
    private List<dataManifest> data;
    @SuppressLint("NewApi")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manifest, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        rv =view.findViewById(R.id.rvmanifest);
        finder=view.findViewById(R.id.finder);
        finder.setOnClickListener(this);
        searchView=view.findViewById(R.id.searchViewManifest);
        presenter=new manifestImpl(this,getContext());
        presenter.getmanifest();
    }

    private void setAdapter(List<dataManifest> data) {
        adapter=new manifestAdapter(this,data,data.size(),getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.finder:
                if(searchView.getVisibility()==View.GONE){
                    searchView.setVisibility(View.VISIBLE);
                    searchView.setQueryHint("Buscar manifiesto");
                    Drawable background= getContext().getDrawable(R.drawable.shape_button);
                    searchView.setIconified(false);
                    searchView.setBackground(background);
                    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                        @Override
                        public boolean onQueryTextSubmit(String query) {
                            return false;
                        }

                        @Override
                        public boolean onQueryTextChange(String newText) {
                            List<dataManifest> filterList =filter(data,newText);
                            adapter.setFilter(filterList);
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
                }else{
                    searchView.setVisibility(View.GONE);
                }
                break;
        }
    }

    private List<dataManifest> filter(List<dataManifest> data, String text) {
        List<dataManifest> mfilterList= new ArrayList<>();
        text =text.toLowerCase();
        if(data!=null){
            for(dataManifest manifest:data)
                {
                    String manifestname=manifest.getIdmanifest().toLowerCase(Locale.ROOT);
                    if(manifestname.contains(text)){
                        mfilterList.add(manifest);
                    }
                }
        }
        return mfilterList;
    }

    public void gotoTickets(int position)
    {
        Bundle bundle = new Bundle();
        bundle.putString("manifestId",data.get(position).getIdmanifest());
        manager = getActivity().getSupportFragmentManager();
        transaction = manager.beginTransaction();
        //tickets ticketsf= new tickets();
        manifestDetailV2 manifestdetail =new manifestDetailV2();
        manifestdetail.setArguments(bundle);
        transaction.replace(R.id.fragments, manifestdetail, manifestDetailV2.TAG).commit();
    }

    @Override
    public void setAllmanifest(List<dataManifest> data) {
        this.data=data;
        setAdapter(data);

    }
}
