package com.example.taobao.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.taobao.model.bean.ContentList;

import java.util.ArrayList;
import java.util.List;

public class LooperPagerAdapter extends PagerAdapter {
    List<ContentList> list = new ArrayList<>();
    private LooperData looperData = null;

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        //要处理一下position的越界问题
        int realPosition = position % list.size();
        ContentList data = list.get(realPosition);
        String baseUrl = data.getCover();

// 检查是否包含协议，补全协议
        if (baseUrl.startsWith("//")) {
            baseUrl = "https:" + baseUrl; // 通常使用 https
        }
        ImageView imageView = new ImageView(container.getContext());
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        imageView.setLayoutParams(layoutParams);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Glide.with(container.getContext()).load(baseUrl).into(imageView);
        container.addView(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (looperData != null) {
                    looperData.OnLooperItemListener(data);
                }
            }
        });
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    public void setData(List<ContentList> data) {
        list.clear();
        list.addAll(data);
        notifyDataSetChanged();
    }

    public void setLooperData(LooperData looperData) {
        this.looperData = looperData;
    }

    public interface LooperData {
        void OnLooperItemListener(ContentList contentList);
    }
}
