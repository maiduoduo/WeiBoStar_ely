package cn.star.weibo.mvp.presenter.iml;

import android.content.Context;


import java.util.ArrayList;

import cn.star.weibo.bean.Group;
import cn.star.weibo.login.home.widget.GroupPopWindowView;
import cn.star.weibo.mvp.model.GroupListModel;
import cn.star.weibo.mvp.model.iml.GroupListModelImp;
import cn.star.weibo.mvp.presenter.GroupListPresenter;
import cn.star.weibo.mvp.view.HomeFragmentView;


/**
 * Created by dash on 16/5/14.
 */
public class GroupListPresenterImp implements GroupListPresenter {

    private GroupPopWindowView mGroupPopView;
    private GroupListModel mGroupListModel;
    private HomeFragmentView mHomeFragmentView;

    public GroupListPresenterImp(GroupPopWindowView groupPopView) {
        this.mGroupPopView = groupPopView;
        this.mGroupListModel = new GroupListModelImp();
    }

    @Override
    public void updateGroupList(final Context context) {
        mGroupListModel.groupsOnlyOnce(context, new GroupListModel.OnGroupListFinishedListener() {
            @Override
            public void noMoreDate() {
                
            }

            @Override
            public void onDataFinish(ArrayList<Group> groupslist) {
                mGroupPopView.updateListView(groupslist);
            }

            @Override
            public void onError(String error) {
                mGroupPopView.showErrorMessage(error);
            }

        });
    }


}
