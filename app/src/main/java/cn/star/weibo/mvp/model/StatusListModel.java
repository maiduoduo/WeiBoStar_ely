package cn.star.weibo.mvp.model;

import android.content.Context;


import java.util.ArrayList;

import cn.star.weibo.bean.Status;
import cn.star.weibo.bean.list.StatusList;

/**
 * User: dcl
 * Method/Class Name:StatusListModel
 * Date: 2016-11-18
 * About:
 * param:
 * FIXME
 */
public interface StatusListModel {

    interface OnDataFinishedListener {
        void noMoreData();

        void noDataInFirstLoad(String error);

        void onDataFinish(ArrayList<Status> statuslist);

        void onError(String error);
    }

    interface OnRequestListener {
        void onSuccess();

        void onError(String error);
    }

    public void timeline(long groundId, Context context, OnDataFinishedListener onDataFinishedListener);

    public void friendsTimeline(Context context, OnDataFinishedListener onDataFinishedListener);

    public void bilateralTimeline(Context context, OnDataFinishedListener onDataFinishedListener);

    public void weibo_destroy(long id, Context context, OnRequestListener onRequestListener);

    public void friendsTimelineNextPage(Context context, OnDataFinishedListener onDataFinishedListener);

    public void bilateralTimelineNextPage(Context context, OnDataFinishedListener onDataFinishedListener);

    public void timelineNextPage(long groundId, Context context, OnDataFinishedListener onDataFinishedListener);

    public void setRefrshFriendsTimelineTask();

    public void cancelTimer();

    public boolean cacheLoad(long groupType, Context context, OnDataFinishedListener onDataFinishedListener);

    public void cacheSave(long groupType, Context context, StatusList statusList);


}
