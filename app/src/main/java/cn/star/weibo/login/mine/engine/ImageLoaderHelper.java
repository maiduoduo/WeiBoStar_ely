package cn.star.weibo.login.mine.engine;

import android.content.Context;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.CircleBitmapDisplayer;

import cn.star.weibo.R;

/**
 * User: dcl
 * Method/Class Name:ImageLoaderHelper
 * Date: 2016-12-05
 * About:ImageLoader图片加载帮助类
 * param:
 * FIXME
 */


public abstract class ImageLoaderHelper{
    protected static ImageLoader imageLoader = ImageLoader.getInstance();//在Application里初始化ImageLoader
    private static DisplayImageOptions options;
    public static  void initImageLoader(Context context){
        imageLoader.init(ImageLoaderConfiguration.createDefault(context));
    }

}
