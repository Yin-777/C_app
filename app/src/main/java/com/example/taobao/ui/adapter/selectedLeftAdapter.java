package com.example.taobao.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taobao.R;

import java.util.ArrayList;
import java.util.List;

public class selectedLeftAdapter extends RecyclerView.Adapter<selectedLeftAdapter.RecycleViewHolder> {
    private int currentColor = 0;
    private selectedInter selectedInt = null;

    public selectedLeftAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    private List<String> list;

    private Context context;
    private LayoutInflater layoutInflater;



    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_selected_page_left, parent, false);
        RecycleViewHolder recycleViewHolder = new RecycleViewHolder(view);
        return recycleViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder holder, @SuppressLint("RecyclerView") int position) {
        String text = list.get(position);
        holder.textView.setText(text);
        if (currentColor == position) {
            holder.textView.setBackgroundColor(Color.parseColor("#EFEEEE"));
        } else {
            holder.textView.setBackgroundColor(Color.parseColor("#ffffff"));
        }

        String text1 = list.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentColor != position && selectedInt != null) {
                    currentColor = position;
                    selectedInt.selected(text1);
                    notifyDataSetChanged();
                }
            }
        });

    }

    public void refresh(List<String> mlist) {
        if (this.list!=null){
            this.list.clear();
        }
        this.list = mlist;
        notifyDataSetChanged();
        if (list.size()>0){
            selectedInt.selected(list.get(currentColor));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RecycleViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public RecycleViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_selected_left);
        }
    }

    public void interSelectedInter(selectedInter selectedInter) {
        this.selectedInt = selectedInter;
    }

    public interface selectedInter {
        void selected(String text);
    }
}
