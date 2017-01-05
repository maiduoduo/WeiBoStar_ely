package cn.star.weibo.mvp.presenter;

import android.content.Context;

/**
 * User: dcl
 * Method/Class Name:HomeFragmentPresent
 * Date: 2016-11-24
 * About:首页数据加载方法封装
 * param:
 * FIXME
 */
public interface HomeFragmentPresent {

    public void refreshUserName(Context context);

    public void firstLoadData(Context context, boolean firstStart);

    public void pullToRefreshData(long groupId, Context context);

    public void requestMoreData(long groupId, Context context);

    public void cancelTimer();

}
