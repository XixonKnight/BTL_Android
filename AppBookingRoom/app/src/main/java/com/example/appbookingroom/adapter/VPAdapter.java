package com.example.appbookingroom.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class VPAdapter extends FragmentPagerAdapter {

    private final List<Fragment> lstFragment = new ArrayList<>();
    private final List<String> lstTitle = new ArrayList<>();


    public VPAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return lstFragment.get(position);
    }

    @Override
    public int getCount() {
        return lstFragment.size();
    }

    public void addFragment(Fragment fgm, String title){
        lstFragment.add(fgm);
        lstTitle.add(title);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return lstTitle.get(position);
    }
}
