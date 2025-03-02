package com.example.taobao.presenter;

import android.util.Log;

import com.example.taobao.model.Retrofit.CategoryRetrofit;
import com.example.taobao.model.Service;
import com.example.taobao.model.bean.HomePagerContent;

import com.example.taobao.view.HomeView.ICategoryPagerCallback;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CategoryPagerPresenterImpl implements ICategoryPagerPresenter {
    private CategoryPagerPresenterImpl() {
    }

    private static ICategoryPagerPresenter sInstance = null;

    public static ICategoryPagerPresenter getInstance() {
        if (sInstance == null) {
            sInstance = new CategoryPagerPresenterImpl();
        }
        return sInstance;
    }

    @Override
    public void getContentByCategoryId(int categoryId) {

    }

    @Override
    public void loaderMore(int categoryId) {

    }

    @Override
    public void reload(int categoryId) {

    }

    @Override
    public void registerViewCallback(ICategoryPagerCallback callBack) {

    }

    @Override
    public void unregisterViewCallback(ICategoryPagerCallback callBack) {

    }
}
