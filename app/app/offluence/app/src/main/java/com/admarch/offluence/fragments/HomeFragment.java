package com.admarch.offluence.fragments;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.admarch.offluence.HomeActivity;
import com.admarch.offluence.R;
//import com.admarch.offluence.RideFragment;
import com.admarch.offluence.RideLayout;
import com.admarch.offluence.model.LoginResponse;
import com.admarch.offluence.model.Ride;
import com.admarch.offluence.rest.APIClient;
import com.admarch.offluence.utils.FileUtil;
import com.admarch.offluence.utils.GPSTracker;
import com.admarch.offluence.utils.SessionManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.google.gson.internal.$Gson$Types.arrayOf;

public class HomeFragment extends Fragment {
    String rideId;
    String regNumber;
    String startTime;
    SessionManager session;
    GPSTracker gpsTracker;


    public HomeFragment() {
        super(R.layout.tab1_fragment);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        startTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(new Date());

        session = new SessionManager(getContext());


        Map userDetails = session.getUserDetails();
        regNumber = (String) userDetails.get(session.KEY_NAME);
        rideId = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+"_"+regNumber;
        Button startBtn = view.findViewById(R.id.startRide);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //place your action here
//                showOtherFragment();

                if(startRide()){
                    Intent myIntent = new Intent(getActivity(), RideLayout.class);
                    myIntent.putExtra("rideId",rideId);
                    startActivity(myIntent);
                }

            }
        });
    }

//    public void showOtherFragment()
//    {
//        Fragment fr=new RideFragment();
//        FragmentChangeListener fc=(FragmentChangeListener)getActivity();
//        fc.replaceFragment(fr);
//    }

    public void checkPermission() {
        GPSTracker gpsTracker = new GPSTracker(getContext());
        if(!gpsTracker.getIsGPSTrackingEnabled()){
            new AlertDialog.Builder(getContext())
                    .setMessage("GPS unavailable")
                    .setPositiveButton("Open network setting", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                            getContext().startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                        }
                    })
                    .show();
        }
        if (Build.VERSION.SDK_INT >= 23) {

            if (ContextCompat.checkSelfPermission( getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                return;
            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            return;
        }
    }

private boolean startRide(){
    final Ride ride = new Ride(regNumber,rideId,startTime,1);

    GPSTracker gpsTracker = new GPSTracker(this.getContext());

    if(!gpsTracker.getIsGPSTrackingEnabled()) {
        checkPermission();
        return Boolean.FALSE;

    }
    if (ContextCompat.checkSelfPermission( getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        checkPermission();
        return Boolean.FALSE;
    }


//    if(!gpsTracker.getIsGPSTrackingEnabled()) {
//
//        ActivityCompat.requestPermissions(this.getActivity(), new String[]{
//                Manifest.permission.ACCESS_FINE_LOCATION,
//                Manifest.permission.ACCESS_COARSE_LOCATION,}, 1);
//    }



//    while(!gpsTracker.getIsGPSTrackingEnabled()){
//        ActivityCompat.requestPermissions(this.getActivity(), new String[]{
//                Manifest.permission.ACCESS_FINE_LOCATION,
//                Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
//    }


//    if(gpsTracker.getIsGPSTrackingEnabled()){
//        ride.setRideSourceLat(String.valueOf(gpsTracker.getLatitude()));
//        ride.setRideSourceLon(String.valueOf(gpsTracker.getLongitude()));
//
//    }
    session.addActiveRideInfo(rideId,startTime,
            String.valueOf(gpsTracker.getLatitude()),
            String.valueOf(gpsTracker.getLongitude()));
//    Call<Ride> call1 = APIClient.getInstance().getMyApi().startRide(ride);
//    call1.enqueue(new Callback<Ride>() {
//        @Override
//        public void onResponse(Call<Ride> call, Response<Ride> response) {
//            Ride ride1 = response.body();
//            if (ride1 != null) {
//                session.addActiveRideInfo(rideId,startTime,
//                        String.valueOf(gpsTracker.getLatitude()),
//                        String.valueOf(gpsTracker.getLongitude()));
//            }
//        }
//
//        @Override
//        public void onFailure(Call<Ride> call, Throwable t) {
//            FileUtil.writeToInternalStorage(ride.toString(), getContext());
//
//            call.cancel();
//        }
//    });
    return Boolean.TRUE;
}
}
