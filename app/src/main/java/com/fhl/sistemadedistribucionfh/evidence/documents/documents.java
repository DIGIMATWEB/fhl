package com.fhl.sistemadedistribucionfh.evidence.documents;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.Retrofit.GeneralConstants;
import com.fhl.sistemadedistribucionfh.evidence.documents.adapter.FileAdapter;
import com.fhl.sistemadedistribucionfh.evidence.documents.model.ApiResponse;
import com.fhl.sistemadedistribucionfh.evidence.documents.model.InnerData;
import com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus.EvidenciaLlegada;
import com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus.EvidenciaSalida;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class documents extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = documents.class.getSimpleName();
   // private FileUploadService service;
    private static final int REQUEST_PICK_FILE = 123;
    private ArrayList<String> tempImageFiles = new ArrayList<>();
    private ArrayList<String> tempImageFilesNames = new ArrayList<>();
    private Button saveFilesDir;
    private ImageView imageMenu,onbackDocs;
    private TextView menuName;
    private RecyclerView recyclerView;
    private FileAdapter fileAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documents);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            List<EvidenciaSalida> evidenciaSalida = (List<EvidenciaSalida>) extras.getSerializable("evidenciaSalida");
            List<EvidenciaLlegada> evidenciaLlegada = (List<EvidenciaLlegada>) extras.getSerializable("evidenciaLlegada");
            // Now you have your lists, you can use them as needed
        } else {
            // Handle case when extras bundle is null
        }
        initView();
        checkShared();


    }

    private void checkShared() {
        SharedPreferences preferences = getBaseContext().getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String docs=preferences.getString(GeneralConstants.DOCS_DIRECTORY, null);
        if(docs!=null){
            Log.e("sendEvidence","uri"+docs);
            imageMenu.setColorFilter(Color.rgb(0, 187, 41), PorterDuff.Mode.SRC_ATOP);
//            File file = new File(docs);
//            String fileName = file.getName();
//           // menuName.setText(fileName);
            String inputString = docs;
            String[] parts = inputString.split(",");
// Create ArrayList<String> from the array
            ArrayList<String> fileList = new ArrayList<>(Arrays.asList(parts));
            tempImageFiles=fileList;
            for(String names:tempImageFiles){
                File file = new File(names);
                String fileName = file.getName();
                tempImageFilesNames.add(fileName);
            }
            filleAdapter();
        }
        else{
            imageMenu.setColorFilter(Color.rgb(112, 112, 112), PorterDuff.Mode.SRC_ATOP);
        }
    }

    private void initView() {
        findViewById(R.id.firma).setOnClickListener(this);
        saveFilesDir=findViewById(R.id.saveFilesDir);
        saveFilesDir.setOnClickListener(this);
        imageMenu = findViewById(R.id.imageMenu);
        menuName = findViewById(R.id.menuName);
        onbackDocs= findViewById(R.id.onbackDocs);
        onbackDocs.setOnClickListener(this);
        recyclerView = findViewById(R.id.recyclerView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_PICK_FILE && resultCode == RESULT_OK) {
            if (data != null) {
                Uri uri = data.getData();
                try {
                    String filePath = FileSelectionUtils.getFilePathFromUri(this, uri).getPath();
                    if (filePath != null) {
                        //uploadFile(filePath); // Upload the selected file
                        String fileName = getFileNameFromUri(uri);
                        //menuName.setText(fileName);
                        if(fileName!=null){
                            imageMenu.setColorFilter(Color.rgb(0, 187, 41), PorterDuff.Mode.SRC_ATOP);
                        }else{
                            imageMenu.setColorFilter(Color.rgb(112, 112, 112), PorterDuff.Mode.SRC_ATOP);
                        }
                        tempImageFiles.add(filePath);
                        tempImageFilesNames.add(fileName);
                        Log.e("uploader",""+filePath);
                        String[] filePathSplit = filePath.split("\\.");
                        String fileExtension = filePathSplit[filePathSplit.length - 1];
                        Log.e("uploader", "File extension: " + fileExtension);
                    } else {
                        Toast.makeText(this, "Failed to get file path", Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Failed to get file path", Toast.LENGTH_SHORT).show();
                }
            }
        }

        filleAdapter();
    }
    private String getFileNameFromUri(Uri uri) {
        String fileName = null;
        String[] projection = {MediaStore.MediaColumns.DISPLAY_NAME};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DISPLAY_NAME);
            fileName = cursor.getString(columnIndex);
            cursor.close();
        }
        return fileName;
    }
    private void saveDir(){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < tempImageFiles.size(); i++) {
            stringBuilder.append(tempImageFiles.get(i));
            if (i < tempImageFiles.size() - 1) {
                stringBuilder.append(","); // Append delimiter except for the last element
            }
        }
        fileAdapter.notifyDataSetChanged();
        Log.e("",""+tempImageFiles);
        SharedPreferences preferences = getApplicationContext().getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(GeneralConstants.DOCS_DIRECTORY, String.valueOf(stringBuilder));
        editor.commit();

        Log.e("sendEvidence", "files "+stringBuilder);

    }

    private void filleAdapter() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Initialize adapter with tempImageFiles
        fileAdapter = new FileAdapter(tempImageFilesNames);
        recyclerView.setAdapter(fileAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.firma:
                // Specify the file path
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*"); // Allow any file type to be selected
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent, REQUEST_PICK_FILE);
                break;
            case R.id.saveFilesDir:
                if(tempImageFiles!=null){
                    if(!tempImageFiles.isEmpty()){
                        saveDir();
                    }
                }
                onBackPressed();
                break;
            case R.id.onbackDocs:
                onBackPressed();
                break;
        }
    }
}