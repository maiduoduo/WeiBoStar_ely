package cn.star.weibo.login.add.activity;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import cn.star.weibo.R;
import cn.star.weibo.base.BaseActivity;
import cn.star.weibo.utils.ShowToast;

/**
 * Created by dcl on 2016/11/25 0025.
 * add界面点击更多，加载功能更多的界面
 */

public class MoreAddActivity extends BaseActivity {
    /**
     * 上下文
     */
    private Activity mContext;
    private ImageButton mIvClose;//关闭
    private ImageView mIvWords;//文字
    private ImageView mIvPic;//照片/视频
    private ImageView mIvTopEssay;//头条文章
    private ImageView mIvSign;//签到
    private ImageView mIvReview;//点评
    private ImageView mIvMore;//更多

    @Override
    protected int loadLayout() {
        return R.layout.fragment_add_more;
    }

    @Override
    protected void findView() {
        mIvClose = $(R.id.ibn_add_close);
        mIvWords = $(R.id.iv_compose_words);
        mIvPic = $(R.id.iv_compose_picture);
        mIvTopEssay = $(R.id.iv_compose_topessay);
        mIvSign = $(R.id.iv_compose_sign);
        mIvReview = $(R.id.iv_compose_review);
        mIvMore = $(R.id.iv_compose_more);


    }

    @Override
    protected void regListener() {
        mIvClose.setOnClickListener(this);
        mIvWords.setOnClickListener(this);
        mIvPic.setOnClickListener(this);
        mIvTopEssay.setOnClickListener(this);
        mIvSign.setOnClickListener(this);
        mIvReview.setOnClickListener(this);
        mIvMore.setOnClickListener(this);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void reqServer() {

    }

    @Override
    protected void processClick(View view) {

        switch (view.getId()){
            case R.id.ibn_add_close ://关闭本界面
                ShowToast.Show(mContext,"点击了X号");
                MoreAddActivity.this.finish();
                break;
            case R.id.iv_compose_words ://文字
                ShowToast.Show(mContext,"文字");
                MoreAddActivity.this.finish();
                break;
            case R.id.iv_compose_picture ://图片/视频
                ShowToast.Show(mContext,"图片/视频");
                MoreAddActivity.this.finish();
                break;
            case R.id.iv_compose_topessay ://头条文章
                ShowToast.Show(mContext,"头条文章");
                MoreAddActivity.this.finish();
                break;
            case R.id.iv_compose_sign ://签到
                ShowToast.Show(mContext,"签到");
                MoreAddActivity.this.finish();
                break;
            case R.id.iv_compose_review ://评论
                ShowToast.Show(mContext,"点评");
                MoreAddActivity.this.finish();
                break;
            case R.id.iv_compose_more ://更多
                ShowToast.Show(mContext,"更多");
                MoreAddActivity.this.finish();
                break;
        }
    }
}
