package com.richa.easyride.home.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.richa.easyride.R;
import com.richa.easyride.api.ApiClient;
import com.richa.easyride.api.response.RentalHistory;
import com.richa.easyride.api.response.RentalHistoryResponse;
import com.richa.easyride.home.fragments.home.adapters.RentalAdapter;
import com.richa.easyride.utils.SharedPrefUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RentalFragment extends Fragment {
    RecyclerView rentalHistoryRV;
    List<RentalHistory> rentals;
    ImageView cycleIV;
    RentalAdapter rentalAdapter;
    RentalHistoryResponse rentalHistoryResponse;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rentalHistoryRV = view.findViewById(R.id.rentalHistoryRV);
//        rentalBackIV = view.findViewById(R.id.rentalBackIV);
        cycleIV = view.findViewById(R.id.cycleIV);
//        rentalBackIV.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//
//            private void finish() {
//            }
//
        rentalDataCall();
    }

    private void rentalDataCall() {
       String key = SharedPrefUtils.getString(getActivity(), getString(R.string.api_key));
        Call<RentalHistoryResponse> rentalHistoryResponseCall= ApiClient.getClient().getRental(key);
        rentalHistoryResponseCall.enqueue(new Callback<RentalHistoryResponse>() {
            @Override
            public void onResponse(Call<RentalHistoryResponse> call, Response<RentalHistoryResponse> response) {
                if (response.isSuccessful()){
                    if (!response.body().getError()){
                        if (response.body().getRentalHistory() != null){
                            setRentalHistoryRV(response.body().getRentalHistory());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<RentalHistoryResponse> call, Throwable t) {

            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rental, container, false);


    }
    private void setRentalHistoryRV(List<RentalHistory> data){
        rentals=data;
        rentalHistoryRV.setHasFixedSize(true);
        rentalHistoryRV.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        rentalAdapter=new RentalAdapter(rentals,getContext());
        rentalHistoryRV.setAdapter(rentalAdapter);
    }
}