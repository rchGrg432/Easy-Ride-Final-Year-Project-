package com.richa.easyride.admin.cycles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.richa.easyride.R;
import com.richa.easyride.api.ApiClient;
import com.richa.easyride.api.response.CycleResponse;
import com.richa.easyride.api.response.RentalHistory;
import com.richa.easyride.api.response.RentalHistoryResponse;
import com.richa.easyride.utils.SharedPrefUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListRentalsActivity extends AppCompatActivity {
    RecyclerView allRentalsRV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_rentals);

        allRentalsRV = findViewById(R.id.allRentalsRV);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Cycles");
        serverCall();
    }

    private void serverCall() {
        String key = SharedPrefUtils.getString(ListRentalsActivity.this, getString(R.string.api_key));
        Call<RentalHistoryResponse> rentalHistoryResponseCall = ApiClient.getClient().getRental(key);
        rentalHistoryResponseCall.enqueue(new Callback<RentalHistoryResponse>() {
            @Override
            public void onResponse(Call<RentalHistoryResponse> call, Response<RentalHistoryResponse> response) {
                setRentalsRecyclerView(response.body().getRentalHistory());
            }

            @Override
            public void onFailure(Call<RentalHistoryResponse> call, Throwable t) {
                Toast.makeText(ListRentalsActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setRentalsRecyclerView(List<RentalHistory> rentalHistory) {
        allRentalsRV.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        allRentalsRV.setLayoutManager(layoutManager);

    }
}