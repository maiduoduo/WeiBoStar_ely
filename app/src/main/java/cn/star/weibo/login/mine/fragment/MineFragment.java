package cn.star.weibo.login.mine.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.CircleBitmapDisplayer;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import cn.star.weibo.R;
import cn.star.weibo.api.UsersAPI;
import cn.star.weibo.base.BaseFragment;
import cn.star.weibo.bean.User;
import cn.star.weibo.common.NewFeature;
import cn.star.weibo.common.login.AccessTokenKeeper;
import cn.star.weibo.login.mine.activity.SettingActivity;
import cn.star.weibo.login.mine.activity.UserActivity;
import cn.star.weibo.mvp.presenter.MineFragmentPresent;
import cn.star.weibo.mvp.presenter.iml.MineFragmentPresentImp;
import cn.star.weibo.mvp.view.MineFragmentView;
import cn.star.weibo.utils.SharedPrefUtil;
import cn.star.weibo.utils.ShowToast;
import cn.star.weibo.view.MineTitleBar;

import static cn.star.weibo.R.id.contentLayout;


/**
 * User: dcl
 * Method/Class Name:MineFragment
 * Date: 2016-11-23
 * About:我的界面
 * param:
 * FIXME
 */

public class MineFragment extends BaseFragment implements MineFragmentView,AdapterView.OnItemClickListener,SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout refreshLayout;
    /**
     * 当前 Token 信息
     */
    private Oauth2AccessToken mAccessToken;
    /**
     * 用户信息接口
     */
    private UsersAPI mUsersAPI;
    private Activity mActivity;
    private Context mContext;
    private MineFragmentPresent mMineFragmentPresent;
    private DisplayImageOptions options;
    private User mUser;


    private MineTitleBar mTitleBar;
    private TextView mTvAddFriend, mTvSetting, mTvWBNum, mTvConcertNum, mTvFanNum, mTvUserName, mTvUserDes;//title添加好友,设置,微博数量，关注数量，粉丝数量，用户
    private LinearLayout mWeiboInfo, mConcertInfo, mFanInfo;//微博数量,关注数量,粉丝数量
    private ImageView circleIv_top;
    private LinearLayout mAll;
    private LinearLayout mBottom;

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    public MineFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
        mContext = getContext();
        mMineFragmentPresent = new MineFragmentPresentImp(this);

        //设置头像初始化
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.avator_default)
                .showImageForEmptyUri(R.drawable.avator_default)
                .showImageOnFail(R.drawable.avator_default)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .displayer(new CircleBitmapDisplayer(14671839, 1))
                .build();


    }

    @Override
    protected View loadLayout(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_mine, null);
    }


    @Override
    protected void findView(View view) {
        refreshLayout = $(R.id.mine_refresh);//下拉刷新组件
        mTitleBar = $(R.id.title_mine);

        mTvAddFriend = $(R.id.tv_mine_title_left);//添加朋友
        mTvSetting = $(R.id.tv_mine_title_right);//设置

        circleIv_top = $(R.id.circleIv_top);//用户头像
        mTvUserName = $(R.id.tv_mine_item_name);//用户名
        mTvUserDes = $(R.id.tv_mine_item_des);//用户简介

        mWeiboInfo = $(R.id.ll_mine_weibo_info);
        mConcertInfo = $(R.id.ll_mine_concert_info);
        mFanInfo = $(R.id.ll_mine_fan_info);

        mTvWBNum = $(R.id.tv_mine_weibo_num);//微博数
        mTvConcertNum = $(R.id.tv_mine_concert_num);//关注数
        mTvFanNum = $(R.id.tv_mine_fan_num);//粉丝数量


        mTitleBar.setTitle("我");
        //初始化
        refreshUserDetail(mContext, true);
    }

    public void refreshUserDetail(Context context, boolean loadicon) {
        mMineFragmentPresent.refreshUserDetail(Long.parseLong(AccessTokenKeeper.readAccessToken(context).getUid()), context, loadicon);
    }

    public boolean haveAlreadyRefresh() {
        if (mTvUserName == null || mTvUserName.getText().length() == 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (NewFeature.refresh_profileLayout == true) {
            refreshUserDetail(mContext, false);
            NewFeature.refresh_profileLayout = false;
        }
    }

    @Override
    public void requestServer() {

    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        refreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                                              android.R.color.holo_green_light,
                                              android.R.color.holo_orange_light,
                                              android.R.color.holo_red_light);
        refreshLayout.setProgressBackgroundColor(R.color.main_bg);

    }

    @Override
    protected void regListener() {
        mTvAddFriend.setOnClickListener(this);
        mTvSetting.setOnClickListener(this);
        circleIv_top.setOnClickListener(this);

        mWeiboInfo.setOnClickListener(this);
        mConcertInfo.setOnClickListener(this);
        mFanInfo.setOnClickListener(this);
        refreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void setUserDetail(User user) {
        if (user != null) {
            mUser = user;
            //用户头像加载
            ImageLoader.getInstance().displayImage(user.avatar_hd, circleIv_top, options);
            //用户名
            mTvUserName.setText(user.name);
            //用户简介
            mTvUserDes.setText("简介:" + user.description);
            //微博数量
            mTvWBNum.setText(user.statuses_count + "");
            //关注数
            mTvConcertNum.setText(user.friends_count + "");
            //粉丝数量
            mTvFanNum.setText(user.followers_count + "");
        }
    }

    @Override
    protected void processClick(View v) {
        switch (v.getId()) {
            case R.id.tv_mine_title_left://添加好友
                ShowToast.Show(getActivity(), "添加好友");
                break;
            case R.id.tv_mine_title_right://设置
                ShowToast.Show(getActivity(), "设置");
                Intent settingIntent = new Intent(getActivity(),SettingActivity.class);
                startActivity(settingIntent);
                break;
            case R.id.circleIv_top://用户头像的点击事件
                //进入用户详情页
                Intent userIconIntent = new Intent(getActivity(), UserActivity.class);
//                Bundle bundle = new Bundle();
//                String[] users = new String[]{mTvUserName.getText()+"",mTvConcertNum.getText()+""
//                        ,mTvFanNum.getText()+"",mTvUserDes.getText()+""};
//                bundle.putStringArray("users",users);
//                bundle.putString("usericon",mUser.avatar_hd);
//                userIconIntent.putExtras(bundle);
                startActivity(userIconIntent);
                break;
            case R.id.ll_mine_weibo_info://微博数
                ShowToast.Show(getActivity(), "微博数");
                break;
            case R.id.ll_mine_concert_info://关注数
                ShowToast.Show(getActivity(), "关注数");
                break;
            case R.id.ll_mine_fan_info://粉丝数
                ShowToast.Show(getActivity(), "粉丝数");
                break;
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            if (mTvUserName == null || mTvUserName.getText() == null || mTvUserName.getText().length() == 0) {
                refreshUserDetail(mContext, false);
            }
        }
    }


    @Override
    public void onRefresh() {
        refreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshUserDetail(mContext, true);
                refreshLayout.setRefreshing(false);
            }
        },3000);
    }



    @Override
    public void showScrollView() {

    }

    @Override
    public void hideScrollView() {

    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hideProgressDialog() {

    }



    //定义一个回调接口
   /* public interface CallBackValue{
        public void SendMessageValue(String strValue);
    }*/

}