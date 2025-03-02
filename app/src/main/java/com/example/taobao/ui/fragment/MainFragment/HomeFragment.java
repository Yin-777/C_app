package com.example.taobao.ui.fragment.MainFragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.taobao.R;
import com.example.taobao.base.BaseFragment;
import com.example.taobao.model.bean.Data;
import com.example.taobao.presenter.tabPresenter;
import com.example.taobao.ui.activity.MainActivity;
import com.example.taobao.ui.adapter.tabViewpager;
import com.example.taobao.ui.adapter.viewPagerAdapter;
import com.example.taobao.ui.custom.LoadingView;
import com.example.taobao.utils.LogUtils;

import com.example.taobao.view.HomeView.homeview;
import com.google.android.material.tabs.TabLayout;

import com.google.zxing.integration.android.IntentIntegrator;
import com.vondear.rxtool.RxActivityTool;
import com.vondear.rxtool.RxTool;

import java.util.List;

public class HomeFragment extends BaseFragment implements homeview {
    private TabLayout tableLayout;
    private ViewPager viewPager;
    private tabPresenter tabPresenter;
    private ImageView imageView;
    private tabViewpager tabViewpager;
    private FrameLayout frameLayout;
    private LoadingView loadingView;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpState(State.LOADING);
        initview(view);
        initLinstener();
        retrofitTab();
    }

    private void initLinstener() {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
startScan();
            }
        });
        frameLayout.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (frameLayout.getVisibility() != View.VISIBLE) {
                    loadingView.setVisibility(View.GONE);
                }
            }
        });
    }

    public void retrofitTab() {
        tabPresenter = new tabPresenter(this);
        tabPresenter.getTabBean();
    }

    ;

    private void initview(View view) {
        RxTool.init(getContext());
        imageView = view.findViewById(R.id.scanner);
        frameLayout = view.findViewById(R.id.fragment_loading);
        loadingView = view.findViewById(R.id.loading);
        tableLayout = view.findViewById(R.id.tableLayout);
        viewPager = view.findViewById(R.id.Home_viewpager);

    }


    @Override
    protected int serach_Inflater() {
        return R.layout.fragment_home;
    }

    @Override
    protected void retrotab() {
        retrofitTab();
    }

    @Override
    protected View Loading(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.base_home_fragement_layout, container, false);
    }

    public void startScan() {
        IntentIntegrator integrator = new IntentIntegrator(getActivity());
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("请对准二维码/条形码扫描");
        integrator.setCameraId(0); // 后置摄像头
        integrator.setBeepEnabled(true); // 扫描成功提示音
        integrator.initiateScan();
    }

    @Override
    public void showTab(List<Data> dataList) {
        setUpState(State.SUCCESS);
        LogUtils.d(MainActivity.class, dataList.toString());
        tabViewpager = new tabViewpager(getChildFragmentManager(), dataList);
        viewPager.setAdapter(tabViewpager);
        tableLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void non_showTab() {
        LogUtils.e(MainActivity.class, "网络请求失败");
    }

    @Override
    public void ErrorTab() {
        setUpState(State.ERROR);
    }
}