package cn.star.weibo.login.add.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import cn.star.weibo.MainActivity;
import cn.star.weibo.R;
import cn.star.weibo.base.BaseActivity;
import cn.star.weibo.bean.Status;
import cn.star.weibo.login.add.popmenu.PopMenu;
import cn.star.weibo.login.add.popmenu.PopMenuItem;
import cn.star.weibo.login.add.popmenu.PopMenuItemListener;
import cn.star.weibo.login.home.activity.RePostWeiboActivity;
import cn.star.weibo.login.home.service.PostService;
import cn.star.weibo.utils.ShowToast;

import static com.sina.weibo.sdk.openapi.legacy.CommonAPI.CAPITAL.m;

/**
 * Created by dcl on 2016/11/25 0025.
 */

public class AddActivity extends BaseActivity {
    private Activity mContext;
    private PopMenu mPopMenuTop;
    private PopMenu mPopMenuNext;
    private ImageButton mIvClose;//关闭
    private RelativeLayout mRlBottomFirst;
    private LinearLayout mLlBottomNext;
    private ImageButton mIbnBottomPre;//第二页的按钮
    private ImageButton mIbnCloseNext;//第二页的关闭按钮
    private Status status;

    @Override
    protected int loadLayout() {
        return R.layout.fragment_add;


    }

    @Override
    protected void findView() {
        mContext = AddActivity.this;
        mIvClose = $(R.id.ibn_add_close);
        mRlBottomFirst = $(R.id.rl_add_first);
        mLlBottomNext = $(R.id.ll_add_nextpop);
        mIbnBottomPre = $(R.id.ibn_add_pre);
        mIbnCloseNext = $(R.id.ibn_add_closenext);
        mRlBottomFirst.setVisibility(View.VISIBLE);
        mLlBottomNext.setVisibility(View.GONE);
        openPopMenu(status);
    }

    private void openPopMenu(final Status status) {
        mPopMenuTop = new PopMenu.Builder().attachToActivity(AddActivity.this)
                .addMenuItem(new PopMenuItem("文字", getResources().getDrawable(R.drawable.tabbar_compose_idea)))
                .addMenuItem(new PopMenuItem("照片/视频", getResources().getDrawable(R.drawable.tabbar_compose_photo)))
                .addMenuItem(new PopMenuItem("头条文章", getResources().getDrawable(R.drawable.tabbar_compose_headlines)))
                .addMenuItem(new PopMenuItem("签到", getResources().getDrawable(R.drawable.tabbar_compose_lbs)))
                .addMenuItem(new PopMenuItem("点评", getResources().getDrawable(R.drawable.tabbar_compose_review)))
                .addMenuItem(new PopMenuItem("更多", getResources().getDrawable(R.drawable.tabbar_compose_more)))
                .setOnItemClickListener(new PopMenuItemListener() {
                        @Override
                    public void onItemClick(PopMenu popMenu, int position) {
                        switch (position){
                            case 0://文字
                                mPopMenuTop.hide();
                                Intent TextIntent = new Intent(mContext, RePostWeiboActivity.class);
                                TextIntent.putExtra("ideaType", PostService.POST_SERVICE_CREATE_WEIBO);
                                TextIntent.putExtra("status", status);
                                mContext.startActivity(TextIntent);
                                AddActivity.this.finish();
                                break;
                            case 1://照片/视频
                                mPopMenuTop.hide();
                                Intent AlbumIntent = new Intent(mContext, RePostWeiboActivity.class);
                                AlbumIntent.putExtra("ideaType", PostService.POST_SERVICE_CREATE_WEIBO);
                                AlbumIntent.putExtra("startAlumbAcitivity", true);
                                mContext.startActivity(AlbumIntent);
                                AddActivity.this.finish();
                                break;
                            case 2:
                                mPopMenuTop.hide();
                                AddActivity.this.finish();
                                break;
                            case 3:
                                mPopMenuTop.hide();
                                AddActivity.this.finish();
                                break;
                            case 4:
                                mPopMenuTop.hide();
                                AddActivity.this.finish();
                                break;
                            case 5:
                                mPopMenuTop.hide();
                                mRlBottomFirst.setVisibility(View.GONE);
                                mLlBottomNext.setVisibility(View.VISIBLE);
                                mPopMenuNext.show();
                                break;
                            default :
                                break;
                        }
                    }
                })
                .build();


        if (!mPopMenuTop.isShowing()){
            mRlBottomFirst.setVisibility(View.VISIBLE);
            mLlBottomNext.setVisibility(View.GONE);
            mPopMenuTop.show();
        }




        mPopMenuNext = new PopMenu.Builder().attachToActivity(AddActivity.this)
                .addMenuItem(new PopMenuItem("直播", getResources().getDrawable(R.drawable.tabbar_compose_idea)))
                .addMenuItem(new PopMenuItem("好友圈", getResources().getDrawable(R.drawable.tabbar_compose_photo)))
                .addMenuItem(new PopMenuItem("音乐", getResources().getDrawable(R.drawable.tabbar_compose_headlines)))
                .addMenuItem(new PopMenuItem("秒拍", getResources().getDrawable(R.drawable.tabbar_compose_lbs)))
                .addMenuItem(new PopMenuItem("商品", getResources().getDrawable(R.drawable.tabbar_compose_review)))
                .addMenuItem(new PopMenuItem("红包", getResources().getDrawable(R.drawable.tabbar_compose_more)))
                .setOnItemClickListener(new PopMenuItemListener() {
                    @Override
                    public void onItemClick(PopMenu popMenu, int position) {
                        switch (position){
                            case 0://文字
                                mPopMenuNext.hide();
                                AddActivity.this.finish();
                                break;
                            case 1://照片/视频
                                mPopMenuNext.hide();
                                AddActivity.this.finish();
                                break;
                            case 2:
                                mPopMenuNext.hide();
                                AddActivity.this.finish();
                                break;
                            case 3:
                                mPopMenuNext.hide();
                                AddActivity.this.finish();
                                break;
                            case 4:
                                mPopMenuNext.hide();
                                AddActivity.this.finish();
                                break;
                            case 5:
                                mPopMenuNext.hide();
                                AddActivity.this.finish();
                                break;
                            default :
                                break;
                        }
                    }
                })
                .build();

        mPopMenuNext.hide();

    }

    @Override
    protected void regListener() {
        mIvClose.setOnClickListener(this);
        mIbnBottomPre.setOnClickListener(this);
        mIbnCloseNext.setOnClickListener(this);
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
//                ShowToast.Show(mContext,"点击了X号");

                if (mPopMenuTop.isShowing() || mPopMenuNext.isShowing()){
                    mPopMenuTop.hide();
                    mPopMenuNext.hide();
                    AddActivity.this.finish();
                }

                break;
            case R.id.ibn_add_pre ://第二页底部回到上一界面按钮
                if (mPopMenuNext.isShowing()){
                    mPopMenuNext.hide();
                    mRlBottomFirst.setVisibility(View.VISIBLE);
                    mLlBottomNext.setVisibility(View.GONE);
                    mPopMenuTop.show();
                }

                break;
            case R.id.ibn_add_closenext ://第二页底部关闭按钮
                if (mPopMenuTop.isShowing() || mPopMenuNext.isShowing()){
                    mPopMenuTop.hide();
                    mPopMenuNext.hide();
                    AddActivity.this.finish();
                }

                break;
        }
    }
}
