package com.admarch.offluence;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.admarch.offluence.R;
import com.admarch.offluence.model.EndRideResponse;
import com.admarch.offluence.model.Ride;
import com.admarch.offluence.model.Viewer;
import com.admarch.offluence.rest.APIClient;
import com.admarch.offluence.utils.SessionManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RideLayout extends Activity implements View.OnClickListener {
    private TextView textTimer;
    private Button endRide;
    private static final String ADD = "add";

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
        textTimer = (TextView)findViewById(R.id.duration);
        startTimer(30000, 1000);
        endRide = (Button)findViewById(R.id.endRide);
        endRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRideDetails(v);
                Intent myIntent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(myIntent);
            }
        });

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
        switch(v.getId()) {

            case R.id.add_m_5_16:
                setTextValue(R.id.count_m_5_16,ADD);
                break;
            case R.id.add_f_5_16:
                setTextValue(R.id.count_f_5_16,ADD);
                break;
            case R.id.add_m_17_30:
                setTextValue(R.id.count_m_17_30,ADD);
                break;
            case R.id.add_f_17_30:
                setTextValue(R.id.count_f_17_30,ADD);
                break;
            case R.id.add_m_30_50:
                setTextValue(R.id.count_m_30_50,ADD);
                break;
            case R.id.add_f_30_50:
                setTextValue(R.id.count_f_30_50,ADD);
                break;
            case R.id.add_m_50:
                setTextValue(R.id.count_m_50,ADD);
                break;
            case R.id.add_f_50:
                setTextValue(R.id.count_f_50,ADD);
                break;
            case R.id.remove_m_5_16:
                setTextValue(R.id.count_m_5_16,REMOVE);
                break;
            case R.id.remove_f_5_16:
                setTextValue(R.id.count_m_5_16,REMOVE);
                break;
            case R.id.remove_m_17_30:
                setTextValue(R.id.count_m_17_30,REMOVE);
                break;
            case R.id.remove_f_17_30:
                setTextValue(R.id.count_f_17_30,REMOVE);
                break;
            case R.id.remove_m_30_50:
                setTextValue(R.id.count_m_30_50,REMOVE);
                break;
            case R.id.remove_f_30_50:
                setTextValue(R.id.count_f_30_50,REMOVE);
                break;
            case R.id.remove_m_50:
                setTextValue(R.id.count_m_50,REMOVE);
                break;
            case R.id.remove_f_50:
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

    private void sendRideDetails(View v){
        Map user = sessionManager.getUserDetails();
        String endTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(new Date());

        EndRideResponse endRideResponse = new EndRideResponse(user.get(SessionManager.KEY_NAME).toString(),
                rideId,endTime,0
                );
        TextView nOfPassaenger  = (TextView)findViewById(R.id.viewer_count);

        endRideResponse.setNoOfPassengers(nOfPassaenger.getText().toString());
        TextView fare = (TextView)findViewById(R.id.fare);
        endRideResponse.setRideFare(fare.getText().toString());
        endRideResponse.setViewers(getAdViewerList());

        Call<EndRideResponse> call1 = APIClient.getInstance().getMyApi().endRide(endRideResponse);
        call1.enqueue(new Callback<EndRideResponse>() {
            @Override
            public void onResponse(Call<EndRideResponse> call, Response<EndRideResponse> response) {
                EndRideResponse endRideResponse1 = response.body();

                if (endRideResponse1 != null) {
                    Toast.makeText(getApplicationContext(),"Successfully sent", Toast.LENGTH_LONG);

                }
            }

            @Override
            public void onFailure(Call<EndRideResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Failed to send", Toast.LENGTH_LONG);

                call.cancel();
            }
        });
    }
    private List<Viewer> getAdViewerList(){
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
}
