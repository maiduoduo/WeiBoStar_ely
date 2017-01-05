package cn.star.weibo.login.home.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;

import cn.star.weibo.R;
import cn.star.weibo.bean.Comment;
import cn.star.weibo.bean.User;
import cn.star.weibo.common.FillContent;
import cn.star.weibo.view.widget.EmojiTextView;


/**
 * 用于显示评论列表的adapter
 */
public class CommentDetailAdapter extends RecyclerView.Adapter<ViewHolder> {

    private Context mContext;
    private ArrayList<Comment> mDatas;
    private View mView;


    public CommentDetailAdapter(Context mContext, ArrayList<Comment> datas) {
        this.mContext = mContext;
        this.mDatas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mView = LayoutInflater.from(mContext).inflate(R.layout.item_home_weibo_detail_commentbar_comment, parent, false);
        CommentViewHolder commentViewHolder = new CommentViewHolder(mView);
        return commentViewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User user = mDatas.get(position).user;
        String content = mDatas.get(position).text;
        FillContent.fillProfileImg(mContext, user, ((CommentViewHolder) holder).profile_img, ((CommentViewHolder) holder).profile_verified);
        FillContent.fillWeiBoContent(content, mContext, ((CommentViewHolder) holder).content);
        FillContent.setWeiBoName(((CommentViewHolder) holder).profile_name, user);
        FillContent.setWeiBoTime(mContext, ((CommentViewHolder) holder).profile_time, mDatas.get(position));
    }


    @Override
    public int getItemCount() {
        if (mDatas != null) {
            return mDatas.size();
        } else {
            return 0;
        }
    }

    public void setData(ArrayList<Comment> data) {
        this.mDatas = data;
    }

    public class CommentViewHolder extends ViewHolder {
        //微博列表的控件
        public ImageView profile_img;
        public ImageView profile_verified;
        public TextView profile_name;
        public TextView profile_time;
        public EmojiTextView content;

        public CommentViewHolder(View v) {
            super(v);
            profile_img = (ImageView) v.findViewById(R.id.mine_img);
            profile_verified = (ImageView) v.findViewById(R.id.mine_verified);
            profile_name = (TextView) v.findViewById(R.id.comment_profile_name);
            profile_time = (TextView) v.findViewById(R.id.comment_profile_time);
            content = (EmojiTextView) v.findViewById(R.id.comment_content);
        }
    }


}
