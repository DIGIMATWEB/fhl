package com.fhl.sistemadedistribucionfh.checkList.view;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
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
import com.fhl.sistemadedistribucionfh.checkList.model.v2.VehiculoVsCheck;
import com.fhl.sistemadedistribucionfh.checkList.model.v2.dataChecklistV2;
import com.fhl.sistemadedistribucionfh.checkList.presenter.checkListPresenterImpl;
import com.fhl.sistemadedistribucionfh.checkList.presenter.checklistPresenter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class checkList extends Fragment implements View.OnClickListener,checklistView{

    public static final String TAG = checkList.class.getSimpleName();
    private RecyclerView rv;
    private adapterChecklist adapter;
    private SearchView searchView;
    private checklistPresenter presenter;
    private dataChecklistV2 data;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_checklist, container, false);
        initView(view);

        return view;
    }
    private void fillSellos(dataChecklistV2 data) {
        adapter=new adapterChecklist(this,data,getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);
    }
    private void initView(View view) {
        rv=view.findViewById(R.id.rvchecklist);
        presenter= new checkListPresenterImpl(this,getContext());
        presenter.getVehicleManifest();

        searchView=view.findViewById(R.id.searchViewChecklist);
        searchView.setQueryHint("Buscar manifiesto");
        Drawable background= getContext().getDrawable(R.drawable.shape_button);
        searchView.setIconified(false);
        searchView.setBackground(background);
        searchView.clearFocus();
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                List<VehiculoVsCheck> filterList =filter(data.getVehiculoVsChecklist(),newText);
//                adapter.setFilter(filterList);
//                return true;
//            }
//        });
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
    public void setCheckList(dataChecklistV2 data) {
        this.data=data;
        Gson gson=new Gson();
        String json=gson.toJson(data);
        Log.e("checklistData",""+json);
        fillSellos(data);
    }

    @Override
    public void continueChecklist() {
        presenter.getCheckList();
    }

    public void goQuestions(String nombre, String placa, String vigencia, String periodicida, Integer position, dataChecklistV2 dataV2) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

        if (fragmentManager != null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            questionFragment questionFragment = new questionFragment();

            // Crear un Bundle para pasar datos al fragmento
            Bundle bundle = new Bundle();
            bundle.putString("nombre", nombre);
            bundle.putString("placa", placa);
            bundle.putString("vigencia", vigencia);
            bundle.putString("periodicida", periodicida);
            bundle.putInt("position", position);

            // Datos para enviar el checklist
            bundle.putInt("VehiculoChkId", dataV2.getVehiculoVsChecklist().get(position).getId());
            bundle.putInt("DespachoId", dataV2.getVehiculoVsChecklist().get(position).getDespacho().getId());
            bundle.putString("FechaAplicado", dataV2.getVehiculoVsChecklist().get(position).getFechaAplicado());
            bundle.putInt("VehiculoId", dataV2.getVehiculoId());
            bundle.putInt("ChecklistId", dataV2.getVehiculoVsChecklist().get(position).getChecklistId());

            // Establecer los argumentos para el fragmento
            questionFragment.setArguments(bundle);

            // Reemplazar el fragmento con los argumentos
            fragmentTransaction.replace(R.id.fragments, questionFragment, questionFragment.TAG);

            // Confirmar la transacción
            fragmentTransaction.commit();
        }
    }
}
