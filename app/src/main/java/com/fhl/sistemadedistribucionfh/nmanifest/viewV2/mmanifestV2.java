package com.fhl.sistemadedistribucionfh.nmanifest.viewV2;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fhl.sistemadedistribucionfh.Dialogs.Loader.view.loaderFH;
import com.fhl.sistemadedistribucionfh.Dialogs.ManifestStatus.manifestStatus;
import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.Retrofit.GeneralConstants;
import com.fhl.sistemadedistribucionfh.nmanifest.adapterV2.manifestAdapterV2;
import com.fhl.sistemadedistribucionfh.nmanifest.modelV2.dataManifestV2;
import com.fhl.sistemadedistribucionfh.nmanifest.presenterV2.manifestImplV2;
import com.fhl.sistemadedistribucionfh.nmanifest.presenterV2.presentermanifestV2;
import com.fhl.sistemadedistribucionfh.login.view.login;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.modelV2.dataTicketsManifestV2;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.view.manifestDetailV2;
import com.google.gson.Gson;

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
    private ImageView finder,finderfilter;
    private SearchView searchViewv2;
    private List<dataManifestV2> data;
    private ProgressDialog mprogres;
    private int dialogfilter=0;
    private Integer positionG;
    private String folioDespachoG, vehiculoModeloG, vehiculoPlacaG, statusManifestG, cedisG,fechaSalida, ciudad, estado, operador, datoManiobra, validacionApp;
    private Handler handler = new Handler();
    private Runnable runnable;
    private loaderFH progress;
    private boolean isDialogVisible = false;
    private Boolean isFiltered=false;
    private String texFilter="";
    @SuppressLint("NewApi")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        View view = inflater.inflate(R.layout.fragment_manifest, container, false);
        initView(view);
        return view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated: Fragment view created");
        // Reinitialize the RecyclerView and adapter
        if (adapter == null) {
            // Initialize adapter and set it
            if (data != null) {
                setAdapter(data);
            } else {
                presenter.getmanifestV2(); // Fetch data if not already fetched
            }
        } else {
            // Adapter already initialized, update with existing data
            if (data != null) {
                adapter.updateManifest(data);
            }
        }
    }
    private void initView(View view) {
        progress = new loaderFH();
        rv = view.findViewById(R.id.rvmanifest);
        finder = view.findViewById(R.id.finder);
        finderfilter= view.findViewById(R.id.finderfilter);
        finderfilter.setOnClickListener(this);
        finder.setOnClickListener(this);
        mprogres = new ProgressDialog(getActivity());
        searchViewv2 = view.findViewById(R.id.searchViewManifest);
        presenter = new manifestImplV2(this, getContext());
        presenter.getmanifestV2();
        // Initialize the Runnable
        runnable = new Runnable() {
            @Override
            public void run() {
                Log.e("dialogUpdate","progress");
                presenter.getmanifestV2(); // Call the method
                handler.postDelayed(this, 30000); // Re-run every 30 seconds
            }
        };

        handler.postDelayed(runnable, 30000); // Start the handler

    }

    private void setAdapter(List<dataManifestV2> data) {
        if(adapter==null) {
            adapter = new manifestAdapterV2(this, data, data.size(), getContext());
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            rv.setLayoutManager(layoutManager);
            rv.setAdapter(adapter);
        }else{
            if(data!=null) {
                adapter.updateManifest(data);
            }
        }
    }
    @Override
    public void checkTickets(List<dataTicketsManifestV2> data) {
        if(data!=null){
            Boolean isAtListOne=true;
            for(dataTicketsManifestV2 ticket:data){
                if(ticket.getTipoEntregaId()==2){
                    Log.e("SendTicket", "Datos: " + this.data.get(positionG).getValidador().getEstatus());
                    if (this.data.get(positionG).getValidador().getEstatus().equals("correcto")) {
                        //isAtListOne=true;
                    } else {
                        isAtListOne=false;
                    }
                    break;
                }else {

                }

            }
            if(!isAtListOne){
                showToast();
                this.data.get(positionG).setRecolecionEntrega(true);
                if(isFiltered){
                    List<dataManifestV2> filterList = filter(this.data, texFilter);
                    adapter.setFilterV2(filterList);
                }else {

                    adapter.updateManifest(this.data);// setAdapter(this.data);
                }

            }else{
                gotoTicketsDetail();
            }
        }else {
            Toast.makeText(getContext(), "No hay ningun ticket", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        handler.removeCallbacks(runnable); // Stop the handler
        isDialogVisible = false;
        rv.setAdapter(null);
        adapter = null;
       // rv = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (data != null) {
            Log.e("fragment","dataonAttach "+data.size());
            setAdapter(data); // Ensure rv and adapter are properly initialized
        } else {
            Log.e("fragment","dataonAttach datanull ");
            presenter.getmanifestV2(); // Fetch data if not already fetched
        }
        if (getView() != null) {
            getView().setFocusableInTouchMode(true);
            getView().requestFocus();
            getView().setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                        if (manager != null && manager.getBackStackEntryCount() > 0) {

                            manager.popBackStack();
                            Log.e("fragments", "manifest"+manager.getBackStackEntryCount());

                        } else {
                            Log.e("fragments", "manifest manager 0" );
                            requireActivity().onBackPressed();
                        }
                        return true;
                    }
                    return false;
                }
            });
        }
    }
    private void gotoTicketsDetail() {
        Bundle bundle = new Bundle();
        bundle.putString("folioDespachoId",folioDespachoG);
        bundle.putString("vehiculoModeloId", vehiculoModeloG);
        bundle.putString("vehiculoPlacaId", vehiculoPlacaG);
        bundle.putString("statusManifest",statusManifestG);
        bundle.putString("cedisId", cedisG);
        bundle.putString("fechaSalida",fechaSalida);
        bundle.putString("ciudad", ciudad);
        bundle.putString("estado", estado);
        bundle.putString("operador", operador);
        bundle.putString("datoManiobra", datoManiobra);
        bundle.putString("validacionApp", validacionApp);

        manager = getActivity().getSupportFragmentManager();
        transaction = manager.beginTransaction();
        manifestDetailV2 manifestdetail = new manifestDetailV2();
        manifestdetail.setArguments(bundle);
        transaction.replace(R.id.fragments, manifestdetail, manifestDetailV2.TAG)
                .addToBackStack(mmanifestV2.TAG) // Agregar la transacción a la pila de retroceso
                .commit();
    }

    private void showToast() {//todo camiar por un dialogo y crear un endpoint para los datos de salida
        mostrarAlerta();
    }
    private void mostrarAlerta() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Validación requerida");
        builder.setMessage("Debes pasar por el validador.");
        builder.setPositiveButton("Entendido", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void gotoTickets(int position, String folioDespacho, String vehiculoModelo, String vehiculoPlaca, String cedis, String statusManifest, String fechaSalida, String ciudad, String estado,
                            String operador, String datoManiobra, String validacionApp) {
        presenter.getTicketByManigest(folioDespacho);
        Log.e("SendTicket", "Datos: " + position + " " + folioDespacho + " " + vehiculoModelo + " " + vehiculoPlaca + " " + cedis + " " + statusManifest);
        this.fechaSalida=fechaSalida;
        this.positionG = position;
        this.folioDespachoG = folioDespacho;
        this.vehiculoModeloG = vehiculoModelo;
        this.vehiculoPlacaG = vehiculoPlaca;
        this.cedisG = cedis;
        this.statusManifestG = statusManifest;
        this.ciudad = ciudad;
        this.estado = estado;
        this.operador = operador;
        this.datoManiobra = datoManiobra;
        this.validacionApp = validacionApp;
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.finderfilter:
                if(dialogfilter==0) {
                    new manifestStatus().show(getActivity().getSupportFragmentManager(), "manifestStatus");
                    dialogfilter=1;
                }
                break;
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
                            isFiltered=true;
                            texFilter=newText;
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
                    isFiltered=false;
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
    public void skipDialog() {
        dialogfilter=0;
    }
    public void setFilterDialog(String fcheckedItem) {
        dialogfilter=0;
        Log.e("filterbystatus",""+fcheckedItem);
        if(!fcheckedItem.equals("Todo")) {
            List<dataManifestV2> filterList = filterbystatus(data, fcheckedItem);
            Log.e("filterbystatus", "" + filterList.size());
            adapter.setFilterV2(filterList);
        }else{
            adapter.setFilterV2(data);
        }
    }
    private List<dataManifestV2> filterbystatus(List<dataManifestV2> data, String text) {
        List<dataManifestV2> mfilterList = new ArrayList<>();
        if (data!=null) {
            for(dataManifestV2 manifestV2:data){
                //TODO cambiar al valor corecto
                if(manifestV2.getEstatus().getNombre().equals(text)) {
                    mfilterList.add(manifestV2);
                }
            }
        }
        return mfilterList;
    }
    @Override
    public void setAllmanifestV2(List<dataManifestV2> data) {
        this.data = data;
        if(isFiltered){
            List<dataManifestV2> filterList = filter(data, texFilter);
            adapter.setFilterV2(filterList);
        }else {
            setAdapter(this.data);
        }
        setVehicleList(this.data);
    }

    private void setVehicleList(List<dataManifestV2> data) {
        if (data != null) {
            List<Integer> listV = new ArrayList<>();
            for (dataManifestV2 datos : data) {
                if (!listV.contains(datos.getVehiculoId())) {
                    listV.add(datos.getVehiculoId());
                }
            }
            Gson gson = new Gson();
            String json = gson.toJson(listV);

            FragmentActivity activity = getActivity();
            if (activity != null) {
                SharedPreferences preferencias = activity.getApplicationContext().getSharedPreferences(
                        GeneralConstants.CREDENTIALS_PREFERENCES,
                        Context.MODE_PRIVATE
                );
                SharedPreferences.Editor editor = preferencias.edit();
                editor.putString(GeneralConstants.LISTA_VEHICULOS_LOCATOR, json);
                editor.apply();
            } else {
                // Handle the null activity case here (e.g., log an error, throw an exception)
                Log.e("setVehicleList", "Activity is null");
            }
        }
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
        if (isDialogVisible && isAdded() && !isDetached() && !isRemoving() && !isStateSaved()) {
            if (progress != null && progress.isAdded()) {
                progress.dismiss();
                isDialogVisible = false; // Reset flag when dialog is dismissed
            }
        }
    }
    @Override
    public void showProgress() {
        if (!isDialogVisible && isAdded() && !isDetached() && !isRemoving() && !isStateSaved()) {
            if (progress != null && this!=null) {
                if(!progress.isVisible()) {
                    isDialogVisible = true; // Set flag to true when showing dialog
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("HAS_TITLE", false);
                    bundle.putString("title", "Cargando detalles");
                    progress.setArguments(bundle);
                    progress.show(getParentFragmentManager(), loaderFH.TAG);
                }
            }
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable); // Stop the handler
        isDialogVisible = false; // Reset the flag in onDestroy
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
