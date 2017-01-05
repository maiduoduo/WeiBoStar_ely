package cn.star.weibo.login.find.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;

import cn.star.weibo.R;
import cn.star.weibo.base.BaseFragment;

/**
 * Created by Administrator on 2016/11/23 0023.
 */

public class FindFragment extends BaseFragment implements AdapterView.OnItemClickListener{
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    protected View loadLayout(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_found, null);
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
