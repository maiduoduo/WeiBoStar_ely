package cn.star.weibo.login.mine.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;

import java.util.List;

import cn.star.weibo.R;

import static android.R.id.tabs;
import static cn.star.weibo.utils.UIUtils.getColor;
import static com.sina.weibo.sdk.openapi.legacy.AccountAPI.CAPITAL.C;

/**
 * User: dcl
 * Method/Class Name:UserDetailsAdapter
 * Date: 2016-12-03
 * About:用户详情界面的数据适配器
 * param:
 * FIXME
 */


public class UserDetailsAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> fragments;
    private Context mContext;
    public UserDetailsAdapter(FragmentManager fm,List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }
    public UserDetailsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }

    @Override
    public Fragment getItem(int position) {
        return (fragments == null || fragments.size() == 0) ? null : fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments == null ? 0 : fragments.size();
    }
}
