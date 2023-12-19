package com.example.shop.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shop.Model.Item;
import com.example.shop.R;

import java.util.List;

public class HomeFragmentsAdapter extends RecyclerView.Adapter<HomeFragmentsAdapter.ViewHolder> {

    Context context;
    List<Item> items;

    public HomeFragmentsAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public HomeFragmentsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pic,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeFragmentsAdapter.ViewHolder holder, int position) {
        Item item = items.get(position);

        Glide.with(context).load(item.getItemImg()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_home_fragment);

        }
    }
}
