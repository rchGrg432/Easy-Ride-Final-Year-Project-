package com.richa.easyride.home.fragments.home.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.richa.easyride.api.response.Category;
import com.richa.easyride.categoryActivity.CategoryActivity;
import com.richa.easyride.utils.DataHolder;
import com.squareup.picasso.Picasso;
import com.richa.easyride.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    List<Category> categories;
    LayoutInflater inflater;
    Context context;
    Boolean showImage;
    Boolean select ;
    Activity activity;
//    Boolean showImage;

    public CategoryAdapter(List<Category> categories, Context context, Boolean showImage, Boolean select, Activity activity) {
        this.categories = categories;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.showImage = showImage;
        this.select = select;
        this.activity = activity;
//        this.showImage = showImage;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (showImage)
            return new CategoryViewHolder(inflater.inflate(R.layout.item_category, parent, false));
        else
            return new CategoryViewHolder(inflater.inflate(R.layout.item_category_no_image, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.catNameTV.setText(categories.get(position).getCategoryName());
        Picasso.get().load(categories.get(position).getCategoryImage()).into(holder.roundedImageView);
        holder.categoryItemLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (select) {
                    DataHolder.category = categories.get(holder.getAdapterPosition());
                    activity.finish();

                } else {
                    Intent intent = new Intent(context, CategoryActivity.class);
                    intent.putExtra(CategoryActivity.CATEGORY_DATA_KEY, categories.get(holder.getAdapterPosition()));
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {
        ImageView roundedImageView;
        TextView catNameTV;
        LinearLayout categoryItemLL;
        RelativeLayout categoryItemRL;
        CardView categoryCV;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            roundedImageView = itemView.findViewById(R.id.roundedImageView);
            catNameTV = itemView.findViewById(R.id.catNameTV);
            categoryItemLL = itemView.findViewById(R.id.categoryItemLL);
            categoryItemRL = itemView.findViewById(R.id.categoryItemRL);
            categoryCV = itemView.findViewById(R.id.categoryCV);
        }
    }
}
