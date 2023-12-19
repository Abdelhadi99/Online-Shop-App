package com.example.shop.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.shop.Activities.ShowItemsActivity;
import com.example.shop.Adapters.HomeFragmentsAdapter;
import com.example.shop.Model.Item;
import com.example.shop.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link KidsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class KidsFragment extends Fragment {



    public KidsFragment() {
        // Required empty public constructor
    }


    public static KidsFragment newInstance() {
      return new KidsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kids, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<Item> items = new ArrayList<>();
        items.add(new Item(R.drawable.kids_items1));
        items.add(new Item(R.drawable.kids_items2));
        items.add(new Item(R.drawable.kids_items3));


        RecyclerView recyclerView = view.findViewById(R.id.recView_home_kids_fragment);
        HomeFragmentsAdapter adapter = new HomeFragmentsAdapter(requireActivity().getApplicationContext(), items);

// Create a LinearLayoutManager with horizontal orientation
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);

// Set the layout manager to the RecyclerView
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


        // button see more
        LinearLayout btnSeeMore = view.findViewById(R.id.btnSeeMore_kids_fragment);
        btnSeeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireActivity(),ShowItemsActivity.class);
                intent.putExtra("category","kids");
                startActivity(intent);
            }
        });
    }
}