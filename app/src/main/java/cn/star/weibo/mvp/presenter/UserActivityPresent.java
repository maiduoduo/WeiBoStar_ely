package cn.star.weibo.mvp.presenter;

import android.content.Context;

/**
 * Created by dash on 2016/6/28.
 */
public interface UserActivityPresent {

    public void pullToRefreshData(String refreshType, String screenName, Context context);

    public void requestMoreData(String refreshType, String screenName, Context context);


}
