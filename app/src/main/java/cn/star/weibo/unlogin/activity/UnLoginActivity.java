package cn.star.weibo.unlogin.activity;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import cn.star.weibo.R;
import cn.star.weibo.base.BaseActivity;
import cn.star.weibo.common.Constants;
import cn.star.weibo.unlogin.fragment.FindFragment;
import cn.star.weibo.unlogin.fragment.HomeFragment;
import cn.star.weibo.unlogin.fragment.MessageFragment;
import cn.star.weibo.unlogin.fragment.MineFragment;
import cn.star.weibo.utils.ShowToast;

/**
 * Created by dcl on 2016/11/23 0023.
 */

public class UnLoginActivity extends BaseActivity{
    private static final int HOME_FRAGMENT = 0X001;
    private static final int MESSAGE_FRAGMENT = 0X002;
    private static final int FIND_FRAGMENT = 0X004;
    private static final int MINE_FRAGMENT = 0X005;

    private Context mContext;
    private HomeFragment mHomeFragment;
    private MessageFragment mMessageFragment;
    private FindFragment mFindFragment;
    private MineFragment mMineFragment;

    private FragmentManager mFragmentManager;
    private Oauth2AccessToken mAccessToken;
    private RelativeLayout mHomeTab, mMessageTab, mFindTab, mMineTab;
    private ImageView mAddTab;

    private int mCurrentIndex;


    @Override
    protected int loadLayout() {
        return R.layout.activity_main_unlogin;
    }

    @Override
    protected void findView() {
        mContext = this;
        mHomeTab = $(R.id.tv_home);
        mMessageTab = $(R.id.tv_message);
        mFindTab = $(R.id.tv_discovery);
        mMineTab = $(R.id.tv_profile);
        mAddTab = $(R.id.fl_post);


    }

    @Override
    protected void loadData() {
        mFragmentManager = getSupportFragmentManager();
        setTabFragment(HOME_FRAGMENT);
    }

    @Override
    protected void regListener() {
        mHomeTab.setOnClickListener(this);
        mMessageTab.setOnClickListener(this);
        mFindTab.setOnClickListener(this);
        mMineTab.setOnClickListener(this);
        mAddTab.setOnClickListener(this);
    }

    /***
     * 界面监听事件
     * @param view
     */
    @Override
    protected void processClick(View view) {
        switch (view.getId()){
            case R.id.tv_home://首页Tab
                setTabFragment(HOME_FRAGMENT);
            break;
            case R.id.tv_message://消息Tab
                setTabFragment(MESSAGE_FRAGMENT);
            break;
            case R.id.tv_discovery://发现Tab
                setTabFragment(FIND_FRAGMENT);
            break;
            case R.id.tv_profile://我的界面Tab
                setTabFragment(MINE_FRAGMENT);
            break;
            case R.id.fl_post://AddTab
                ShowToast.Show(UnLoginActivity.this,"您还未登录，请注册/登录");
                SystemClock.sleep(500);
                Intent postIntent = new Intent(this,PostActivity.class);
                startActivity(postIntent);
            break;
        }

    }

    /***
     * 设置按钮绑定界面
     * @param index
     */
    private void setTabFragment(int index) {
        if (mCurrentIndex != index) {
            FragmentTransaction transaction = mFragmentManager.beginTransaction();
            hideAllFragments(transaction);
            switch (index) {
                case HOME_FRAGMENT:
                    mHomeTab.setSelected(true);
                    if (mHomeFragment == null) {
                        mHomeFragment = new HomeFragment();
                        transaction.add(R.id.contentLayout, mHomeFragment);
                    } else {
                        transaction.show(mHomeFragment);
                    }
                    mCurrentIndex = HOME_FRAGMENT;
                    break;
                case MESSAGE_FRAGMENT:
                    mMessageTab.setSelected(true);
                    if (mMessageFragment == null) {
                        mMessageFragment = new MessageFragment();
                        transaction.add(R.id.contentLayout, mMessageFragment);
                    } else {
                        transaction.show(mMessageFragment);
                    }
                    mCurrentIndex = MESSAGE_FRAGMENT;
                    break;

                case FIND_FRAGMENT:
                    mFindTab.setSelected(true);
                    if (mFindFragment == null) {
                        mFindFragment = new FindFragment();
                        transaction.add(R.id.contentLayout, mFindFragment);
                    } else {
                        transaction.show(mFindFragment);
                    }
                    mCurrentIndex = FIND_FRAGMENT;
                    break;
                case MINE_FRAGMENT:
                    mMineTab.setSelected(true);
                    if (mMineFragment == null) {
                        mMineFragment = new MineFragment();
                        transaction.add(R.id.contentLayout, mMineFragment);
                    } else {
                        transaction.show(mMineFragment);
                    }
                    mCurrentIndex = MINE_FRAGMENT;
                    break;
            }
            transaction.commit();
        } else if (mCurrentIndex == HOME_FRAGMENT && mHomeFragment != null) {

        }
    }

    /***
     * 隐藏对应的界面
     * @param transaction
     */
    private void hideAllFragments(FragmentTransaction transaction) {
        if (mHomeFragment != null) {
            transaction.hide(mHomeFragment);
        }
        if (mMessageFragment != null) {
            transaction.hide(mMessageFragment);
        }

        if (mFindFragment != null) {
            transaction.hide(mFindFragment);
        }
        if (mMineFragment != null) {
            transaction.hide(mMineFragment);
        }
        mHomeTab.setSelected(false);
        mMessageTab.setSelected(false);
        mFindTab.setSelected(false);
        mMineTab.setSelected(false);
    }

    /***
     * 登陆/注册（无官方客户端的情况下sso授权）加载web端
     * @param view
     */
    public void openLoginWebView(View view) {
        String authurl = "https://open.weibo.cn/oauth2/authorize" + "?" + "client_id=" + Constants.APP_KEY
                + "&response_type=token&redirect_uri=" + Constants.REDIRECT_URL
                + "&key_hash=" + Constants.AppSecret + (TextUtils.isEmpty(Constants.PackageName) ? "" : "&packagename=" + Constants.PackageName)
                + "&display=mobile" + "&scope=" + Constants.SCOPE;

        Intent intent = new Intent(mContext, WebViewActivity.class);
        intent.putExtra("url", authurl);
        startActivity(intent);
        finish();
    }


    @Override
    protected void reqServer() {

    }

}
