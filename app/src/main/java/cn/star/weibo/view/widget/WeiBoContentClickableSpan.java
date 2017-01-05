package cn.star.weibo.view.widget;

import android.content.Context;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

import cn.star.weibo.R;


/**
 * User: dcl
 * Method/Class Name:WeiBoContentClickableSpan
 * Date: 2016-12-05
 * About:WeiBoContentClickableSpan
 * param:
 * FIXME
 */
public class WeiBoContentClickableSpan extends ClickableSpan {

    private Context mContext;

    public WeiBoContentClickableSpan(Context context) {
        mContext = context;
    }

    @Override
    public void onClick(View widget) {
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);
        ds.setColor(mContext.getResources().getColor(R.color.home_weiboitem_content_keyword));
        ds.setUnderlineText(false);
    }


}
