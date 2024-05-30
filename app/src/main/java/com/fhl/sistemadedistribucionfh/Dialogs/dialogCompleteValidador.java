package com.fhl.sistemadedistribucionfh.Dialogs;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import com.fhl.sistemadedistribucionfh.Dialogs.habilities.model.driver.habiltiesDriver;
import com.fhl.sistemadedistribucionfh.Dialogs.habilities.model.vehicle.habiltiesVehicle;
import com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.presenter.presenterSetValidacion;
import com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.presenter.presenterSetValidacionImpl;
import com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.view.viewSetValidacion;
import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.mainContainer.mainContainer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class dialogCompleteValidador extends DialogFragment implements View.OnClickListener, viewSetValidacion  {
public static final String TAG = dialogCompleteValidador.class.getSimpleName();
private ImageButton imageButton2;
private presenterSetValidacion presentador;
private String manifest;
private ConstraintLayout bottomStatusManifestHabilidades,bottomStatusManifestHabilidadesVehiculo;
private List<String> vehicleL= new ArrayList<>();
private List<String> operadorL= new ArrayList<>();
private List<habiltiesVehicle> mhabiltiesVehicle= new ArrayList<>();
private List<habiltiesDriver> mhabiltiesDriver= new ArrayList<>();
private Boolean allmVehicles=false;
private Boolean allmDrivers=false;
private ImageView imageViewHC ,imageokHC,imageHV,imageokHV;
private Integer  claveVehicleID;
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
        }
        initDialog(view);

        //setFonts();
        return view;
        }

private void initDialog(View view) {
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
        presentador.getdriverHabilities();
        presentador.getVehicleHabilities(claveVehicleID);
        }

public void closeDialog() {
        this.dismiss();

        }

        @Override
        public void statusValidacion(String code) {
                if(code.equals("105")){
                        Intent intent = new Intent(getContext(), mainContainer.class);
                        startActivity(intent);
                        // Close the dialog if needed
                        closeDialog();
                        Log.e("Validador", "ir a manifiestos");
                }else{
                        Toast.makeText(getContext(), ""+code, Toast.LENGTH_SHORT).show();
                }

        }

        @Override
        public void setDriverHailities(String habilidades) {
                Log.e("habilidades","Operador "+habilidades);
//                Gson gson=new Gson();
//                String json=gson.toJson(habilidades);
                operadorL.clear();
                mhabiltiesDriver.clear();
                if(habilidades!=null) {
                        List<String> habilidadesList = extractNombreValues(habilidades);

                        // Print the list of "Nombre" values
                        for (String habilidad : habilidadesList) {
                                Log.e("habilidades", "Operador json " + habilidad);
                                operadorL.add(habilidad);
                                mhabiltiesDriver.add(new habiltiesDriver(habilidad, false));
                        }
                }else {
                        bottomStatusManifestHabilidades.setVisibility(View.GONE);
                }
        }
        private List<String> extractNombreValues(String habilidadesJson) {
                List<String> nombreValues = new ArrayList<>();

                try {
                        JSONArray jsonArray = new JSONArray(habilidadesJson);
                        for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String nombre = jsonObject.getString("Nombre");
                                nombreValues.add(nombre);
                        }
                } catch (JSONException e) {
                        e.printStackTrace();
                }

                return nombreValues;
        }
        @Override
        public void setVehicleHailities(String habilidadVehiculos) {
                Log.e("habilidades","Vehiculo "+habilidadVehiculos);
                vehicleL.clear();
                mhabiltiesVehicle.clear();
                if(habilidadVehiculos!=null) {
                        List<String> habilidadesList = extractNombreValues2(habilidadVehiculos);

                        // Print the list of "Nombre" values
                        for (String habilidad : habilidadesList) {
                                Log.e("habilidades", "Operador json " + habilidad);
                                vehicleL.add(habilidad);
                                mhabiltiesVehicle.add(new habiltiesVehicle(habilidad, false));
                        }
                }else{
                        bottomStatusManifestHabilidadesVehiculo.setVisibility(View.GONE);
                }
        }
        private List<String> extractNombreValues2(String habilidadesJson) {
                List<String> nombreValues = new ArrayList<>();

                try {
                        JSONArray jsonArray = new JSONArray(habilidadesJson);
                        for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String nombre = jsonObject.getString("nombre");
                                nombreValues.add(nombre);
                        }
                } catch (JSONException e) {
                        e.printStackTrace();
                }

                return nombreValues;
        }
        private void showDialogWithCheckbox(Context context, List<String> items, boolean vehicleDriver) {//todo vehicle true driver false
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Check Dialog");

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
                                allmVehicles=true;
                                imageHV.setBackgroundResource(R.drawable.ic_inventary_icon);
                                imageokHV.setBackgroundResource(R.drawable.ic_checkokqr);
                        } else {
                                Log.e("habilidades", " NO todas las habilidades en vehiculos estan seleccionadas");
                                allmVehicles=false;
                                imageHV.setBackgroundResource(R.drawable.ic_inventary_icongrey);
                                imageokHV.setBackgroundResource(R.drawable.ic_checkokqrgrey);
                        }
                }
                if(mhabiltiesDriver!=null) {
                        boolean allDriverSelected = true;
                        for (habiltiesDriver driver : mhabiltiesDriver) {
                                if (!driver.getSelected()) {
                                        allDriverSelected = false;
                                        break;
                                }
                        }
                        if (allDriverSelected) {
                                Log.e("habilidades", " todas las habilidades en operadores estan seleccionadas");
                                allmDrivers=true;
                                imageViewHC.setBackgroundResource(R.drawable.ic_inventary_icon);
                                imageokHC.setBackgroundResource(R.drawable.ic_checkokqr);
                        } else {
                                Log.e("habilidades", " NO todas las habilidades en operadores estan seleccionadas");
                                allmDrivers=false;
                                imageViewHC.setBackgroundResource(R.drawable.ic_inventary_icongrey);
                                imageokHC.setBackgroundResource(R.drawable.ic_checkokqrgrey);
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
                        }else {
                                Toast.makeText(getContext(), "Validar  habilidades", Toast.LENGTH_SHORT).show();
                        }

                      presentador.setValidacionMenifest(manifest);
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