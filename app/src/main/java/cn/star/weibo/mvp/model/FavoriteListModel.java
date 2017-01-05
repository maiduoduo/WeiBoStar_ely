package cn.star.weibo.mvp.model;

import android.content.Context;


import java.util.ArrayList;

import cn.star.weibo.bean.Status;

/**
 * Created by dcl on 16/11/6.
 */
public interface FavoriteListModel {

    interface OnRequestUIListener {
        void onSuccess();

        void onError(String error);
    }

    interface OnDataFinishedListener {
        void noMoreDate();

        void onDataFinish(ArrayList<Status> statuslist);

        void onError(String error);

    }


    public void createFavorite(Status status, Context context, OnRequestUIListener onRequestUIListener);

    public void cancelFavorite(Status status, Context context, OnRequestUIListener onRequestUIListener);

    public void favorites(Context context, OnDataFinishedListener onDataFinishedListener);

    public void favoritesNextPage(Context context, OnDataFinishedListener onDataFinishedListener);

    public void cacheSave(Context context, String response);

    public void cacheLoad(Context context, OnDataFinishedListener onDataFinishedListener);

}
