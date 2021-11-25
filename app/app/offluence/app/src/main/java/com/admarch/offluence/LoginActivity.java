package com.admarch.offluence;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.admarch.offluence.model.LoginResponse;
import com.admarch.offluence.rest.APIClient;
import com.admarch.offluence.utils.CommonMethod;
import com.admarch.offluence.utils.SessionManager;

import java.io.IOException;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//import com.admarch.offluence.rest.APIInterface;

public class LoginActivity extends AppCompatActivity {
    SessionManager session;

//    APIInterface userApiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button registerBtn = findViewById(R.id.register);
        final EditText editText = findViewById(R.id.reg_number);
        session = new SessionManager(getApplicationContext());
        registerBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                if( Objects.isNull(editText.getText()))  ;


                if (CommonMethod.isNetworkAvailable(LoginActivity.this))
                    registerUser(editText.getText().toString());
                else
                    CommonMethod.showAlert("Internet Connectivity Failure", LoginActivity.this);

                Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(i);
                finish();

            }
        });



    }
    private void registerUser(String regNumber) {
        final LoginResponse login = new LoginResponse(regNumber);
        try {
            Call<LoginResponse> call1 = APIClient.getInstance().getMyApi().createUser(login);
            Response<LoginResponse> loginResponse = call1.execute();
            if (loginResponse.isSuccessful()) {
                session.createLoginSession(loginResponse.body().getRegNumber());
            }
        } catch (Exception exception) {

        }
    }
}