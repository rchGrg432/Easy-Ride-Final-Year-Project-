package com.richa.easyride.categoryActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.richa.easyride.R;
import com.richa.easyride.api.ApiClient;
import com.richa.easyride.api.response.Category;
import com.richa.easyride.api.response.Cycle;
import com.richa.easyride.api.response.CycleResponse;
import com.richa.easyride.home.fragments.home.adapters.ShopAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryActivity extends AppCompatActivity {

    public static final String CATEGORY_DATA_KEY ="cdk";
    Category category;
    RecyclerView allProductsRV;
    ImageView emptyIV;
    ProgressBar loadingProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        if (getIntent().getSerializableExtra(CATEGORY_DATA_KEY) == null)
            finish();
        allProductsRV = findViewById(R.id.allProductRV);
        loadingProgress = findViewById(R.id.loadingProgress);
        emptyIV = findViewById(R.id.emptyIV);

        category = (Category) getIntent().getSerializableExtra(CATEGORY_DATA_KEY);
        getSupportActionBar().setTitle(category.getCategoryName());
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getCategoryOnline();
    }

    private void getCategoryOnline() {
        toggleLoading(true);
        Call<CycleResponse> getProductsByCategory = ApiClient.getClient().getCyclesByCategory(category.getCategoryId());
        getProductsByCategory.enqueue(new Callback<CycleResponse>() {
            @Override
            public void onResponse(Call<CycleResponse> call, Response<CycleResponse> response) {
                if (response.isSuccessful()) {
                    toggleLoading(false);
                    if (!response.body().getError()) {
                        if (response.body().getCycles().size() == 0)
                            showEmptyLayout();
                        else
                            showCategoriesProducts(response.body().getCycles());
                    }
                }
            }

            @Override
            public void onFailure(Call<CycleResponse> call, Throwable t) {
                toggleLoading(false);
                Toast.makeText(CategoryActivity.this, "An Unknown Error Occoured", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showEmptyLayout() {
        emptyIV.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void showCategoriesProducts(List<Cycle> products) {
        allProductsRV.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        allProductsRV.setLayoutManager(layoutManager);
        ShopAdapter shopAdapter = new ShopAdapter(products, this, false);
        allProductsRV.setAdapter(shopAdapter);
    }

    void toggleLoading(boolean toggle) {
        if (toggle)
            loadingProgress.setVisibility(View.VISIBLE);
        else
            loadingProgress.setVisibility(View.GONE);
    }

}
