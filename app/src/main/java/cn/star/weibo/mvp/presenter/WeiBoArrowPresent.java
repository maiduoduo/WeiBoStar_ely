package cn.star.weibo.mvp.presenter;

import android.content.Context;

import cn.star.weibo.bean.Status;
import cn.star.weibo.bean.User;


/**
 * User: dcl
 * Method/Class Name:WeiBoArrowPresent
 * Date: 2016-11-16
 * About:首页箭头触发事件方法封装
 * param:
 * FIXME
 */
public interface WeiBoArrowPresent {

    public void weibo_destroy(long id, Context context, int position, String weiboGroup);

    public void user_destroy(User user, Context context);

    public void user_create(User user, Context context);

    public void createFavorite(Status status, Context context);

    public void cancalFavorite(int position, Status status, Context context, boolean deleteAnimation);


}
