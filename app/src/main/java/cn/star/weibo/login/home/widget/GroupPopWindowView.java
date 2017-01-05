package cn.star.weibo.login.home.widget;


import java.util.ArrayList;

import cn.star.weibo.bean.Group;

/**
 * Created by dash on 16/7/20.
 */
public interface GroupPopWindowView {

    /**
     * 将网络请求返回的数据，添加到ListView上,需要返回返回
     */
    public void updateListView(ArrayList<Group> datas);


    /**
     * Toast显示网络请求失败的错误信息
     *
     * @param error
     */
    public void showErrorMessage(String error);
}
