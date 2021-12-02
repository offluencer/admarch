package com.admarch.offluence.utils;

import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.admarch.offluence.LoginActivity;

public class SessionManager {

    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "Offluence";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    // User name (make variable public to access from outside)
    public static final String KEY_NAME = "name";

    public static final String KEY_ACTIVE_RIDE = "activeRideId";
    public static final String KEY_ACTIVE_RIDE_START_TIME = "activeStartTime";
    public static final String KEY_ACTIVE_RIDE_LAT = "sourceLat";
    public static final String KEY_ACTIVE_RIDE_LON = "sourceLon";


    // Email address (make variable public to access from outside)
    public static final String KEY_EMAIL = "email";

    // Constructor
    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


    /**
     * Create login session
     * */
    public void createLoginSession(String name){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing name in pref
        editor.putString(KEY_NAME, name);

        // Storing email in pref
//        editor.putString(KEY_EMAIL, email);

        // commit changes
        editor.commit();
    }

    public String getActiveRideInfo(){
//        if(pref.contains(KEY_ACTIVE_RIDE)){
        return pref.getString(KEY_ACTIVE_RIDE,"NORIDE");
//        }
//        editor.putString(KEY_ACTIVE_RIDE,rideId);
//        return rideId;
    }
    public String getKeyActiveRideStartTime(){
//        if(pref.contains(KEY_ACTIVE_RIDE)){
        return pref.getString(KEY_ACTIVE_RIDE_START_TIME,"NORIDE");
//        }
//        editor.putString(KEY_ACTIVE_RIDE,rideId);
//        return rideId;
    }
    public String getKeyActiveRideLat(){
//        if(pref.contains(KEY_ACTIVE_RIDE)){
        return pref.getString(KEY_ACTIVE_RIDE_LAT,"NORIDE");
//        }
//        editor.putString(KEY_ACTIVE_RIDE,rideId);
//        return rideId;
    }
    public String getKeyActiveRideLon(){
//        if(pref.contains(KEY_ACTIVE_RIDE)){
        return pref.getString(KEY_ACTIVE_RIDE_LON,"NORIDE");
//        }
//        editor.putString(KEY_ACTIVE_RIDE,rideId);
//        return rideId;
    }
    public void removeActiveRideInfo(){
        if(pref.contains(KEY_ACTIVE_RIDE)){
            editor.remove(KEY_ACTIVE_RIDE);
            editor.remove(KEY_ACTIVE_RIDE_START_TIME);
            editor.remove(KEY_ACTIVE_RIDE_LAT);
            editor.remove(KEY_ACTIVE_RIDE_LON);

            editor.commit();

        }
    }
    public void addActiveRideInfo(String rideId,String startDateTime,String sourceLat, String sourceLon){
//        if(pref.contains(KEY_ACTIVE_RIDE)){
//            return pref.getString(KEY_ACTIVE_RIDE,"NORIDE");
//        }
        editor.putString(KEY_ACTIVE_RIDE,rideId);
        editor.putString(KEY_ACTIVE_RIDE_START_TIME,startDateTime);
        editor.putString(KEY_ACTIVE_RIDE_LAT,sourceLat);
        editor.putString(KEY_ACTIVE_RIDE_LON,sourceLon);

        editor.commit();
//        return rideId;
    }

    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     * */
    public void checkLogin(){
        // Check login status
        if(!this.isLoggedIn()){
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, LoginActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);
        }

    }

    /**
     * Get stored session data
     * */
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));
        return user;
    }
    // Get Login State
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }
}
