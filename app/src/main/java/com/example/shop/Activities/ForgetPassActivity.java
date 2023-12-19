package com.example.shop.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.shop.Adapters.LoginSignAdapter;
import com.example.shop.Fragments.ForgetPassFragment;
import com.example.shop.R;
import com.google.android.material.tabs.TabLayout;

public class ForgetPassActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass);

        ViewPager viewPager = findViewById(R.id.pagerForgetPass);
        ForgetPassFragment.ForgetPassAdapter pagerAdapter = new ForgetPassFragment.ForgetPassAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabsForgetPass);
        tabLayout.setupWithViewPager(viewPager);

    }
}