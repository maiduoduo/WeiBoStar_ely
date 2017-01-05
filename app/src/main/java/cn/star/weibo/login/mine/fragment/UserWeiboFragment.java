package cn.star.weibo.login.mine.fragment;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cn.star.weibo.R;
import cn.star.weibo.base.BaseFragment;
import cn.star.weibo.login.mine.adapter.UserWeiboFragmentAdapter;
import cn.star.weibo.login.mine.bean.WeiboBean;
import cn.star.weibo.view.RecycleViewDivider;
import cn.star.weibo.view.widget.MyLinearLayoutManager;

import static cn.star.weibo.utils.UIUtils.getResources;


/**
 * User: dcl
 * Method/Class Name:UserWeiboFragment
 * Date: 2016-12-03
 * About:用户详情界面的微博界面
 * param:
 * FIXME
 */


public class UserWeiboFragment extends BaseFragment{
    private RecyclerView mRecycler;
    private List<WeiboBean> weibos = new ArrayList<>();
    @Override
    protected View loadLayout(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_user_weibo,null);
    }

    @Override
    protected void findView(View view) {
        mRecycler = $(R.id.recycler_mine_detail_weibo);

    }

    @Override
    public void requestServer() {

    }

    @Override
    protected void loadData(Bundle savedInstanceState) {

        for (int i = 0; i < 25; i++) {
            weibos.add(new WeiboBean());
        }

        MyLinearLayoutManager linearLayoutManager = new MyLinearLayoutManager(getActivity()
                                                        ,LinearLayoutManager.VERTICAL,false);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        mRecycler.setLayoutManager(linearLayoutManager);

        mRecycler.setItemAnimator(new DefaultItemAnimator());
        //设置分割线
        int color = getResources().getColor(R.color.main_bg);
        mRecycler.addItemDecoration(new RecycleViewDivider(getActivity(),linearLayoutManager.getOrientation(),30,color));
        mRecycler.setAdapter(new UserWeiboFragmentAdapter(getActivity(),weibos));
    }

    @Override
    protected void regListener() {

    }

    @Override
    protected void processClick(View v) {

    }

}
