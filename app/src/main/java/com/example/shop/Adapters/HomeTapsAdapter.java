package com.example.shop.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.shop.Fragments.AllFragment;
import com.example.shop.Fragments.KidsFragment;
import com.example.shop.Fragments.LoginFragment;
import com.example.shop.Fragments.ManFragment;
import com.example.shop.Fragments.SignUpFragment;
import com.example.shop.Fragments.WomenFragment;

public class HomeTapsAdapter extends FragmentStatePagerAdapter {
    public HomeTapsAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        // Return the fragment for the corresponding page position
        switch (position) {
            case 0:
                return WomenFragment.newInstance();
            case 1:
                return ManFragment.newInstance();
            case 2:
                return KidsFragment.newInstance();
            case 3:
                return AllFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Woman";
            case 1:
                return"Man";
            case 2:
                return "Kids";
            case 3:
                return"All";

            default:
                return null;
        }
    }
}
