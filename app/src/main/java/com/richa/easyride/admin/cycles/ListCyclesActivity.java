package com.richa.easyride.admin.cycles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.richa.easyride.R;
import com.richa.easyride.api.ApiClient;
import com.richa.easyride.api.response.Cycle;
import com.richa.easyride.api.response.CycleResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListCyclesActivity extends AppCompatActivity {
    RecyclerView allProductRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_cycles);
        allProductRV = findViewById(R.id.allProductRV);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Cycles");
        serverCall();
    }

    private void serverCall() {
        Call<CycleResponse> cycleResponseCall = ApiClient.getClient().getAllCycles();
        cycleResponseCall.enqueue(new Callback<CycleResponse>() {
            @Override
            public void onResponse(Call<CycleResponse> call, Response<CycleResponse> response) {
                setCycleRecyclerView(response.body().getCycles());
            }

            @Override
            public void onFailure(Call<CycleResponse> call, Throwable t) {
                Toast.makeText(ListCyclesActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setCycleRecyclerView(List<Cycle> products) {
//        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
//        allProductRV.setLayoutManager(layoutManager);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(gtActivity(), LinearLayoutManager.VERTICAL, false);
//        allProductRV.setLayoutManager(layoutManager);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        allProductRV.setLayoutManager(layoutManager);
        ShopAdapterAdmin shopAdapter = new ShopAdapterAdmin(products, this);
        allProductRV.setAdapter(shopAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}