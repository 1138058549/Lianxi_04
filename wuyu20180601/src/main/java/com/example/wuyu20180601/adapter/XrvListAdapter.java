package com.example.wuyu20180601.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wuyu20180601.R;
import com.example.wuyu20180601.ShowActivity;
import com.example.wuyu20180601.bean.AdBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class XrvListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<AdBean.ResultBean.DataBean> list;
    private LayoutInflater inflater;

    private OnListItemClickListener onListItemClickListener;

    public interface OnListItemClickListener {
        void OnItemClick(AdBean dataBean);
    }

    public XrvListAdapter(Context context, List<AdBean.ResultBean.DataBean> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    public void setOnListItemClickListener(OnListItemClickListener onListItemClickListener) {
        this.onListItemClickListener = onListItemClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.rvlist_item, parent, false);
        XlvViewHolder xlvViewHolder = new XlvViewHolder(view);
        return xlvViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final XlvViewHolder xlvViewHolder = (XlvViewHolder) holder;
        final AdBean.ResultBean.DataBean dataBean = list.get(position);
       /* List<String> s=dataBean.getAlbums();
        String s1 = s.get(position);*/
        xlvViewHolder.iv.setImageURI(dataBean.getAlbums().get(0));
        xlvViewHolder.tvTitle.setText(dataBean.getTitle());
        xlvViewHolder.tvPrice.setText(dataBean.getTags());

        //设置条目监听
        xlvViewHolder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShowActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class XlvViewHolder extends RecyclerView.ViewHolder {

        private final LinearLayout ll;
        private final SimpleDraweeView iv;
        private final TextView tvTitle;
        private final TextView tvPrice;

        public XlvViewHolder(View itemView) {
            super(itemView);
            ll = itemView.findViewById(R.id.ll);
            iv = itemView.findViewById(R.id.iv);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvPrice = itemView.findViewById(R.id.tvPrice);
        }
    }



}
