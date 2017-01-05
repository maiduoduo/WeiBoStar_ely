package cn.star.weibo.login;

import android.graphics.Color;
import android.os.Bundle;


import com.wenming.swipebacklayout.SwipeBackLayout;
import com.wenming.swipebacklayout.app.SwipeBackActivity;

import cn.star.weibo.base.BaseActivity;
import cn.star.weibo.utils.SharedPrefUtil;
import cn.star.weibo.utils.StatusBarUtils;


/**
 * Created by dash on 2016/6/27.
 */
public class LoginBaseActivity extends SwipeBackActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSwipeBackLayout().setSwipeMode(SwipeBackLayout.FULL_SCREEN_LEFT);
        getSwipeBackLayout().setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
        getSwipeBackLayout().setSensitivity(LoginBaseActivity.this, 0.3f);
        boolean setNightMode = (boolean) SharedPrefUtil.get(this, "setNightMode", false);
        if (!setNightMode) {
            StatusBarUtils.from(this)
                    .setTransparentStatusbar(true)
                    .setStatusBarColor(Color.parseColor("#FFFFFF"))
                    .setLightStatusBar(true)
                    .process(this);
        }else {
            StatusBarUtils.from(this)
                    .setTransparentStatusbar(true)
                    .setStatusBarColor(Color.parseColor("#262626"))
                    .setLightStatusBar(true)
                    .process(this);
        }


    }
}