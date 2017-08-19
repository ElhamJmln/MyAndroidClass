package com.example.jamalian.myandroidclass;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.jamalian.myandroidclass.Models.DownloadModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class SimpleDownloadManagerActivity extends AppCompatActivity {
    EditText etUrl;
    Button btnDownload;
    ProgressBar pbProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_download_manager);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1500);
            bind();
            btnDownload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent downloader = new Intent(SimpleDownloadManagerActivity.this, DownloaderService.class);

                    downloader.putExtra("url", etUrl.getText().toString());
                    startService(downloader);

                }
            });


        }
    }
    public void bind()
    {

        etUrl=(EditText) findViewById(R.id.etUrl);
        btnDownload=(Button)findViewById(R.id.btnDownload);
       pbProgress= (ProgressBar)findViewById(R.id.pbProgress);
    }
    @Subscribe
    public void updateProgress(DownloadModel dModel){
        pbProgress.setProgress(dModel.getPercent());

    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }
}

