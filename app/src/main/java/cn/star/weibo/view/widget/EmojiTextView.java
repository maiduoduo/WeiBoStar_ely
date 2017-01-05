package cn.star.weibo.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * User: dcl
 * Method/Class Name:EmojiTextView
 * Date: 2016-4-16 0019
 * About:
 * param:cn.star.weibo.view.widget.EmojiTextView
 * FIXME
 */
public class EmojiTextView extends TextView {


    private final Context mContext;

    public EmojiTextView(Context context) {
        super(context, null);
        mContext = context;
    }

    public EmojiTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

}
