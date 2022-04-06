package com.richa.easyride.admin.cycles;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.richa.easyride.api.response.Cycle;
import com.squareup.picasso.Picasso;
import com.richa.easyride.R;
import com.richa.easyride.api.response.Cycle;


import java.util.List;

public class ShopAdapterAdmin extends RecyclerView.Adapter<ShopAdapterAdmin.ShopViewHolder> {
    List<Cycle> cycleDataList;
    LayoutInflater layoutInflater;
    Context context;

    public ShopAdapterAdmin(List<Cycle> productDataList, Context context) {
        this.cycleDataList = cycleDataList;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public ShopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ShopViewHolder(layoutInflater.inflate(R.layout.item_cycle_admin, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ShopViewHolder holder, int position) {
        holder.cycleNameTV.setText(cycleDataList.get(position).getCycleName());
//        if (productDataList.get(position).getDiscountPrice() == null || productDataList.get(position).getDiscountPrice() == 0) {
//            holder.priceTv.setVisibility(View.GONE);
//            holder.discountPrice.setText("Rs. " + productDataList.get(position).getPrice() + "");
//        } else
//            holder.discountPrice.setText("Rs. " + productDataList.get(position).getDiscountPrice() + "");
        holder.rateTV.setText(cycleDataList.get(position).getRentalRate() + "");
        holder.availabilityTV.setText(cycleDataList.get(position).getAvailability());
        Picasso.get().load(cycleDataList.get(position).getImages().get(0)).into(holder.cycleIV);
        holder.mainLL.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
    }


    @Override
    public int getItemCount() {
        return cycleDataList.size();
    }

    public class ShopViewHolder extends RecyclerView.ViewHolder {
        ImageView cycleIV;
        LinearLayout mainLL;
        TextView cycleNameTV, rateTV, availabilityTV;

        public ShopViewHolder(@NonNull View itemView) {
            super(itemView);
            cycleIV = itemView.findViewById(R.id.cycleIV);
            cycleNameTV = itemView.findViewById(R.id.cycleNameTV);
            mainLL = itemView.findViewById(R.id.mainLL);
            rateTV = itemView.findViewById(R.id.rateTV);
            availabilityTV = itemView.findViewById(R.id.availabilityTV);


        }
    }


}
