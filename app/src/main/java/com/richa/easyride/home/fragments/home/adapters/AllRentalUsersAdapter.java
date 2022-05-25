package com.richa.easyride.home.fragments.home.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.richa.easyride.R;
import com.richa.easyride.admin.rentals.RidesOfUsers;
import com.richa.easyride.api.response.AllRentalsResponse;
import com.richa.easyride.api.response.RentalHistory;
import com.richa.easyride.singleProductPage.SingleProductActivity;

import java.io.Serializable;
import java.util.List;

public class AllRentalUsersAdapter extends RecyclerView.Adapter<AllRentalUsersAdapter.ViewHolder> implements Serializable {
    LayoutInflater layoutInflater;
    List<AllRentalsResponse> data;
    Context context;

    public AllRentalUsersAdapter(List<AllRentalsResponse> data, Context context) {
        this.data = data;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public AllRentalUsersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.user_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllRentalUsersAdapter.ViewHolder holder, int position) {
        AllRentalsResponse allRentalsResponse = data.get(position);
        holder.rentalUserNameTV.setText(allRentalsResponse.getName());
//        holder.noOfRides.setText(Integer.toString(allRentalsResponse.getRentalId()));
        holder.viewRidesTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent productPage = new Intent(context, RidesOfUsers.class);
                context.startActivity(productPage);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView rentalUserNameTV, noOfRides, viewRidesTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rentalUserNameTV = itemView.findViewById(R.id.rentalUserNameTV);
            noOfRides = itemView.findViewById(R.id.noOfRidesTV);
            viewRidesTV = itemView.findViewById(R.id.viewRidesTV);


        }
    }
}
