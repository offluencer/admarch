package com.admarch.offluence.utils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Handler;
import android.os.IBinder;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.admarch.offluence.R;
import com.admarch.offluence.fragments.HomeFragment;
import com.admarch.offluence.model.Earnings;
import com.admarch.offluence.model.Locations;
import com.admarch.offluence.rest.APIClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LocationService extends Service {

    SessionManager session;
    private GPSTracker gpsTracker;
    private Handler handler = new Handler();
    private Timer timer = new Timer();
    //    private Distance pastDistance = new Distance();
//    private Distance currentDistance = new Distance();
    public static double DISTANCE;
    boolean flag = true;
    private double totalDistance;

    @Override
    @Deprecated
    public void onStart(Intent intent, int startId) {

        super.onStart(intent, startId);
        session = new SessionManager(this.getApplicationContext());

        gpsTracker = new GPSTracker(this.getApplicationContext());
        TimerTask timerTask = new TimerTask() {

            @Override
            public void run() {
                handler.post(new Runnable() {

                    @Override
                    public void run() {
                        if (flag) {
                            gpsTracker.getLatitude();
                            gpsTracker.getLongitude();
                            flag = false;
                        } else {
                            Double lat = gpsTracker.getLatitude();
                            Double lon = gpsTracker.getLongitude();
                            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

                            com.admarch.offluence.model.Location location = new com.admarch.offluence.model.Location(lat,lon,timestamp.getTime());
                            List<com.admarch.offluence.model.Location> locationList = new ArrayList<>();
                            locationList.add(location);
                            Locations locations  = new Locations();
                            locations.setLocations(locationList);


                            sendLocation(locations);


//                            flag = comapre_LatitudeLongitude();
                        }
//                        Toast.makeText(, "latitude:"+gpsTracker.getLocation().getLatitude(), 4000).show();

                    }
                });


            }
        };

        timer.schedule(timerTask, 0, 300000);

    }

    private double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        return (dist);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }


    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }


    @Override
    public void onDestroy() {

        super.onDestroy();
        System.out.println("--------------------------------onDestroy -stop service ");
        timer.cancel();
        DISTANCE = totalDistance;
    }

    //    public boolean comapre_LatitudeLongitude(){
//
//        if(pastDistance.getLatitude() == currentDistance.getLatitude() && pastDistance.getLongitude() == currentDistance.getLongitude()){
//            return false;
//        }else{
//
//            final double distance = distance(pastDistance.getLatitude(),pastDistance.getLongitude(),currentDistance.getLatitude(),currentDistance.getLongitude());
//            System.out.println("Distance in mile :"+distance);
//            handler.post(new Runnable() {
//
//                @Override
//                public void run() {
//                    float kilometer=1.609344f;
//
//                    totalDistance = totalDistance +  distance * kilometer;
//                    DISTANCE = totalDistance;
//                    //Toast.makeText(HomeFragment.HOMECONTEXT, "distance in km:"+DISTANCE, 4000).show();
//
//                }
//            });
//
//            return true;
//        }
    private void sendLocation(Locations locations) {
        try {
            String rideId = session.getActiveRideInfo();
            Map userDetails = session.getUserDetails();
            String regNumber = (String) userDetails.get(session.KEY_NAME);

            Call<Boolean> call = APIClient.getInstance().getMyApi().sendLocation(regNumber,rideId, locations);
            call.enqueue(new Callback<Boolean>() {
                @Override
                public void onResponse(Call<Boolean> call, Response<Boolean> response) {



                }

                @Override
                public void onFailure(Call<Boolean> call, Throwable t) {
                    call.cancel();
                }
            });
        } catch (Exception exception) {

        }

    }
}


