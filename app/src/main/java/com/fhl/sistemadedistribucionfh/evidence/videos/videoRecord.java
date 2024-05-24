package com.fhl.sistemadedistribucionfh.evidence.videos;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.Retrofit.GeneralConstants;
import com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus.EvidenciaLlegada;
import com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus.EvidenciaSalida;
import com.fhl.sistemadedistribucionfh.evidence.videos.adapter.OnItemClickListener;
import com.fhl.sistemadedistribucionfh.evidence.videos.adapter.adapterVideoRecord;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class videoRecord  extends AppCompatActivity implements View.OnClickListener, OnItemClickListener {

    private static final String TAG = "videoRecord";
    private static final int REQUEST_CODE_PERMISSIONS = 101;
    private static final String[] REQUIRED_PERMISSIONS = {Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO};
    private static final int REQUEST_CODE_VIDEO_CAPTURE = 102;

    private ImageButton recordButton,eraseFolder;
    private boolean isRecording = false;
    private Uri videoUri;
    private RecyclerView rv;
    private adapterVideoRecord adapter;
    private Integer positionErase;
    private List<String> lisEvidenceVideo=new ArrayList<>();
    private Integer flowDetail;
    private Integer posVid;
    private List<Uri> mfUrilist;
    private Button guardarVidosButon;
    private ImageView backImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_capture);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            lisEvidenceVideo.clear();
            List<EvidenciaSalida> evidenciaSalida = (List<EvidenciaSalida>) extras.getSerializable("evidenciaSalida");
            List<EvidenciaLlegada> evidenciaLlegada = (List<EvidenciaLlegada>) extras.getSerializable("evidenciaLlegada");

            flowDetail=extras.getInt("flowDetail");//todo si es 2 es de recoleccion o salida si es 1 es de llegada
            if(flowDetail==2){
                if(evidenciaSalida!=null){
                    for(EvidenciaSalida evidence:evidenciaSalida){
                        if(evidence.getTipoEvidencia()==4) {
                            lisEvidenceVideo.add(evidence.getValor());
                        }
                    }
                }
            }else {
                if(evidenciaLlegada!=null)
                {
                    for (EvidenciaLlegada evidence:evidenciaLlegada){
                        if(evidence.getTipoEvidencia()==4) {
                            lisEvidenceVideo.add(evidence.getValor());
                        }
                    }
                }
            }
            // Now you have your lists, you can use them as needed
        } else {
            // Handle case when extras bundle is null
        }
        initView();
        if (!allPermissionsGranted()) {
            ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS);
        }
    }

    private void initView() {
        rv=findViewById(R.id.rvVideos);
        backImage = findViewById(R.id.backImage);
        backImage.setOnClickListener(this);
        recordButton = findViewById(R.id.recordButton);
        recordButton.setOnClickListener(this);
        eraseFolder = findViewById(R.id.eraseFolder);
        eraseFolder.setOnClickListener(this);
        guardarVidosButon = findViewById(R.id.guardarVidosButon);
        guardarVidosButon.setOnClickListener(this);
        fillAdapter();
    }

    //region camera permissions
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                // Permission granted, do nothing
            } else {
                Toast.makeText(this, "Permissions not granted by the user.", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
    private boolean allPermissionsGranted() {
        for (String permission : REQUIRED_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }
    //endregion
    //region activity result video record
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            // Video recorded successfully
            // Handle the recorded video using the videoUri
            Toast.makeText(this, "Video recorded: " + videoUri.toString(), Toast.LENGTH_SHORT).show();
            Log.e("FHvideoR",""+videoUri.toString());
            adapter.addVideoUri(videoUri,posVid);
        }
    }
    private String getRealPathFromURI(Uri contentUri) {
        String[] projection = {MediaStore.Video.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, projection, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
            String filePath = cursor.getString(columnIndex);
            cursor.close();
            return filePath;
        }
        return null; // If cursor is null or empty, or if file path not found
    }
    private void startRecording() {
      //  createVideoDirectory();
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
            ContentValues values = new ContentValues();
            values.put(MediaStore.Video.Media.TITLE, "VIDEO_" + System.currentTimeMillis());
            values.put(MediaStore.Video.Media.DESCRIPTION, "Video captured using app");
            videoUri = getContentResolver().insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, values);
            if (videoUri != null) {
                takeVideoIntent.putExtra(MediaStore.EXTRA_OUTPUT, videoUri);
                startActivityForResult(takeVideoIntent, REQUEST_CODE_VIDEO_CAPTURE);
            } else {
                Toast.makeText(this, "Failed to create video file", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "No app can handle video recording", Toast.LENGTH_SHORT).show();
        }
    }
    private void createVideoDirectory() {//todo crea el directorio en pictures pero no es correcto hacer esto ya que el media store es generico para android
        File picturesDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File videosDir = new File(picturesDir, "FHLVideos");

        // Check if the directory doesn't exist, then create it
        if (!videosDir.exists()) {
            if (videosDir.mkdirs()) {
                Log.d(TAG, "Directory created successfully");
            } else {
                Log.e(TAG, "Failed to create directory");
            }
        } else {
            Log.d(TAG, "Directory already exists");
        }
    }
    private void stopRecording() {
        isRecording = false;
        recordButton.setImageResource(R.drawable.ic_menu_camera); // Change button icon to record
        // Implement any additional logic needed when stopping recording
    }
    //endregion
    @Override
    public void onItemClick(Uri videoUri, int position) {
        // Show a dialog to play the video
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_video_play, null);
        VideoView videoView = dialogView.findViewById(R.id.videoView);
        videoView.setVideoURI(videoUri);
        videoView.start();
        builder.setView(dialogView);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Eliminar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                positionErase=position;
                adapter.removeFile(videoUri);
                adapter.removeItem(positionErase);
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void removeformmedia(Uri uri) {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES).toURI().toString()+uri);

        // Check if the file exists
        if (file.exists()) {
            // Attempt to delete the file
            if (file.delete()) {
                Log.e("FHvideoR", "File removed successfully: " + String.valueOf(file));
            } else {
                Log.e("FHvideoR", "Failed to remove file: " + String.valueOf(file));
            }
        } else {
            Log.e("FHvideoR", "File does not exist: V " + String.valueOf(file));
        }
    }

    @Override
    public void startRecord(int position) {
        this.posVid=position;
        if (!isRecording) {
            startRecording();
        } else {
            stopRecording();
        }
    }

    @Override
    public void setUrilist(List<Uri> videoUriList) {
        this.mfUrilist=videoUriList;
        Log.e("faddVideoUri",""+mfUrilist);
        if(mfUrilist.isEmpty()){
            guardarVidosButon.setVisibility(View.GONE);
        }else{
            savePathsOnShared();
            guardarVidosButon.setVisibility(View.VISIBLE);
        }
    }

    private void savePathsOnShared() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < mfUrilist.size(); i++) {
            if (mfUrilist.get(i) != null) {
                String filePath = getRealPathFromURI(mfUrilist.get(i));
                if (filePath != null) {
                    stringBuilder.append(filePath);
                    if (i < mfUrilist.size() - 1) {
                        stringBuilder.append(","); // Append delimiter except for the last element
                    }
                }
            }
        }

        String filePathsString = stringBuilder.toString(); // Convert StringBuilder to String

        SharedPreferences preferences = getApplicationContext().getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(GeneralConstants.VIDEO_DIRECTORY, filePathsString);
        editor.apply(); // Use apply() instead of commit() for asynchronous save
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.recordButton:
                if (!isRecording) {
                    startRecording();
                } else {
                    stopRecording();
                }
                break;
            case R.id.backImage:
                onBackPressed();
                break;
            case R.id.eraseFolder:
                if(positionErase!=null){
                    Log.e("FHvideoR","video to remove by position " +positionErase);
                    adapter.removeItem(positionErase);
                }
                break;
            case R.id.guardarVidosButon://TODO una URI no es lo mismo que un filepath
                SharedPreferences preferences = getBaseContext().getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
                String videos=preferences.getString(GeneralConstants.VIDEO_DIRECTORY,null);
                Log.e("faddVideoUri","full list "+mfUrilist);
                Log.e("faddVideoUri","not null "+videos);
                Toast.makeText(this, "guardar uris", Toast.LENGTH_SHORT).show();
                onBackPressed();

                break;
        }
    }
    private void fillAdapter( ) {
        adapter=new adapterVideoRecord(getApplicationContext(),this,lisEvidenceVideo);
        LinearLayoutManager linearLayoutManager = new GridLayoutManager(getApplicationContext(),3);
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(adapter);
    }
}