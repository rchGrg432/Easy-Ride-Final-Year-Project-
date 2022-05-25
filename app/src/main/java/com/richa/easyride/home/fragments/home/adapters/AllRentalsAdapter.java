package com.richa.easyride.home.fragments.home.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.richa.easyride.R;
import com.richa.easyride.api.response.AllRentalsResponse;

import java.io.Serializable;
import java.util.List;

public class AllRentalsAdapter extends RecyclerView.Adapter<AllRentalsAdapter.ViewHolder> implements Serializable {
    LayoutInflater layoutInflater;
    List<AllRentalsResponse> data;
    Context context;

    public AllRentalsAdapter(List<AllRentalsResponse> data, Context context) {
        this.data = data;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public AllRentalsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.rides, parent, false);
        return new AllRentalsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllRentalsAdapter.ViewHolder holder, int position) {
        AllRentalsResponse allRentalsResponse = data.get(position);
        holder.cycleNameTV.setText(allRentalsResponse.getCycleName());
        holder.dateTV.setText(allRentalsResponse.getPickupDate());
        holder.pickupTimeTV.setText(allRentalsResponse.getPickupTime());
        holder.dropoffTimeTV.setText(allRentalsResponse.getDropoffTime());
        holder.rentalUserNameTV.setText(allRentalsResponse.getName());
        holder.rentalIDTV.setText(Integer.toString(allRentalsResponse.getRentalId()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView cycleNameTV, dateTV, pickupTimeTV, dropoffTimeTV, rentalCompleteTV, rentalUserNameTV, rentalIDTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cycleNameTV = itemView.findViewById(R.id.cycleNameTV);
            dateTV = itemView.findViewById(R.id.dateTV);
            pickupTimeTV = itemView.findViewById(R.id.pickupTimeTV);
            dropoffTimeTV = itemView.findViewById(R.id.dropoffTimeTV);
//            rentalCompleteTV = itemView.findViewById(R.id.rentalCompleteTV);
            rentalUserNameTV = itemView.findViewById(R.id.rentalUserNameTV);
            rentalIDTV = itemView.findViewById(R.id.rentalIDTV);
        }
    }
}
