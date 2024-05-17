package com.fhl.sistemadedistribucionfh.evidence.checklist.view;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.Tickets.model.ticketsdetail.Check;
import com.fhl.sistemadedistribucionfh.checkList.model.v2.VehiculoVsCheck;
import com.fhl.sistemadedistribucionfh.checkList.model.v2.dataChecklistV2;
import com.fhl.sistemadedistribucionfh.checkList.view.checklistView;
import com.fhl.sistemadedistribucionfh.evidence.checklist.adapter.adapterChecklistEvidence;
import com.fhl.sistemadedistribucionfh.evidence.checklist.zQuestionCheckllist.questionEvidence;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class checklistEvidence extends AppCompatActivity implements View.OnClickListener, checklistView,ChecklistObserver {

    public static final String TAG = checklistEvidence.class.getSimpleName();
    private RecyclerView rv;
    private adapterChecklistEvidence adapter;
    private SearchView searchView;
    private dataChecklistV2 data;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private String folioTicket;
   /*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_checklist, container, false);


        return view;
    }*/
    public static List<Check> checkList=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragmet_checklist_evidence);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            checkList = (List<Check>) extras.getSerializable("checklist");
            Gson gson= new Gson();
            String json =gson.toJson(checkList);
            Log.e("jsonChecklist",""+json);
        }
        folioTicket = extras.getString("folioTicket");
        Log.d("SendCheck: ","Cargando la vista");
        initView();
        fillSellos(checkList);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Aquí puedes colocar cualquier lógica de actualización de la vista que necesites
        // Por ejemplo, si tienes una lista de elementos que se debe actualizar, puedes volver a cargar esa lista aquí
        // Por ejemplo:
        // actualizarLista();
        Log.d("SendCheck: ","Cargando la vista onResume");
        Log.e("jsonChecklist",""+checkList);
    }

    private void initView() {
        rv=findViewById(R.id.rvchecklist);
//        presenter= new checkListPresenterImpl(this,getApplicationContext());
//        presenter.getCheckList();
        searchView=findViewById(R.id.searchViewChecklist);
        searchView.setQueryHint("Buscar manifiesto");
        Drawable background= getApplicationContext().getDrawable(R.drawable.shape_button);
        searchView.setIconified(false);
        searchView.setBackground(background);
        searchView.clearFocus();
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSu
//            bmit(String query) {
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
        //fillSellos(data);
    }

    @Override
    public void continueChecklist() {

    }

    private void fillSellos(List<Check> mdata) {
        adapter=new adapterChecklistEvidence(this,mdata,getApplicationContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);
    }
    public void goQuestions(List<Check> dataQ, int positionChecklist) {
        //TODO cambiar todo este metodo
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        questionEvidence q = new questionEvidence();

        // Create a Bundle to pass data to the fragment
        Bundle bundle = new Bundle();
        String nombre = dataQ.get(positionChecklist).getValor();
        Integer llave = dataQ.get(positionChecklist).getLlave();
        Integer positionSelected = positionChecklist;

        bundle.putString("nombre", nombre);
        bundle.putInt("llave", llave);
        bundle.putString("folioTicket", folioTicket);
        bundle.putInt("posicionChecklist", positionSelected);

        if (dataQ != null) {
            bundle.putSerializable("preguntas", (Serializable)dataQ.get(positionChecklist).getPreguntas());
        }

        // Set the arguments for the fragment
        q.setArguments(bundle);
        // Replace the fragment with arguments
        transaction.replace(R.id.fragments, q, questionEvidence.TAG);
        // Add the transaction to the back stack
        transaction.addToBackStack(null);
        // Commit the transaction
        transaction.commit();
    }

    @Override
    public void onChecklistChanged(List<Check> updatedChecklist) {
        fillSellos(updatedChecklist);
        Log.e("jsonChecklist", new Gson().toJson(updatedChecklist));
    }

    public void setmypostandvalues(Integer positionChecklist) {
        Log.e("jsonChecklist","checklist name" + " positionchecklist "+positionChecklist);
        if(checkList!=null)
        {
            for(int i=0; i<checkList.size();i++)
            {
                if(i==positionChecklist){
                    checkList.get(positionChecklist).setAplicado(true);
                }
            }
        }
         adapter.notifyDataSetChanged();
    }
}