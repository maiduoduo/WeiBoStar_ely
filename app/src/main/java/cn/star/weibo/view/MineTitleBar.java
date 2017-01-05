package cn.star.weibo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.star.weibo.R;

/**
 * 我的页面Title
 */
public class MineTitleBar extends RelativeLayout {
	private Context context;
	private TextView titleTextView;// 标题
	private TextView backImageView;// 左侧添加好友
	private TextView rightImageView;// 右侧设置

	public MineTitleBar(Context context) {
		super(context);
		this.context = context;
		if (isInEditMode())
			return;
		initView();
	}

	public MineTitleBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		if (isInEditMode())
			return;
		initView();
	}

	private void initView() {
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.activity_title_mine, this);
		titleTextView = (TextView) view.findViewById(R.id.tv_mine_title_center);
		backImageView = (TextView) view.findViewById(R.id.tv_mine_title_left);
		rightImageView = (TextView) view.findViewById(R.id.tv_mine_title_right);
	}

	/**
	 * R.id.title_back_imageview
	 * 
	 * @param click
	 */
	public void setBackClick(OnClickListener click) {
		backImageView.setOnClickListener(click);
	}


	/**
	 * 设置右侧点击事件 R.id.title_right_textview R.id.title_right_imageview
	 * 
	 * @param click
	 */
	public void setRightClick(OnClickListener click) {
		rightImageView.setOnClickListener(click);
	}


	/**
	 * 设置左侧控件
	 * 
	 * @param text
	 */
	public void setLeftImage(String text) {
		backImageView.setText(text);
	}




	/**
	 * 设置标题文字
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		titleTextView.setText(title);
	}

	/**
	 * 设置标题文字
	 * 
	 */
	public void setTitle(int titleId) {
		titleTextView.setText(titleId);
	}

	/**
	 * 设置标题文字颜色
	 * 
	 */
	public void setTitleTextColor(int colorId) {
		titleTextView.setTextColor(colorId);
	}

	/**
	 * 设置左侧图标显示与否
	 * 
	 * @param visible
	 */
	public void setLeftVisible(int visible) {
		backImageView.setVisibility(visible);
	}

}
