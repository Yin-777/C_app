package com.example.taobao.ui.fragment.MainFragment;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.taobao.R;
import com.example.taobao.base.BaseFragment;
import com.example.taobao.model.Retrofit.CategoryRetrofit;
import com.example.taobao.model.Service;
import com.example.taobao.model.bean.selected_root;
import com.example.taobao.ui.activity.TicketActivity;
import com.example.taobao.ui.adapter.selectedLeftAdapter;
import com.example.taobao.ui.adapter.selectedRightAdapter;
import com.example.taobao.utils.LogUtils;
import com.example.taobao.utils.SizeUtils;
import com.example.taobao.utils.ToastUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Selected_BlankFragment extends BaseFragment implements com.example.taobao.ui.adapter.selectedLeftAdapter.selectedInter, com.example.taobao.ui.adapter.selectedRightAdapter.selectedRightInter {

    private selectedLeftAdapter selectedLeftAdapter;
    private RecyclerView recyclerView_right, recyclerView_left;
    private List<String> all = new ArrayList<>();
    private List<String> img = new ArrayList<>();
    private selectedRightAdapter selectedRightAdapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpState(State.LOADING);
        init(view);
    }

    private void init(View view) {
        recyclerView_right = view.findViewById(R.id.recycleview_right);
        recyclerView_left = view.findViewById(R.id.recycleView_left);
        selectedRightAdapter = new selectedRightAdapter(img, img, img, img, img, getContext());
        selectedLeftAdapter = new selectedLeftAdapter(all, getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        LinearLayoutManager linearLayoutManagers = new LinearLayoutManager(getContext());
        linearLayoutManagers.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView_left.setLayoutManager(linearLayoutManagers);
        recyclerView_left.setAdapter(selectedLeftAdapter);
        recyclerView_right.setLayoutManager(linearLayoutManager);
        recyclerView_right.setAdapter(selectedRightAdapter);
        LogUtils.d(getClass(), all.toString());
        recyclerView_right.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                int topAndBottom = SizeUtils.dip2px(getContext(), 4);
                int leftAndRight = SizeUtils.dip2px(getContext(), 6);
                outRect.top = topAndBottom;
                outRect.left = leftAndRight;
                outRect.right = leftAndRight;
                outRect.bottom = topAndBottom;
            }
        });
        initlistener();
        all = new ArrayList<>(Arrays.asList("办公室零食",
                "程序员必备",
                "上班族早餐",
                "日用品",
                "电脑周边",
                "秋冬必备", "节日礼物", "运动锻炼", "熬夜护肤"));
        selectedLeftAdapter.refresh(all);

    }

    private void initlistener() {
        selectedLeftAdapter.interSelectedInter(this);
        selectedRightAdapter.setselected(this);
    }

    @Override
    protected void initView() {
        super.initView();


    }


    @Override
    protected int serach_Inflater() {
        return R.layout.fragment_selected__blank;
    }


    @Override
    public void selected(String text) {
        //左边的分页面被点击了
        LogUtils.d(getClass(), text);
        selectedRightAdapter.refresh(img, img, img, img, img);
        int page = 0;

        if (text.equals("办公室零食")) {
            page = 1;
        }
        if (text.equals("程序员必备")) {
            page = 2;
        }
        if (text.equals("办公室零食")) {
            page = 3;
        }
        if (text.equals("上班族早餐")) {
            page = 4;
        }
        if (text.equals("日用品")) {
            page = 5;
        }
        if (text.equals("电脑周边")) {
            page = 6;
        }
        if (text.equals("节日礼物")) {
            page = 7;
        }
        if (text.equals("运动锻炼")) {
            page = 8;
        }
        if (text.equals("熬夜护肤")) {
            page = 9;
        }

        getApi(page);
    }

    @Override
    protected View Loading(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_with_title_layout, container, false);
    }

    private void getApi(int page) {
        Service service = CategoryRetrofit.getClient().create(Service.class);
        Call<selected_root> call = service.getSelectedInfo(String.valueOf(page));
        call.enqueue(new Callback<selected_root>() {
            @Override
            public void onResponse(Call<selected_root> call, Response<selected_root> response) {
                //网络请求成功，每次点击到就进行一次网络请求
                if (response.isSuccessful()) {
                    setUpState(State.SUCCESS);
                    selected_root root = response.body();
                    List<String> contentUrl = new ArrayList<>();
                    List<String> listString = new ArrayList<>();
                    List<String> titleList = new ArrayList<>();
                    List<String> priceList = new ArrayList<>();
                    List<String> cutList = new ArrayList<>();
                    for (int i = 0; i < root.getData().getList().size(); i++) {
                        contentUrl.add(root.getData().getList().get(i).getCouponShareUrl());
                        listString.add(root.getData().getList().get(i).getCover());
                        titleList.add(root.getData().getList().get(i).getTitle());
                        priceList.add(root.getData().getList().get(i).getJustPrice());
                        cutList.add(String.valueOf(root.getData().getList().get(i).getCouponAmount()));
                    }
                    getActivity().runOnUiThread(() -> {
                        selectedRightAdapter.refresh(listString, titleList, priceList, cutList, contentUrl);
                        recyclerView_right.scrollToPosition(0);

                    });
                }
            }

            @Override
            public void onFailure(Call<selected_root> call, Throwable throwable) {
                LogUtils.d(getClass(), throwable.toString());
            }
        });
    }

    @Override
    public void setTileText(String contentUrl, String img) {
        String baseurl = contentUrl;
        Intent intent = new Intent(getActivity(), TicketActivity.class);
        if (baseurl.startsWith("//")) {
            baseurl = "https:" + baseurl; // 通常使用 https
        }
        Bundle bundle = new Bundle();
        bundle.putString("url", img);
        bundle.putString("contentUrl", baseurl);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}