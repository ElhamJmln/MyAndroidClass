package com.example.jamalian.myandroidclass;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.jamalian.myandroidclass.Models.DownloadModel;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.FileAsyncHttpResponseHandler;

import org.greenrobot.eventbus.EventBus;

import java.io.File;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Jamalian on 8/18/2017.
 */

public class DownloaderService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String url = intent.getStringExtra("url");
       // Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();
        download(url);
        //Toast.makeText(this, "download was successful", Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
    }


    void download(final String url) {

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new FileAsyncHttpResponseHandler(this) {
            @Override
            public void onProgress(long bytesWritten, long totalSize) {
                int percent = (int) ((bytesWritten * 100.0f) / totalSize);
                DownloadModel dModel=new DownloadModel();
                dModel.setPercent(percent);
                dModel.setName(url);
                EventBus.getDefault().post(dModel);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, File file) {

            }



        });
    }
}



