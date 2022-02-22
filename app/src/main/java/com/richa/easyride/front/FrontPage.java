package com.richa.easyride.front;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.richa.easyride.R;
import com.richa.easyride.userAccount.UserAccountActivity;

public class FrontPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_page);
    }

    public void getStarted(View view) {
        Intent intent = new Intent(FrontPage.this, UserAccountActivity.class);
        startActivity(intent);
        finish();
    }
}