package com.example.shop.Dao;

import com.example.shop.Model.Cart;
import com.example.shop.Model.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;

public class DaoCart {
    DatabaseReference databaseReference;

    public DaoCart() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference(Cart.class.getSimpleName());
    }

    public Task<Void> add(Cart cart){

        return databaseReference.child(FirebaseAuth.getInstance().getUid()+" "+cart.getItemName()+cart.getItemPrice()+"").setValue(cart);
    }


    public Task<Void>update(String key, HashMap<String,Object> hashMap){

        return databaseReference.child(key).updateChildren(hashMap);

    }

    public Task<Void>remove(String key){


        return databaseReference.child(key).removeValue();
    }
    public Query get(){

        return databaseReference.orderByValue();
    }


}
