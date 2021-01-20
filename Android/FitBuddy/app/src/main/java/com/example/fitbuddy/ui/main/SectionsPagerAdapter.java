package com.example.fitbuddy.ui.main;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;


import com.example.fitbuddy.BmiFragment;
import com.example.fitbuddy.UserActivity;
import com.example.fitbuddy.exerciseFragment;
import com.example.fitbuddy.nutritionFragment;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentStateAdapter {

    public SectionsPagerAdapter(Context context, UserActivity fm) {
        super(fm);
    }

    Fragment bmi,nutrition,exercise;

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new BmiFragment();
            case 1:
                return new exerciseFragment();
            default:
                return new nutritionFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}