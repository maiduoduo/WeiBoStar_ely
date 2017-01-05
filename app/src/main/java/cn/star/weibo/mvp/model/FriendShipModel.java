package cn.star.weibo.mvp.model;

import android.content.Context;

import cn.star.weibo.bean.User;


/**
 * Created by dcl on 16/11/6.
 */
public interface FriendShipModel {

    interface OnRequestListener {
        void onSuccess();

        void onError(String error);
    }

    public void user_destroy(User user, Context context, OnRequestListener onRequestListener, boolean updateCache);

    public void user_create(User user, Context context, OnRequestListener onRequestListener, boolean updateCache);

}
