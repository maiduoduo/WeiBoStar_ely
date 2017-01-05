package cn.star.weibo.login.home.activity;

import android.view.View;
import android.widget.LinearLayout;

import cn.star.weibo.base.BaseActivity;
import cn.star.weibo.login.home.widget.OriginPicTextHeaderView;
import cn.star.weibo.utils.RecyclerViewUtils;


/**
 * User: dcl
 * Method/Class Name:OriginDetailActivity
 * Date: 2016-12-06
 * About:首页点击内容的详情页面
 * param:
 * FIXME
 */


public class OriginDetailActivity extends BaseDetailActivity {
    public LinearLayout mHeaderView;

    @Override
    protected void addHeaderView(int type) {
        mHeaderView = new OriginPicTextHeaderView(mContext, mStatus, type);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mHeaderView.setLayoutParams(layoutParams);
        ((OriginPicTextHeaderView) mHeaderView).setOnDetailButtonClickListener(onDetailButtonClickListener);
        RecyclerViewUtils.setHeaderView(mRecyclerView, mHeaderView);
    }

    @Override
    protected int getHeaderViewHeight() {
        return mHeaderView.getHeight();
    }

    @Override
    protected void refreshDetailBar(int comments, int reposts, int attitudes) {
        ((OriginPicTextHeaderView) mHeaderView).refreshDetailBar(comments, reposts, attitudes);
    }


}
