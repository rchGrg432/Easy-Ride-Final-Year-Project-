package com.richa.easyride.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SearchView;

import com.richa.easyride.R;
import com.richa.easyride.api.ApiClient;
import com.richa.easyride.api.response.Cycle;
import com.richa.easyride.api.response.CycleResponse;
import com.richa.easyride.home.fragments.home.adapters.SearchCycleAdapter;
import com.richa.easyride.home.fragments.home.adapters.ShopAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {
    android.widget.SearchView searchView;
    RecyclerView food_RV;
    SearchCycleAdapter searchCycleAdapter;
//    ShopAdapter shopAdapter;
    LinearLayout searchLL;
//    ImageView backIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//        getWindow().setStatusBarColor(Color.WHITE);
//        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchView = findViewById(R.id.searchView);
        food_RV = findViewById(R.id.food_RV);
        searchLL = findViewById(R.id.searchLL);
        searchListener();

        Call<CycleResponse> cycleResponseCall = ApiClient.getClient().getAllCycles();
        cycleResponseCall.enqueue(new Callback<CycleResponse>() {
            @Override
            public void onResponse(Call<CycleResponse> call, Response<CycleResponse> response) {
                if (response.isSuccessful()){
                    if (!response.body().getError()){
                        setSearchView(response.body().getCycles());
                    }
                }
            }

            private void setSearchView(List<Cycle> cycles) {
                food_RV.setHasFixedSize(true);
                food_RV.setLayoutManager(new GridLayoutManager(SearchActivity.this,1));
//                shopAdapter = new ShopAdapter(cycles, SearchActivity.this, false);
//                food_RV.setAdapter(shopAdapter);
                searchCycleAdapter = new SearchCycleAdapter(cycles, SearchActivity.this, false);
                food_RV.setAdapter(searchCycleAdapter);
            }

            @Override
            public void onFailure(Call<CycleResponse> call, Throwable t) {

            }
        });
    }

    private void searchListener() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
//                if(shopAdapter != null){
//                    shopAdapter.getFilter().filter(newText);
//                }
                if(searchCycleAdapter != null){
                    searchCycleAdapter.getFilter().filter(newText);
                }
//                if(newText.length()>0){
//                    food_RV.setVisibility(View.VISIBLE);
//                    searchLL.setVisibility(View.GONE);
//                }else{
//                    food_RV.setVisibility(View.GONE);
//                    searchLL.setVisibility(View.VISIBLE);
//                }
                return false;

            }
        });
    }
}