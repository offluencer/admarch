package com.admarch.offluence.rest;

import com.admarch.offluence.model.LoginResponse;

import java.util.List;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by hcpl on 01/09/14.
 */
public class APIClient {

//    private static final String SERVER_PATH = "http://10.0.2.2:8080/admarch/";
    private static final String SERVER_PATH = "http://54.167.96.193:8080/admarch/";
//
    private static Retrofit retrofit;
    private APIInterface myApi;
    private static APIClient instance = null;



    //    public static Retrofit getUserApiClient() {
//            if (retrofit == null) {
//                retrofit = new Retrofit.Builder()
//                        .baseUrl(SERVER_PATH)
//                        .addConverterFactory(GsonConverterFactory.create())
//                        .build();
//            }
//        return retrofit;
//
//    }
    private APIClient() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(SERVER_PATH)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        myApi = retrofit.create(APIInterface.class);
    }

    public static synchronized APIClient getInstance() {
        if (instance == null) {
            instance = new APIClient();
        }
        return instance;
    }
    public APIInterface getMyApi() {
        return myApi;
    }


}

