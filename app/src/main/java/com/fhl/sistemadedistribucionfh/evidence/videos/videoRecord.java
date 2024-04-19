package com.fhl.sistemadedistribucionfh.evidence.videos;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fhl.sistemadedistribucionfh.Cancelar.adapter.adapterNoCompletado;
import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.evidence.videos.adaoter.adapterVideoRecord;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class videoRecord  extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "videoRecord";
    private static final int REQUEST_CODE_PERMISSIONS = 101;
    private static final String[] REQUIRED_PERMISSIONS = {Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO};
    private static final int REQUEST_CODE_VIDEO_CAPTURE = 102;

    private ImageButton recordButton;
    private boolean isRecording = false;
    private Uri videoUri;
    private RecyclerView rv;
    private adapterVideoRecord adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_capture);
        initView();
        if (!allPermissionsGranted()) {
            ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS);
        }
    }

    private void initView() {
        rv=findViewById(R.id.rvVideos);
        recordButton = findViewById(R.id.recordButton);
        recordButton.setOnClickListener(this);
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
        }
    }
    private void startRecording() {
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
    private File createVideoFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String videoFileName = "VIDEO_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(null);
        return File.createTempFile(videoFileName, ".mp4", storageDir);
    }
    private void stopRecording() {
        isRecording = false;
        recordButton.setImageResource(R.drawable.ic_menu_camera); // Change button icon to record
        // Implement any additional logic needed when stopping recording
    }
    //endregion
    @Override
    public void onClick(View v) {
        if (!isRecording) {
            startRecording();
        } else {
            stopRecording();
        }
    }
    private void fillAdapter( ) {
        adapter=new adapterVideoRecord(getApplicationContext());
        LinearLayoutManager linearLayoutManager = new GridLayoutManager(getApplicationContext(),3);
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(adapter);
    }
}