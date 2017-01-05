package cn.star.weibo.base;

import android.app.Activity;
import android.app.Application;


import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

import cn.star.weibo.login.mine.engine.ImageLoaderHelper;
import cn.star.weibo.utils.ILog;


public class MApplication extends Application {
    public static List<Activity> activityList;


    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        activityList = new ArrayList<>();
        ImageLoaderHelper.initImageLoader(this);//初始化ImageLoader
        if (Constants.IS_TEST) {
            ILog.isDebug = true;
        } else {
            ILog.isDebug = false;
        }
    }


    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

    public void exit() {
        for (Activity activity : activityList) {
            activity.finish();
        }
        System.exit(0);
    }

    public void removeActivity(Activity activity) {
        activityList.remove(activity);
    }

    public void finishAll() {
        for (Activity activity : activityList) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    public void recreateAll() {
        for (Activity activity : activityList) {
            if (!activity.isFinishing()) {
                activity.recreate();
            }
        }
    }


}
