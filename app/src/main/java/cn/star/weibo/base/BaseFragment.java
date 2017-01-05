package cn.star.weibo.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Toast;


/**********************************************
 * @类名: BaseFragment
 * 
 * @描述: Fragment基类
 * Created by dcl on 2016/11/20 0023.
 *
 ***********************************************/
public abstract class BaseFragment extends Fragment implements OnClickListener {
	/*** @Fields ct 上下文对象 */
	/*** @Fields rootView 要显示的布局 */
	public FragmentActivity mFragmentContext;
	public View rootView;


	

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		loadData(savedInstanceState);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		rootView = loadLayout(inflater);
		// getScreenHW();
		mFragmentContext = this.getActivity();
		findView(rootView);
		regListener();
		requestServer();
		return rootView;
	}



	public View getRootView() {
		return rootView;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		processClick(arg0);
	}

	/**
	 * @方法名: showToast
	 * @方法描述: 显示自定义Toast
	 */
	public void requestFail() {
		Toast.makeText(getActivity(), "数据加载失败", Toast.LENGTH_SHORT).show();
	}

	public void NetFail() {
		Toast.makeText(getActivity(), "网络不给力哦！请检查网络", Toast.LENGTH_SHORT)
				.show();
	}

	public void showToast(String msg) {
		Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
	}

	public void showToast(int resource) {
		Toast.makeText(getActivity(), getString(resource), Toast.LENGTH_SHORT)
				.show();
	}




	/**
	 * @方法名: initView
	 * @方法描述: 初始化界面
	 */
	protected abstract View loadLayout(LayoutInflater inflater);
	/**
	 *findViewById()的封装，不用强转
	 */
	@SuppressWarnings("unchecked")
	public <T extends View> T $(int id) {
		return (T) rootView.findViewById(id);
	}

	/** 初始化组件 */
	protected abstract void findView(View view);

	/** 请求网络 */
	public abstract void requestServer();

	/**
	 * @方法名: initData
	 * @方法描述: 初始化数据
	 * @param savedInstanceState
	 *            ： void
	 * @异常： 无
	 * @作者： LXY
	 * @创建日期： 2015-8-24 下午12:06:39
	 */
	protected abstract void loadData(Bundle savedInstanceState);
	/**
	 * 注册监听
	 */
	protected abstract void regListener();

	/**
	 * @方法名: processClick
	 * @方法描述: 设置控件点击事件监听
	 * @param v
	 *            ： void
	 * @异常： 无
	 * @作者： dcl
	 */
	protected abstract void processClick(View v);



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


	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
	}
}
