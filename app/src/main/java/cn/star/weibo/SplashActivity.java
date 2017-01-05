package cn.star.weibo;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;

import java.util.Timer;
import java.util.TimerTask;

import cn.star.weibo.base.BaseActivity;
import cn.star.weibo.common.login.AccessTokenKeeper;
import cn.star.weibo.unlogin.activity.UnLoginActivity;


/***
 * 欢迎页
 */
public class SplashActivity extends BaseActivity {
	private Intent mStartIntent;
	private RelativeLayout rlSplash;

	@Override
	protected int loadLayout() {
		requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题
		return R.layout.activity_splash;
	}

	@Override
	protected void findView() {
		rlSplash = $(R.id.rl_splash);
	}

	@Override
	protected void regListener() {

	}

	@Override
	protected void loadData() {
		initAnim();
		delayedTimer(true);

		if (AccessTokenKeeper.readAccessToken(this).isSessionValid()) {
				mStartIntent = new Intent(SplashActivity.this, MainActivity.class);
		} else {
				mStartIntent = new Intent(SplashActivity.this, UnLoginActivity.class);
		}

	}

	@Override
	protected void reqServer() {

	}

	@Override
	protected void processClick(View view) {

	}


	private void initAnim() {
		AlphaAnimation alphaAnimation = new AlphaAnimation(0.1f,1.0f);
		alphaAnimation.setDuration(3000);
		rlSplash.startAnimation(alphaAnimation);
	}

	private boolean hasEnterMain = false;
	private void delayedTimer(boolean isDelay) {
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				mHandler.sendMessage(Message.obtain());
			}
		}, isDelay? 4000:0);
	}


	public Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if(!hasEnterMain) {
				hasEnterMain = true;
				startActivity(mStartIntent);
				overridePendingTransition(R.anim.zoomin,R.anim.zoomout);
			}
			finish();
		}
	};

	/***
	 * 触摸事件：触摸，没有延时，直接进入主界面
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN://手指按下的时候
				delayedTimer(false);//0s后进入界面
				break;

			default:
				break;
		}
		return super.onTouchEvent(event);
	}
	@Override
	protected void onDestroy() {
		mHandler.removeCallbacksAndMessages(null);
		super.onDestroy();
	}



}
