package com.fhl.sistemadedistribucionfh.evidence.documents;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.Retrofit.GeneralConstants;
import com.fhl.sistemadedistribucionfh.evidence.documents.model.ApiResponse;
import com.fhl.sistemadedistribucionfh.evidence.documents.model.InnerData;
import com.fhl.sistemadedistribucionfh.evidence.documents.util.FileUploadService;

import java.io.File;
import java.io.IOException;

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
    private FileUploadService service;
    private static final int REQUEST_PICK_FILE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documents);
        initView();

        // Create Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.233:7420/App/Manifiesto/") // Base URL of the API
                .addConverterFactory(GsonConverterFactory.create()) // Gson converter factory for serialization/deserialization
                .build();

        // Create the Retrofit service
        service = retrofit.create(FileUploadService.class);
    }

    private void initView() {
        findViewById(R.id.firma).setOnClickListener(this);
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
                        uploadFile(filePath); // Upload the selected file
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
        }
    }

    private void uploadFile(String filePath) {
        // Create a file object
        File file = new File(filePath);

        // Create request body for file
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part listaArchivos = MultipartBody.Part.createFormData("ListaArchivos", file.getName(), requestBody);

        // Other request parameters
        RequestBody folioObjeto = RequestBody.create(MediaType.parse("text/plain"), "00000168");
        RequestBody tipoEvidencia = RequestBody.create(MediaType.parse("text/plain"), "2");
        RequestBody usuario = RequestBody.create(MediaType.parse("text/plain"), "texto");

        SharedPreferences preferences = getBaseContext().getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstants.TOKEN, null);
        // Authorization header
        String authorization = token;

        // Call the uploadFile method in Retrofit service
        Call<ApiResponse> call = service.uploadFile(authorization, folioObjeto, tipoEvidencia, listaArchivos, usuario);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    ApiResponse apiResponse = response.body();
                    if (apiResponse != null && apiResponse.getData() != null && !apiResponse.getData().isEmpty()) {
                        InnerData innerData = apiResponse.getData().get(0).getData();
                        // Handle the response data as needed
                        Log.e("uploader",""+innerData.getDocumentoId());
                        Toast.makeText(getApplicationContext(), "File uploaded successfully: " + innerData.getDocumentoId(), Toast.LENGTH_SHORT).show();

                    }
                } else {
                    Toast.makeText(getApplicationContext(), "File upload failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "File upload failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}