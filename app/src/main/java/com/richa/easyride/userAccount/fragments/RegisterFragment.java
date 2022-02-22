package com.richa.easyride.userAccount.fragments;

import static com.richa.easyride.R.id.contactET;
import static com.richa.easyride.R.id.registerBtn;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.richa.easyride.R;
import com.richa.easyride.api.ApiClient;
import com.richa.easyride.api.response.RegisterResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterFragment extends Fragment {
    EditText emailET, passwordET, birthdateET, nameET, contactET;
    LinearLayout registerBtn;
    ProgressBar circularProgress;


    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        emailET = view.findViewById(R.id.emailET);
        nameET = view.findViewById(R.id.nameET);
        passwordET = view.findViewById(R.id.passwordET);
        birthdateET = view.findViewById(R.id.birthdateET);
        contactET = view.findViewById(R.id.contactET);
        circularProgress = view.findViewById(R.id.circularProgress);
        registerBtn = view.findViewById(R.id.registerBtn);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                   toggleLoading(true);
                    Call<RegisterResponse> registerCall = ApiClient.getClient().register(nameET.getText().toString(), emailET.getText().toString(), passwordET.getText().toString(), birthdateET.getText().toString(), contactET.getText().toString());
                    registerCall.enqueue(new Callback<RegisterResponse>() {
                        @Override
                        public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                            RegisterResponse registerResponse = response.body();
                            toggleLoading(false);
                            if (response.isSuccessful()) {
                                if (!registerResponse.getError()) {
                                    Toast.makeText(getActivity(), registerResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.formContainer, new LoginFragment()).commit();

                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<RegisterResponse> call, Throwable t) {
                           toggleLoading(false);
                            Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });

                }
            }
        });
    }

    void toggleLoading(Boolean toogle) {
        if (toogle)
            circularProgress.setVisibility(View.VISIBLE);
        else
            circularProgress.setVisibility(View.GONE);
    }

    public boolean validate() {
        boolean validate = true;
        if (emailET.getText().toString().isEmpty()
                || passwordET.getText().toString().isEmpty()
                || nameET.getText().toString().isEmpty()) {
            Toast.makeText(getActivity(), "None of the above fields can be empty", Toast.LENGTH_SHORT).show();
            validate = false;
        } else {

            Toast.makeText(getActivity(), "Successful", Toast.LENGTH_SHORT).show();

        }

        return validate;
    }
}