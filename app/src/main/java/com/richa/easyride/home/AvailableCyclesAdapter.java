package com.richa.easyride.home;

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
import com.richa.easyride.home.fragments.home.adapters.ShopAdapter;
import com.richa.easyride.singleProductPage.SingleProductActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AvailableCyclesAdapter extends RecyclerView.Adapter<AvailableCyclesAdapter.CycleViewHolder> {
    List<Cycle> cycleDataList;
    LayoutInflater layoutInflater;
    Context context;

    public  AvailableCyclesAdapter(List<Cycle> cycleDataList, Context context, boolean b){
        this.cycleDataList = cycleDataList;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
//        searchData = new ArrayList<>(cycleDataList);



    }

    @NonNull
    @Override
    public CycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CycleViewHolder(layoutInflater.inflate(R.layout.item_product, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CycleViewHolder holder, int position) {
        holder.cycleNameTV.setText(cycleDataList.get(position).getCycleName());
        holder.rateTV.setText(cycleDataList.get(position).getRentalRate()+"");
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
    public int getItemCount() {
        return cycleDataList.size();
    }

    public class CycleViewHolder extends RecyclerView.ViewHolder{
        ImageView cycleIV;
        LinearLayout mainLL, searchLayout;
        TextView cycleNameTV, rateTV, availabilityTV;

        public CycleViewHolder(View itemView) {
            super(itemView);
            cycleIV = itemView.findViewById(R.id.cycleIV);
            cycleNameTV = itemView.findViewById(R.id.cycleNameTV);
            mainLL = itemView.findViewById(R.id.mainLL);
            rateTV = itemView.findViewById(R.id.rateTV);
            availabilityTV = itemView.findViewById(R.id.availabilityTV);
        }
    }
}
