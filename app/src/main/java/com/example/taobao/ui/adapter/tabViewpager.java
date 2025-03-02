package com.example.taobao.ui.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.taobao.model.bean.Data;
import com.example.taobao.ui.fragment.fraFragement.HomePagerFragement;

import java.util.List;

public class tabViewpager extends FragmentPagerAdapter {
    private List<Data> dataList;

    public tabViewpager(@NonNull FragmentManager fm, List<Data> dataList) {
        super(fm);
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Data data = dataList.get(position);
        HomePagerFragement homePagerFragement = HomePagerFragement.newInstance(data);
        return homePagerFragement;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return dataList.get(position).getTitle();
    }
}
