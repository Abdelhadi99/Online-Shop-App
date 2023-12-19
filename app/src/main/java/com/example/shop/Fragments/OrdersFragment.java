package com.example.shop.Fragments;

import android.content.Intent;
import android.hardware.lights.LightState;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.shop.Activities.CartActivity;
import com.example.shop.Activities.HomeActivity;
import com.example.shop.Adapters.CartAdapter;
import com.example.shop.Adapters.OrderAdapter;
import com.example.shop.Dao.DaoOrder;
import com.example.shop.Model.Order;
import com.example.shop.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrdersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrdersFragment extends Fragment {

    ConstraintLayout layoutIfEmpty, layoutIfNotEmpty;
    List<Order> orders = new ArrayList<>();
    RecyclerView recView;
    OrderAdapter adapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OrdersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrdersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OrdersFragment newInstance(String param1, String param2) {
        OrdersFragment fragment = new OrdersFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_orders, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        layoutIfEmpty = view.findViewById(R.id.orderLayoutIfEmpty);
        layoutIfNotEmpty = view.findViewById(R.id.orderLayoutIfNotEmpty);
        recView = view.findViewById(R.id.recViewOrder);

        DaoOrder daoOrder = new DaoOrder();
        daoOrder.get().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()) {
                    Order order = data.getValue(Order.class);
                    if (order != null) {

                    }

                    if (isAdded()) {
                        if (order.getUserID() != null) {
                            if (order.getUserID().equals(FirebaseAuth.getInstance().getUid())) {
                                orders.add(order);
                                layoutIfNotEmpty.setVisibility(View.VISIBLE);
                                layoutIfEmpty.setVisibility(View.GONE);

                                recView.setLayoutManager(new LinearLayoutManager(requireActivity().getApplicationContext()));

                                adapter = new OrderAdapter(requireActivity().getApplicationContext(), orders);
                                recView.setAdapter(adapter);
                            } else {
                                orders.clear();
                                layoutIfNotEmpty.setVisibility(View.GONE);
                                layoutIfEmpty.setVisibility(View.VISIBLE);
                            }
                        }


                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        Button btnOrderNow = view.findViewById(R.id.btnOrderNow_ordersF);
        btnOrderNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recreateActivity();
                Toast.makeText(requireActivity().getApplicationContext(), "Order Now", Toast.LENGTH_SHORT).show();

            }
        });

    }

    // Inside your activity where you want to recreate it
    private void recreateActivity() {
        requireActivity().finish();

        Intent intent = new Intent(requireActivity(), HomeActivity.class);
        startActivity(intent);
    }
}