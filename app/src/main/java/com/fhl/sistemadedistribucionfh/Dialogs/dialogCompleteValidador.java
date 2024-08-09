package com.fhl.sistemadedistribucionfh.Dialogs;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;

import com.fhl.sistemadedistribucionfh.Dialogs.Loader.view.loaderFH;
import com.fhl.sistemadedistribucionfh.Dialogs.habilities.model.driver.habiltiesDriver;
import com.fhl.sistemadedistribucionfh.Dialogs.habilities.model.vehicle.habiltiesVehicle;
import com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.model.habilitiesManifest.HabilidadesOperadore;
import com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.model.habilitiesManifest.HabilidadesVehiculo;
import com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.model.habilitiesManifest.ValidacionApp;
import com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.model.sentriplusCheckTickets;
import com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.presenter.presenterSetValidacion;
import com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.presenter.presenterSetValidacionImpl;
import com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.view.viewSetValidacion;
import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.Retrofit.GeneralConstants;
import com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus.dataTicketsDetailsendtrip;
import com.fhl.sistemadedistribucionfh.mainContainer.mainContainer;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class dialogCompleteValidador extends DialogFragment implements View.OnClickListener, viewSetValidacion  {
public static final String TAG = dialogCompleteValidador.class.getSimpleName();
private ImageButton imageButton2;
private presenterSetValidacion presentador;
private String manifest, vehicleVin, rfcUser;
private ConstraintLayout bottomStatusManifestHabilidades,bottomStatusManifestHabilidadesVehiculo;
private List<String> vehicleL= new ArrayList<>();
private List<String> operadorL= new ArrayList<>();
private List<habiltiesVehicle> mhabiltiesVehicle= new ArrayList<>();
private List<habiltiesDriver> mhabiltiesDriver= new ArrayList<>();
private Boolean allmVehicles=false;
private Boolean allmDrivers=false;
private ImageView imageViewHC ,imageokHC,imageHV,imageokHV;
private Integer  claveVehicleID;
private List<dataTicketsDetailsendtrip> ticketsEntegra=new ArrayList<>();
private List<dataTicketsDetailsendtrip> ticketsRecoleccion=new ArrayList<>();
private List<dataTicketsDetailsendtrip> ticketsAll;
private List<sentriplusCheckTickets> listCompare=new ArrayList<>();
private Boolean ticketsAllFirst=false;
private loaderFH progress;
@Override
public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_DeviceDefault_Light_NoActionBar);
        }

@Nullable
@Override
public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_despacho_validador, container, false);
        //getDialog().getWindow().setBackgroundDrawableResource(R.color.customTransparent);
        setCancelable(true);
        Bundle args = getArguments();
        if (args != null) {
                manifest = args.getString("currentManifest");
                claveVehicleID= args.getInt("claveVehicleID");
                vehicleVin = args.getString("vehicleVin");
                rfcUser = args.getString("RFCUser");
        }
        initDialog(view);

        //setFonts();
        return view;
        }

private void initDialog(View view) {
        progress = new loaderFH();
        imageButton2=view.findViewById(R.id.imageButtonValidador);
        imageButton2.setOnClickListener(this);
        bottomStatusManifestHabilidades=view.findViewById(R.id.bottomStatusManifestHabilidades);
        bottomStatusManifestHabilidades.setOnClickListener(this);
        bottomStatusManifestHabilidadesVehiculo=view.findViewById(R.id. bottomStatusManifestHabilidadesVehiculo);
        bottomStatusManifestHabilidadesVehiculo.setOnClickListener(this);
        imageViewHC=view.findViewById(R.id.imageViewHC);
        imageokHC=view.findViewById(R.id.imageokHC);
        imageHV =view.findViewById(R.id.imageHV);
        imageokHV=view.findViewById(R.id.imageokHV);
        presentador= new presenterSetValidacionImpl(this,getContext());
      //  presentador.getdriverHabilities();
      //  presentador.getVehicleHabilities(claveVehicleID);
        presentador.getManifestHabilities(manifest);
        presentador.requestTicketsByManifest(manifest,null);//todo verificar para todos los tickets
        ticketsAllFirst=false;
        presentador.tokenAvocado();
        bottomStatusManifestHabilidadesVehiculo.setVisibility(View.GONE);
        bottomStatusManifestHabilidades.setVisibility(View.GONE);
        }
        @Override
        public void showDialog() {
                if (progress != null && !progress.isVisible()) {
                        Bundle bundle = new Bundle();
                        bundle.putBoolean("HAS_TITLE", false);
                        bundle.putString("title", "Cargando detalles");
                        progress.setArguments(bundle);
                        progress.show(getChildFragmentManager(), loaderFH.TAG);
                }
        }

        @Override
        public void hideDialog() {
                new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                                if (progress != null && this != null)
                                        if(progress.isAdded()) {
                                                progress.dismiss();
                                        }
                        }
                }, 2000);
        }
