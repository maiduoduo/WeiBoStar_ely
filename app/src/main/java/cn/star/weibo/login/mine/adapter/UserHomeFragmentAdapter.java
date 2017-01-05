package cn.star.weibo.login.mine.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * User: dcl
 * Method/Class Name:UserHomeFragmentAdapter
 * Date: 2016-12-03
 * About:
 * param:
 * FIXME
 */


public class UserHomeFragmentAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> fragments;


    public UserHomeFragmentAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }
    public UserHomeFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
