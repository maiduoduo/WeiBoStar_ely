package cn.star.weibo.view.widget.load;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import cn.star.weibo.R;


/**
 * Created by ronghui on 2015/10/9.
 * <p/>
 * RecyclerView的HeaderView，简单的展示一个TextView
 */
public class SampleHeader extends RelativeLayout {

    public SampleHeader(Context context) {
        super(context);
        init(context);
    }

    public SampleHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SampleHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {
        inflate(context, R.layout.sample_header, this);
    }
}