package com.example.taobao.ui.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.taobao.R;
import com.example.taobao.base.BaseActivity;
import com.example.taobao.model.Retrofit.tabRetrofit;
import com.example.taobao.model.Service;
import com.example.taobao.model.bean.tb_root;
import com.example.taobao.ui.fragment.fraFragement.HomePagerFragement;
import com.example.taobao.utils.LogUtils;
import com.example.taobao.utils.ToastUtil;
import com.google.gson.Gson;

import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TicketActivity extends BaseActivity {
    private ImageView imageView_back, imageView_show;
    private Button button_show;
    private TextView textView;
    private String talking;
    private boolean mHasTaBaoApp = false;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_ticket;
    }

    @Override
    protected void initView() {
        textView = findViewById(R.id.text_show);
        button_show = findViewById(R.id.show_button);
        imageView_back = findViewById(R.id.tao_img_back);
        imageView_show = findViewById(R.id.tao_img);
    }

    @Override
    protected void dataSolve() {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if (bundle!=null){
                String url = bundle.getString("url");
                LogUtils.d(getClass(), url);
                Glide.with(this).load(url).into(imageView_show);
                String contentUrl = bundle.getString("contentUrl");
                //  LogUtils.d(getClass(), contentUrl);
                //进行网络请求
                showApi(contentUrl);
            }

        }
        //判断是否有安装淘宝
        //找到淘宝的包名为com.taobao.taobao
        PackageManager pm = getPackageManager();
        try {
            PackageInfo packageInfo = pm.getPackageInfo("com.taobao.taobao", 0);
            mHasTaBaoApp = packageInfo != null;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            mHasTaBaoApp = false;
        }
        LogUtils.d(getClass(), "mHasTaBaoApp---> " + mHasTaBaoApp);
        //根据这个值去修改ui
        button_show.setText(mHasTaBaoApp ? "打开淘宝领券" : "复制淘口令");
        //运用Clipboard粘贴版，点击按钮后将文本内容进行复制
    }

    private void showApi(String contentUrl) {
        HashMap<String, String> map = new HashMap<>();
        map.put("url", contentUrl);
        Gson gson = new Gson();
//需要把数据转成json类型的
        String str = gson.toJson(map);
        // LogUtils.d(getClass(), "str--->" + str);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), str);
        Service service = tabRetrofit.getClient().create(Service.class);
        Call<tb_root> call = service.getword(requestBody);
        call.enqueue(new Callback<tb_root>() {
            @Override
            public void onResponse(Call<tb_root> call, Response<tb_root> response) {
                tb_root body = response.body();
                talking = body.getData().getTbk_tpwd_create_response().getData().getModel();
                TicketActivity.this.runOnUiThread(() -> {//网络请求子线程放回到主线程，进行ui操作
                    textView.setText(talking);

                });
            }

            @Override
            public void onFailure(Call<tb_root> call, Throwable throwable) {
                LogUtils.d(getClass(), throwable.toString());
            }
        });
    }

    @Override
    protected void initListener() {
        button_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                if (talking != null) {
                    ClipData clipData = ClipData.newPlainText("label", talking);//想要复制的数据
                    clipboardManager.setPrimaryClip(clipData);
                    LogUtils.d(getClass(), talking);
                    ToastUtil.showToast("复制淘口令成功");
                    //利用包名来进行跳转到相应的app里面
                    Intent intent = getPackageManager().getLaunchIntentForPackage("com.taobao.taobao"); // 替换为目标应用的包名
                    if (intent != null) {
                        startActivity(intent); // 启动目标应用
                    } else {
                      ToastUtil.showToast("未安装淘宝应用");
                    }
                }


                //判断是否有淘宝
                if (mHasTaBaoApp) {//有则跳转界面

                } else {//没有就提示
                    ToastUtil.showToast("淘口令已成功复制，快去打开淘宝吧!");
                }
            }
        });
        imageView_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        imageView_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
