package com.richa.easyride.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.richa.easyride.R;
import com.richa.easyride.api.ApiClient;
import com.richa.easyride.api.response.RegisterResponse;
import com.richa.easyride.utils.DataHolder;
import com.richa.easyride.utils.SharedPrefUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePWActivity extends AppCompatActivity {

    EditText newPasswordET,oldPasswordET, confirmPasswordET;
    TextView changeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pwactivity);
        newPasswordET=findViewById(R.id.newPasswordET);
        oldPasswordET = findViewById(R.id.oldPasswordET);
        confirmPasswordET = findViewById(R.id.confirmPasswordET);
        changeBtn = findViewById(R.id.changeBtn);

        changeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePassword();
            }

            private void updatePassword() {
//                if (validateAll()) {
                    String key = SharedPrefUtils.getString(ChangePWActivity.this, getString(R.string.api_key));
                    Call<RegisterResponse> changePassword = ApiClient.getClient().forgotpassword(key, newPasswordET.getText().toString());
                    changePassword.enqueue(new Callback<RegisterResponse>() {
                        @Override
                        public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                            if (response.isSuccessful()){
                                if (!response.body().getError()){
                                    Toast.makeText(ChangePWActivity.this, "Password Successfully changed", Toast.LENGTH_SHORT).show();
//                                    SharedPrefUtils.setString(ChangePWActivity.this, DataHolder.PASSWORD_KEY, newPasswordET.getText().toString());
                                    finish();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<RegisterResponse> call, Throwable t) {

                        }
                    });
                }
//            }

//            private boolean validateAll() {
//                if (newPassword()){
//                    return true;
//                }
//                return false;
//            }

//            private boolean validatePassword(){
//                String dbPassword = SharedPrefUtils.getString(ChangePWActivity.this, DataHolder.PASSWORD_KEY);
//
//                String passwordText = oldPasswordET.getText().toString().trim();
//                if (passwordText.isEmpty()) {
//                    Toast.makeText(ChangePWActivity.this, "Field cannot be left empty", Toast.LENGTH_SHORT).show();
//                    return false;
//                } else if (!passwordText.equals(dbPassword)){
//                    Toast.makeText(ChangePWActivity.this, "Old password doesn't match", Toast.LENGTH_SHORT).show();
//                    return false;
//                }
//                return true;
//            }

//            private boolean newPassword() {
//                String passwordText = oldPasswordET.getText().toString().trim();
//                String confirmPasswordText = newPasswordET.getText().toString().trim();
//
//                if (confirmPasswordText.isEmpty()){
//                    Toast.makeText(ChangePWActivity.this, "Field cannot be left empty", Toast.LENGTH_SHORT).show();
//                    return false;
//                } else if (confirmPasswordText.equals(passwordText)){
//                    Toast.makeText(ChangePWActivity.this, "New password must differ from old password!", Toast.LENGTH_SHORT).show();
//                    return false;
//                } else if (!newPasswordET.getText().toString().equals(confirmPasswordET.getText().toString())) {
//                    confirmPasswordET.setError("Password does not match please check !!");
//                    return false;
//                }
//                return true;
//            }
        });

    }
}