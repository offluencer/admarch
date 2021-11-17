package com.admarch.offluence;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.admarch.offluence.model.LoginResponse;
import com.admarch.offluence.rest.APIClient;
//import com.admarch.offluence.rest.APIInterface;
import com.admarch.offluence.utils.CommonMethod;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

//    APIInterface userApiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button registerBtn = findViewById(R.id.register);
        final EditText editText = findViewById(R.id.reg_number);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                if( Objects.isNull(editText.getText()))  ;
                if (CommonMethod.isNetworkAvailable(MainActivity.this))
                    registerUser(editText.getText().toString());
                else
                    CommonMethod.showAlert("Internet Connectivity Failure", MainActivity.this);

            }
        });



    }
    private void registerUser(String regNumber){
        final LoginResponse login = new LoginResponse(regNumber);
        Call<LoginResponse> call1 = APIClient.getInstance().getMyApi().createUser(login);
        call1.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();

                if (loginResponse != null) {


//                    String responseCode = loginResponse.();

//                    if (responseCode != null && responseCode.equals("404")) {
//                        Toast.makeText(MainActivity.this, "Invalid Login Details \n Please try again", Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(MainActivity.this, "Welcome " + loginResponse.getRegNumber(), Toast.LENGTH_SHORT).show();
//                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "onFailure called ", Toast.LENGTH_SHORT).show();
                call.cancel();
            }
        });
    }
}