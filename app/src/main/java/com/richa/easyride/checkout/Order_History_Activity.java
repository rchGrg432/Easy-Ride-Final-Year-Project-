package com.richa.easyride.checkout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.richa.easyride.R;
import com.richa.easyride.api.response.RentalHistory;
import com.richa.easyride.api.response.RentalHistoryResponse;

import java.util.List;

public class Order_History_Activity extends AppCompatActivity {
    RecyclerView rentalHistoryRV;
    List<RentalHistory> rentals;
    TextView rentalBackIV;
    RentalHistoryResponse rentalHistoryResponse;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        rentalHistoryRV = findViewById(R.id.rentalHistoryRV);
        rentalBackIV = findViewById(R.id.rentalBackIV);


    }
}