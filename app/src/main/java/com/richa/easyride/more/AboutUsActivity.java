package com.richa.easyride.more;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.richa.easyride.R;

public class AboutUsActivity extends AppCompatActivity {
    ImageView backIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        backIV = findViewById(R.id.backIV);

        setOnclickListeners();
    }

    private void setOnclickListeners() {
        backIV.setOnClickListener(v -> finish());
    }
}