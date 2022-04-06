package com.richa.easyride.profile;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.richa.easyride.R;
import com.richa.easyride.admin.AdminActivity;
import com.richa.easyride.checkout.BottomDialogActivity;
import com.richa.easyride.utils.SharedPrefUtils;

import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity {
    EditText getNameTV, getEmailTV, getBirthDateTV, getContactTV;
    TextView changePasswordTV, updateTV;
    ProgressDialog progressDialog;
    ImageView gobackIV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getNameTV = findViewById(R.id.getNameTV);
        getEmailTV = findViewById(R.id.getEmailTV);
        getBirthDateTV = findViewById(R.id.getBirthDateTV);
        getContactTV = findViewById(R.id.getContactTV);
        gobackIV = findViewById(R.id.gobackIV);
        changePasswordTV = findViewById(R.id.changePasswordTV);
        updateTV = findViewById(R.id.updateTV);

        setOnclickListeners();
        setGetNameTV();

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("loading....");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        Intent i = getIntent();
        String mName = i.getStringExtra("name");
        String mEmail = i.getStringExtra("email");
        String mBirthDate = i.getStringExtra("birthdate");
        String mContact = i.getStringExtra("contact");

        getNameTV.setText(mName);
        getEmailTV.setText(mEmail);
        getBirthDateTV.setText(mBirthDate);
        getContactTV.setText(mContact);

//        changePasswordTV.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                View resetpasswordlayout = LayoutInflater.from(ProfileActivity.this).inflate(R.layout.change_password, null);
//                final EditText OldPass = resetpasswordlayout.findViewById(R.id.oldPasswordET);
//                final EditText NewPass = resetpasswordlayout.findViewById(R.id.newPasswordET);
//                final EditText ConfirmNewPass = resetpasswordlayout.findViewById(R.id.confirmNewPasswordET);
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
//                builder.setTitle("CHANGE PASSWORD");
//                builder.setView(resetpasswordlayout);
//                builder.setPositiveButton("CHANGE", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        String oldpassword = OldPass.getText().toString().trim();
//                        String newpassword = NewPass.getText().toString().trim();
//                        String confirmpassword = ConfirmNewPass.getText().toString().trim();
//
//                        if (oldpassword.isEmpty() || newpassword.isEmpty() || confirmpassword.isEmpty()){
//                            message("some field are empty");
//                        }else {
//                            progressDialog.show();
//                            StringRequest stringRequest = new StringRequest(Request.Method.POST, Urls.RESET_PASSWORD_URL, new Response.Listener<String>() {
//                                @Override
//                                public void onResponse(String response) {
//                                    progressDialog.dismiss();
//                                    message(response);
//                                }
//                            }, new Response.ErrorListener() {
//                                @Override
//                                public void onErrorResponse(VolleyError error) {
//                                    progressDialog.dismiss();
//                                    message(error.getMessage());
//                                }
//                            }) {
//                                @Nullable
//                                @Override
//                                protected Map<String, String> getParams() throws AuthFailureError {
//                                    Map<String, String> params = new HashMap<>();
//                                    params.put("oldpassword", oldpassword);
//                                    params.put("newpassword", newpassword);
//                                    params.put("confirmpassword", confirmpassword);
//                                    params.put("email", mEmail);
//                                    return super.getParams();
//                                }
//                            };
//                            RequestQueue queue = Volley.newRequestQueue(ProfileActivity.this);
//                            queue.add(stringRequest);
//                        }
//                    }
//                });
//                AlertDialog dialog = builder.create();
//                dialog.show();
//            }
//        });

    }




    public void message(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void setOnclickListeners() {

    gobackIV.setOnClickListener(v -> finish());

    changePasswordTV.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), ChangePWActivity.class);
            startActivity(intent);
        }
    });
    }

    public void setGetNameTV(){
        getNameTV.setText(SharedPrefUtils.getString(this, getString(R.string.name_key)));
        getEmailTV.setText(SharedPrefUtils.getString(this,getString(R.string.email_id)));
        getBirthDateTV.setText(SharedPrefUtils.getString(this,getString(R.string.dateofbirth)));
        getContactTV.setText(SharedPrefUtils.getString(this,getString(R.string.contact)));

    }
}