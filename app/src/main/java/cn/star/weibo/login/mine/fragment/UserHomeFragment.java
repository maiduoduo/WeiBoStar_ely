package cn.star.weibo.login.mine.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import cn.star.weibo.R;
import cn.star.weibo.base.BaseFragment;


/**
 * User: dcl
 * Method/Class Name:UserHomeFragment
 * Date: 2016-12-03
 * About:用户详情界面的主页界面
 * param:
 * FIXME
 */


public class UserHomeFragment extends BaseFragment {
    @Override
    protected View loadLayout(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_user_home,null);
    }

    @Override
    protected void findView(View view) {

    }

    @Override
    public void requestServer() {

    }

    @Override
    protected void loadData(Bundle savedInstanceState) {

    }

    @Override
    protected void regListener() {

    }

    @Override
    protected void processClick(View v) {

    }
}
