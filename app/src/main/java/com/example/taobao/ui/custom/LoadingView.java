package com.example.taobao.ui.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.taobao.R;
import com.example.taobao.utils.LogUtils;

public class LoadingView extends androidx.appcompat.widget.AppCompatImageView {
    private float angle = 0;
    private boolean mNeedRotate = true;

    public LoadingView(Context context) {
        this(context, null);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setImageResource(R.drawable.load);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startRotate();
    }

    private void startRotate() {
        post(new Runnable() {
            @Override
            public void run() {
                angle += 10;
                if (angle >= 360) {
                    angle = 0;
                }
                invalidate();//重新进行绘图调用onDraw
                //判断是否旋转，如果mNeedRotate为false或者不可见就不旋转
//                LogUtils.d(getClass(), "LoadingView-->" + mNeedRotate + getVisibility());
                if (!mNeedRotate || getVisibility() != VISIBLE) {
                    removeCallbacks(this);
                } else {
                    postDelayed(this, 10);//递归调用 Runnable 自身，实现循环逻辑。
                }

            }
        });
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopRotate();
    }

    private void stopRotate() {
        mNeedRotate = false;
        LogUtils.d(getClass(), "123");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.rotate(angle, getWidth() / 2, getHeight() / 2);
        super.onDraw(canvas);
    }
}
