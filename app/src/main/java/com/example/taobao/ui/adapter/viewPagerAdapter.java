package com.example.taobao.ui.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.List;

public class viewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;

    public viewPagerAdapter(@NonNull FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragmentList = fragments;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
