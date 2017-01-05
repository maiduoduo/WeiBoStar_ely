package cn.star.weibo.mvp.presenter.iml;

import android.content.Context;

import java.util.ArrayList;

import cn.star.weibo.bean.Status;
import cn.star.weibo.bean.User;
import cn.star.weibo.common.Constants;
import cn.star.weibo.login.mine.activity.UserActivity;
import cn.star.weibo.mvp.model.UserModel;
import cn.star.weibo.mvp.model.iml.UserModelImp;
import cn.star.weibo.mvp.presenter.UserActivityPresent;
import cn.star.weibo.mvp.view.UserActivityView;

/**
 * Created by dash on 2016/6/28.
 */
public class UserActivityPresentImp implements UserActivityPresent {

    private UserModel userModel;
    private UserActivityView userActivityView;

    public UserActivityPresentImp(UserActivityView userActivityView) {
        this.userActivityView = userActivityView;
        this.userModel = new UserModelImp();
    }



    @Override
    public void pullToRefreshData(final String refreshType, String screenName, final Context context) {
        userActivityView.showLoadingIcon();
        switch (refreshType) {
            case UserActivity.USER_ACTIVITY_USER_INFO:
                userModel.show(screenName, context, new UserModel.OnUserDetailRequestFinish() {
                    @Override
                    public void onComplete(User user) {
                        userActivityView.hideLoadingIcon();
                        userActivityView.updateUserInfoListView(user, true);
                    }

                    @Override
                    public void onError(String error) {
                        userActivityView.hideLoadingIcon();
                    }
                });
                break;
            case UserActivity.USER_ACTIVITY_USER_STATUS:
                userModel.userTimeline(screenName, Constants.GROUP_MYWEIBO_TYPE_ALL, context, new UserModel.OnStatusListFinishedListener() {
                    @Override
                    public void noMoreDate() {
                        userActivityView.hideLoadingIcon();
                    }

                    @Override
                    public void onDataFinish(ArrayList<Status> statuslist) {
                        userActivityView.hideLoadingIcon();
                        userActivityView.updateStatusListView(statuslist, true);
                    }

                    @Override
                    public void onError(String error) {
                        userActivityView.hideLoadingIcon();
                    }
                });
                break;
            case UserActivity.USER_ACTIVITY__USER_PHOTO:
                userModel.userTimeline(screenName, Constants.GROUP_MYWEIBO_TYPE_ALL, context, new UserModel.OnStatusListFinishedListener() {

                    @Override
                    public void noMoreDate() {
                        userActivityView.hideLoadingIcon();
                    }

                    @Override
                    public void onDataFinish(ArrayList<Status> statuslist) {
                        userActivityView.hideLoadingIcon();
                        userActivityView.updatePhotoListView(statuslist, true);
                    }

                    @Override
                    public void onError(String error) {
                        userActivityView.hideLoadingIcon();
                    }
                });
                break;
        }
    }

    @Override
    public void requestMoreData(String refreshType, String screenName, Context context) {
        switch (refreshType) {
            case UserActivity.USER_ACTIVITY_USER_STATUS:
                userModel.userTimelineNextPage(screenName, Constants.GROUP_MYWEIBO_TYPE_ALL, context, new UserModel.OnStatusListFinishedListener() {

                    @Override
                    public void noMoreDate() {
                        userActivityView.showEndFooterView();
                    }

                    @Override
                    public void onDataFinish(ArrayList<Status> statuslist) {
                        userActivityView.hideFooterView();
                        userActivityView.updateStatusListView(statuslist, false);
                    }

                    @Override
                    public void onError(String error) {
                        userActivityView.showErrorFooterView();
                    }
                });
                break;
            case UserActivity.USER_ACTIVITY__USER_PHOTO:
                userModel.userTimelineNextPage(screenName, Constants.GROUP_MYWEIBO_TYPE_PICWEIBO, context, new UserModel.OnStatusListFinishedListener() {
                    @Override
                    public void noMoreDate() {
                        userActivityView.showEndFooterView();
                    }

                    @Override
                    public void onDataFinish(ArrayList<Status> statuslist) {
                        userActivityView.hideFooterView();
                        userActivityView.updatePhotoListView(statuslist, false);
                    }

                    @Override
                    public void onError(String error) {
                        userActivityView.showErrorFooterView();
                    }
                });
                break;
        }
    }

}
