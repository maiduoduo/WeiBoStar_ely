package cn.star.weibo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.star.weibo.R;


public class FindTitleBar extends RelativeLayout {
	private Context context;
	private TextView titleTextView;// 标题
	private ImageView backImageView;// 左侧返回按钮
	private ImageView rightImageView;// 右侧图标

	public FindTitleBar(Context context) {
		super(context);
		this.context = context;
		if (isInEditMode())
			return;
		initView();
	}

	public FindTitleBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		if (isInEditMode())
			return;
		initView();
	}

	private void initView() {
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.activity_title_find, this);
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
	 * 设置左侧imageview的图片
	 * 
	 * @param resourceId
	 */
	public void setLeftImage(int resourceId) {
		backImageView.setImageResource(resourceId);
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
