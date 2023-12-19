package com.example.shop.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shop.Adapters.ShowItemsAdapter;
import com.example.shop.Model.Item;
import com.example.shop.R;

import java.util.ArrayList;
import java.util.List;

public class ShowItemsActivity extends AppCompatActivity {
    ImageView btnGoToCart;
    TextView txtNameOfCategory;

    private List<Item> originalItems;
    private ShowItemsAdapter adapter;
    SearchView searchView;
    LinearLayout layout ;
    TextView txtNumberOfResult;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_items);
        txtNameOfCategory = findViewById(R.id.nameOfCategory_showItemsActivity);
        layout = findViewById(R.id.linearLayout5);
        txtNumberOfResult = findViewById(R.id.txtNumberOfResult);
        searchView = findViewById(R.id.searchView);


        Intent intent = getIntent();
        String category = intent.getStringExtra("category");


        setData(category);

        //////////////////////////

        // Set up RecyclerView and adapter
        RecyclerView recyclerView = findViewById(R.id.recView_showItems);
        recyclerView.setLayoutManager(new GridLayoutManager(ShowItemsActivity.this, 2));

        adapter = new ShowItemsAdapter(ShowItemsActivity.this, originalItems);
        recyclerView.setAdapter(adapter);

        // Set up SearchView
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Filter the items based on the search query
                List<Item> filteredList = filter(originalItems, newText);
                txtNumberOfResult.setText(""+filteredList.size());
                adapter.setItems(filteredList);
                adapter.notifyDataSetChanged();
                return true;
            }
        });

        ///////////////////////////////////

        btnGoToCart = findViewById(R.id.btnGoToCartFromShowItems);
        btnGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ShowItemsActivity.this,CartActivity.class));
            }
        });

    }

    public void btnBack(View view) {
        onBackPressed();
    }

    private void setData(String category){
        switch (category){
            case "woman":
                List<Item> items1 = new ArrayList<>();
                items1.add(new Item("woman","Women Jacket 1","Made in Jordan","30","0", "Women Jacket\n",R.drawable.woman_items1));
                items1.add(new Item("woman","Women Jacket 2","Made in Jordan","45","0", "Women Jacket\n",R.drawable.woman_items2));
                items1.add(new Item("woman","Women Jacket 3","Made in Jordan","50","0", "Women Jacket\n",R.drawable.woman_items3));

                originalItems = items1;
                searchView.setVisibility(View.GONE);
                txtNameOfCategory.setText("Woman");
                layout.setVisibility(View.GONE);
                break;
            case "man":
                List<Item> items2 = new ArrayList<>();
                items2.add(new Item("man","Man Jacket 1","Made in Jordan","26","0",  "Man Jacket\n",R.drawable.man_items1));
                items2.add(new Item("man","Man Jacket 2","Made in Jordan","20","0",  "Man Jacket\n",R.drawable.man_items2));
                items2.add(new Item("man","Man Jacket 3","Made in Jordan","26","0",  "Man Jacket\n",R.drawable.man_items3));

                originalItems = items2;
                searchView.setVisibility(View.GONE);
                txtNameOfCategory.setText("Man");
                layout.setVisibility(View.GONE);
                break;
            case "kids":
                List<Item> items3 = new ArrayList<>();
                items3.add(new Item("kids","Kids Jacket 1","Made in Jordan","20","0","Kids Jacket\n",R.drawable.kids_items1));
                items3.add(new Item("kids","Kids Jacket 2","Made in Jordan","40","0","Kids Jacket\n",R.drawable.kids_items2));
                items3.add(new Item("kids","Kids Jacket 3","Made in Jordan","40","0","Kids Jacket\n",R.drawable.kids_items3));

                originalItems = items3;
                searchView.setVisibility(View.GONE);
                txtNameOfCategory.setText("Kids");
                layout.setVisibility(View.GONE);
                break;
            case "all":
                List<Item> items = new ArrayList<>();

                items.add(new Item("all","Kids Jacket 1","Made in Jordan","20","0", "Kids Jacket\n",R.drawable.kids_items1));
                items.add(new Item("all","Kids Jacket 2","Made in Jordan","40","0", "Kids Jacket\n",R.drawable.kids_items2));
                items.add(new Item("all","Kids Jacket 3","Made in Jordan","40","0", "Kids Jacket\n",R.drawable.kids_items3));
                items.add(new Item("all","Man Jacket 1","Made in Jordan","26","0",   "Man Jacket\n",R.drawable.man_items1));
                items.add(new Item("all","Man Jacket 2","Made in Jordan","20","0",   "Man Jacket\n",R.drawable.man_items2));
                items.add(new Item("all","Man Jacket 3","Made in Jordan","26","0",   "Man Jacket\n",R.drawable.man_items3));
                items.add(new Item("all","Women Jacket 1","Made in Jordan","30","0",  "Women Jacket\n",R.drawable.woman_items1));
                items.add(new Item("all","Women Jacket 2","Made in Jordan","45","0",  "Women Jacket\n",R.drawable.woman_items2));
                items.add(new Item("all","Women Jacket 3","Made in Jordan","50","0",  "Women Jacket\n",R.drawable.woman_items3));


                originalItems = items;
                searchView.setVisibility(View.GONE);
                layout.setVisibility(View.GONE);
                txtNameOfCategory.setText("All");
                break;

            case "search":
                List<Item> items0 = new ArrayList<>();

                items0.add(new Item("all","Kids Jacket 1","Made in Jordan","20","0", "Kids Jacket\n",R.drawable.kids_items1));
                items0.add(new Item("all","Kids Jacket 2","Made in Jordan","40","0", "Kids Jacket\n",R.drawable.kids_items2));
                items0.add(new Item("all","Kids Jacket 3","Made in Jordan","40","0", "Kids Jacket\n",R.drawable.kids_items3));
                items0.add(new Item("all","Man Jacket 1","Made in Jordan","26","0",   "Man Jacket\n",R.drawable.man_items1));
                items0.add(new Item("all","Man Jacket 2","Made in Jordan","20","0",   "Man Jacket\n",R.drawable.man_items2));
                items0.add(new Item("all","Man Jacket 3","Made in Jordan","26","0",   "Man Jacket\n",R.drawable.man_items3));
                items0.add(new Item("all","Women Jacket 1","Made in Jordan","30","0",  "Women Jacket\n",R.drawable.woman_items1));
                items0.add(new Item("all","Women Jacket 2","Made in Jordan","45","0",  "Women Jacket\n",R.drawable.woman_items2));
                items0.add(new Item("all","Women Jacket 3","Made in Jordan","50","0",  "Women Jacket\n",R.drawable.woman_items3));


                originalItems = items0;
                searchView.setVisibility(View.VISIBLE);
                layout.setVisibility(View.VISIBLE);
                txtNameOfCategory.setText("search");
                txtNumberOfResult.setText(""+items0.size());
                break;
            default:

        }
    }

    // Helper method to filter items based on the search query
    private List<Item> filter(List<Item> items, String query) {
        query = query.toLowerCase();

        List<Item> filteredList = new ArrayList<>();
        for (Item item : items) {
            if (item.getItemName().toLowerCase().contains(query)) {
                filteredList.add(item);
            }
        }
        return filteredList;
    }


}