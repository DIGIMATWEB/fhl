package com.fhl.sistemadedistribucionfh.Dialogs.detailManifestTicketsSummary.Sellos;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fhl.sistemadedistribucionfh.Dialogs.detailManifestTicketsSummary.Sellos.Adapter.SwipeToDeleteCallback;
import com.fhl.sistemadedistribucionfh.Dialogs.detailManifestTicketsSummary.Sellos.Adapter.adapterSellosManifestDetail;
import com.fhl.sistemadedistribucionfh.Dialogs.detailManifestTicketsSummary.Sellos.presenter.presenterSello;
import com.fhl.sistemadedistribucionfh.Dialogs.detailManifestTicketsSummary.Sellos.presenter.presenterSelloImpl;
import com.fhl.sistemadedistribucionfh.Dialogs.detailManifestTicketsSummary.Sellos.view.sellosSummaryView;
import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.Sellos.model.Sello;
import com.fhl.sistemadedistribucionfh.evidence.evidencia;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.modelV2.dataTicketsManifestV2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class sellosSummary extends DialogFragment implements View.OnClickListener , sellosSummaryView {
    public static final String TAG = sellosSummary.class.getSimpleName();
    private adapterSellosManifestDetail adapter;
    private String currentManifest;
    private List<dataTicketsManifestV2> data;
    private RecyclerView rv;
    private ImageButton imageButton;
    private List<Sello> sellos;
    private CardView sellosAdd;
    private Boolean control = false;
    private presenterSello presenter;
    private Integer manifestId=0;
    private Integer flow=0;
    private Boolean createMore=true;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_DeviceDefault_Light_NoActionBar);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_sellos_detail_manifest, container, false);
        getDialog().getWindow().setBackgroundDrawableResource(R.color.customTransparent2);
        setCancelable(true);
        Bundle args = getArguments();

        if (args != null) {
            currentManifest = args.getString("currentManifest");
            data = (List<dataTicketsManifestV2>) args.getSerializable("dataTcikets");
            sellos = (List<Sello>) args.getSerializable("sellos");
            flow= Integer.valueOf( args.getString("flowSellos"));
            if (sellos != null) {

            }
        }
        initView(view);
        if (sellos != null) {
            fillTicketsRV(sellos);
        } else {
           //no es correcto debido a que  espera la finalizacion del endpoint Toast.makeText(getContext(), "No tienes sellos", Toast.LENGTH_SHORT).show();
        }
        //setFonts();
        return view;
    }

    private void initView(View view) {
        rv = view.findViewById(R.id.rvTickets);
        imageButton = view.findViewById(R.id.imageButton);
        imageButton.setOnClickListener(this);
        sellosAdd = view.findViewById(R.id.sellosAdd);
        sellosAdd.setOnClickListener(this);
       // presenter= new presenterSelloImpl(this,getContext());
        presenter.requestManifestdetail(currentManifest);
        presenter.reqSellos(currentManifest);
    }
    @Override
    public void seteginsellos(List<Sello> sellos) {
        if (sellos != null) {
            this.sellos = sellos;
            Log.e("QR", "nSellos: " + sellos.size()); // Debug log
            this.control=true;
            fillTicketsRV(sellos);
        } else {
            Log.e("QR", "sellos is null");
        }
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void hideDialog() {

    }

    private void fillTicketsRV(List<Sello> sellos) {
        if (adapter == null) {
            adapter = new adapterSellosManifestDetail(this, sellos, getContext());
            rv.setLayoutManager(new LinearLayoutManager(getContext()));
            rv.setAdapter(adapter);
            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallback(adapter));
            itemTouchHelper.attachToRecyclerView(rv);
        } else {
            adapter.updateData(sellos);
        }
    }
    @Override
    public void saveManifestId(Integer id) {
        this.manifestId=id;
    }



    private void showDialog(String value) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Confirma la accion")
                .setMessage(value)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                        if (sellos != null) {
                            if (sellos.isEmpty()) {
                                goEvidence();
                            } else {
                                //presenter.mandarSellos
                                for (int i = sellos.size() - 1; i >= 0; i--) {
                                    Sello sello = sellos.get(i);
                                    if (sello.getNumeroSello() == null || sello.getNumeroSello().isEmpty()) {
                                        sellos.remove(i);
                                    }
                                }
                                adapter.updateData(sellos);
                                if (control) {
                                 //   Toast.makeText(getContext(), "guardar en endpoint valentin", Toast.LENGTH_SHORT).show();
                                    presenter.setSello(manifestId,sellos);
                                }
                            }
                        } else {
                            goEvidence();
                        }
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        dialog.dismiss();
                    }
                });

        // Create the AlertDialog object and return it
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    @Override
    public void setMessageSello() {
        goEvidence();
    }



    public void goEvidence() {
      //  Toast.makeText(getContext(), "ir a evidencias", Toast.LENGTH_SHORT).show();
        if(flow==1) {
            getActivity().finish();
            Intent intent = new Intent(getActivity(), evidencia.class);
            Bundle bundle = new Bundle();
            bundle.putInt("flujoId", 1);
            bundle.putString("sentripPlusFlow", "Recoleccion");
            bundle.putString("currentManifest", currentManifest);
            bundle.putString("folioTicket", null);
            bundle.putSerializable("dataTcikets", (Serializable) data);
            intent.putExtras(bundle);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }else{
            dismiss();
        }
    }

    public void updateSellos(List<Sello> data) {
        this.sellos = data;
    }

    public void control(Boolean control) {
        this.control=control;
    }
    public void letCreateMore(Boolean createMore) {
        boolean check=true;
        for(Sello s:sellos){
            if(s.getNumeroSello()==null||s.getNumeroSello().isEmpty()){
                check=false;
            }
        }
        if(check) {
            this.createMore = createMore;
        }else {
            Toast.makeText(getContext(), "Debes guardar el sello antes de crear otro", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sellosAdd:
                if(createMore) {
                    if (sellos != null) {
                        adapter.updateSellos(new Sello("", "", Integer.valueOf(currentManifest), 0));
                    } else {
                        fillTicketsRV(sellos);
                        adapter.updateSellos(new Sello("", "", Integer.valueOf(currentManifest), 0));
                    }
                    createMore=false;
                }else{
                    if(sellos.size()!=0) {
                        Toast.makeText(getContext(), "Guarda el sello para poder agregar otro", Toast.LENGTH_SHORT).show();
                    }else{
                        createMore=true;
                        if (sellos != null) {
                            adapter.updateSellos(new Sello("", "", Integer.valueOf(currentManifest), 0));
                        } else {
                            fillTicketsRV(sellos);
                            adapter.updateSellos(new Sello("", "", Integer.valueOf(currentManifest), 0));
                        }
                    }
                }
                break;
            case R.id.imageButton:
                // Toast.makeText(getContext(), "Modificar flujo de sellos", Toast.LENGTH_SHORT).show();
                if(adapter!=null) {
                    if (adapter.validateFields()) {
                        if (control) {
                            if (sellos != null) {
                                if (sellos.isEmpty()) {
                                    showDialog("Quieres continuar sin sellos");
                                } else {
                                    if (adapter.validateFields()){
                                        showDialog("Guardar los siguientes sellos");
                                    }else{

                                    }
                                }
                            } else {
                                showDialog("Quieres continuar sin sellos");
                            }
                        } else {
                            Toast.makeText(getContext(), "Guarda el valor para continuar", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        control=true;
                        showDialog("Continuar");
                    }
                }else {
                    showDialog("Quieres continuar sin sellos");
                }

                break;
        }
    }



}