package com.example.taobao.ui.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.example.taobao.R;
import com.example.taobao.utils.LogUtils;

public class AutoLooperViewpage extends ViewPager {
    public static final long DEFAULT_DURATION = 3000;
    private long mDuration = DEFAULT_DURATION;
    private boolean isChange = false;

    /**
     * 功能:自动轮播方法
     */
    public AutoLooperViewpage(@NonNull Context context) {
        super(context, null);
    }

    public AutoLooperViewpage(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //读取属性
        initData(context, attrs);
    }

    private void initData(Context context, AttributeSet attrs) {
        TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.AutoLooperStyle);
        //获取属性
        mDuration = t.getInteger(R.styleable.AutoLooperStyle_duration, (int) DEFAULT_DURATION);

        //回收
        t.recycle();
    }

    /**
     * 设置切换时长
     * duration 时长，单位:毫秒
     */
    public void serDuration(int duration) {
        this.mDuration = duration;
    }

    public void startLooper() {
        isChange = true;
        post(runnable);
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            int currentPos = getCurrentItem();
            currentPos++;
            setCurrentItem(currentPos);
            if (isChange) {
                postDelayed(this, 3000);
            }
        }
    };

    public void stopLooper() {
        isChange = false;
        removeCallbacks(runnable);
    }
}
