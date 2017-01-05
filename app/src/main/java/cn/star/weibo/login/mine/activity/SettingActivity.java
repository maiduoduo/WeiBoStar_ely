package cn.star.weibo.login.mine.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;

import cn.star.weibo.R;
import cn.star.weibo.base.BaseActivity;
import cn.star.weibo.base.MApplication;
import cn.star.weibo.login.LoginBaseActivity;
import cn.star.weibo.mvp.presenter.SettingActivityPresent;
import cn.star.weibo.mvp.presenter.iml.SettingActivityPresentImp;
import cn.star.weibo.mvp.view.SettingActivityView;
import cn.star.weibo.utils.SharedPrefUtil;
import cn.star.weibo.view.SlideSwitchView;


/**
 * User: dcl
 * Method/Class Name:SettingActivity
 * Date: 2016-12-05
 * About:设置界面
 * param:
 * FIXME
 */


public class SettingActivity extends LoginBaseActivity implements SettingActivityView {
    private Context mContext;
    private RelativeLayout mExitLayout;
    private ImageView mBackImageView;
    private RelativeLayout mClearCache;
    private SettingActivityPresent mSettingActivityPresent;
    private RelativeLayout mAccountLayout;
    private CheckBox mCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_settings);
        mContext = this;
        mSettingActivityPresent = new SettingActivityPresentImp(this);
        mExitLayout = (RelativeLayout) findViewById(R.id.exitLayout);
        mBackImageView = (ImageView) findViewById(R.id.toolbar_back);
        mClearCache = (RelativeLayout) findViewById(R.id.clearCache_layout);
        mAccountLayout = (RelativeLayout) findViewById(R.id.accoutlayout);
        mCheckBox = (CheckBox) findViewById(R.id.nightMode_cb);
        initView();
        setUpListener();
    }

    private void initView() {
        boolean isNightMode = (boolean) SharedPrefUtil.get(this, "setNightMode", false);
        mCheckBox.setChecked(isNightMode);
    }

    private void setUpListener() {
        mExitLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setMessage("确定要退出微博？")
                        .setCancelable(false)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //mSettingActivityPresent.logout(mContext);
                                ((MApplication) getApplication()).exit();

                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
        mBackImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mClearCache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSettingActivityPresent.clearCache(mContext);
            }
        });
        mAccountLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, AccoutActivity.class);
                startActivity(intent);
            }
        });
        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SharedPrefUtil.put(mContext, "setNightMode", true);
                    getDelegate().setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    SharedPrefUtil.put(mContext, "setNightMode", false);
                    getDelegate().setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
                ((MApplication) mContext.getApplicationContext()).recreateAll();
            }
        });
    }

}
