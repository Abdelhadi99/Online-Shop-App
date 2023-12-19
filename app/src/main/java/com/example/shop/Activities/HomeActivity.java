package com.example.shop.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.shop.Fragments.HomeFragment;
import com.example.shop.Fragments.NotificationsFragment;
import com.example.shop.Fragments.OrdersFragment;
import com.example.shop.Fragments.ProfileFragment;
import com.example.shop.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        // Load the default fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new HomeFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            item -> {
                Fragment selectedFragment = null;

                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        selectedFragment = new HomeFragment();
                        break;
                    case R.id.navigation_orders:
                        selectedFragment = new OrdersFragment();
                        break;
                    case R.id.navigation_profile:
                        selectedFragment = new ProfileFragment();
                        break;
                    case R.id.navigation_notifications:
                        selectedFragment = new NotificationsFragment();
                        break;
                }

                loadFragment(selectedFragment);

                return true;
            };


    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                fragment).commit();
    }

//    @Override
//    public void onBackPressed() {
//        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
//            getSupportFragmentManager().popBackStack();
//        } else {
//            super.onBackPressed();
//        }
//    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    //Press on any place in screen to cancel the keyboard
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {

                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }

        return super.dispatchTouchEvent(event);
    }
}