package cn.star.weibo.mvp.model;

import android.content.Context;


import java.util.ArrayList;

import cn.star.weibo.bean.Comment;
import cn.star.weibo.bean.list.CommentList;

/**
 */
public interface CommentModel {

    interface OnDataFinishedListener {
        void noMoreDate();

        void onDataFinish(ArrayList<Comment> commentlist);

        void onError(String error);
    }


    public void toMe(int groupType, Context context, OnDataFinishedListener onDataFinishedListener);

    public void toMeNextPage(int groupType, Context context, OnDataFinishedListener onDataFinishedListener);

    public void byMe(Context context, OnDataFinishedListener onDataFinishedListener);

    public void byMeNextPage(Context context, OnDataFinishedListener onDataFinishedListener);

    public void cacheSave(int groupType, Context context, CommentList commentList);

    public void cacheLoad(int groupType, Context context, OnDataFinishedListener onDataFinishedListener);

}
