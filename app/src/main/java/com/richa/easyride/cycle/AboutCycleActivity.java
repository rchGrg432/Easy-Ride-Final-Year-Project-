package com.richa.easyride.cycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.richa.easyride.R;
import com.richa.easyride.cycle.fragment.BookingFragment;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

public class AboutCycleActivity extends AppCompatActivity {

    SliderView sliderView;
    int[] images = {R.drawable.mtb1,
            R.drawable.mtb2,
            R.drawable.mtb3,
            R.drawable.b1,
            R.drawable.b2};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_cycle);

        sliderView = findViewById(R.id.image_slider);

        getSupportFragmentManager().beginTransaction().add(R.id.frameContainer, new BookingFragment()).commit();

        SliderAdapter sliderAdapter = new SliderAdapter(images);

        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();




    }


}