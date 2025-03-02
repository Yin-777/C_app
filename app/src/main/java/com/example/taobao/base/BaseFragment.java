package com.example.taobao.base;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.state.State;
import androidx.fragment.app.Fragment;

import com.example.taobao.R;
import com.example.taobao.presenter.tabPresenter;
import com.example.taobao.ui.custom.LoadingView;
import com.example.taobao.utils.LogUtils;
import com.example.taobao.utils.ToastUtil;

public abstract class BaseFragment extends Fragment {//Fragment的基类
    private FrameLayout mBaseContainer;
    private ImageView imageView_error;
    private View successView, loadingView, loadingErroView, loadingEmptyView;
    private State currentState = State.NONE;//初始状态为无


    //定义了五种状态为无,加载，成功，失败，空
    public enum State {
        NONE, LOADING, SUCCESS, ERROR, EMPTY
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //单独开了一个framelayout的页面来展示其的状态
        View rootView = Loading(inflater, container);
        mBaseContainer = rootView.findViewById(R.id.base_container);//拿到了framelayout
        loadStateView(inflater, container);
        initview_img(loadingErroView);
        initView();
        return rootView;
    }

    private void initview_img(View rootView) {
        imageView_error = rootView.findViewById(R.id.error_img);
        imageView_error.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrotab();//实现网络请求
            }


        });
    }

    //设置监听事件
    protected void initListener() {


    }

    protected void release() {


    }

    protected void initPresenter() {
        //创建Presenter

    }

    //写出一个protected方法，可以让其他的类来重写其的方法
    protected void retrotab() {

    }

    ;

    //记载数据
    protected void loadData() {
//加载
    }

    ;

    protected View Loading(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.base_fragment_layout, container, false);
    }

    //加载各种状态的View，将四种状态的view加载到framelayout中去，注意参数要加false
    /*将某个子 View 添加到一个新的 ViewGroup 中，但这个
    子 View 已经被添加到另一个父布局中了。Android 不允许同一个 View 同时存在于多个父布局中
    因此会抛出 IllegalStateException 错误。
     */
    protected void loadStateView(LayoutInflater inflater, ViewGroup container) {

        // 成功的 View
        successView = SuccessView(inflater, container);
        if (successView.getParent() != null) {
            ((ViewGroup) successView.getParent()).removeView(successView);

        }
        mBaseContainer.addView(successView);

        // 加载中的 View
        loadingView = LoadingView(inflater, container);
        if (loadingView.getParent() != null) {
            ((ViewGroup) loadingView.getParent()).removeView(loadingView);
        }
        mBaseContainer.addView(loadingView);

        // 网络请求失败的 View
        loadingErroView = LoadingErroView(inflater, container);
        if (loadingErroView.getParent() != null) {
            ((ViewGroup) loadingErroView.getParent()).removeView(loadingErroView);
        }
        mBaseContainer.addView(loadingErroView);

        // 空的 View
        loadingEmptyView = LoadingEmptyView(inflater, container);
        if (loadingEmptyView.getParent() != null) {
            ((ViewGroup) loadingEmptyView.getParent()).removeView(loadingEmptyView);
        }
        mBaseContainer.addView(loadingEmptyView);
        setUpState(State.NONE);
    }

    protected void initView() {

    }


    //子类通过这个页面来切换状态即可
    public void setUpState(State currentStates) {
        this.currentState = currentStates;
        if (currentState == State.SUCCESS) {
            successView.setVisibility(View.VISIBLE);
        } else {
            successView.setVisibility(View.GONE);
        }
        if (currentState == State.LOADING) {
            loadingView.setVisibility(View.VISIBLE);
        } else {
            loadingView.setVisibility(View.GONE);
        }
        if (currentState == State.ERROR) {
            loadingErroView.setVisibility(View.VISIBLE);
        } else {
            loadingErroView.setVisibility(View.GONE);
        }
        if (currentState == State.EMPTY) {
            loadingEmptyView.setVisibility(View.VISIBLE);
        } else {
            loadingEmptyView.setVisibility(View.GONE);
        }
    }

    protected View SuccessView(LayoutInflater inflater, ViewGroup container) {
        int Inflater = serach_Inflater();
        return inflater.inflate(Inflater, container, false);

    }

    //空的
    protected View LoadingEmptyView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_loadingempty_layout, container, false);
    }

    //失败
    protected View LoadingErroView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_loadingerro_layout, container, false);
    }

    //加载
    protected View LoadingView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_loading_layout, container, false);
    }

    protected abstract int serach_Inflater();
}
