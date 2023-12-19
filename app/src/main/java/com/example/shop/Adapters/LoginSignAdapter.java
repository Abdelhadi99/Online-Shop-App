package com.example.shop.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.shop.Fragments.LoginFragment;
import com.example.shop.Fragments.SignUpFragment;

public class LoginSignAdapter extends FragmentStatePagerAdapter {

    public LoginSignAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        // Return the fragment for the corresponding page position
        switch (position) {
            case 0:
                return LoginFragment.newInstance();
            case 1:
                return SignUpFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        // Return the total number of pages
        return 2; // Two fragments: Login and Sign Up
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Return the title for each tab
        return (position == 0) ? "Login" : "Sign Up";
    }

}
