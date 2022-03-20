package com.richa.easyride.home.fragments.home.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.richa.easyride.R;
import com.richa.easyride.api.response.Cycle;
import com.richa.easyride.singleProductPage.SingleProductActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ShopViewHolder> {
    List<Cycle> cycleDataList;
    LayoutInflater layoutInflater;
    Context context;
    Boolean isCart = false;

    public  ShopAdapter(List<Cycle> cycleDataList, Context context, boolean b){
        this.cycleDataList = cycleDataList;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.isCart = isCart;
    }

    @NonNull
    @Override
    public ShopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        if(isCart)
            return new ShopViewHolder(layoutInflater.inflate(R.layout.item_cart, parent, false));
        else
            return new ShopViewHolder(layoutInflater.inflate(R.layout.item_product, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ShopViewHolder holder, int position) {
        holder.cycleNameTV.setText(cycleDataList.get(position).getCycleName());
//        if (cycleDataList.get(position).getDiscountPrice() == null || cycleDataList.get(position).getDiscountPrice() == 0) {
//            holder.priceTv.setVisibility(View.GONE);
//            holder.discountPrice.setText("Rs. " + cycleDataList.get(position).getPrice() + "");
//        } else
//            holder.discountPrice.setText("Rs. " + cycleDataList.get(position).getDiscountPrice() + "");
        holder.availabilityTV.setText(cycleDataList.get(position).getAvailability());
        holder.rateTV.setText(cycleDataList.get(position).getRentalRate() + ""); // + "" changes it to string

        Picasso.get().load(cycleDataList.get(position).getImages().get(0)).into(holder.cycleIV);
        holder.mainLL.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v){
            Intent productPage = new Intent(context, SingleProductActivity.class);
            productPage.putExtra(SingleProductActivity.key, cycleDataList.get(holder.getAdapterPosition()));
            context.startActivity(productPage);
        }
    });
}

        @Override
        public int getItemCount(){ return  cycleDataList.size();}

        public class ShopViewHolder extends RecyclerView.ViewHolder{
            ImageView cycleIV;
            LinearLayout mainLL;
            TextView cycleNameTV, rateTV, availabilityTV;

            public ShopViewHolder(@NonNull View  itemView){
                super(itemView);
                cycleIV = itemView.findViewById(R.id.cycleIV);
                cycleNameTV = itemView.findViewById(R.id.cycleNameTV);
                mainLL = itemView.findViewById(R.id.mainLL);
                rateTV = itemView.findViewById(R.id.rateTV);
                availabilityTV = itemView.findViewById(R.id.availabilityTV);

            }
         }
    }

