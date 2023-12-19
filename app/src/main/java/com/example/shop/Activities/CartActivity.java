package com.example.shop.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.shop.Adapters.CartAdapter;
import com.example.shop.Adapters.ShowItemsAdapter;
import com.example.shop.Dao.DaoCart;
import com.example.shop.Dao.DaoOrder;
import com.example.shop.Model.Cart;
import com.example.shop.Model.Order;
import com.example.shop.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    ConstraintLayout layoutIfEmpty, layoutIfNotEmpty;
    RecyclerView recView;
    Button btnCheckOut;

    DaoCart daoCart;
    List<Cart> carts = new ArrayList<>();
    CartAdapter adapter;

    int total = 0;
    int totalItemsCount = 0;

    DaoOrder daoOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        initData();

        daoCart.get().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()) {
                    Cart cart = data.getValue(Cart.class);


                    if (cart != null) {

                    }
                    if (cart.getUserID() != null) {
                        if (cart.getUserID().equals(FirebaseAuth.getInstance().getUid())) {
                            carts.add(cart);
                            layoutIfNotEmpty.setVisibility(View.VISIBLE);
                            layoutIfEmpty.setVisibility(View.GONE);

                            total += Integer.parseInt(cart.getItemPrice()) * Integer.parseInt(cart.getItemCount());
                            totalItemsCount += Integer.parseInt(cart.getItemCount());

                            recView.setLayoutManager(new GridLayoutManager(CartActivity.this, 2));

                            adapter = new CartAdapter(CartActivity.this, carts);
                            recView.setAdapter(adapter);
                        } else {
                            carts.clear();
                            layoutIfNotEmpty.setVisibility(View.GONE);
                            layoutIfEmpty.setVisibility(View.VISIBLE);
                        }
                    }





                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Order order = new Order(FirebaseAuth.getInstance().getUid(), totalItemsCount, total);

                daoOrder.add(order).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                        Intent intent = new Intent(CartActivity.this, DeliveryActivity.class);
                        intent.putExtra("total", total);

                        startActivity(intent);
                    }
                });


            }
        });

    }

    private void initData() {
        daoCart = new DaoCart();
        layoutIfEmpty = findViewById(R.id.cartLayoutIfEmpty);
        layoutIfNotEmpty = findViewById(R.id.cartLayoutIfNotEmpty);
        recView = findViewById(R.id.recViewCart);
        btnCheckOut = findViewById(R.id.btnCheckOut);
        daoOrder = new DaoOrder();
    }


    public void btnBackCart(View view) {
        onBackPressed();
    }

    public void btnOrderNow(View view) {
        startActivity(new Intent(CartActivity.this, HomeActivity.class));
    }
}