package com.admarch.offluence;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.admarch.offluence.R;
import com.admarch.offluence.model.EndRideResponse;
import com.admarch.offluence.model.Ride;
import com.admarch.offluence.model.Viewer;
import com.admarch.offluence.rest.APIClient;
import com.admarch.offluence.utils.FileUtil;
import com.admarch.offluence.utils.GPSTracker;
import com.admarch.offluence.utils.SessionManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RideLayout extends Activity implements View.OnClickListener {
    private TextView textTimer;
    private Button endRide;
    private static final String ADD = "add";
    private int clickCount=0;
    private Spinner passengerInfo;

    ImageButton add_m_5_16;
    ImageButton add_f_5_16;
    ImageButton add_m_17_30;
    ImageButton add_f_17_30;
    ImageButton add_m_30_50;
    ImageButton add_f_30_50;
    ImageButton add_m_50;
    ImageButton add_f_50;
    ImageButton remove_m_5_16;
    ImageButton remove_f_5_16;
    ImageButton remove_m_17_30;
    ImageButton remove_f_17_30;
    ImageButton remove_m_30_50;
    ImageButton remove_f_30_50;
    ImageButton remove_m_50;
    ImageButton remove_f_50;

    String rideId = "";
    private static final String REMOVE = "remove";
    SessionManager sessionManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        sessionManager = new SessionManager(getApplicationContext());

        rideId = getIntent().getExtras().getString("rideId");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.ride_fragment);
