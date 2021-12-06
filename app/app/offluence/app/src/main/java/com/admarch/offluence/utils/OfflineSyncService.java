package com.admarch.offluence.utils;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

import com.admarch.offluence.model.Location;
import com.admarch.offluence.model.Locations;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.Nullable;

public class OfflineSyncService extends Service {
    private Handler handler = new Handler();
    private Timer timer = new Timer();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    @Deprecated
    public void onStart(Intent intent, int startId) {

        super.onStart(intent, startId);
        Context context = this.getApplicationContext();

        TimerTask timerTask = new TimerTask() {

            @Override
            public void run() {
                handler.post(new Runnable() {

                    @Override
                    public void run() {
                        if(CommonMethod.isNetworkAvailable(context)){
                            FileUtil.readFromInternalStorage(context);
                            FileUtil.readLocationFromInternalStorage(context);
                        }


//                        Toast.makeText(, "latitude:"+gpsTracker.getLocation().getLatitude(), 4000).show();

                    }
                });


            }
        };

        timer.schedule(timerTask, 0, 30000000);

    }
}
