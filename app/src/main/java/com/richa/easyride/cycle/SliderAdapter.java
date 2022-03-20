package com.richa.easyride.cycle;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.richa.easyride.R;
import com.richa.easyride.api.response.Slider;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderViewHolder> {
    List<Slider> sliders;
    LayoutInflater inflater;
    Context context;
    OnSliderClickLister onSliderClickLister;
    Boolean isFitted;

    public SliderAdapter(List<Slider> sliders, Context context, Boolean isFitted) {
        this.sliders = sliders;
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.isFitted = isFitted;
    }

    public SliderViewHolder onCreateViewHolder(ViewGroup parent){
        return new SliderViewHolder(inflater.inflate(R.layout.slider_item, parent, false));
    }

    public void setClickLister(OnSliderClickLister onSliderClickLister) {
        this.onSliderClickLister = onSliderClickLister;
    }

    @Override
    public void onBindViewHolder(SliderViewHolder viewHolder, int position) {
        Picasso.get().load(sliders.get(position).getImage()).into(viewHolder.imageViewBackground);
        if (isFitted)
            viewHolder.imageViewBackground.setScaleType(ImageView.ScaleType.FIT_XY);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSliderClickLister.onSliderClick(position, sliders.get(position));
            }
        });
    }

    @Override
    public int getCount() {
        return sliders.size();
    }

    public class SliderViewHolder extends SliderViewAdapter.ViewHolder {
        View itemView;
        ImageView imageViewBackground;
        ImageView imageGifContainer;

        public SliderViewHolder(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
            imageGifContainer = itemView.findViewById(R.id.iv_gif_container);
            this.itemView = itemView;
        }
    }

    public interface OnSliderClickLister {
        public void onSliderClick(int position, Slider slider);
    }

//    public class Holder extends SliderViewAdapter.ViewHolder{
//        ImageView imageView;
//
//        public Holder(View itemView) {
//            super(itemView);
//            imageView = itemView.findViewById(R.id.image_view);
//        }
//    }
}
