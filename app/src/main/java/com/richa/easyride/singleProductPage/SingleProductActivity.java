package com.richa.easyride.singleProductPage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.richa.easyride.R;
import com.richa.easyride.api.ApiClient;
import com.richa.easyride.api.response.Cycle;
import com.richa.easyride.api.response.RegisterResponse;
import com.richa.easyride.api.response.Slider;
import com.richa.easyride.categoryActivity.CategoryActivity;
import com.richa.easyride.checkout.BottomDialogActivity;
import com.richa.easyride.cycle.SliderAdapter;
import com.richa.easyride.utils.SharedPrefUtils;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SingleProductActivity extends AppCompatActivity {
    public static String key = "pKey";
    Cycle cycle;
    SliderView imageSlider;
    ProgressBar addingCartPR;
    ImageView backIV;
    TextView name, rateeTV, desc, oldPrice, quantityTV, addToCartLL;
//    LinearLayout addToCartLL;
    int quantity = 1;
    boolean isAdding = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.WHITE);

        setContentView(R.layout.activity_single_product);
        backIV = findViewById(R.id.backIV);
        imageSlider = findViewById(R.id.imageSlider);
        name = findViewById(R.id.productNameTV);
        rateeTV = findViewById(R.id.rateeTV);
//        price = findViewById(R.id.productPriceTV);
        quantityTV = findViewById(R.id.quantityTV);
//        oldPrice = findViewById(R.id.productOldPriceTV);
        addToCartLL = findViewById(R.id.addToCartLL);
        addingCartPR = findViewById(R.id.addingCartPR);
        desc = findViewById(R.id.decTV);
//        plusIV = findViewById(R.id.plusIV);
//        minusIV = findViewById(R.id.minusIV);
        setOnclickListeners();
        if (getIntent().getSerializableExtra(key) != null) {
            cycle = (Cycle) getIntent().getSerializableExtra(key);
            setCycle(cycle);
        }
    }

    private void setOnclickListeners() {
        backIV.setOnClickListener(v -> finish());
        addToCartLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BottomDialogActivity.class);
                intent.putExtra(BottomDialogActivity.key, cycle);
                startActivity(intent);
            }
        });
    }


    private void setCycle(Cycle cycle) {
        setSliders(cycle.getImages());
        name.setText(cycle.getCycleName());
        rateeTV.setText("Rs. " + cycle.getRentalRate());
        desc.setText(cycle.getDescription());
    }

    private void setSliders(List<String> images) {
        List<Slider> sliders = new ArrayList<>();
        for (int i = 0; i < images.size(); i++) {
            Slider slider = new Slider();
            slider.setImage(images.get(i));
            sliders.add(slider);
        }
        SliderAdapter sliderAdapter = new SliderAdapter(sliders, this, false);
        sliderAdapter.setClickLister(new SliderAdapter.OnSliderClickLister() {
            @Override
            public void onSliderClick(int position, Slider slider) {

            }
        });
        imageSlider.setSliderAdapter(sliderAdapter);
        imageSlider.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        imageSlider.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        imageSlider.setIndicatorUnselectedColor(Color.GRAY);

    }


    private void setQuantity() {
        quantityTV.setText(quantity + "");
    }

    private void addingToggle(Boolean b) {
        if (b)
            addingCartPR.setVisibility(View.VISIBLE);
        else {
            addingCartPR.setVisibility(View.GONE);
        }
    }
}