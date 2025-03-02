package com.example.taobao.view.HomeView;

import com.example.taobao.model.bean.ContentData;
import com.example.taobao.model.bean.HomePagerContent;

import java.util.List;

public interface ICategoryPagerCallback {
    //数据加载回来
    void onContentLoaded(List<ContentData> contents);

    //记载中
    void onLoading(int categoryId);

    //记载错误
    void onError(int categoryId);

    //数据为空
    void onEmpty(int categoryId);
    //加载更多网络错误

    void onLoaderMoreError(int categoryId);

    //没有更多内容
    void onLoaderMoreEmpty(int categoryId);

    //记载到更多内容
    void onLoaderMoreLoading(List<ContentData> contents);

    //轮播图内容加载到了
    void onLooperListLoaded(List<ContentData> contents);
}
