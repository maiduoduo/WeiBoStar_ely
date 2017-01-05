package cn.star.weibo.mvp.presenter;

import android.content.Context;

import cn.star.weibo.bean.Status;


/**
 * Created by dash on 16/6/26.
 */
public interface DetailActivityPresent {
    public void pullToRefreshData(int groupId, Status status, Context context);

    public void requestMoreData(int groupId, Status status, Context context);
}
