package com.fhl.sistemadedistribucionfh.checkList.view;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.checkList.Questions.view.questionFragment;
import com.fhl.sistemadedistribucionfh.checkList.adapter.adapterChecklist;
import com.fhl.sistemadedistribucionfh.checkList.model.v1.dataChecklist;
import com.fhl.sistemadedistribucionfh.checkList.model.v2.VehiculoVsCheck;
import com.fhl.sistemadedistribucionfh.checkList.presenter.checkListPresenterImpl;
import com.fhl.sistemadedistribucionfh.checkList.presenter.checklistPresenter;

import java.util.ArrayList;
import java.util.List;

public class checkList extends Fragment implements View.OnClickListener,checklistView{

    public static final String TAG = checkList.class.getSimpleName();
    private RecyclerView rv;
    private adapterChecklist adapter;
    private SearchView searchView;
    private checklistPresenter presenter;
    private List<VehiculoVsCheck> data;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_checklist, container, false);
        initView(view);

        return view;
    }
    private void fillSellos(List<VehiculoVsCheck> data) {
        adapter=new adapterChecklist(this,data,getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);
    }
    private void initView(View view) {
        rv=view.findViewById(R.id.rvchecklist);
        presenter= new checkListPresenterImpl(this,getContext());
        presenter.getCheckList();
        searchView=view.findViewById(R.id.searchViewChecklist);
        searchView.setQueryHint("Buscar manifiesto");
        Drawable background= getContext().getDrawable(R.drawable.shape_button);
        searchView.setIconified(false);
        searchView.setBackground(background);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<VehiculoVsCheck> filterList =filter(data,newText);
                adapter.setFilter(filterList);
                return true;
            }
        });
    }

    private List<VehiculoVsCheck> filter(List<VehiculoVsCheck> data, String text) {
        List<VehiculoVsCheck> mfilterList= new ArrayList<>();
        text =text.toLowerCase();
        if(data!=null){
            for(VehiculoVsCheck cehcklist:data)
            {
                String manifestname=cehcklist.getChecklist().getNombre().toLowerCase();
                if(manifestname.contains(text)){
                    mfilterList.add(cehcklist);
                }
            }
        }
        return mfilterList;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void setCheckList(List<VehiculoVsCheck> data) {
        this.data=data;
        fillSellos(data);
    }

    public void goQuestions() {
        manager = getActivity().getSupportFragmentManager();
        transaction = manager.beginTransaction();
        questionFragment q =new questionFragment();
        transaction.replace(R.id.fragments, q, questionFragment.TAG).commit();
    }
}
