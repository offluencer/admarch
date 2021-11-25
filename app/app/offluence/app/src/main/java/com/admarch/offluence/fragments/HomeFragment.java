package com.admarch.offluence.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.admarch.offluence.R;
//import com.admarch.offluence.RideFragment;
import com.admarch.offluence.RideLayout;
import com.admarch.offluence.model.LoginResponse;
import com.admarch.offluence.model.Ride;
import com.admarch.offluence.rest.APIClient;
import com.admarch.offluence.utils.SessionManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    String rideId;
    String regNumber;
    String startTime;
    SessionManager session;
    public HomeFragment() {
        super(R.layout.tab1_fragment);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        rideId = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        startTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(new Date());

        session = new SessionManager(getContext());

        Map userDetails = session.getUserDetails();
        regNumber = (String) userDetails.get(session.KEY_NAME);
        Button startBtn = view.findViewById(R.id.startRide);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //place your action here
//                showOtherFragment();

                startRide();
                Intent myIntent = new Intent(getActivity(), RideLayout.class);
                myIntent.putExtra("rideId",rideId);
                startActivity(myIntent);
            }
        });
    }

//    public void showOtherFragment()
//    {
//        Fragment fr=new RideFragment();
//        FragmentChangeListener fc=(FragmentChangeListener)getActivity();
//        fc.replaceFragment(fr);
//    }
private void startRide(){
    final Ride ride = new Ride(regNumber,rideId,startTime,1);
    Call<Ride> call1 = APIClient.getInstance().getMyApi().startRide(ride);
    call1.enqueue(new Callback<Ride>() {
        @Override
        public void onResponse(Call<Ride> call, Response<Ride> response) {
            Ride ride1 = response.body();

            if (ride1 != null) {

            }
        }

        @Override
        public void onFailure(Call<Ride> call, Throwable t) {
            call.cancel();
        }
    });
}
}
