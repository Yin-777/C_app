package com.example.taobao.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.taobao.R;
import com.example.taobao.base.BaseActivity;
import com.example.taobao.ui.adapter.viewPagerAdapter;
import com.example.taobao.ui.fragment.MainFragment.HomeFragment;
import com.example.taobao.ui.fragment.MainFragment.Red_packerFragment;
import com.example.taobao.ui.fragment.MainFragment.SearchFragment;
import com.example.taobao.ui.fragment.MainFragment.Selected_BlankFragment;
import com.example.taobao.utils.LogUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private BottomNavigationView bottomNavigationView;
    private ViewPager viewPager;
    private viewPagerAdapter viewPagerAdapter;
    private List<Fragment> fragmentList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initListener() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    bottomNavigationView.setSelectedItemId(R.id.Home);
                }
                if (position == 1) {
                    bottomNavigationView.setSelectedItemId(R.id.selected);
                }
                if (position == 2) {
                    bottomNavigationView.setSelectedItemId(R.id.red_packet);
                }
                if (position == 3) {
                    bottomNavigationView.setSelectedItemId(R.id.search);
                }
            }


            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.Home) {
                    viewPager.setCurrentItem(0);
                    LogUtils.d(MainActivity.class, "首页");
                } else if (item.getItemId() == R.id.selected) {
                    viewPager.setCurrentItem(1);
                    LogUtils.e(MainActivity.class, "精选");
                } else if (item.getItemId() == R.id.red_packet) {
                    viewPager.setCurrentItem(2);
                    LogUtils.w(MainActivity.class, "优惠");
                } else if (item.getItemId() == R.id.search) {
                    viewPager.setCurrentItem(3);
                    LogUtils.i(MainActivity.class, "搜索");
                }
                return true;
            }
        });

    }

    @Override
    protected void initView() {
        bottomNavigationView = findViewById(R.id.bottomNavigationview);
        fragmentList = new ArrayList<>();
        viewPager = findViewById(R.id.home_viewpager);
        HomeFragment homeFragment = new HomeFragment();
        Red_packerFragment redPackerFragment = new Red_packerFragment();
        SearchFragment searchFragment = new SearchFragment();
        Selected_BlankFragment selectedBlankFragment = new Selected_BlankFragment();
        fragmentList.add(homeFragment);
        fragmentList.add(selectedBlankFragment);
        fragmentList.add(redPackerFragment);
        fragmentList.add(searchFragment);
        viewPagerAdapter = new viewPagerAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(viewPagerAdapter);

    }


}