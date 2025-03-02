package com.example.taobao.presenter;

import com.example.taobao.base.IBasePresenter;
import com.example.taobao.view.HomeView.ICategoryPagerCallback;

public interface ICategoryPagerPresenter extends IBasePresenter<ICategoryPagerCallback> {
    //根据分类id来获取内容
    void getContentByCategoryId(int categoryId);

    void loaderMore(int categoryId);

    void reload(int categoryId);


}
