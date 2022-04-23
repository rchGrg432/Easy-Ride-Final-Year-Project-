package com.richa.easyride.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.richa.easyride.home.MainActivity;
import com.richa.easyride.R;
import com.richa.easyride.front.FrontPage;
import com.richa.easyride.utils.SharedPrefUtils;

public class SplashActivity extends AppCompatActivity {
    boolean isLoggedIn = false;
    ImageView background_image;
    TextView powered_by_line;
    Animation sideAnim, bottomAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getIsLoggedInOrNot();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        powered_by_line = findViewById(R.id.powered_by_line);
        background_image = findViewById(R.id.background_image);


        //animations
        sideAnim = AnimationUtils.loadAnimation(this, R.anim.side_anim);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_anim);

        //set animations on elements
        background_image.setAnimation(sideAnim);
        powered_by_line.setAnimation(bottomAnim);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isLoggedIn)
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                else
                    startActivity(new Intent(SplashActivity.this, FrontPage.class));

                finish();
            }
        }, 5000);


    }

    public void getIsLoggedInOrNot() {
        isLoggedIn = SharedPrefUtils.getBool(this, getString(R.string.isLogged), false);
    }
}