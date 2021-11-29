package com.admarch.offluence.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.admarch.offluence.R;
import com.admarch.offluence.model.Earnings;
import com.admarch.offluence.model.Ride;
import com.admarch.offluence.rest.APIClient;
import com.admarch.offluence.utils.SessionManager;

import java.util.Map;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {
    public ProfileFragment() {
        super(R.layout.tab3_fragment);
    }
    SessionManager session;

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        session = new SessionManager(getContext());

        Map userDetails = session.getUserDetails();

        String regNumber = (String) userDetails.get(session.KEY_NAME);

        TextView reg = view.findViewById(R.id.registerNumber);
        reg.setText(regNumber);
        fillEarningsData(regNumber,view);
    }
    private void fillEarningsData(String regNumber, View view) {
        try {
            Call<Earnings> call = APIClient.getInstance().getMyApi().getEarnings(regNumber);
            call.enqueue(new Callback<Earnings>() {
                @Override
                public void onResponse(Call<Earnings> call, Response<Earnings> response) {
                    Earnings earnings = response.body();

                    if (earnings != null) {
                        TextView space = view.findViewById(R.id.space_earning);
                        space.setText("50");
                        TextView conversion = view.findViewById(R.id.conversion_earning);
                        conversion.setText(String.valueOf(earnings.getConversionEarning()));
                        TextView totalConversion = view.findViewById(R.id.total_earning);
                        int totalC = earnings.getConversionEarning() + 50;
                        totalConversion.setText(String.valueOf(totalC));
                    }
                }

                @Override
                public void onFailure(Call<Earnings> call, Throwable t) {
                    call.cancel();
                }
            });
        } catch (Exception exception) {

        }
    }
}
