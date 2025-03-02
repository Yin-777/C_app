package com.example.taobao.view.HomeView;

import com.example.taobao.model.bean.Data;
import com.example.taobao.model.bean.tabBean;

import java.util.List;

public interface homeview {
    void showTab(List<Data> dataList);
    void non_showTab();
    void ErrorTab();
}
