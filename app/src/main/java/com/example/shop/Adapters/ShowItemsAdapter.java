package com.example.shop.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shop.Activities.DetailsItemActivity;
import com.example.shop.Model.Item;
import com.example.shop.R;

import java.util.List;

public class ShowItemsAdapter extends RecyclerView.Adapter<ShowItemsAdapter.ViewHolder> {

    Context context;
    List<Item> items;

    public ShowItemsAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ShowItemsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_first_rec_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Item item = items.get(position);

        Glide.with(context).load(item.getItemImg()).into(holder.imageView);
        holder.itemName.setText(item.getItemName());
        holder.itemPrice.setText(item.getItemPrice());
        holder.itemCount.setText(item.getItemCount());

        holder.showDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailsItemActivity.class);
                intent.putExtra("name",item.getItemName());
                intent.putExtra("desc",item.getItemDesc());
                intent.putExtra("price",item.getItemPrice());
                intent.putExtra("img",item.getItemImg());
                intent.putExtra("category",item.getCategory());
                context.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView itemName,itemPrice,itemCount;
        ConstraintLayout showDetails;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_img_rec1);
            itemName = itemView.findViewById(R.id.item_name_rec1);
            itemPrice = itemView.findViewById(R.id.item_price_rec1);
            itemCount = itemView.findViewById(R.id.item_count_rec1);
            showDetails = itemView.findViewById(R.id.layout_first_recView);


        }
    }
}
