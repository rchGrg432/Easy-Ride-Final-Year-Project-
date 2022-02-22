package com.richa.easyride.cycle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.richa.easyride.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.Holder> {
    int[] images;

    public SliderAdapter(int[] images){
        this.images = images;
    }

    public Holder onCreateViewHolder(ViewGroup parent){

        boolean attachToRoot;
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.slider_item,parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(SliderAdapter.Holder viewHolder, int position) {
           viewHolder.imageView.setImageResource(images[position]);
    }

    @Override
    public int getCount() {
        return images.length;
    }

    public class Holder extends SliderViewAdapter.ViewHolder{
        ImageView imageView;

        public Holder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
        }
    }
}
