package com.example.taobao.view.HomeView;

import com.example.taobao.base.IBaseCallBack;
import com.example.taobao.model.bean.Data;

public interface IHomeCallBack extends IBaseCallBack {
    void onCategoriesLoaded(Data data);
}
