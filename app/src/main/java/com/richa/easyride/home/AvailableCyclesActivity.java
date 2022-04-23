package com.richa.easyride.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.richa.easyride.R;
import com.richa.easyride.api.ApiClient;
import com.richa.easyride.api.response.Cycle;
import com.richa.easyride.api.response.CycleResponse;
import com.richa.easyride.home.fragments.home.adapters.ShopAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AvailableCyclesActivity extends AppCompatActivity {
    RecyclerView availableCyclesRV;
    ProgressBar loadingProgress;
    
    AvailableCyclesAdapter availableCyclesAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_cycles);
        
        availableCyclesRV = findViewById(R.id.availableCyclesRV);
        loadingProgress = findViewById(R.id.loadingProgress);
        
        serverCall();
    }

    private void serverCall() {
//        toggleLoading(true);
//        Call<CycleResponse> cycleResponseCall = ApiClient.getClient().getAllCycles();
//        cycleResponseCall.enqueue(new Callback<CycleResponse>() {
        
        toggleLoading(true);
        Call<CycleResponse> cycleResponseCall = ApiClient.getClient().getAvailableCycles();
        cycleResponseCall.enqueue(new Callback<CycleResponse>() {
            @Override
            public void onResponse(Call<CycleResponse> call, Response<CycleResponse> response) {
                if (response.isSuccessful()) {
                    toggleLoading(false);
                    setCycleRecyclerView(response.body().getCycles());
                }
            }

            @Override
            public void onFailure(Call<CycleResponse> call, Throwable t) {
                toggleLoading(false);
//                Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setCycleRecyclerView(List<Cycle> cycles) {
        availableCyclesRV.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        availableCyclesRV.setLayoutManager(layoutManager);
        if (this != null) {
            AvailableCyclesAdapter availableCyclesAdapter = new AvailableCyclesAdapter(cycles, this, false);
            availableCyclesRV.setAdapter(availableCyclesAdapter);
        }
    }

    private void toggleLoading(boolean toggle) {
        if (toggle)
            loadingProgress.setVisibility(View.VISIBLE);
        else
            loadingProgress.setVisibility(View.GONE);
    }
}