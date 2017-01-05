package cn.star.weibo.unlogin.activity;

import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import cn.star.weibo.R;
import cn.star.weibo.base.BaseActivity;
import cn.star.weibo.login.add.popmenu.PopMenu;
import cn.star.weibo.login.add.popmenu.PopMenuItem;
import cn.star.weibo.login.add.popmenu.PopMenuItemListener;
import cn.star.weibo.utils.ShowToast;

/**
 * Created by dcl on 2016/11/25 0025.
 */

public class PostActivity extends BaseActivity {
    private PopMenu mPopMenuTop;
    private PopMenu mPopMenuNext;
    private ImageButton mIvClose;//关闭
    private RelativeLayout mRlBottomFirst;
    private LinearLayout mLlBottomNext;
    private ImageButton mIbnBottomPre;//第二页的按钮
    private ImageButton mIbnCloseNext;//第二页的关闭按钮

    @Override
    protected int loadLayout() {
        return R.layout.fragment_post;


    }

    @Override
    protected void findView() {
        mIvClose = $(R.id.ibn_add_close);
        mRlBottomFirst = $(R.id.rl_add_first);
        mLlBottomNext = $(R.id.ll_add_nextpop);
        mIbnBottomPre = $(R.id.ibn_add_pre);
        mIbnCloseNext = $(R.id.ibn_add_closenext);
        mRlBottomFirst.setVisibility(View.VISIBLE);
        mLlBottomNext.setVisibility(View.GONE);
        openPopMenu();
    }

    private void openPopMenu() {
        mPopMenuTop = new PopMenu.Builder().attachToActivity(PostActivity.this)
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
                                PostActivity.this.finish();
                                break;
                            case 1://照片/视频
                                ShowToast.Show(PostActivity.this,"您还未登录，请注册/登录");
                                mPopMenuTop.hide();
                                PostActivity.this.finish();
                                break;
                            case 2:
                                mPopMenuTop.hide();
                                PostActivity.this.finish();
                                break;
                            case 3:
                                mPopMenuTop.hide();
                                PostActivity.this.finish();
                                break;
                            case 4:
                                mPopMenuTop.hide();
                                PostActivity.this.finish();
                                break;
                            case 5:
                                ShowToast.Show(PostActivity.this,"您还未登录，请注册/登录");
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




        mPopMenuNext = new PopMenu.Builder().attachToActivity(PostActivity.this)
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
                            case 0://直播
                                mPopMenuNext.hide();
                                PostActivity.this.finish();
                                break;
                            case 1://好友圈
                                ShowToast.Show(PostActivity.this,"您还未登录，请注册/登录");
                                mPopMenuNext.hide();
                                PostActivity.this.finish();
                                break;
                            case 2://音乐
                                mPopMenuNext.hide();
                                PostActivity.this.finish();
                                break;
                            case 3://秒拍
                                mPopMenuNext.hide();
                                PostActivity.this.finish();
                                break;
                            case 4://商品
                                mPopMenuNext.hide();
                                PostActivity.this.finish();
                                break;
                            case 5://红包
                                ShowToast.Show(PostActivity.this,"您还未登录，请注册/登录");
                                mPopMenuNext.hide();
                                PostActivity.this.finish();
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
                if (mPopMenuTop.isShowing() || mPopMenuNext.isShowing()){
                    mPopMenuTop.hide();
                    mPopMenuNext.hide();
                    PostActivity.this.finish();
                }

                break;
            case R.id.ibn_add_pre ://第二页底部回到上一界面按钮
                if (mPopMenuNext.isShowing()){
                    ShowToast.Show(PostActivity.this,"您还未登录，请注册/登录");
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
                    PostActivity.this.finish();
                }

                break;
        }
    }
}
