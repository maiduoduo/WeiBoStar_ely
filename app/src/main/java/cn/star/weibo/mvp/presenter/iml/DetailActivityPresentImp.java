package cn.star.weibo.mvp.presenter.iml;

import android.content.Context;


import java.util.ArrayList;

import cn.star.weibo.bean.Comment;
import cn.star.weibo.bean.Status;
import cn.star.weibo.login.home.activity.BaseDetailActivity;
import cn.star.weibo.mvp.model.StatusDetailModel;
import cn.star.weibo.mvp.model.iml.StatusDetailModelImp;
import cn.star.weibo.mvp.presenter.DetailActivityPresent;

/**
 * Created by dash on 16/6/26.
 */
public class DetailActivityPresentImp implements DetailActivityPresent {

    private BaseDetailActivity baseDetailActivity;
    private StatusDetailModel statusDetailModel;

    public DetailActivityPresentImp(BaseDetailActivity baseDetailActivity) {
        this.baseDetailActivity = baseDetailActivity;
        this.statusDetailModel = new StatusDetailModelImp();
    }

    @Override
    public void pullToRefreshData(int groupId, Status status, Context context) {
        switch (groupId) {
            case StatusDetailModelImp.COMMENT_PAGE:
                statusDetailModel.comment(groupId, status, context, new StatusDetailModel.OnCommentCallBack() {
                    @Override
                    public void noMoreDate() {
                        baseDetailActivity.hideLoadingIcon();
                        baseDetailActivity.updateEmptyCommentHeadView();
                    }

                    @Override
                    public void onDataFinish(ArrayList<Comment> commentlist) {
                        baseDetailActivity.hideLoadingIcon();
                        baseDetailActivity.updateCommentListView(commentlist, true);
                        baseDetailActivity.restoreScrollOffset(false);
                    }

                    @Override
                    public void onError(String error) {
                        baseDetailActivity.hideLoadingIcon();
                        baseDetailActivity.showErrorFooterView();
                    }
                });
                break;
            case StatusDetailModelImp.REPOST_PAGE:
                statusDetailModel.repost(groupId, status, context, new StatusDetailModel.OnRepostCallBack() {
                    @Override
                    public void noMoreDate() {
                        baseDetailActivity.hideLoadingIcon();
                        baseDetailActivity.updateEmptyRepostHeadView();
                    }

                    @Override
                    public void onDataFinish(ArrayList<Status> repostList) {
                        baseDetailActivity.hideLoadingIcon();
                        baseDetailActivity.updateRepostListView(repostList, true);
                        baseDetailActivity.restoreScrollOffset(false);
                    }

                    @Override
                    public void onError(String error) {
                        baseDetailActivity.hideLoadingIcon();
                        baseDetailActivity.showErrorFooterView();
                    }
                });
                break;
        }
    }

    @Override
    public void requestMoreData(int groupId, Status status, Context context) {
        switch (groupId) {
            case StatusDetailModelImp.COMMENT_PAGE:
                statusDetailModel.commentNextPage(groupId, status, context, new StatusDetailModel.OnCommentCallBack() {
                    @Override
                    public void noMoreDate() {
                        baseDetailActivity.showEndFooterView();
                    }

                    @Override
                    public void onDataFinish(ArrayList<Comment> commentlist) {
                        baseDetailActivity.hideFooterView();
                        baseDetailActivity.updateCommentListView(commentlist, false);
                    }

                    @Override
                    public void onError(String error) {
                        baseDetailActivity.showErrorFooterView();
                    }
                });
                break;
            case StatusDetailModelImp.REPOST_PAGE:
                statusDetailModel.repostNextPage(groupId, status, context, new StatusDetailModel.OnRepostCallBack() {
                    @Override
                    public void noMoreDate() {
                        baseDetailActivity.showEndFooterView();
                    }

                    @Override
                    public void onDataFinish(ArrayList<Status> repostList) {
                        baseDetailActivity.hideFooterView();
                        baseDetailActivity.updateRepostListView(repostList, false);
                    }

                    @Override
                    public void onError(String error) {
                        baseDetailActivity.showErrorFooterView();
                    }
                });
                break;
        }
    }
}
