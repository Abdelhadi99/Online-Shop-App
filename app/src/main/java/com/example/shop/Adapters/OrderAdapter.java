package com.example.shop.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop.Model.Order;
import com.example.shop.R;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    Context context;
    List<Order > orders = new ArrayList<>();

    public OrderAdapter(Context context, List<Order> orders) {
        this.context = context;
        this.orders = orders;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.ViewHolder holder, int position) {
        Order order = orders.get(position);
        holder.txtTotalPrice.setText(""+order.getTotalPrice());
        holder.txtTotalCount.setText(""+order.getCountItems());

    }


    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTotalCount,txtTotalPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTotalCount = itemView.findViewById(R.id.item_count_Orders);
            txtTotalPrice = itemView.findViewById(R.id.item_totalPrice_Orders);

        }
    }
}
