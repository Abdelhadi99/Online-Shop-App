package com.example.shop.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.shop.R;

public class PaymentActivity extends AppCompatActivity {
    TextView txtTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        txtTotal = findViewById(R.id.total_price_Payment);


        Intent intent = getIntent();
        int total = intent.getIntExtra("total",0);
        txtTotal.setText(""+total);
    }

    public void btnBack(View view) {
        onBackPressed();
    }

    public void GoToDone(View view) {
        startActivity(new Intent(PaymentActivity.this,DoneActivity.class));
    }
}