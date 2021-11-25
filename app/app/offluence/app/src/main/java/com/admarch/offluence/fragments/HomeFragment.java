package com.admarch.offluence.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.admarch.offluence.R;
//import com.admarch.offluence.RideFragment;
import com.admarch.offluence.RideLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {
    public HomeFragment() {
        super(R.layout.tab1_fragment);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        Button startBtn = view.findViewById(R.id.startRide);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //place your action here
//                showOtherFragment();
                Intent myIntent = new Intent(getActivity(), RideLayout.class);
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
}
