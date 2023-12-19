package com.example.shop.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shop.Dao.DaoProfile;
import com.example.shop.Model.Profile;
import com.example.shop.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class DeliveryActivity extends AppCompatActivity {

    TextView txtName,txtAddress,txtPhoneNum,txtTotal;
    int total;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);
        txtName = findViewById(R.id.item_customerName_Delivery);
        txtAddress = findViewById(R.id.item_Address_Delivery);
        txtPhoneNum = findViewById(R.id.customer_phoneNum_Delivery);
        txtTotal = findViewById(R.id.total_price_Delivery);


        Intent intent = getIntent();
        total = intent.getIntExtra("total",0);
        txtTotal.setText(""+total);



        DaoProfile daoProfile = new DaoProfile();

        daoProfile.get().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()){
                    Profile profile = data.getValue(Profile.class);
                    
                    if (profile != null){
                        if (profile.getUserID().equals(FirebaseAuth.getInstance().getUid())){

                            txtName.setText(""+profile.getName());
                            txtAddress.setText(""+profile.getAddress());
                            txtPhoneNum.setText(""+profile.getPhoneNum());

                            break;
                        }
                    }else{
                        Toast.makeText(DeliveryActivity.this, "No data in your Profile\nClick Change button", Toast.LENGTH_LONG).show();
                    }
                   
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void btnBack(View view) {
        onBackPressed();
    }

    public void GoToPayment(View view) {
        Intent intent = new Intent(DeliveryActivity.this,PaymentActivity.class);
        intent.putExtra("total",total);
        startActivity(intent);
    }

    public void btnGoToProfile(View view) {
        Toast.makeText(this, "Change the data on your profile", Toast.LENGTH_LONG).show();
        startActivity(new Intent(DeliveryActivity.this, HomeActivity.class));
    }
}