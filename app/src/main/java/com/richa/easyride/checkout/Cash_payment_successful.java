package com.richa.easyride.checkout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.richa.easyride.R;

public class Cash_payment_successful extends AppCompatActivity {
    TextView backTV,rentMoreTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_payment_successful);

        backTV = findViewById(R.id.backTV);
        rentMoreTV = findViewById(R.id.rentMoreTv);


        backTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        rentMoreTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}