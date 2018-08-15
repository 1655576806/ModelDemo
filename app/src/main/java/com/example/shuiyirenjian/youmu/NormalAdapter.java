package com.example.shuiyirenjian.youmu;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class NormalAdapter  extends RecyclerView.Adapter<NormalAdapter.VH> {
    //② 创建ViewHolder
    public static class VH extends RecyclerView.ViewHolder{
        public final TextView title;
        public VH(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.tv_item);
        }
    }
    public void addData(int position) {
        mDatas.add(position, "Insert One");
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        if(mDatas!=null){
            mDatas.remove(position);
            notifyItemRemoved(position);
        }

    }
    private List<String> mDatas;
    public NormalAdapter(List<String> data) {
        this.mDatas = data;
    }
    @NonNull
    @Override
    public NormalAdapter.VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_1, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NormalAdapter.VH holder, int position) {
        holder.title.setText(mDatas.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //item 点击事件
            }
        });


    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}
