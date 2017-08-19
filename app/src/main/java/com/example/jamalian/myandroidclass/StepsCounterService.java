package com.example.jamalian.myandroidclass;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.SmartLocation;

/**
 * Created by Jamalian on 8/19/2017.
 */

public class StepsCounterService  extends Service {
    public StepsCounterService() {

    }

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
    public int onStartCommand(final Intent intent, int flags, int startId) {

        SmartLocation.with(StepsCounterService.this).location()
                .start(new OnLocationUpdatedListener() {
                    @Override
                    public void onLocationUpdated(Location location) {
                        LocationModel model= new LocationModel() ;
                        model.setLatitude(location.getLatitude());
                        model.setLongtitude(location.getLongitude());

                        EventBus.getDefault().post(model);

                    }
                });





        return super.onStartCommand(intent, flags, startId);
    }





}



