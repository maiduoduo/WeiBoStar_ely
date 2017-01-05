package cn.star.weibo.base;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

/**
 * @param : BaseApplication
 * @param: 应用程序的入口
 */
public class BaseApplication extends Application
{
	private static Context	mContext;
	private static Thread	mMainThread;
	private static long		mMainThreadId;
	private static Handler	mMainThreadHandler;
	private static Looper	mMainThreadLooper;

	@Override
	public void onCreate()
	{
		super.onCreate();

		// 在应用程序入口提供全局的工具

		// 上下文
		mContext = this;

		// 主线程和子线程
		mMainThread = Thread.currentThread();

		// 主线程id
		// mMainThreadId = mMainThread.getId();
		// android.os.Process.myPid();// 进程id
		mMainThreadId = android.os.Process.myTid();// 当前线程id
		// android.os.Process.myUid();//用户id

		// 主线程handler
		mMainThreadHandler = new Handler();

		//
		mMainThreadLooper = getMainLooper();
	}

	public static Context getContext()
	{
		return mContext;
	}

	public static Thread getMainThread()
	{
		return mMainThread;
	}

	public static long getMainThreadId()
	{
		return mMainThreadId;
	}

	public static Handler getMainThreadHandler()
	{
		return mMainThreadHandler;
	}

	public static Looper getMainThreadLooper()
	{
		return mMainThreadLooper;
	}
}
