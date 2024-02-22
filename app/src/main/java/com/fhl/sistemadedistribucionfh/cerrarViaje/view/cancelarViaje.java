package com.fhl.sistemadedistribucionfh.cerrarViaje.view;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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
import com.fhl.sistemadedistribucionfh.Retrofit.GeneralConstants;
import com.fhl.sistemadedistribucionfh.cerrarViaje.adapter.adapterNoCompletado;
import com.fhl.sistemadedistribucionfh.mainContainer.mainContainer;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class cancelarViaje extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = cancelarViaje.class.getSimpleName();
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
    private Button buttonSave;
    private  String closeDialog;
    private String idReason;
    private TextView textView9111;
    private ArrayList<File> tempImageFiles = new ArrayList<>();
    private List<String> directories=new ArrayList<>();
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
        buttonSave=findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(this);
        textView9111 = findViewById(R.id.textView9111);

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
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    private File createImageFile() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File imagesDir = new File(storageDir, "MyEvidence");
        if (!imagesDir.exists()) {
            if (!imagesDir.mkdirs()) {

                Log.e("carrusel1", "Failed to create directory: " + imagesDir.getAbsolutePath());

            }
        }
        try {
            File imageFile = File.createTempFile(imageFileName, ".jpg", imagesDir);
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

            if (bitmap != null) {
                // Convert bitmap to byte array
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 65, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();

                // Convert byte array to Base64-encoded string
                String imageBase64 = Base64.encodeToString(byteArray, Base64.DEFAULT);
                Log.e("imageFilePhoto", "" + imageBase64);

                // Add Base64 string to imageCollections list
                if (!imageCollections.contains(imageBase64)) {
                    imageCollections.add(imageBase64);
                }
                adapter.UpdateArray(imageCollections);

                // Save the captured image as a temporary file
              //  saveTempImage(bitmap);
            } else {
                // Handle case where bitmap is null
                Toast.makeText(this, "Failed to load captured image", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Failed to capture image", Toast.LENGTH_SHORT).show();
        }
    }

    private void moveImagesToPhotosFolder() {
        for (File tempImageFile : tempImageFiles) {
            if (tempImageFile.exists()) {
                try {
                    // Convert image file to Base64
                    String base64Image = convertImageToBase64(tempImageFile);

                    if (!base64Image.isEmpty()) {
                        // Save Base64 image in memory
                        saveBase64ImageInMemory(base64Image, tempImageFile.getName());
                        Log.e("carrusel1", "Image saved in memory: " + tempImageFile.getName());
                        Log.e("carrusel1", "Image saved in memory: " + tempImageFile.getAbsolutePath());

                    } else {
                        Log.e("carrusel1", "Base64 image string is empty");
                    }
                } catch (IOException e) {
                    Log.e("carrusel1", "Error saving image in memory: " + e.getMessage());
                }
            } else {
                Log.e("carrusel1", "Source file does not exist: " + tempImageFile.getAbsolutePath());
            }
        }
        deleteFilesInTempFolder();
        // tempImageFiles.clear(); // Clear the list of temporary image files
    }
    private void deleteFilesInTempFolder() {
        File tempDir = getExternalFilesDir("Android/data/com.fhl.sistemadedistribucionfh/files/Pictures/");
        if (tempDir != null && tempDir.exists() && tempDir.isDirectory()) {
            File[] files = tempDir.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        if (file.delete()) {
                            Log.d("DeleteFiles", "Deleted file: " + file.getAbsolutePath());
                        } else {
                            Log.e("DeleteFiles", "Failed to delete file: " + file.getAbsolutePath());
                        }
                    }
                }
            }
        } else {
            Log.e("DeleteFiles", "Temp directory not found: " + tempDir);
        }
    }
    private void saveBase64ImageInMemory(String base64Image, String filename) throws IOException {
        byte[] decodedBytes = Base64.decode(base64Image, Base64.DEFAULT);

        File picturesDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File imagesDir = new File(picturesDir, "MyEvidence");
        if (!imagesDir.exists()) {
            if (!imagesDir.mkdirs()) {

                Log.e("carrusel1", "Failed to create directory: " + imagesDir.getAbsolutePath());
                return;
            }
        }

        File imageFile = new File(imagesDir, filename);
        FileOutputStream outputStream = new FileOutputStream(imageFile);
        outputStream.write(decodedBytes);
        outputStream.close();
        directories.add(imagesDir+"/"+filename);
    }
    private String convertImageToBase64(File imageFile) throws IOException {
        FileInputStream inputStream = new FileInputStream(imageFile);
        byte[] buffer = new byte[(int) imageFile.length()];
        inputStream.read(buffer);
        inputStream.close();
        String base64Image = Base64.encodeToString(buffer, Base64.DEFAULT);
        Log.e("carrusel1", "Base64 image length: " + base64Image.length());
        return base64Image;
    }

    private File saveTempImage(Bitmap bitmap) {
        File tempDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES); // Change to external storage directory
        File tempFile = null;
        try {
            tempFile = File.createTempFile("temp_image", ".jpg", tempDir);
            FileOutputStream out = new FileOutputStream(tempFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
            // Log the temporary file directory
            Log.e("carrusel1", "Temporary file path: " + tempFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempFile;
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
    public void showToast(String closeDialog,String idReason) {
        this.closeDialog=closeDialog;
        this.idReason=idReason;
        textView9111.setText(this.closeDialog);

    }
    private void gotomanifestV2(){
        Intent intent = new Intent(this, mainContainer.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_CLEAR_TOP);//
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }
    private void cleanFolder(){
        //.Toast.makeText(this, "Eliminar todo", Toast.LENGTH_SHORT).show();
        SharedPreferences preferences = getApplicationContext().getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(GeneralConstants.IMAGE_DIRECTORY, null);
        editor.commit();
        File picturesDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File imagesDir = new File(picturesDir, "MyEvidence");
        if (imagesDir.exists() && imagesDir.isDirectory()) {
            File[] files = imagesDir.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        if (file.delete()) {
                            Log.d("DeleteFiles", "Deleted file: " + file.getAbsolutePath());
                        } else {
                            Log.e("DeleteFiles", "Failed to delete file: " + file.getAbsolutePath());
                        }
                    }
                }
            }
        } else {
            Log.e("DeleteFiles", "Images directory not found: " + imagesDir.getAbsolutePath());
        }
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
               trashicon.setVisibility(View.GONE);
               //todo esto arreglo remueve los indexes de forma incorre
                break;
            case R.id.dialogReasons:
                new dialogReasons().show(this.getSupportFragmentManager(),"dialogReasons");
                break;
            case R.id.camera:
                openCamera();
                break;
            case R.id.buttonSave:
              //  moveImagesToPhotosFolder(); este metodo no sirve todo eliminar
             //   cleanFolder(); 
                if(idReason!=null) {
                   Toast.makeText(this, "guardar evidencias "+closeDialog , Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "Falta seleccionar un motivo ", Toast.LENGTH_SHORT).show();
                }
                //gotomanifestV2();
                break;
        }
    }
}