public void closeDialog() {
        this.dismiss();

        }
        @Override
        public void setDetailTickets(List<dataTicketsDetailsendtrip> data) {
                //TODO AQUI DEBES VER SI LOS TICKETS SE ENCUENTRAN EN TipoEntregaId": 2   //nose puede enviar un ticket sin el detalle
                if(!ticketsAllFirst) {
                        ticketsAll = data;
                        ticketsEntegra.clear();
                        ticketsRecoleccion.clear();
                        listCompare.clear();
                        Boolean allValid = true;
                        Gson gson = new Gson();
                        String json = gson.toJson(data);
                        Log.e("sensendtrip", "" + json);
                        for (dataTicketsDetailsendtrip ticket : data) {
                                listCompare.add(new sentriplusCheckTickets(ticket.getFolioTicket(), false));
                                if (ticket.getTipoEntregaId() == 2) {
                                        ticketsEntegra.add(ticket);
                                        Log.e("ticketValidacion", ticket.getFolioTicket() + " ticket tipo recoleccion " + ticket.getEstatus().getNombre());
                                        if (ticket.getEstatus().getId() != 3) {
                                                allValid = false;
                                                break;
                                        }
                                } else {
                                        ticketsRecoleccion.add(ticket);
                                        Log.e("ticketValidacion", ticket.getFolioTicket() + " ticket tipo recoleccion");
                                }
                        }
                        if (ticketsEntegra != null) {
                                presentador.requestTicketsByManifest(manifest, ticketsEntegra.get(0).getFolioTicket());//todo se vuelve a solicitar la informacion del ticket pero solo de entrega
                                ticketsAllFirst = true;
                        }
                        if (allValid) {
                                Log.e("ticketValidacion", "Vehiculo cargado");
                        } else {
                                Log.e("ticketValidacion", "Vehiculo no ha sido cargado");
                                // At least one ticket does not have status ID 3
                        }
                }else {
                        this.ticketsEntegra=data;
                }
        }

        @Override
        public void gomanifest(Integer iteration) {
                goBegin();
//               listCompare.set(iteration,new sentriplusCheckTickets(ticketsAll.get(iteration).getFolioTicket(),true));
//                if(ticketsAll.size()>1){//si hay mas tickets
//                        iteration=iteration+1; //suma uno al iterador
//                        if(iteration<ticketsAll.size()-1) {//si el iterador es menor al numero de tickets
//                                presentador.sendSentriplus(manifest, ticketsEntegra, "Entrega", iteration);// vuelve a mandar el sendtrip con el iterador +1
//                        }else {//si el iterador es rebasado vuelve al manifiesto
//                                goBegin();
//                        }
//                }
        }

        @Override
        public void setHabilitiesManifest(ValidacionApp validacionApp) {
                if(validacionApp!=null){
                        Gson gson=new Gson();
                        String json=gson.toJson(validacionApp);
                        Log.e("validacionFinal",json);
                        if(validacionApp.getHabilidadesVehiculos()!=null){
                                List<HabilidadesVehiculo> habilidadesV=validacionApp.getHabilidadesVehiculos();
                                if(!habilidadesV.isEmpty()) {
                                        for (HabilidadesVehiculo habilidad : habilidadesV) {
                                                Log.e("habilidades", "Operador json " + habilidad);
                                                vehicleL.add(habilidad.getValor());
                                                mhabiltiesVehicle.add(new habiltiesVehicle(habilidad.getValor(), false));
                                        }
                                        bottomStatusManifestHabilidadesVehiculo.setVisibility(View.VISIBLE);
                                }else {
                                        bottomStatusManifestHabilidadesVehiculo.setVisibility(View.GONE);
                                        allmVehicles=true;
                                }
                        }
                        if(validacionApp.getHabilidadesOperadores()!=null){
                                List<HabilidadesOperadore> habilidadesO=validacionApp.getHabilidadesOperadores();
                                if(!habilidadesO.isEmpty()) {
                                        for (HabilidadesOperadore habilidad : habilidadesO) {
                                                Log.e("habilidades", "Operador json " + habilidad);
                                                operadorL.add(habilidad.getValor());
                                                mhabiltiesDriver.add(new habiltiesDriver(habilidad.getValor(), false));
                                        }
                                        bottomStatusManifestHabilidades.setVisibility(View.VISIBLE);
                                }else {
                                        bottomStatusManifestHabilidades.setVisibility(View.GONE);
                                        allmDrivers=true;
                                }
                        }
                }
        }

        public void goBegin(){
                Intent intent = new Intent(getContext(), mainContainer.class);
                startActivity(intent);
                // Close the dialog if needed
                closeDialog();
                Log.e("Validador", "ir a manifiestos");
        }

        @Override
        public void statusValidacion(String code) {
                presentador.hideDialog();
                if(code.equals("105")){
                        goBegin();
//                        if(ticketsAll!=null) {
//                                if(!ticketsAll.isEmpty()) {
//                                        presentador.sendSentriplus(manifest, ticketsEntegra, "Entrega", 0);
//                                }else {
//                                        Toast.makeText(getContext(), "El manifiesto no cuenta con sellos", Toast.LENGTH_SHORT).show();
//                                }
//                        }else {
//                                Toast.makeText(getContext(), "El manifiesto no cuenta con sellos", Toast.LENGTH_SHORT).show();
//                        }
                }else{
                        Toast.makeText(getContext(), ""+code, Toast.LENGTH_SHORT).show();
                        goBegin();
                }

        }

