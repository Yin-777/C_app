package com.example.taobao.ui.fragment.fraFragement;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taobao.R;
import com.example.taobao.base.BaseFragment;
import com.example.taobao.model.Retrofit.CategoryRetrofit;
import com.example.taobao.model.Service;
import com.example.taobao.model.bean.ContentData;
import com.example.taobao.model.bean.ContentList;
import com.example.taobao.model.bean.Data;
import com.example.taobao.model.bean.HomePagerContent;
import com.example.taobao.presenter.CategoryPagerPresenterImpl;
import com.example.taobao.presenter.ICategoryPagerPresenter;
import com.example.taobao.ui.activity.TicketActivity;
import com.example.taobao.ui.adapter.HomePagerContentAdapter;
import com.example.taobao.ui.adapter.HomePagerContentAdapter.OnClickListener;
import com.example.taobao.ui.adapter.LooperPagerAdapter;
import com.example.taobao.ui.custom.AutoLooperViewpage;
import com.example.taobao.ui.custom.TbNestedScrollView;
import com.example.taobao.utils.LogUtils;
import com.example.taobao.utils.SizeUtils;

import com.example.taobao.utils.ToastUtil;
import com.example.taobao.view.HomeView.ICategoryPagerCallback;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePagerFragement extends BaseFragment implements ICategoryPagerCallback, OnClickListener, LooperPagerAdapter.LooperData {

    private RecyclerView recyclerView;
    private TwinklingRefreshLayout twinklingRefreshLayout;
    private LinearLayout linearLayout;
    private TextView textView;
    private LinearLayout layout;
    private TbNestedScrollView tbNestedScrollView;
    private List<ContentList> contentLists = new ArrayList<>();
    private List<View> viewList = new ArrayList<>();
    private HomePagerContentAdapter homePagerContentAdapter;
    private ICategoryPagerPresenter categoryPagerPresenter;
    private AutoLooperViewpage viewPager_looper;
    private List<ContentList> list = new ArrayList<>();
    private LooperPagerAdapter looperPagerAdapter;
    private List<ContentList> Looperlist = new ArrayList<>();

    @Override
    public void onResume() {
        super.onResume();
        //可见的时候我们去调用轮播
        viewPager_looper.startLooper();
    }

    @Override
    public void onPause() {
        super.onPause();
        //不可见的时候暂停
        viewPager_looper.stopLooper();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpState(State.LOADING);
        //解决了NestedScrollView与recycleView的嵌套问题，当recycleView要自定义高度，以LinerLayout的最大高度为recycleview的高度
//        homePagerParent = view.findViewById(R.id.home_pager_parent);
//        homePagerParent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                int headerHeight = layout.getMeasuredHeight();
//                tbNestedScrollView.setHeaderHeight(headerHeight);
//                int MeasureHeight = homePagerParent.getMeasuredHeight();
//                ViewGroup.LayoutParams layoutParams = recyclerView.getLayoutParams();
////                LogUtils.d(HomePagerContent.class, "Height-->" + MeasureHeight);
//                layoutParams.height = MeasureHeight;
//                recyclerView.setLayoutParams(layoutParams);
//                if (MeasureHeight != 0) {
//                    homePagerParent.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//                }
//            }
//        });
        // viewPager实现轮播图
        twinklingRefreshLayout = view.findViewById(R.id.home_pager_refresh);
        layout = view.findViewById(R.id.home_pager_header_container);
        tbNestedScrollView = view.findViewById(R.id.home_pager_nested_scroller);
        linearLayout = view.findViewById(R.id.looper_point_container);
        textView = view.findViewById(R.id.show_title1);
        viewPager_looper = view.findViewById(R.id.looper_pager);
        looperPagerAdapter = new LooperPagerAdapter();
        viewPager_looper.setAdapter(looperPagerAdapter);
        recyclerView = view.findViewById(R.id.goods_recycleview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        homePagerContentAdapter = new HomePagerContentAdapter(contentLists, getContext());
        recyclerView.setAdapter(homePagerContentAdapter);
        initData();
        if (!viewList.isEmpty()) {
            changeui();
        } else {
            loadData();
        }
        initListener();
    }

    private void changeui() {
        extracted();


    }

    private void extracted() {
        looperPagerAdapter.setData(Looperlist);
        int dx = (Integer.MAX_VALUE / 2) % Looperlist.size();
        int targertCenterPosition = (Integer.MAX_VALUE / 2) - dx;
        viewPager_looper.setCurrentItem(targertCenterPosition);
        setUpState(State.SUCCESS);
        Log.d("TAG", "onResponse: " + Looperlist.size());
        if (!Looperlist.isEmpty()) {
            getActivity().runOnUiThread(() -> {
                for (int i = 0; i < 6; i++) {
                    View view = new View(getContext());
                    int size = SizeUtils.dip2px(getContext(), 8);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(size, size);
                    layoutParams.leftMargin = SizeUtils.dip2px(getContext(), 5);
                    layoutParams.rightMargin = SizeUtils.dip2px(getContext(), 5);
                    view.setLayoutParams(layoutParams);
                    if (i == 0) {
                        view.setBackground(getContext().getDrawable(R.drawable.shape_indicater));
                    } else {
                        view.setBackground(getContext().getDrawable(R.drawable.shape_indicator_non));
                    }
                    viewList.add(view);

                }
            });
        }
    }

    private void initData() {
        twinklingRefreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                //TODO：来真正地实现数据地加载
                //1.拿到当前页面
                //2.页码++
                //3.加载数据
                //4.处理数据结果
                Log.d("TAG", "onLoadMore: 100条数据");
                Call<HomePagerContent> homePagerCategories = getHomePagerContentCall();
                homePagerCategories.enqueue(new Callback<HomePagerContent>() {
                    @Override
                    public void onResponse(Call<HomePagerContent> call, Response<HomePagerContent> response) {
                        int code = response.code();
                        if (code == HttpsURLConnection.HTTP_OK) {
                            HomePagerContent body = response.body();
                            List<ContentList> contentLists1 = new ArrayList<>();

                            for (int i = 0; i < 8; i++) {
                                if (body.getData().getList().get(i) == null) {
                                    onLoaderMoreEmpty(1);
                                    return;
                                } else {
                                    contentLists1.add(body.getData().getList().get(i));
                                    homePagerContentAdapter.addData(contentLists1);
                                }

                            }
                            twinklingRefreshLayout.finishLoadmore();
                            ToastUtil.showToast("完成加载");
                        }


                    }

                    @Override
                    public void onFailure(Call<HomePagerContent> call, Throwable throwable) {

                    }
                });
            }
        });
    }

    public static HomePagerFragement newInstance(Data data) {
        HomePagerFragement homePagerFragement = new HomePagerFragement();
        Bundle bundle = new Bundle();
        bundle.putInt("Id", data.getId());
        bundle.putString("Title", data.getTitle());
        homePagerFragement.setArguments(bundle);
        return homePagerFragement;
    }

    @Override
    protected void initPresenter() {
        categoryPagerPresenter = CategoryPagerPresenterImpl.getInstance();
        categoryPagerPresenter.registerViewCallback(this);
    }

    @Override
    protected int serach_Inflater() {
        return R.layout.fragment_home_pager_fragement;
    }

    @Override
    protected void initView() {
        setUpState(State.SUCCESS);
    }

    @Override
    protected void loadData() {//进行数据的加载
        Looperlist.clear();
        viewList.clear();
        Bundle bundle = getArguments();
        String title = bundle.getString("Title");
        int id = bundle.getInt("Id");
        Log.d("TAG", "loadData:   title为" + title);
        Log.d("TAG", "loadData:   title为" + id);

        //根据分类id来加载内容
        Call<HomePagerContent> homePagerCategories = getHomePagerContentCall();
        homePagerCategories.enqueue(new Callback<HomePagerContent>() {
            @Override
            public void onResponse(Call<HomePagerContent> call, Response<HomePagerContent> response) {
                if (title != null) {
                    textView.setText(title);
                }
                int code = response.code();
                if (code == HttpsURLConnection.HTTP_OK) {
                    //TODO 把recycleview写好
                    HomePagerContent homePagerContent = response.body();
                    for (int i = 0; i < 15; i++) {
                        list.add(homePagerContent.getData().getList().get(i));
                    }
                    for (int i = 10; i < 16; i++) {
                        Looperlist.add(homePagerContent.getData().getList().get(i));
                        Log.d("TAG", "onResponse: " + homePagerContent.getData().getList().get(i).toString());
                    }
                    extracted();
                    // 检查并移除已有父视图的视图

                    for (int j = 0; j < viewList.size(); j++) {
                        ViewGroup parent = (ViewGroup) viewList.get(j).getParent();
                        if (parent != null) {
                            parent.removeView(viewList.get(j));  // 如果已经有父视图，先移除
                        }
                        linearLayout.addView(viewList.get(j));
                    }
                    getActivity().runOnUiThread(() -> {

                        contentLists.clear();
                        contentLists.addAll(list);
                        homePagerContentAdapter.notifyDataSetChanged();
                    });
                    initlistenerPager(viewPager_looper);

                }
            }

            public void onFailure(Call<HomePagerContent> call, Throwable throwable) {

            }
        });
    }

    private static Call<HomePagerContent> getHomePagerContentCall() {
        Service service = CategoryRetrofit.getClient().create(Service.class);
        String createCategory = "shop/m/1/84229";
        Call<HomePagerContent> homePagerCategories = service.getHomePagerCategories(createCategory);
        return homePagerCategories;
    }

    private void initlistenerPager(ViewPager viewPager_looper) {
        viewPager_looper.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int targetPosition = position % 6;
                Log.d("TAG3", "onPageSelected: ");
                //切换指示器
                upDataChangeIndicator(targetPosition);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void initListener() {
        looperPagerAdapter.setLooperData(this);
        homePagerContentAdapter.setOnItemClickListener(this);
    }

    private void upDataChangeIndicator(int targetPosition) {
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            View view = linearLayout.getChildAt(i);
            if (i == targetPosition) {
                view.setBackground(getContext().getDrawable(R.drawable.shape_indicater));
            } else {
                view.setBackground(getContext().getDrawable(R.drawable.shape_indicator_non));
            }
        }
    }


    @Override
    public void onContentLoaded(List<ContentData> contents) {

    }

    @Override
    public void onLoading(int categoryId) {

    }

    @Override
    public void onError(int categoryId) {

    }

    @Override
    public void onEmpty(int categoryId) {

    }

    @Override
    public void onLoaderMoreError(int categoryId) {

    }

    @Override
    public void onLoaderMoreEmpty(int categoryId) {
    }

    @Override
    public void onLoaderMoreLoading(List<ContentData> contents) {

    }

    @Override
    public void onLooperListLoaded(List<ContentData> contents) {

    }

    @Override
    protected void release() {
        if (categoryPagerPresenter != null) {
            categoryPagerPresenter.unregisterViewCallback(this);
        }
    }

    @Override
    public void OnItemListener(ContentList contentList) {
        // LogUtils.d(getClass(), contentList.getTitle());
        String url = contentList.getCover();
        String contentUrl = contentList.getCouponShareUrl();
        contentUrl = "https:" + contentUrl;
        // LogUtils.d(getClass(), " contentUrl--->" + contentUrl);
        handleItemOnClick(url, contentUrl);
    }

    private void handleItemOnClick(String url, String contentUrl) {
        String baseurl = url;
        Intent intent = new Intent(getActivity(), TicketActivity.class);
        if (baseurl.startsWith("//")) {
            baseurl = "https:" + baseurl; // 通常使用 https
        }
        Bundle bundle = new Bundle();
        bundle.putString("url", baseurl);
        bundle.putString("contentUrl", contentUrl);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void OnLooperItemListener(ContentList contentList) {
        // LogUtils.d(getClass(), contentList.getTitle());
        String url = contentList.getCover();
        String contentUrl = contentList.getCouponShareUrl();
        contentUrl = "https:" + contentUrl;
        //LogUtils.d(getClass(), " contentUrl--->" + contentUrl);
        handleItemOnClick(url, contentUrl);

    }

}