package com.lgh.wine.ui.personal.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lgh.wine.R;
import com.lgh.wine.beans.AddressBean;
import com.lgh.wine.beans.CommentBean;
import com.lgh.wine.utils.BaseRecyclerAdapter;
import com.lgh.wine.utils.Constant;
import com.lgh.wine.utils.GlideHelper;

import org.w3c.dom.Comment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by niujingtong on 2018/7/16.
 * 模块：
 */
public class CommentAdapter extends BaseRecyclerAdapter<CommentBean, CommentAdapter.ViewHolder> {
    public OnChildClickListener onChildClickListener;

    public void setOnChildClickListener(OnChildClickListener onChildClickListener) {
        this.onChildClickListener = onChildClickListener;
    }

    public CommentAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        CommentBean item = getItem(position);
        holder.tvName.setText(item.getGoods_name());
        holder.tvContent.setText(item.getComment_content());
        GlideHelper.loadImage(mContext, holder.iv_pic, Constant.IMG_IP + item.getGoods_pic());

        holder.tvLook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onChildClickListener != null)
                    onChildClickListener.onLookClick(holder.getAdapterPosition());
            }
        });

    }

    @Override
    protected boolean useItemAnimation() {
        return true;
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_name)
        TextView tvName;

        @BindView(R.id.tv_look)
        TextView tvLook;

        @BindView(R.id.iv_pic)
        ImageView iv_pic;

        @BindView(R.id.tv_content)
        TextView tvContent;

        @BindView(R.id.tv_time)
        TextView tvTime;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnChildClickListener {
        void onLookClick(int po);
    }
}
