package cn.star.weibo.unlogin.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.star.weibo.R;
import cn.star.weibo.base.BaseFragment;


/**
 * Created by dcl on 16/11/21.
 */
public class MessageFragment extends BaseFragment {

    @Override
    protected View loadLayout(LayoutInflater inflater) {
        return inflater.inflate(R.layout.unlogin_messagefragment_layout,null);
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
