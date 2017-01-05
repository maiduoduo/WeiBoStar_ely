package cn.star.weibo.unlogin.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import cn.star.weibo.R;
import cn.star.weibo.base.BaseFragment;


/**
 * Created by dcl on 16/11/21.
 */
public class HomeFragment extends BaseFragment {
    private ImageView mCircleView;

    @Override
    protected View loadLayout(LayoutInflater inflater) {
        return inflater.inflate(R.layout.unlogin_mainfragment_layout,null);
    }

    @Override
    protected void findView(View view) {
        mCircleView = $(R.id.circleView);
    }

    @Override
    public void requestServer() {

    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        Animation rotateAnim = AnimationUtils.loadAnimation(getContext(), R.anim.endlessrotate);
        LinearInterpolator interpolator = new LinearInterpolator();
        rotateAnim.setInterpolator(interpolator);
        mCircleView.setAnimation(rotateAnim);
    }

    @Override
    protected void regListener() {

    }

    @Override
    protected void processClick(View v) {

    }


}
