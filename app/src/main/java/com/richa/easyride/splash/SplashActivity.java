package com.richa.easyride.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.richa.easyride.home.MainActivity;
import com.richa.easyride.R;
import com.richa.easyride.front.FrontPage;
import com.richa.easyride.utils.SharedPrefUtils;

public class SplashActivity extends AppCompatActivity {
    boolean isLoggedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getIsLoggedInOrNot();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isLoggedIn)
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                else
                    startActivity(new Intent(SplashActivity.this, FrontPage.class));

                finish();
            }
        }, 1000);


    }

    public void getIsLoggedInOrNot() {
        isLoggedIn = SharedPrefUtils.getBool(this, getString(R.string.isLogged), false);
    }
}