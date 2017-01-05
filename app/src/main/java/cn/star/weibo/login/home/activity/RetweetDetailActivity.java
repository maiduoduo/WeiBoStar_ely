package cn.star.weibo.login.home.activity;

import android.widget.LinearLayout;

import cn.star.weibo.login.home.widget.RetweetPicTextHeaderView;
import cn.star.weibo.utils.RecyclerViewUtils;


/**
 * User: dcl
 * Method/Class Name:RetweetDetailActivity
 * Date: 2016-12-06
 * About:微博背景的点击事件的详情页
 * param:
 * FIXME
 */


public class RetweetDetailActivity extends BaseDetailActivity {
    public LinearLayout mHeaderView;

    @Override
    protected void addHeaderView(int type) {
        mHeaderView = new RetweetPicTextHeaderView(mContext, mStatus, type);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mHeaderView.setLayoutParams(layoutParams);
        ((RetweetPicTextHeaderView) mHeaderView).setOnDetailButtonClickListener(onDetailButtonClickListener);
        RecyclerViewUtils.setHeaderView(mRecyclerView, mHeaderView);
    }

    @Override
    protected int getHeaderViewHeight() {
        return mHeaderView.getHeight();
    }

    @Override
    protected void refreshDetailBar(int comments, int reposts, int attitudes) {
        ((RetweetPicTextHeaderView) mHeaderView).refreshDetailBar(comments, reposts, attitudes);
    }

}
