package com.example.taobao.ui.fragment.MainFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.taobao.R;
import com.example.taobao.base.BaseFragment;


public class SearchFragment extends BaseFragment {


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpState(State.SUCCESS);
    }

    @Override
    protected int serach_Inflater() {
        return R.layout.fragment_search;
    }
}