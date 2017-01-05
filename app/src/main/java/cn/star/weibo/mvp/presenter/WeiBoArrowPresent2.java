package cn.star.weibo.mvp.presenter;

import android.content.Context;

import cn.star.weibo.bean.Status;
import cn.star.weibo.bean.User;


/**
 * Created by ronghui on 2016/6/28.
 */
public interface WeiBoArrowPresent2 {

    public void weibo_destroy(long id, Context context, int position, String weiboGroup);

    public void user_destroy(User user, Context context);

    public void user_create(User user, Context context);

    public void createFavorite(Status status, Context context);

    public void cancalFavorite(int position, Status status, Context context, boolean deleteAnimation);


}
