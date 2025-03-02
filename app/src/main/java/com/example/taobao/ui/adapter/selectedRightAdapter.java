package com.example.taobao.ui.adapter;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.taobao.R;
import com.example.taobao.utils.LogUtils;


import java.util.ArrayList;
import java.util.List;

public class selectedRightAdapter extends RecyclerView.Adapter<selectedRightAdapter.MyViewHolder> {
    private List<String> img_list, price_list, cut_list, title_list, contentUrl_list;
    private Context context;
    private LayoutInflater layoutInflater;
    private selectedRightInter select = null;

    public selectedRightAdapter(List<String> list, List<String> price_list, List<String> cut_list, List<String> title_list, List<String> contentUrl_list, Context context) {
        this.img_list = list;
        this.title_list = title_list;
        this.cut_list = cut_list;
        this.contentUrl_list = contentUrl_list;
        this.price_list = price_list;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_selected_pager_right, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public int getItemCount() {
        return img_list.size();
    }

    public void refresh(List<String> img_list, List<String> title_list, List<String> price_list, List<String> cut_list, List<String> content_url) {
        if (this.img_list != null && this.title_list != null && this.price_list != null && this.cut_list != null && this.contentUrl_list != null) {
            this.img_list.clear();
            this.contentUrl_list.clear();
            ;
            this.price_list.clear();
            this.cut_list.clear();
            this.title_list.clear();
        }
        this.contentUrl_list = content_url;
        this.img_list = img_list;
        this.title_list = title_list;
        this.cut_list = cut_list;
        this.price_list = price_list;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String url = img_list.get(position);
        if (url.startsWith("//")) {
            url = "https:" + url; // 通常使用 https
        }
//        ViewGroup.LayoutParams layoutParams = holder.imageView.getLayoutParams();
//        int height = layoutParams.height;
//        int width = layoutParams.width;
//        int sizeCover = (Math.max(height, width)) / 2;
//        String result_url =url + "_" + sizeCover + "x" + sizeCover + ".jpg";
        Glide.with(context).load(url).into(holder.imageView);
        String contentUrl = contentUrl_list.get(position);
        if (contentUrl.startsWith("//")) {
            contentUrl = "https:" + contentUrl; // 通常使用 https
        }
        String title = title_list.get(position);
        holder.textView_title.setText(title);
        String cut = cut_list.get(position);
        int cutprice = (int) Double.parseDouble(cut);
        String c = String.valueOf(cutprice);
        c = "领券省" + c + "元";
        if (cutprice == 0) {
            holder.textView_cut.setVisibility(View.GONE);
            holder.textView_price.setText("晚啦，没有优惠券了");
        } else {
            holder.textView_cut.setText(c);
            String price = price_list.get(position);
            price = "原价 : " + price + "元";
            holder.textView_price.setText(price);
        }
        String finalContentUrl = contentUrl;
        String finalUrl = url;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (select != null) {
                    select.setTileText(finalContentUrl, finalUrl);
                }

            }
        });

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView_title, textView_cut, textView_price;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_img_right);
            textView_title = itemView.findViewById(R.id.selected_right_text);
            textView_cut = itemView.findViewById(R.id.selected_right_cut);
            textView_price = itemView.findViewById(R.id.selected_right_price);
        }
    }

    public void setselected(selectedRightInter selectedRightInter) {
        this.select = selectedRightInter;
    }

    public interface selectedRightInter {
        void setTileText(String contentUrl, String img);
    }
}
