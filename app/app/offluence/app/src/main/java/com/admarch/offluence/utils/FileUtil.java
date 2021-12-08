package com.admarch.offluence.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.admarch.offluence.model.EndRideResponse;
import com.admarch.offluence.model.Location;
import com.admarch.offluence.model.Locations;
import com.admarch.offluence.rest.APIClient;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import androidx.core.app.ActivityCompat;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.os.Build.VERSION.SDK_INT;

public class FileUtil {
    private final static String filename = "backup.txt";
    private final static String locationFilename = "location.txt";

    public static void writeToInternalStorage(String data, Context context){

        FileOutputStream fos;

        try {
            String rootPath = Environment.getExternalStorageDirectory() + "/com.admarch.offluence/";
            File file = new File(rootPath);
            if (!file.mkdirs()) {
                file.mkdirs();
            }
            File f = new File(rootPath + filename);
            f.createNewFile();

            fos = new FileOutputStream(f,true);
//            fos = context.openFileOutput(file, Context.MODE_PRIVATE);
            //default mode is PRIVATE, can be APPEND etc.
            fos.write(data.getBytes());
            fos.close();

//            Toast.makeText(getApplicationContext(),filename + " saved",
//                    Toast.LENGTH_LONG).show();


        } catch (FileNotFoundException e) {e.printStackTrace();}
         catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void writeLocationToInternalStorage(String data, Context context){

        FileOutputStream fos;

        try {
            String rootPath = Environment.getExternalStorageDirectory() + "/com.admarch.offluence/";
            File file = new File(rootPath);
            if (!file.mkdirs()) {
                file.mkdirs();
            }
            File f = new File(rootPath + filename);
            f.createNewFile();

            fos = new FileOutputStream(f,true);
//            fos = context.openFileOutput(file, Context.MODE_PRIVATE);
            //default mode is PRIVATE, can be APPEND etc.
            fos.write(data.getBytes());
            fos.close();

//            Toast.makeText(getApplicationContext(),filename + " saved",
//                    Toast.LENGTH_LONG).show();


        } catch (FileNotFoundException e) {e.printStackTrace();}
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void readFromInternalStorage(Context context) {
        String rootPath = Environment.getExternalStorageDirectory() + "/com.admarch.offluence/";
        try {

            File f = new File(rootPath + filename);

            FileInputStream fis = new FileInputStream(f);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            Gson gson = new Gson();
            List<EndRideResponse> endRideResponseList = new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null) {
                try {

                    EndRideResponse rideResponse = gson.fromJson(line, EndRideResponse.class);
                    endRideResponseList.add(rideResponse);
                }catch (Exception e){
                    continue;
                }
//                sb.append(line);
            }
            if(endRideResponseList!=null && CommonMethod.isNetworkAvailable(context)){
                Call<List<EndRideResponse>> call1 = APIClient.getInstance().getMyApi().endRideBatch(endRideResponseList);
                call1.enqueue(new Callback<List<EndRideResponse>>() {
                    @Override
                    public void onResponse(Call<List<EndRideResponse>> call, Response<List<EndRideResponse>> response) {
//                        EndRideResponse endRideResponse1 = response.body();
//
//                        if (endRideResponse1 != null) {
//                            Toast.makeText(getApplicationContext(),"Ride details successfully sent", Toast.LENGTH_LONG).show();
//
//                        }else{
//                            FileUtil.writeToInternalStorage(endRideResponse.toString(),getApplicationContext());
//                            Toast.makeText(getApplicationContext(),"Network unavailable", Toast.LENGTH_LONG).show();
//
//                        }
                    }
                    @Override
                    public void onFailure(Call<List<EndRideResponse>> call, Throwable t) {
//                        FileUtil.writeToInternalStorage(endRideResponse.toString(),getApplicationContext());
//                        Toast.makeText(getApplicationContext(),"Network unavailable", Toast.LENGTH_LONG).show();

                        call.cancel();
                    }
                });
                }



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readLocationFromInternalStorage(Context context) {
        String rootPath = Environment.getExternalStorageDirectory() + "/com.admarch.offluence/";
        try {
            SessionManager sessionManager =  new SessionManager(context);
            File f = new File(rootPath + locationFilename);

            FileInputStream fis = new FileInputStream(f);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            Gson gson = new Gson();
            List<Location> locationList = new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null) {
                try {

                    Location location = gson.fromJson(line, Location.class);
                    locationList.add(location);
                }catch (Exception e){
                    continue;
                }
//                sb.append(line);
            }

            if(locationList!=null && CommonMethod.isNetworkAvailable(context)){
                Locations locations = new Locations();
                locations.setLocations(locationList);
                String reg = sessionManager.getUserDetails().get(SessionManager.KEY_NAME);
                Call<Boolean> call1 = APIClient.getInstance().getMyApi().sendLocation(reg,"",locations);
                call1.enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
//                        EndRideResponse endRideResponse1 = response.body();
//
//                        if (endRideResponse1 != null) {
//                            Toast.makeText(getApplicationContext(),"Ride details successfully sent", Toast.LENGTH_LONG).show();
//
//                        }else{
//                            FileUtil.writeToInternalStorage(endRideResponse.toString(),getApplicationContext());
//                            Toast.makeText(getApplicationContext(),"Network unavailable", Toast.LENGTH_LONG).show();
//
//                        }
                    }
                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {
//                        FileUtil.writeToInternalStorage(endRideResponse.toString(),getApplicationContext());
//                        Toast.makeText(getApplicationContext(),"Network unavailable", Toast.LENGTH_LONG).show();

                        call.cancel();
                    }
                });
            }



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

