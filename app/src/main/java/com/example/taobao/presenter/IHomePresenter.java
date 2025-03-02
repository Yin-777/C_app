package com.example.taobao.presenter;

import com.example.taobao.base.IBasePresenter;
import com.example.taobao.view.HomeView.IHomeCallBack;

public interface IHomePresenter extends IBasePresenter<IHomeCallBack> {
    //获取商品分类
    void getCategories();

}
