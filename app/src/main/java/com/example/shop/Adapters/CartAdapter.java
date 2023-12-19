package com.example.shop.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shop.Model.Cart;
import com.example.shop.R;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    Context context;
    List<Cart> carts;

    public CartAdapter(Context context, List<Cart> carts) {
        this.context = context;
        this.carts = carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_first_rec_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {

        Cart cart = carts.get(position);

        Glide.with(context).load(cart.getStrImage()).into(holder.imageView);
        holder.itemName.setText(cart.getItemName());
        holder.itemPrice.setText(cart.getItemPrice());
        holder.itemCount.setText(cart.getItemCount());

    }


    @Override
    public int getItemCount() {
        return carts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView itemName,itemPrice,itemCount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_img_rec1);
            itemName = itemView.findViewById(R.id.item_name_rec1);
            itemPrice = itemView.findViewById(R.id.item_price_rec1);
            itemCount = itemView.findViewById(R.id.item_count_rec1);


        }
    }
}
