package com.richa.easyride.more;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.richa.easyride.R;

public class ContactUsActivity extends AppCompatActivity {
    private static final int REQUEST_CALL = 1;
    private TextView number, emailTV;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        number = findViewById(R.id.number);
        back =  findViewById(R.id.back);
        emailTV = findViewById(R.id.emailTV);

        number.setPaintFlags(number.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
        backOnClick();
        setOnclickListeners();
        clickSendEmail();


        }

    private void clickSendEmail() {
        emailTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }

            private void sendEmail() {
                startActivity(new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:rchgurung@gmail.com")));
            }
        });
    }

    private void setOnclickListeners() {
        number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall();
            }
        });
    }


    private void backOnClick() { back.setOnClickListener(v -> finish()); }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            } else{
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void makePhoneCall() {
        String num = number.getText().toString();
        if (ContextCompat.checkSelfPermission(ContactUsActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(ContactUsActivity.this, new String[] {Manifest.permission.CALL_PHONE}, REQUEST_CALL);
        } else {
            String dial = "tel:" + num;
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
        }
    }

}