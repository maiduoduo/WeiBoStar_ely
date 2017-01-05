package cn.star.weibo.utils;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import android.app.Activity;
import android.widget.Toast;

/**
 * 在主线程和子线程的Toast调用。
 * @param: Activity context Activity
 * @param:String string 接收流
 * @param :2015-6-16
 * @author dcl
 *
 */
public class ShowToast {

	public static void  Show(final Activity context,final String string) {
		if (Thread.currentThread().getName().equals("main")) {
			Toast.makeText(context, string, Toast.LENGTH_LONG).show();
		}else {//子线程
			context.runOnUiThread(new Runnable() {
				@Override
				public void run() {
					Toast.makeText(context, string, Toast.LENGTH_LONG).show();
				}
			});

		}
	}

}
