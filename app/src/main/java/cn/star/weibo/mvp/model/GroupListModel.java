package cn.star.weibo.mvp.model;

import android.content.Context;


import java.util.ArrayList;

import cn.star.weibo.bean.Group;

/**
 * Created by dash on 16/5/14.
 */
public interface GroupListModel {

    interface OnGroupListFinishedListener {
        void noMoreDate();

        void onDataFinish(ArrayList<Group> groupslist);

        void onError(String error);
    }


    public void groupsOnlyOnce(Context context, OnGroupListFinishedListener onDataFinishedListener);

    public void cacheLoad(Context context, OnGroupListFinishedListener onGroupListFinishedListener);

    public void cacheSave(Context context, String response);


}
