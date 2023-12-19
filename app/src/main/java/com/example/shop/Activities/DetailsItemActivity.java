package com.example.shop.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.shop.Dao.DaoCart;
import com.example.shop.Model.Cart;
import com.example.shop.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

public class DetailsItemActivity extends AppCompatActivity {
    Button btnAddToCart;
    TextView txtName, txtPrice, txtDesc, txt_item_count, txtCategory;
    ImageView imageView;

    int itemCount = 0;
    DaoCart daoCart;

    String downloadUrl;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_item);
        initData();

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String price = intent.getStringExtra("price");
        String desc = intent.getStringExtra("desc");
        int img = intent.getIntExtra("img", 0);
        Glide.with(this).asBitmap().load(img).into(imageView);

        String category = intent.getStringExtra("category");
        setCategory(category);


        txtName.setText(name);
        txtDesc.setText(desc + getResources().getString(R.string.item_description));
        txtPrice.setText(price);


        btnAddToCart = findViewById(R.id.btnAddToCart);
        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                if (itemCount > 0) {
                    // Use a background thread for image processing
                    new Thread(() -> {
                        // Convert the Drawable resource directly to bytes
                        Drawable drawable = getResources().getDrawable(img);
                        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
                        Bitmap bitmap = bitmapDrawable.getBitmap();

                        // Compress the image with reduced quality
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
                        byte[] imageData = baos.toByteArray();

                        // Create a unique filename for the image (e.g., timestamp)
                        String timestamp = "afasdjla;l";
                        String fileName = "image_" + timestamp + ".jpeg";

                        // Specify the Firebase Storage path
                        String storagePath = "images/" + fileName;

                        StorageReference storageRef = FirebaseStorage.getInstance().getReference().child("items_images");
                        StorageReference imageRef = storageRef.child(storagePath);

                        // Upload the image to Firebase Storage
                        imageRef.putBytes(imageData)
                                .addOnSuccessListener(taskSnapshot -> {
                                    // Image uploaded successfully
                                    // Now you can get the download URL
                                    imageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                                        String downloadUrl = uri.toString();

                                        // Switch to the main thread for UI updates
                                        runOnUiThread(() -> {
                                            Cart cart = new Cart(FirebaseAuth.getInstance().getUid(), name, price, String.valueOf(itemCount), downloadUrl);

                                            daoCart.add(cart).addOnSuccessListener(unused -> {
                                                progressBar.setVisibility(View.GONE);
                                                Toast.makeText(DetailsItemActivity.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(DetailsItemActivity.this, CartActivity.class));
                                                finish();
                                            });
                                        });
                                    });
                                })
                                .addOnFailureListener(e -> {
                                    // Handle image upload failure
                                    runOnUiThread(() -> {
                                        progressBar.setVisibility(View.GONE);
                                        Toast.makeText(DetailsItemActivity.this, "Image upload failed", Toast.LENGTH_SHORT).show();
                                    });
                                });
                    }).start();
                } else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(DetailsItemActivity.this, "No items to Add", Toast.LENGTH_SHORT).show();
                }
            }
        });


        ////////////////////////////////////////

    }

    private void initData() {
        daoCart = new DaoCart();
        txtCategory = findViewById(R.id.nameOfCategory_detailsActivity);
        txt_item_count = findViewById(R.id.item_count_det);
        txtDesc = findViewById(R.id.item_desc_det);
        txtName = findViewById(R.id.item_name_det);
        txtPrice = findViewById(R.id.item_price_det);
        imageView = findViewById(R.id.item_img_det);
        progressBar = findViewById(R.id.progressBarDet);
    }


    public void GoToCart(View view) {
        startActivity(new Intent(DetailsItemActivity.this, CartActivity.class));
    }

    public void btnBack(View view) {
        onBackPressed();
    }

    public void btnPlus_det(View view) {
        if (itemCount >= 0) {
            itemCount += 1;
            txt_item_count.setText("" + itemCount);
        }
    }


    public void btnMinus_det(View view) {
        if (itemCount > 0) {
            itemCount -= 1;
            txt_item_count.setText("" + itemCount);
        }
    }

    public void setCategory(String category) {
        switch (category) {
            case "woman":
                txtCategory.setText("Women");
                break;
            case "man":
                txtCategory.setText("Man");
                break;
            case "kids":
                txtCategory.setText("Kids");
                break;
            case "all":
                txtCategory.setText("All");
                break;
        }
    }
}

