package com.richa.easyride.home.fragments.home.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.richa.easyride.R;
import com.richa.easyride.api.response.RentalHistory;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

public class RentalAdapter extends RecyclerView.Adapter<RentalAdapter.ViewHolder> implements Serializable {
    LayoutInflater layoutInflater;
    List<RentalHistory> data;
    Context context;

    public RentalAdapter(List<RentalHistory> data,Context context){
        this.data=data;
        this.context=context;
        layoutInflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RentalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.your_ride,parent,false);
         return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RentalHistory rentalHistory=data.get(position);
        holder.cycleNameTV.setText(rentalHistory.getCycleName());
        Picasso.get().load(rentalHistory.getUrl()).into(holder.cycleIV);
//        Picasso.get().load(rentalHistory.getUrl().get(0)).into(holder.cycleIV);
      //  Picasso.get().load(rentalHistory.get(position).getImages().get(0)).into(holder.cycleIV);
        holder.dateTV.setText(rentalHistory.getPickupDate());
        holder.pickupTimeTV.setText(rentalHistory.getPickupTime());
        holder.dropoffTimeTV.setText(rentalHistory.getDropoffTime());
        holder.paymentRefTV.setText(rentalHistory.getPaymentRefrence());
//        holder.payemntStatusTV.setText(rentalHistory.getPayStatus());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView cycleNameTV, dateTV, pickupTimeTV, dropoffTimeTV, paymentRefTV, payemntStatusTV;
       ImageView cycleIV;

        public ViewHolder(View view) {
            super(view);
            cycleNameTV = view.findViewById(R.id.cycleNameTV);
            cycleIV = view.findViewById(R.id.cycleIV);
            dateTV = view.findViewById(R.id.dateTV);
            pickupTimeTV = view.findViewById(R.id.pickupTimeTV);
            dropoffTimeTV = view.findViewById(R.id.dropoffTimeTV);
            paymentRefTV = view.findViewById(R.id.paymentRefTV);
//            payemntStatusTV = view.findViewById(R.id.paymentStatusTV);


        }
    }
}
