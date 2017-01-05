package cn.star.weibo.login.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.star.weibo.R;
import cn.star.weibo.login.mine.bean.WeiboBean;
import cn.star.weibo.view.widget.EmojiTextView;

/**
 * User: dcl
 * Method/Class Name:UserHomeFragmentAdapter
 * Date: 2016-12-03
 * About:
 * param:
 * FIXME
 */


public class UserWeiboFragmentAdapter extends RecyclerView.Adapter<UserWeiboFragmentAdapter.ViewHolder>  {
    private Context context;
    private List<WeiboBean> weibos;
    private TextView tvName;



    public UserWeiboFragmentAdapter(Context context, List<WeiboBean> weibos) {
        this.context = context;
        this.weibos = weibos;
    }
    @Override
    public UserWeiboFragmentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_mine_user_detail_weibo,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(UserWeiboFragmentAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return weibos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        View view;
        ImageView ivIcon;
//        TextView tvName;
        TextView tvDate;
        TextView tvFrom;
        TextView tvdiscuss;
        TextView tvAite;
        TextView tvContent;
        ImageView ivContent;

        public ViewHolder(View itemView) {
            super(itemView);

            view = itemView.findViewById(R.id.item_mine_user_weibo);


            ivIcon = (ImageView) itemView.findViewById(R.id.mine_img);
            tvName = (TextView) itemView.findViewById(R.id.mine_user_name);
            tvDate = (TextView) itemView.findViewById(R.id.mine_user_detail_time);

            tvFrom = (TextView) itemView.findViewById(R.id.mine_user_detail_from);

            tvContent = (EmojiTextView) itemView.findViewById(R.id.mention_content);


        }
    }


}
