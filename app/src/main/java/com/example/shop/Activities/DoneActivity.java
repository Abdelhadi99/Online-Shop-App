package com.example.shop.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shop.R;
import com.google.firebase.auth.FirebaseAuth;

public class DoneActivity extends AppCompatActivity {
    TextView txtOrderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);
        txtOrderId = findViewById(R.id.orderID_DonePage);
        txtOrderId.setText(""+ FirebaseAuth.getInstance().getUid().substring(5));
    }

    public void btnBack(View view) {
        onBackPressed();
    }

    public void GoToHome(View view) {
        Toast.makeText(this, "You can see your Orders", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(DoneActivity.this,HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}