//        textTimer = (TextView)findViewById(R.id.duration);
//        startTimer(30000, 1000);
        endRide = (Button)findViewById(R.id.endRide);
        endRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sendRideDetails(v)){
                    sessionManager.removeActiveRideInfo();
                    Intent myIntent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(myIntent);
                }

            }
        });
        passengerInfo = (Spinner) findViewById(R.id.spinner1);

        add_m_5_16 = (ImageButton)findViewById(R.id.add_m_5_16);
        add_m_5_16.setOnClickListener(this);
        add_f_5_16 = (ImageButton)findViewById(R.id.add_f_5_16);
        add_f_5_16.setOnClickListener(this);
        add_m_17_30 = (ImageButton)findViewById(R.id.add_m_17_30);
        add_m_17_30.setOnClickListener(this);
        add_f_17_30 = (ImageButton)findViewById(R.id.add_f_17_30);
        add_f_17_30.setOnClickListener(this);
        add_m_30_50 = (ImageButton)findViewById(R.id.add_m_30_50);
        add_m_30_50.setOnClickListener(this);
        add_f_30_50 = (ImageButton)findViewById(R.id.add_f_30_50);
        add_f_30_50.setOnClickListener(this);
        add_m_50 = (ImageButton)findViewById(R.id.add_m_50);
        add_m_50.setOnClickListener(this);
        add_f_50 = (ImageButton)findViewById(R.id.add_f_50);
        add_f_50.setOnClickListener(this);
        remove_m_5_16 = (ImageButton)findViewById(R.id.remove_m_5_16);
        remove_m_5_16.setOnClickListener(this);
        remove_f_5_16 = (ImageButton)findViewById(R.id.remove_f_5_16);
        remove_f_5_16.setOnClickListener(this);
        remove_m_17_30 = (ImageButton)findViewById(R.id.remove_m_17_30);
        remove_m_17_30.setOnClickListener(this);
        remove_f_17_30 = (ImageButton)findViewById(R.id.remove_f_17_30);
        remove_f_17_30.setOnClickListener(this);
        remove_m_30_50 = (ImageButton)findViewById(R.id.remove_m_30_50);
        remove_m_30_50.setOnClickListener(this);
        remove_f_30_50 = (ImageButton)findViewById(R.id.remove_f_30_50);
        remove_f_30_50.setOnClickListener(this);
        remove_m_50 = (ImageButton)findViewById(R.id.remove_m_50);
        remove_m_50.setOnClickListener(this);
        remove_f_50 = (ImageButton)findViewById(R.id.remove_f_50);
        remove_f_50.setOnClickListener(this);
    }
    public void startTimer(final long finish, long tick) {
        CountDownTimer t;
        t = new CountDownTimer(finish, tick) {

            public void onTick(long millisUntilFinished) {
                long remainedSecs = millisUntilFinished / 1000;
                textTimer.setText("" + (remainedSecs / 60) + ":" + (remainedSecs % 60));// manage it accordign to you
            }

            public void onFinish() {
                textTimer.setText("00:00:00");
//                Toast.makeText(MainActivity.this, "Finish", Toast.LENGTH_SHORT).show();

                cancel();
            }
        }.start();
    }



    @Override
    public void onClick(View v) {
        String passengers = String.valueOf(passengerInfo.getSelectedItem());//passengerInfo.getText().toString();
        if(passengers.isEmpty()){
            Toast.makeText(getApplicationContext(),"Please enter total passengers", Toast.LENGTH_LONG);
            return;
        }

        switch(v.getId()) {
            case R.id.add_m_5_16:

                if(clickCount == Integer.parseInt(passengers)){
                    Toast.makeText(getApplicationContext(),"Please limit reached", Toast.LENGTH_LONG);

                    return;
                }
                clickCount++;

                setTextValue(R.id.count_m_5_16,ADD);
                break;
            case R.id.add_f_5_16:

                if(clickCount == Integer.parseInt(passengers)){
                    Toast.makeText(getApplicationContext(),"Please limit reached", Toast.LENGTH_LONG);

                    return;
                }
                clickCount++;

                setTextValue(R.id.count_f_5_16,ADD);
                break;
            case R.id.add_m_17_30:

                if(clickCount == Integer.parseInt(passengers)){
                    Toast.makeText(getApplicationContext(),"Please limit reached", Toast.LENGTH_LONG);

                    return;
                }
                clickCount++;

                setTextValue(R.id.count_m_17_30,ADD);
                break;
            case R.id.add_f_17_30:

                if(clickCount == Integer.parseInt(passengers)){
                    Toast.makeText(getApplicationContext(),"Please limit reached", Toast.LENGTH_LONG);

                    return;
                }
                clickCount++;

                setTextValue(R.id.count_f_17_30,ADD);
                break;
            case R.id.add_m_30_50:

                if(clickCount == Integer.parseInt(passengers)){
                    Toast.makeText(getApplicationContext(),"Please limit reached", Toast.LENGTH_LONG);

                    return;
                }
                clickCount++;

                setTextValue(R.id.count_m_30_50,ADD);
                break;
            case R.id.add_f_30_50:

                if(clickCount == Integer.parseInt(passengers)){
                    Toast.makeText(getApplicationContext(),"Please limit reached", Toast.LENGTH_LONG);
                    return;
                }
                clickCount++;

                setTextValue(R.id.count_f_30_50,ADD);
                break;
            case R.id.add_m_50:

                if(clickCount == Integer.parseInt(passengers)){
                    Toast.makeText(getApplicationContext(),"Please limit reached", Toast.LENGTH_LONG);
                    return;
                }
                clickCount++;

                setTextValue(R.id.count_m_50,ADD);
                break;
            case R.id.add_f_50:

                if(clickCount == Integer.parseInt(passengers)){
                    Toast.makeText(getApplicationContext(),"Please limit reached", Toast.LENGTH_LONG);
                    return;
                }
                clickCount++;

                setTextValue(R.id.count_f_50,ADD);
                break;
            case R.id.remove_m_5_16:
                if(clickCount == 0){
                    Toast.makeText(getApplicationContext(),"zero limit reached", Toast.LENGTH_LONG);
                    return;
                }
                clickCount--;

                setTextValue(R.id.count_m_5_16,REMOVE);
                break;
            case R.id.remove_f_5_16:
                if(clickCount == 0){
                    Toast.makeText(getApplicationContext(),"zero limit reached", Toast.LENGTH_LONG);
                    return;
                }
                clickCount--;

                setTextValue(R.id.count_f_5_16,REMOVE);
                break;
            case R.id.remove_m_17_30:
                if(clickCount == 0){
                    Toast.makeText(getApplicationContext(),"zero limit reached", Toast.LENGTH_LONG);
                    return;
                }
                clickCount--;

                setTextValue(R.id.count_m_17_30,REMOVE);
                break;
            case R.id.remove_f_17_30:
                if(clickCount == 0){
                    Toast.makeText(getApplicationContext(),"zero limit reached", Toast.LENGTH_LONG);
                    return;
                }
                clickCount--;

                setTextValue(R.id.count_f_17_30,REMOVE);
                break;
            case R.id.remove_m_30_50:
                if(clickCount == 0){
                    Toast.makeText(getApplicationContext(),"zero limit reached", Toast.LENGTH_LONG);
                    return;
                }
                clickCount--;

                setTextValue(R.id.count_m_30_50,REMOVE);
                break;
            case R.id.remove_f_30_50:
                if(clickCount == 0){
                    Toast.makeText(getApplicationContext(),"zero limit reached", Toast.LENGTH_LONG);
                    return;
                }
                clickCount--;

                setTextValue(R.id.count_f_30_50,REMOVE);
                break;
            case R.id.remove_m_50:
                if(clickCount == 0){
                    Toast.makeText(getApplicationContext(),"zero limit reached", Toast.LENGTH_LONG);
                    return;
                }
                clickCount--;

                setTextValue(R.id.count_m_50,REMOVE);
                break;
            case R.id.remove_f_50:
                if(clickCount == 0){
                    Toast.makeText(getApplicationContext(),"zero limit reached", Toast.LENGTH_LONG);
                    return;
                }
                clickCount--;

                setTextValue(R.id.count_f_50,REMOVE);
                break;

        }

    }

    private void setTextValue( int componentId, String action){
        TextView count;

        count = (TextView)findViewById(componentId);
        int value = Integer.parseInt(count.getText().toString());
        if(action.equalsIgnoreCase(ADD)){
            value = value + 1;
        }
        if(action.equalsIgnoreCase(REMOVE)){
            if(value != 0)
                value = value - 1;
        }
        count.setText(String.valueOf(value));
    }

    private boolean sendRideDetails(View v){
        GPSTracker gpsTracker = new GPSTracker(this);

        Map user = sessionManager.getUserDetails();
        String endTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(new Date());

        EndRideResponse endRideResponse = new EndRideResponse(user.get(SessionManager.KEY_NAME).toString(),
                rideId,endTime,0
                );
//        TextView nOfPassaenger  = (TextView)findViewById(R.id.viewer_count);
//        if(String.valueOf(passengerInfo.getSelectedItem()) == null || String.valueOf(passengerInfo.getSelectedItem());.isEmpty()){
//            Toast.makeText(this,"Enter number of passenger",Toast.LENGTH_LONG).show();
//            return Boolean.FALSE;
//
//        }

        endRideResponse.setNoOfPassengers(String.valueOf(passengerInfo.getSelectedItem()));
        TextView fare = (TextView)findViewById(R.id.fare);
        if(fare.getText() == null || fare.getText().toString().isEmpty()){
            Toast.makeText(this,"Enter fare",Toast.LENGTH_LONG).show();
            return Boolean.FALSE;

        }

        endRideResponse.setRideFare(fare.getText().toString());

        List<Viewer> viewers = getAdViewerList(String.valueOf(passengerInfo.getSelectedItem()));
        if(viewers == null || viewers.size() != Integer.parseInt(String.valueOf(passengerInfo.getSelectedItem()))){
            Toast.makeText(this,"Add passenger details",Toast.LENGTH_LONG).show();
            return Boolean.FALSE;
        }
        endRideResponse.setViewers(viewers);
        if (gpsTracker.getIsGPSTrackingEnabled())
        {
            endRideResponse.setRideDestinationLat(String.valueOf(gpsTracker.getLatitude()));
            endRideResponse.setRideDestinationLon(String.valueOf(gpsTracker.getLongitude()));
        }else{
            checkPermission();
        }

        endRideResponse.setRideSourceLat(sessionManager.getKeyActiveRideLat());
        endRideResponse.setRideSourceLon(sessionManager.getKeyActiveRideLon());
        endRideResponse.setStartRideTime(sessionManager.getKeyActiveRideStartTime());
        Call<EndRideResponse> call1 = APIClient.getInstance().getMyApi().endRide(endRideResponse);
        call1.enqueue(new Callback<EndRideResponse>() {
            @Override
            public void onResponse(Call<EndRideResponse> call, Response<EndRideResponse> response) {
                EndRideResponse endRideResponse1 = response.body();

                if (endRideResponse1 != null) {
                    Toast.makeText(getApplicationContext(),"Ride details successfully sent", Toast.LENGTH_LONG).show();

                }else{
                    FileUtil.writeToInternalStorage(endRideResponse.toString(),getApplicationContext());
                    Toast.makeText(getApplicationContext(),"Network unavailable", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<EndRideResponse> call, Throwable t) {
                FileUtil.writeToInternalStorage(endRideResponse.toString(),getApplicationContext());
                Toast.makeText(getApplicationContext(),"Network unavailable", Toast.LENGTH_LONG).show();

                call.cancel();
            }
        });
        return Boolean.TRUE;
    }
    private List<Viewer> getAdViewerList(String noOfPassengers){
        if(noOfPassengers.isEmpty()){
            Toast.makeText(getApplicationContext(),"Please enter number of passengers", Toast.LENGTH_SHORT).show();
            return null;
        }



        List<Viewer> viewers = new ArrayList<>();
        TextView count = (TextView)findViewById(R.id.count_m_5_16);
        if(!count.getText().toString().equalsIgnoreCase("0")){
            int noOfViewers = Integer.parseInt(count.getText().toString());
            for(int i=noOfViewers;i>0;i--){
                Viewer viewer = new Viewer("M","11");
                viewers.add(viewer);
            }
        }

        count = findViewById(R.id.count_f_5_16);
        if(!count.getText().toString().equalsIgnoreCase("0")){
            int noOfViewers = Integer.parseInt(count.getText().toString());
            for(int i=noOfViewers;i>0;i--){
                Viewer viewer = new Viewer("F","11");
                viewers.add(viewer);
            }
        }

        count = findViewById(R.id.count_m_17_30);
        if(!count.getText().toString().equalsIgnoreCase("0")){
            int noOfViewers = Integer.parseInt(count.getText().toString());
            for(int i=noOfViewers;i>0;i--){
                Viewer viewer = new Viewer("M","24");
                viewers.add(viewer);
            }
        }

        count = findViewById(R.id.count_f_17_30);
        if(!count.getText().toString().equalsIgnoreCase("0")){
            int noOfViewers = Integer.parseInt(count.getText().toString());
            for(int i=noOfViewers;i>0;i--){
                Viewer viewer = new Viewer("F","24");
                viewers.add(viewer);
            }
        }

        count = findViewById(R.id.count_m_30_50);
        if(!count.getText().toString().equalsIgnoreCase("0")){
            int noOfViewers = Integer.parseInt(count.getText().toString());
            for(int i=noOfViewers;i>0;i--){
                Viewer viewer = new Viewer("M","40");
                viewers.add(viewer);
            }
        }

        count = findViewById(R.id.count_f_30_50);
        if(!count.getText().toString().equalsIgnoreCase("0")){
            int noOfViewers = Integer.parseInt(count.getText().toString());
            for(int i=noOfViewers;i>0;i--){
                Viewer viewer = new Viewer("F","40");
                viewers.add(viewer);
            }
        }
        count = findViewById(R.id.count_m_50);
        if(!count.getText().toString().equalsIgnoreCase("0")){
            int noOfViewers = Integer.parseInt(count.getText().toString());
            for(int i=noOfViewers;i>0;i--){
                Viewer viewer = new Viewer("M","50");
                viewers.add(viewer);
            }
        }
        count = findViewById(R.id.count_f_50);
        if(!count.getText().toString().equalsIgnoreCase("0")){
            int noOfViewers = Integer.parseInt(count.getText().toString());
            for(int i=noOfViewers;i>0;i--){
                Viewer viewer = new Viewer("F","50");
                viewers.add(viewer);
            }
        }
        return viewers;
            }

    @Override
    public void onBackPressed() {
// super.onBackPressed();
// Not calling **super**, disables back button in current screen.
    }
    public void checkPermission() {
        GPSTracker gpsTracker = new GPSTracker(getApplicationContext());
        if(!gpsTracker.getIsGPSTrackingEnabled()){
            new AlertDialog.Builder(RideLayout.this)
                    .setMessage("GPS unavailable")
                    .setPositiveButton("Open network setting", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                            getApplicationContext().startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                        }
                    })
                    .show();
        }
        if (Build.VERSION.SDK_INT >= 23) {

            if (ContextCompat.checkSelfPermission( this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                return;
            } else {
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            return;
        } else {
            return;
        }
    }

}
