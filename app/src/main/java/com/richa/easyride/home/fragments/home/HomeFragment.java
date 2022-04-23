package com.richa.easyride.home.fragments.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.richa.easyride.R;
import com.richa.easyride.api.ApiClient;
//import com.richa.easyride.home.fragments.home.adapters.CategoryAdapter;
//import com.richa.easyride.home.fragments.home.adapters.ShopAdapter;
import com.richa.easyride.api.response.Category;
import com.richa.easyride.api.response.CategoryResponse;
import com.richa.easyride.api.response.Cycle;
import com.richa.easyride.api.response.CycleResponse;
import com.richa.easyride.home.AvailableCyclesActivity;
import com.richa.easyride.home.SearchActivity;
import com.richa.easyride.home.fragments.home.adapters.CategoryAdapter;
import com.richa.easyride.home.fragments.home.adapters.ShopAdapter;
import com.richa.easyride.profile.ProfileActivity;
import com.richa.easyride.utils.DataHolder;
import com.richa.easyride.utils.SharedPrefUtils;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {
    RecyclerView allProductRV, categoryRV;
    ProgressBar loadingProgress;
    TextView viewAllTV, userNameTV, goSearch, availableCyclesTV;
//    ImageView profileIV;
    MeowBottomNavigation bottomNavigation;
    CircleImageView profileImage;
    LinearLayout searchLL;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
//    public void setBottomNav(MeowBottomNavigation bottomNavigation) {
//        this.bottomNavigation = bottomNavigation;
//    }

    @Override
    public void onViewCreated(@NonNull View view, @NonNull Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        allProductRV = view.findViewById(R.id.allProductRV);
        categoryRV = view.findViewById(R.id.categoryRV);
        loadingProgress = view.findViewById(R.id.loadingProgress);
        viewAllTV = view.findViewById(R.id.viewAllTV);
        userNameTV = view.findViewById(R.id.userNameTV);
        profileImage = view.findViewById(R.id.profileImage);
        goSearch = view.findViewById(R.id.goSearch);
        searchLL = view.findViewById(R.id.searchLL);
        availableCyclesTV = view.findViewById(R.id.availableCyclesTV);

        availableCyclesTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AvailableCyclesActivity.class);
                startActivity(intent);
            }
        });
//        viewAllTV.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                bottomNavigation.show(2, true);
//            }
//        });
        serverCall();
        getCategoriesOnline();
        profileOnClick();
        getName();
        getSearch();

    }

    private void getSearch() {
        goSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getName() {
        userNameTV.setText(SharedPrefUtils.getString(getActivity(), getString(R.string.name_key)));
    }

    private void profileOnClick() {
        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProfileActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getCategoriesOnline() {
        Call<CategoryResponse> getCategories = ApiClient.getClient().getCategories();
        getCategories.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if (response.isSuccessful()) {
                    if (!response.body().getError()) {
                        DataHolder.categories = response.body().getCategories();
                        showCategories(response.body().getCategories());
                    }
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {

            }
        });


    }

    private void showCategories(List<Category> categories) {
//        List<Category> temp;
//        if (categories.size() > 8) {
//            temp = new ArrayList<>();
//            for (int i = 0; i < 8; i++) {
//                temp.add(categories.get(categories.size() - i - 1));
//            }
//
//        } else {
//            temp = categories;
//        }
        categoryRV.setHasFixedSize(true);
        categoryRV.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        if (getActivity() != null) {
            CategoryAdapter categoryAdapter = new CategoryAdapter(categories, getContext(), true, false, null);
            categoryRV.setAdapter(categoryAdapter);

        }
    }


//    private void serverCall() {
//        toggleLoading(true);
//        Call<CycleResponse> allProductResponseCall = ApiClient.getClient().getAllProducts();
//        allProductResponseCall.enqueue(new Callback<CycleResponse>() {
//            @Override
//            public void onResponse
//                    (Call<CycleResponse> call, Response<CycleResponse> response) {
//                toggleLoading(false);
//                setCycleRecyclerView(response.body().getCycles());
//            }
//
//            @Override
//            public void onFailure(Call<CycleResponse> call, Throwable t) {
//                toggleLoading(false);
//                Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//
//            }
//        });
//    }

    private void serverCall() {
        toggleLoading(true);
        Call<CycleResponse> cycleResponseCall = ApiClient.getClient().getAllCycles();
        cycleResponseCall.enqueue(new Callback<CycleResponse>() {
            @Override
            public void onResponse(Call<CycleResponse> call, Response<CycleResponse> response) {
                if (response.isSuccessful()) {
                    toggleLoading(false);
                    setCycleRecyclerView(response.body().getCycles());
                }
            }

            @Override
            public void onFailure(Call<CycleResponse> call, Throwable t) {
                toggleLoading(false);
//                Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setCycleRecyclerView(List<Cycle> cycles) {
        allProductRV.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        allProductRV.setLayoutManager(layoutManager);
        if (getActivity() != null) {
            ShopAdapter shopAdapter = new ShopAdapter(cycles, getContext(), false);
            allProductRV.setAdapter(shopAdapter);
        }
    }

    void toggleLoading(boolean toggle) {
        if (toggle)
            loadingProgress.setVisibility(View.VISIBLE);
        else
            loadingProgress.setVisibility(View.GONE);
    }
}





