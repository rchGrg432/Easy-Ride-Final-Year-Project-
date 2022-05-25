package com.richa.easyride.home.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.richa.easyride.R;
import com.richa.easyride.admin.AdminActivity;
import com.richa.easyride.checkout.BottomDialogActivity;
import com.richa.easyride.more.AboutUsActivity;
import com.richa.easyride.more.ContactUsActivity;
import com.richa.easyride.profile.ProfileActivity;
import com.richa.easyride.userAccount.UserAccountActivity;
import com.richa.easyride.utils.SharedPrefUtils;

public class SettingFragment extends Fragment {
    RelativeLayout profileRL, logOutRL, contactUsRL, AboutUS;
    TextView adminAreaTV;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        profileRL = view.findViewById(R.id.profileRL);
        logOutRL = view.findViewById(R.id.logOutRL);
        adminAreaTV = view.findViewById(R.id.adminAreaTV);
        contactUsRL = view.findViewById(R.id.contactUsRL);
        AboutUS = view.findViewById(R.id.AboutUS);

        checkAdmin();
        profileOnClick();
        setClickListeners();
        loadAdmin();
        loadContactUs();
        loadAboutUs();
    }

    private void loadAboutUs() {
        AboutUS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AboutUsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loadContactUs() {
        contactUsRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ContactUsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loadAdmin() {
        adminAreaTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AdminActivity.class);
                startActivity(intent);
            }
        });
    }

    private void checkAdmin() {
        boolean is_staff = SharedPrefUtils.getBool(getActivity(), "sfk", false);
        if (is_staff)
            adminAreaTV.setVisibility(View.VISIBLE);
    }

    private void setClickListeners() {
        logOutRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPrefUtils.clear(getContext());
                Intent userAccount = new Intent(getContext(), UserAccountActivity.class);
                startActivity(userAccount);
                getActivity().finish();
            }
        });
    }

    private void profileOnClick() {
        profileRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ProfileActivity.class);
                startActivity(intent);
            }
        });
    }



}