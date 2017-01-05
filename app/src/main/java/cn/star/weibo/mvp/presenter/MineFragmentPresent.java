package cn.star.weibo.mvp.presenter;

import android.content.Context;

/**
 * User: dcl
 * Method/Class Name:MineFragmentPresent
 * Date: 2016-11-16
 * About:我的界面刷新数据方法封装
 * param:
 * FIXME
 */
public interface MineFragmentPresent {
    public void refreshUserDetail(long uid, Context context, boolean loadicon);
}
