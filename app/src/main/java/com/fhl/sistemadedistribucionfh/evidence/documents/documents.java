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
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.Retrofit.GeneralConstants;
import com.fhl.sistemadedistribucionfh.evidence.documents.model.ApiResponse;
import com.fhl.sistemadedistribucionfh.evidence.documents.model.InnerData;
import com.fhl.sistemadedistribucionfh.evidence.documents.util.FileUploadService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
    private Button saveFilesDir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documents);
        initView();
    }

    private void initView() {
        findViewById(R.id.firma).setOnClickListener(this);
        saveFilesDir=findViewById(R.id.saveFilesDir);
        saveFilesDir.setOnClickListener(this);
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
                        tempImageFiles.add(filePath);
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
    private void saveDir(){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < tempImageFiles.size(); i++) {
            stringBuilder.append(tempImageFiles.get(i));
            if (i < tempImageFiles.size() - 1) {
                stringBuilder.append(", "); // Append delimiter except for the last element
            }
        }
        Log.e("",""+tempImageFiles);
        SharedPreferences preferences = getApplicationContext().getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(GeneralConstants.DOCS_DIRECTORY, String.valueOf(stringBuilder));
        editor.commit();
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
        }
    }


}