package cn.star.weibo.mvp.presenter.iml;

import android.content.Context;

import cn.star.weibo.bean.User;
import cn.star.weibo.mvp.model.UserModel;
import cn.star.weibo.mvp.model.iml.UserModelImp;
import cn.star.weibo.mvp.presenter.MineFragmentPresent;
import cn.star.weibo.mvp.view.MineFragmentView;

//import cn.star.weibo.mvp.model.imp.UserModelImp;

/**
 * Created by dcl on 16/11/26.
 */
public class MineFragmentPresentImp implements MineFragmentPresent {

    private UserModel mUserModel;
    private MineFragmentView mMineFragmentView;

    public MineFragmentPresentImp(MineFragmentView mineFragmentView) {
        this.mMineFragmentView = mineFragmentView;
        this.mUserModel = new UserModelImp();
    }

    @Override
    public void refreshUserDetail(long uid, Context context, boolean loadIcon) {
        if (loadIcon) {
            mMineFragmentView.showProgressDialog();
        }
        mUserModel.show(uid, context, new UserModel.OnUserDetailRequestFinish() {
            @Override
            public void onComplete(User user) {
                mMineFragmentView.showScrollView();
                mMineFragmentView.hideProgressDialog();
                mMineFragmentView.setUserDetail(user);
            }

            @Override
            public void onError(String error) {
                mMineFragmentView.showScrollView();
                mMineFragmentView.hideProgressDialog();
            }
        });
    }
}
