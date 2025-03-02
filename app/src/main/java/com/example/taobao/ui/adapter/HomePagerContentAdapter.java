package com.example.taobao.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.taobao.R;
import com.example.taobao.model.bean.ContentList;
import com.example.taobao.ui.fragment.fraFragement.HomePagerFragement;
import com.example.taobao.utils.LogUtils;

import java.util.List;

public class HomePagerContentAdapter extends RecyclerView.Adapter<HomePagerContentAdapter.RecycleViewHolder> {
    private List<ContentList> list;

    private LayoutInflater inflater;

    private OnClickListener mItemClickListener = null;

    public HomePagerContentAdapter(List<ContentList> mlist, Context context) {
        list = mlist;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    private Context context;


    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_home_pager_content, parent, false);
        RecycleViewHolder recycleViewHolder = new RecycleViewHolder(view);
        return recycleViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ContentList contentList = (ContentList) list.get(position);
        String baseUrl = contentList.getCover();
// 检查是否包含协议，补全协议
        if (baseUrl.startsWith("//")) {
            baseUrl = "https:" + baseUrl; // 通常使用 https
        }
        ViewGroup.LayoutParams layoutParams = holder.imageView.getLayoutParams();
        int height = layoutParams.height;
        int width = layoutParams.width;
        int sizeCover = (Math.max(height, width)) / 2;
        String result_url = baseUrl + "_" + sizeCover + "x" + sizeCover + ".jpg";
        // LogUtils.d(getClass(), result_url);
        Glide.with(context)
                .load(result_url)
                .into(holder.imageView);

        holder.textView_title.setText(contentList.getTitle());
        String offCut = "省" + contentList.getZkFinalPrice() + "元";
        holder.textView_off.setText(offCut);
        String price = contentList.getZkFinalPrice() + "元";
        holder.textView_price.setText(price);
        holder.textView_cut.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        holder.textView_cut.setText(contentList.getJustPrice() + "元");
        holder.textView_num.setText("1000人买");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) {
                    ContentList mcontentList = (ContentList) list.get(position);
                    mItemClickListener.OnItemListener(mcontentList);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addData(List<ContentList> contentLists1) {
        int size = list.size();

        list.addAll(contentLists1);

        notifyItemRangeChanged(size, contentLists1.size());
    }


    public class RecycleViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        private TextView textView_title, textView_off, textView_price, textView_cut, textView_num;

        public RecycleViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.goods_img);
            textView_title = itemView.findViewById(R.id.goods_title);
            textView_off = itemView.findViewById(R.id.off_price);
            textView_price = itemView.findViewById(R.id.goods_offPrice);
            textView_cut = itemView.findViewById(R.id.goods_cutPrice);
            textView_num = itemView.findViewById(R.id.goods_num);
        }
    }

    public void setOnItemClickListener(OnClickListener listener) {
        this.mItemClickListener = listener;
    }


    public interface OnClickListener {
        void OnItemListener(ContentList contentList);
    }
}
