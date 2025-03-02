package com.example.taobao.presenter;

import com.example.taobao.base.BaseFragment;
import com.example.taobao.model.Retrofit.tabRetrofit;
import com.example.taobao.model.Service;
import com.example.taobao.model.bean.Data;
import com.example.taobao.model.bean.tabBean;
import com.example.taobao.view.HomeView.homeview;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class tabPresenter {
    private Service service;
    private homeview homeview;
    private List<Data> dataList;

    public tabPresenter(homeview homeview) {
        this.homeview = homeview;
        service = tabRetrofit.getClient().create(Service.class);
    }

    public void getTabBean() {
        Call<tabBean> call = service.categories();
        call.enqueue(new Callback<tabBean>() {
            @Override
            public void onResponse(Call<tabBean> call, Response<tabBean> response) {
                if (response.isSuccessful()) {
                    tabBean tabBean = response.body();
                    dataList = tabBean.getData();
                    homeview.showTab(dataList);
                } else {
                    homeview.ErrorTab();
                    homeview.non_showTab();
                }
            }

            @Override
            public void onFailure(Call<tabBean> call, Throwable t) {
                homeview.ErrorTab();
            }
        });

    }


}
