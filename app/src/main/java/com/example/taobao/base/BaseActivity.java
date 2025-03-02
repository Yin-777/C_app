package com.example.taobao.base;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.taobao.R;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView();//初始化控件必须实现所以要改为抽象类
        initListener();//控件监听事件不需要强制执行所以用正常的方法即可
        dataSolve();//
    }

    protected abstract int getLayoutId();


    protected void initListener() {
    }

    protected void dataSolve() {
    }

    protected abstract void initView();

}
