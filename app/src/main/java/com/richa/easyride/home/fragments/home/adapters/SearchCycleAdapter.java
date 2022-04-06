package com.richa.easyride.home.fragments.home.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.richa.easyride.R;
import com.richa.easyride.api.response.Cycle;
import com.richa.easyride.singleProductPage.SingleProductActivity;
import com.squareup.picasso.Picasso;

import java.text.CollationElementIterator;
import java.util.ArrayList;
import java.util.List;

public class SearchCycleAdapter extends RecyclerView.Adapter<SearchCycleAdapter.SearchViewHolder> implements Filterable {

    List<Cycle> cycleDataList;
    LayoutInflater layoutInflater;
    List<Cycle> searchData;
    Context context;

    public  SearchCycleAdapter(List<Cycle> cycleDataList, Context context, boolean b){
        this.cycleDataList = cycleDataList;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        searchData = new ArrayList<>(cycleDataList);
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Cycle> suggestions = new ArrayList<>();
            if (constraint == null || constraint.length() == 0){
                suggestions.addAll(searchData);
            }else{
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Cycle item : searchData){
                    if (item.getCycleName().toLowerCase().contains(filterPattern)){
                        suggestions.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = suggestions;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            cycleDataList.clear();
            cycleDataList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SearchViewHolder(layoutInflater.inflate(R.layout.search_product,parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        holder.cycleName.setText(cycleDataList.get(position).getCycleName());
        Picasso.get().load(cycleDataList.get(position).getImages().get(0)).into(holder.cycleImage);
        holder.searchCycleCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent productPage = new Intent(context, SingleProductActivity.class);
                productPage.putExtra(SingleProductActivity.key, cycleDataList.get(holder.getAdapterPosition()));
                context.startActivity(productPage);
            }
        });


//    public class SearchViewHolder extends RecyclerView.ViewHolder {
//            ImageView cycleImage;
//            TextView cycleName;
//            CardView searchCycleCV;
//
//        public SearchViewHolder(@NonNull View itemView) {
//            super(itemView);
//            cycleImage = itemView.findViewById(R.id.cycleImage);
//            cycleName = itemView.findViewById(R.id.cycleName);
//            searchCycleCV = itemView.findViewById(R.id.searchCycleCV);
//
//        }
//        }
    }

    @Override
    public int getItemCount() {
        return  cycleDataList.size();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {
        public TextView cycleName;
        public ImageView cycleImage;
        public CardView searchCycleCV;

        public SearchViewHolder(View inflate) {
            super(inflate);
            cycleImage = itemView.findViewById(R.id.cycleImage);
            cycleName = itemView.findViewById(R.id.cycleName);
            searchCycleCV = itemView.findViewById(R.id.searchCycleCV);
        }
    }
}
