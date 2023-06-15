package com.fhl.sistemadedistribucionfh.cerrarViaje.view;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fhl.sistemadedistribucionfh.Dialogs.Reasons.view.dialogReasons;
import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.cerrarViaje.adapter.adapterNoCompletado;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class cerrarViaje  extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = cerrarViaje.class.getSimpleName();
    private RecyclerView rv;
    private adapterNoCompletado adapter;
    private ImageView trashicon;
    private List<String> vcurrentSelected,moldlist;
    private int sizeAfterErase;
    private CardView dialogReasonsC;

    private ImageView camera;
    private static final int REQUEST_CAMERA_PERMISSION = 200;
    private static final int REQUEST_IMAGE_CAPTURE = 201;
    private String currentImagePath;
    private ImageView imageView;
    private List<String> imageCollections=new ArrayList<>();
    private List<String> meraseList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cerrar_viaje);
        initView();
        imageCollections.clear();
        fillAdapter(imageCollections,getApplicationContext());
    }

    private void fillAdapter(List<String> imageCollections, Context applicationContext) {
        adapter=new adapterNoCompletado(this, imageCollections,getApplicationContext());
        LinearLayoutManager linearLayoutManager = new GridLayoutManager(applicationContext,3);
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(adapter);
    }

    private void initView() {
        rv=findViewById(R.id.carrusel);
        trashicon=findViewById(R.id.trashicon);
        trashicon.setOnClickListener(this);
        dialogReasonsC=findViewById(R.id.dialogReasons);
        dialogReasonsC.setOnClickListener(this);
        camera=findViewById(R.id.camera);
        camera.setOnClickListener(this);
    }
    /**este metodo muestra el icono de eliminar y guarda el arreglo de items a eliminar*/
    public void sawTrash(List<String> currentSelected) { //este metodo recibe dos listas la primera es de items seleccionados la segunda deberia ser data
        List<String> oldList=new ArrayList<>();
        oldList.add("1");
        oldList.add("2");
        oldList.add("3");
        oldList.add("4");
        oldList.add("5");
        oldList.add("6");
        oldList.add("7");
        oldList.add("8");
        oldList.add("9");
        this.moldlist=oldList;
        this.vcurrentSelected=currentSelected;
        for(String value:currentSelected){
            Log.e("borrarFotos",""+value);
        }
        if(!currentSelected.isEmpty()){
            trashicon.setVisibility(View.VISIBLE);
        }
    }
    private void openCamera() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {
            dispatchTakePictureIntent();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
        }
    }
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = createImageFile();
            if (photoFile != null) {
                Uri photoUri = FileProvider.getUriForFile(this,
                        "com.fhl.sistemadedistribucionfh.fileprovider", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                takePictureIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    private File createImageFile() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(null);
        try {
            File imageFile = File.createTempFile(imageFileName, ".jpg", storageDir);
            currentImagePath = imageFile.getAbsolutePath();

            // Get the content:// URI using FileProvider
            Uri photoUri = FileProvider.getUriForFile(this,
                    "com.fhl.sistemadedistribucionfh.fileprovider", imageFile);

            return imageFile;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            // Load the captured image into an ImageView
            Bitmap bitmap = BitmapFactory.decodeFile(currentImagePath);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 65, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();

// Convert byte array to Base64-encoded string
            String imageBase64 = Base64.encodeToString(byteArray, Base64.DEFAULT);
            Log.e("imageFilePhoto",""+imageBase64);
            if(!imageCollections.contains(imageBase64)) {
                imageCollections.add(imageBase64);
            }
            adapter.UpdateArray(imageCollections);
        } else {
            Toast.makeText(this, "Failed to capture image", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                dispatchTakePictureIntent();
            } else {
                Toast.makeText(this, "Camera permission required", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void updateEraselist(List<String> eraseList) {
        this.meraseList=eraseList;

    }
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.trashicon:
                if(!meraseList.isEmpty()) {
                    meraseList.sort(Collections.reverseOrder());
                    for (String indexStr : meraseList) {
                        int index = Integer.parseInt(indexStr);
                        if (index >= 0 && index < imageCollections.size()) {
                            imageCollections.remove(index);
                        }
                    }
                    adapter.updateSize(imageCollections, true);
                }else{
                    Toast.makeText(this, "No haz selecionado ningun item", Toast.LENGTH_SHORT).show();
                }
               //todo esto arreglo remueve los indexes de forma incorre
                break;
            case R.id.dialogReasons:
                new dialogReasons().show(this.getSupportFragmentManager(),"dialogReasons");
                break;
            case R.id.camera:
                openCamera();
                break;
        }
    }



}
