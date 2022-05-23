package com.richa.easyride.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.richa.easyride.R;
import com.richa.easyride.api.ApiClient;
import com.richa.easyride.api.response.RegisterResponse;
import com.richa.easyride.utils.SharedPrefUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        getNameTV.setText(SharedPrefUtils.getString(this, getString(R.string.name_key)));
        getEmailTV.setText(SharedPrefUtils.getString(this, getString(R.string.email_id)));
        getBirthDateTV.setText(SharedPrefUtils.getString(this, getString(R.string.dateofbirth)));
        getContactTV.setText(SharedPrefUtils.getString(this, getString(R.string.contact)));

        gobackIV.setOnClickListener(v -> finish());

        updateTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    callResponse(getNameTV.getText().toString(), getEmailTV.getText().toString(), getBirthDateTV.getText().toString(), getContactTV.getText().toString());
                    getNameTV.setText("");
                    getEmailTV.setText("");
                    getBirthDateTV.setText("");
                    getContactTV.setText("");
                }
            }
            private void callResponse(String names, String email, String dateofbirth, String contact) {
                String key = SharedPrefUtils.getString(ProfileActivity.this, getString(R.string.api_key));
                Call<RegisterResponse> registerResponseCall = ApiClient.getClient().updateProfile(key, names, email, dateofbirth, contact);

                registerResponseCall.enqueue(new Callback<RegisterResponse>() {
                    @Override
                    public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                        if (response.isSuccessful()) {
                            if (!response.body().getError()) {
                                Toast.makeText(ProfileActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                                SharedPrefUtils.setString(ProfileActivity.this, getString(R.string.name_key), names);
                                SharedPrefUtils.setString(ProfileActivity.this, getString(R.string.email_id), email);
                                SharedPrefUtils.setString(ProfileActivity.this, getString(R.string.dateofbirth), dateofbirth);
                                SharedPrefUtils.setString(ProfileActivity.this, getString(R.string.contact), contact);

                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<RegisterResponse> call, Throwable t) {

                    }
                });
            }


            private boolean validate() {
                if (getContactTV.getText().toString().length() < 10) {
                    Toast.makeText(getApplicationContext(), "Contact number cannot be less than 10 letters", Toast.LENGTH_SHORT).show();
                    return false;
                } else {
                    Toast.makeText(getApplicationContext(), "You have successfully updated your profile", Toast.LENGTH_SHORT).show();
                    return true;
                }
            }
        });

        setOnclickListeners();

    }
//        setGetNameTV();

//        ProgressDialog progressDialog = new ProgressDialog(this);
//        progressDialog.setMessage("loading....");
//        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//
//        Intent i = getIntent();
//        String mName = i.getStringExtra("name");
//        String mEmail = i.getStringExtra("email");
//        String mBirthDate = i.getStringExtra("birthdate");
//        String mContact = i.getStringExtra("contact");
//
//        getNameTV.setText(mName);
//        getEmailTV.setText(mEmail);
//        getBirthDateTV.setText(mBirthDate);
//        getContactTV.setText(mContact);

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

//    public void setGetNameTV(){
//        getNameTV.setText(SharedPrefUtils.getString(this, getString(R.string.name_key)));
//        getEmailTV.setText(SharedPrefUtils.getString(this,getString(R.string.email_id)));
//        getBirthDateTV.setText(SharedPrefUtils.getString(this,getString(R.string.dateofbirth)));
//        getContactTV.setText(SharedPrefUtils.getString(this,getString(R.string.contact)));
//
//    }
}