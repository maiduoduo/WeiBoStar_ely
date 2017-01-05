package cn.star.weibo.mvp.view;


import cn.star.weibo.bean.User;

/**
 * Created by dcl on 16/11/26.
 */
public interface MineFragmentView {

    public void setUserDetail(User user);

    public void showScrollView();

    public void hideScrollView();

    public void showProgressDialog();

    public void hideProgressDialog();

}
