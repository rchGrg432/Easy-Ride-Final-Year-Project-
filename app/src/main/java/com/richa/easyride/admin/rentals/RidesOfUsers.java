package com.richa.easyride.admin.rentals;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.richa.easyride.R;
import com.richa.easyride.api.ApiClient;
import com.richa.easyride.api.response.AllRentalsResponse;
import com.richa.easyride.api.response.RentalHistory;
import com.richa.easyride.api.response.SetRentalResponse;
import com.richa.easyride.home.fragments.home.adapters.AllRentalUsersAdapter;
import com.richa.easyride.home.fragments.home.adapters.AllRentalsAdapter;
import com.richa.easyride.utils.DataHolder;
import com.richa.easyride.utils.SharedPrefUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RidesOfUsers extends AppCompatActivity {
    public static String key = "pKey";
    RecyclerView ridesRV;
    ProgressBar loadingProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rides_of_users);

        ridesRV = findViewById(R.id.ridesRV);
        loadingProgress = findViewById(R.id.loadingProgress);
        serverCall();
    }

    void serverCall(){
        String key = SharedPrefUtils.getString(this, getString(R.string.api_key));
        Call<SetRentalResponse> setRentalResponseCall = ApiClient.getClient().getAllRentalResponse(key);
        setRentalResponseCall.enqueue(new Callback<SetRentalResponse>() {
            @Override
            public void onResponse(Call<com.richa.easyride.api.response.SetRentalResponse> call, Response<SetRentalResponse> response) {
                if (response.isSuccessful()) {
                    toggleLoading(false);
                    setRecycleView(response.body().getRentalHistory());
                }
            }

            @Override
            public void onFailure(Call<com.richa.easyride.api.response.SetRentalResponse> call, Throwable t) {

            }
        });
    }

    void setRecycleView(List<AllRentalsResponse> rentalHistoryList){
        ridesRV.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        ridesRV.setLayoutManager(layoutManager);
        AllRentalsAdapter allRentalsAdapter = new AllRentalsAdapter(rentalHistoryList, this);
        ridesRV.setAdapter(allRentalsAdapter);
    }

    void toggleLoading(boolean toggle) {
        if (toggle)
            loadingProgress.setVisibility(View.VISIBLE);
        else
            loadingProgress.setVisibility(View.GONE);
    }
}