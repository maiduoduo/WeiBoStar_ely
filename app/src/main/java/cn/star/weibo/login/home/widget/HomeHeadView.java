package cn.star.weibo.login.home.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import cn.star.weibo.R;


/**
 * Created by dash on 16/4/27.
 */
public class HomeHeadView extends RelativeLayout {

    public HomeHeadView(Context context) {
        super(context);
        init(context);
    }

    public HomeHeadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public HomeHeadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {
        inflate(context, R.layout.home_headview, this);
    }
}