//        @Override
//        public void setDriverHailities(String habilidades) {
//                Log.e("habilidades","Operador "+habilidades);
////                Gson gson=new Gson();
////                String json=gson.toJson(habilidades);
//                operadorL.clear();
//                mhabiltiesDriver.clear();
//                if(habilidades!=null) {
//                        List<String> habilidadesList = extractNombreValues(habilidades);
//
//                        // Print the list of "Nombre" values
//                        for (String habilidad : habilidadesList) {
//                                Log.e("habilidades", "Operador json " + habilidad);
//                                operadorL.add(habilidad);
//                                mhabiltiesDriver.add(new habiltiesDriver(habilidad, false));
//                        }
//                }else {
//                        bottomStatusManifestHabilidades.setVisibility(View.GONE);
//                }
//        }
//        private List<String> extractNombreValues(String habilidadesJson) {
//                List<String> nombreValues = new ArrayList<>();
//
//                try {
//                        JSONArray jsonArray = new JSONArray(habilidadesJson);
//                        for (int i = 0; i < jsonArray.length(); i++) {
//                                JSONObject jsonObject = jsonArray.getJSONObject(i);
//                                String nombre = jsonObject.getString("Nombre");
//                                nombreValues.add(nombre);
//                        }
//                } catch (JSONException e) {
//                        e.printStackTrace();
//                }
//
//                return nombreValues;
//        }
//        @Override
//        public void setVehicleHailities(String habilidadVehiculos) {
//                Log.e("habilidades","Vehiculo "+habilidadVehiculos);
//                vehicleL.clear();
//                mhabiltiesVehicle.clear();
//                if(habilidadVehiculos!=null) {
//                        List<String> habilidadesList = extractNombreValues2(habilidadVehiculos);
//
//                        // Print the list of "Nombre" values
//                        for (String habilidad : habilidadesList) {
//                                Log.e("habilidades", "Operador json " + habilidad);
//                                vehicleL.add(habilidad);
//                                mhabiltiesVehicle.add(new habiltiesVehicle(habilidad, false));
//                        }
//                }else{
//                        bottomStatusManifestHabilidadesVehiculo.setVisibility(View.GONE);
//                }
//        }
//
//
//
//        private List<String> extractNombreValues2(String habilidadesJson) {
//                List<String> nombreValues = new ArrayList<>();
//
//                try {
//                        JSONArray jsonArray = new JSONArray(habilidadesJson);
//                        for (int i = 0; i < jsonArray.length(); i++) {
//                                JSONObject jsonObject = jsonArray.getJSONObject(i);
//                                String nombre = jsonObject.getString("nombre");
//                                nombreValues.add(nombre);
//                        }
//                } catch (JSONException e) {
//                        e.printStackTrace();
//                }
//
//                return nombreValues;
//        }
        private void showDialogWithCheckbox(Context context, List<String> items, boolean vehicleDriver) {//todo vehicle true driver false
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Habilidades");

                // Create a layout for the checkboxes
                LinearLayout layout = new LinearLayout(context);
                layout.setOrientation(LinearLayout.VERTICAL);

                // Add checkboxes dynamically
                for (String item : items) {
                        CheckBox checkBox = new CheckBox(context);
                        checkBox.setText(item);
                        layout.addView(checkBox);
                        if (vehicleDriver) {
                                for (habiltiesVehicle vehicle : mhabiltiesVehicle) {
                                        if (vehicle.getHabilidad().equals(item)) {
                                                checkBox.setChecked(vehicle.getSelected()); // Set checkbox checked based on isSelected
                                                break;
                                        }
                                }
                        } else {
                                for (habiltiesDriver driver : mhabiltiesDriver) {
                                        if (driver.getHabilidad().equals(item)) {
                                                checkBox.setChecked(driver.getSelected()); // Set checkbox checked based on isSelected
                                                break;
                                        }
                                }
                        }
                }

                builder.setView(layout);

                // Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                                // Handle OK button click
                                // You can iterate through the checkboxes to see which ones are checked
                                for (int i = 0; i < layout.getChildCount(); i++) {
                                        CheckBox checkBox = (CheckBox) layout.getChildAt(i);
                                        String itemName = checkBox.getText().toString();
                                        boolean isChecked = checkBox.isChecked();

                                        // Update isSelected field based on checkbox status
                                        if (vehicleDriver) {
                                                for (habiltiesVehicle vehicle : mhabiltiesVehicle) {
                                                        if (vehicle.getHabilidad().equals(itemName)) {
                                                                vehicle.setSelected(isChecked);
                                                                break;
                                                        }
                                                }
                                        } else {
                                                for (habiltiesDriver driver : mhabiltiesDriver) {
                                                        if (driver.getHabilidad().equals(itemName)) {
                                                                driver.setSelected(isChecked);
                                                                break;
                                                        }
                                                }
                                        }
                                }
                                checkStatus();
                        }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                                // User cancelled the dialog
                                checkStatus();
                                dialog.dismiss();

                        }
                });

                // Create and show the dialog
                builder.create().show();
        }
        private void checkStatus(){
                if (mhabiltiesVehicle != null)
                {
                        if(mhabiltiesVehicle.isEmpty()){
                                allmVehicles=true;
                        }else {
                                boolean allVehicleSelected = true;
                                for (habiltiesVehicle vehicle : mhabiltiesVehicle) {
                                        if (!vehicle.getSelected()) {
                                                allVehicleSelected = false;
                                                break;
                                        }
                                }
                                // Use allVehicleSelected and allDriverSelected as needed
                                if (allVehicleSelected) {
                                        Log.e("habilidades", " todas las habilidades en vehiculos estan seleccionadas");
                                        allmVehicles = true;
                                        imageHV.setBackgroundResource(R.drawable.ic_inventary_icon);
                                        imageokHV.setBackgroundResource(R.drawable.ic_checkokqr);
                                } else {
                                        Log.e("habilidades", " NO todas las habilidades en vehiculos estan seleccionadas");
                                        allmVehicles = false;
                                        imageHV.setBackgroundResource(R.drawable.ic_inventary_icongrey);
                                        imageokHV.setBackgroundResource(R.drawable.ic_checkokqrgrey);
                                }
                        }
                }
                if(mhabiltiesDriver!=null) {
                        if(mhabiltiesDriver.isEmpty()) {
                                allmVehicles=true;
                        }else{
                                boolean allDriverSelected = true;
                                for (habiltiesDriver driver : mhabiltiesDriver) {
                                        if (!driver.getSelected()) {
                                                allDriverSelected = false;
                                                break;
                                        }
                                }
                                if (allDriverSelected) {
                                        Log.e("habilidades", " todas las habilidades en operadores estan seleccionadas");
                                        allmDrivers = true;
                                        imageViewHC.setBackgroundResource(R.drawable.ic_inventary_icon);
                                        imageokHC.setBackgroundResource(R.drawable.ic_checkokqr);
                                } else {
                                        Log.e("habilidades", " NO todas las habilidades en operadores estan seleccionadas");
                                        allmDrivers = false;
                                        imageViewHC.setBackgroundResource(R.drawable.ic_inventary_icongrey);
                                        imageokHC.setBackgroundResource(R.drawable.ic_checkokqrgrey);
                                }
                        }
                }
        }

        @Override
        public void onClick(View view) {
        switch (view.getId()) {
                case R.id.imageButtonValidador:
                //closeDialog();
                        if(allmDrivers&&allmVehicles){
                                Toast.makeText(getContext(), "Continuar", Toast.LENGTH_SHORT).show();


                        // Datos para enviar al Endpoint
                        Gson gson= new Gson();
                        String jsonHabVehicles=gson.toJson(mhabiltiesVehicle);
                        String jsonHabDriver = gson.toJson(mhabiltiesDriver);

                        SharedPreferences preferences = getContext().getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
                        String token = preferences.getString(GeneralConstants.TOKEN, null);
                        String user = preferences.getString(GeneralConstants.OPERADOR_NAME, null);

                        Log.e("SendTicket:", "Habilidades conductor:" + jsonHabDriver);
                        Log.e("SendTicket:" , "Habilidades Vehiculo" + jsonHabVehicles);
                        Log.e("SendTicket: ", "Datos" + manifest + " " + vehicleVin + " " + rfcUser + " " + jsonHabDriver + " " + jsonHabVehicles + " " + user);

                        // Presenter con los datos
                        presentador.showDialog();
                        presentador.setDatosValidador(manifest, vehicleVin, rfcUser, jsonHabDriver, jsonHabVehicles, user);
                        presentador.setValidacionMenifest(manifest);
                        }else {
                                Toast.makeText(getContext(), "Validar  habilidades", Toast.LENGTH_SHORT).show();
                        }
                break;
                case R.id.bottomStatusManifestHabilidadesVehiculo:
                        //Toast.makeText(getContext(), "aqui va un dialog del vehiculo", Toast.LENGTH_SHORT).show();
                        if(vehicleL!=null) {
                                showDialogWithCheckbox(getContext(), vehicleL,true);
                                checkStatus();
                        }
                         break;
                case R.id.bottomStatusManifestHabilidades:
                        //Toast.makeText(getContext(), "aqui va un dialog del Operador", Toast.LENGTH_SHORT).show();
                        if(operadorL!=null) {
                                showDialogWithCheckbox(getContext(), operadorL,false);
                                checkStatus();
                        }
                        break;
               }
        }

}