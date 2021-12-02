package com.admarch.offluence;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;

import com.admarch.offluence.fragments.FragmentChangeListener;
import com.admarch.offluence.utils.BackgroundLocationUpdateService;
import com.admarch.offluence.utils.GPSTracker;
import com.admarch.offluence.utils.LocationService;
import com.admarch.offluence.utils.SectionPageAdapter;
import com.admarch.offluence.utils.SessionManager;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

public class HomeActivity extends AppCompatActivity  {
    TabLayout tabLayout;
    ViewPager viewPager;
    // Session Manager Class
    SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        startService(new Intent(this.getApplicationContext(), LocationService.class));

        session = new SessionManager(getApplicationContext());


        session.checkLogin();
        checkPermission();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        tabLayout=(TabLayout)findViewById(R.id.tabLayout);
        viewPager=(ViewPager)findViewById(R.id.viewPager);
        tabLayout.addTab(tabLayout.newTab().setText("Home"));
        tabLayout.addTab(tabLayout.newTab().setText("Profile"));
        tabLayout.addTab(tabLayout.newTab().setText("LeaderBoard"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final SectionPageAdapter adapter = new SectionPageAdapter(this,getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
    public void checkPermission() {
        GPSTracker gpsTracker = new GPSTracker(getApplicationContext());
        if(!gpsTracker.getIsGPSTrackingEnabled()){
            new AlertDialog.Builder(HomeActivity.this)
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
            checkPermission();
        }
    }

//    @Override
//    public void replaceFragment(Fragment fragment) {
//        FragmentManager fragmentManager = getSupportFragmentManager();;
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.activity_tab, fragment, fragment.toString());
//        fragmentTransaction.addToBackStack(fragment.toString());
//        fragmentTransaction.commit();
//    }
}
