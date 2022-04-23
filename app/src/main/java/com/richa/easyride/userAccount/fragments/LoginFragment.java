package com.richa.easyride.userAccount.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import  androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.richa.easyride.R;
import com.richa.easyride.api.ApiClient;
import com.richa.easyride.api.response.LoginResponse;
import com.richa.easyride.home.MainActivity;
import com.richa.easyride.utils.SharedPrefUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment implements View.OnClickListener {

    EditText emailEt, passwordET;
    Button loginBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_log_in, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        emailEt = view.findViewById(R.id.emailET);
        passwordET = view.findViewById(R.id.passwordET);
        loginBtn = view.findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == loginBtn) {
            String email, password_hash;
            email = emailEt.getText().toString();
            password_hash = passwordET.getText().toString();
            if (email.isEmpty() || password_hash.isEmpty())
                Toast.makeText(getContext(), "Email or Password is Empty!", Toast.LENGTH_LONG).show();
            else {
                Call<LoginResponse> loginResponseCall = ApiClient.getClient().login(email, password_hash);
                loginResponseCall.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        LoginResponse loginResponse = response.body();
                        if (response.isSuccessful()) {
                            if (loginResponse.getError()) {
                                System.out.println("222222221222222222222 my error  is: " + loginResponse.getError());
                            } else {
//                                SharedPrefUtils.setBoolean(getActivity(), getString(R.string.isLoggedKey), true);
                                System.out.println("222222221222222222222 my api key is: " + loginResponse.getApiKey());
                                SharedPrefUtils.setBoolean(getActivity(), getString(R.string.isLogged), true);
                                SharedPrefUtils.setString(getActivity(), getString(R.string.name_key), loginResponse.getName());
                                SharedPrefUtils.setString(getActivity(), getString(R.string.email_id), loginResponse.getEmail());
                                SharedPrefUtils.setString(getActivity(), getString(R.string.created_key), loginResponse.getCreatedAt());
                                SharedPrefUtils.setString(getActivity(), getString(R.string.api_key), loginResponse.getApiKey());
                                SharedPrefUtils.setString(getActivity(), getString(R.string.contact), loginResponse.getContact());
                                SharedPrefUtils.setString(getActivity(), getString(R.string.dateofbirth), loginResponse.getDateofbirth());
                                SharedPrefUtils.setBoolean(getActivity(),  getString(R.string.staff_key), loginResponse.getIsStaff());


                                getActivity().startActivity(new Intent(getActivity(), MainActivity.class));
                                getActivity().finish();
                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {

                    }
                });
            }
//            else if (email.equals("thesaugatt@gmail.com") && password.equals("Pass123")) {
//                SharedPrefUtils.setBoolean(getActivity(), getString(R.string.isLoggedKey), true);
//                getActivity().startActivity(new Intent(getActivity(), MainActivity.class));
//                getActivity().finish();
//            } else
//                Toast.makeText(getContext(), "Wrong email or password", Toast.LENGTH_LONG).show();
        }

        /*if (validate()) {
            //  toggleLoading(true);
            Call<LoginResponse> loginCall = ApiClient.getClient().login( emailEt.getText().toString(), passwordET.getText().toString());
            loginCall.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> login) {
                    LoginResponse loginResponse = login.body();
                    // toggleLoading(false);
                    if (login.isSuccessful()) {
                        Toast.makeText(getActivity(), loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        if (!loginResponse.getError()) {
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.formContainer, new LoginFragment()).commit();

                        }
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    //  toggleLoading(false);
                    Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                }
            });

        } */
    }

   /* public boolean validate() {
        boolean validate = true;
        if (emailEt.getText().toString().isEmpty()
                || passwordET.getText().toString().isEmpty()
                ) {
            Toast.makeText(getActivity(), "None of the above fields can be empty", Toast.LENGTH_SHORT).show();
            validate = false;
        } else {

            Toast.makeText(getActivity(), "Successful", Toast.LENGTH_SHORT).show();

        }

        return validate;
    } */
}

