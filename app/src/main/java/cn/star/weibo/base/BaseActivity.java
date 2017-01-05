package cn.star.weibo.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;


import com.wenming.swipebacklayout.SwipeBackLayout;
import com.wenming.swipebacklayout.app.SwipeBackActivity;

import java.util.ArrayList;
import java.util.List;

import cn.star.weibo.utils.SharedPrefUtil;
import cn.star.weibo.utils.StatusBarUtils;


/**
 * 类名: BaseActivity 描述: Activity基础抽象类，提供规范的方法
 */
public abstract class BaseActivity extends FragmentActivity implements OnClickListener {
    private MApplication mMApplication;
    /**
     * 上下文
     */
    private Context mContext;
    public static List<Activity> activityList = new ArrayList<Activity>();


    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        addActivity();
        initView();
        mContext = BaseActivity.this;
        AppManager.getAppManager().addActivity(this);


    }


    /**
     * 描述：Toast提示文本.
     *
     * @param text 文本
     */
    public void showToast(String text) {
        Toast.makeText(this, "" + text, Toast.LENGTH_SHORT).show();
    }

    /**
     * 描述：Toast提示文本.
     * 文本
     */
    public void showToast(int resourse) {
        Toast.makeText(this, resourse, Toast.LENGTH_SHORT).show();
    }

    /*
     * @param context 上下文
     */
    public static void closeActivity(Context context) {
        // 关闭所有Activity
        for (int i = 0; i < activityList.size(); i++) {
            if (null != activityList.get(i)) {
                activityList.get(i).finish();
            }
        }
    }

    /*
     * @param context 上下文
     */
    public static void exitClient(Context context) {
        // 关闭所有Activity
        for (int i = 0; i < activityList.size(); i++) {
            if (null != activityList.get(i)) {
                activityList.get(i).finish();
            }
        }
        ActivityManager activityMgr = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        activityMgr.restartPackage(context.getPackageName());
        System.exit(0);
    }


    public void addActivity() {
        mMApplication = (MApplication) getApplication();
        mMApplication.addActivity(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMApplication.removeActivity(this);
    }

    private void initView() {
        int layout = loadLayout();
        setContentView(layout);
        findView();
        regListener();
        loadData();
        reqServer();

    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    @Override
    public void onClick(View v) {
        processClick(v);
    }

    /**
     * 加载布局
     */
    protected abstract int loadLayout();

    /**
     * 初始化组件
     */
    protected abstract void findView();

    /**
     * 注册监听
     */
    protected abstract void regListener();

    /**
     * 加载数据
     */
    protected abstract void loadData();

    /**
     * 请求网络
     */
    protected abstract void reqServer();

    /**
     * 相应监听的方法
     *
     * @param view
     */
    public void setOnClick(View... view) {
        for (View v : view) {
            v.setOnClickListener(this);
        }
    }

    /**
     * 监听事件
     */
    protected abstract void processClick(View view);

    /**
     * @param v ： void
     * @方法名: processClick
     * @方法描述: 点击事件
     * @异常： 无
     * @作者： LXY
     * @创建日期： 2015-8-24 上午10:53:23
     */

    /**
     * findViewById()的封装，不用强转
     */
    @SuppressWarnings("unchecked")
    public <T extends View> T $(int id) {
        return (T) super.findViewById(id);
    }

}
