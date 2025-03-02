package com.example.taobao.base;

import com.example.taobao.view.HomeView.IHomeCallBack;

public interface IBasePresenter<T> {
    //注册UI通知接口
    void registerViewCallback(T callBack);

    //通知UI取消接口
    void unregisterViewCallback(T callBack);

}
