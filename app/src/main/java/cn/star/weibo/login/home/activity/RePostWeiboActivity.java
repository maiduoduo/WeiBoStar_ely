package cn.star.weibo.login.home.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.openapi.models.ErrorInfo;

import java.util.ArrayList;

import cn.star.weibo.R;
import cn.star.weibo.api.UsersAPI;
import cn.star.weibo.bean.Comment;
import cn.star.weibo.bean.Status;
import cn.star.weibo.bean.User;
import cn.star.weibo.common.Constants;
import cn.star.weibo.common.FillContent;
import cn.star.weibo.common.login.AccessTokenKeeper;
import cn.star.weibo.login.LoginBaseActivity;
import cn.star.weibo.login.add.activity.AlbumActivity;
import cn.star.weibo.login.add.adapter.ImgListAdapter;
import cn.star.weibo.login.home.bean.CommentReplyBean;
import cn.star.weibo.login.home.bean.ImageInfoBean;
import cn.star.weibo.login.home.bean.WeiBoCommentBean;
import cn.star.weibo.login.home.bean.WeiBoCreateBean;
import cn.star.weibo.login.home.service.PostService;
import cn.star.weibo.utils.ILog;
import cn.star.weibo.utils.KeyBoardUtil;
import cn.star.weibo.utils.ShowToast;
import cn.star.weibo.utils.ToastUtil;
import cn.star.weibo.view.widget.WeiBoContentTextUtil;


/**
 * Author: dcl
 * Method/Class Name:RePostWeiboActivity
 * Date: 2016-12-06
 * About:转发微博界面
 * param:
 * FIXME
 */


public class RePostWeiboActivity extends LoginBaseActivity implements ImgListAdapter.OnFooterViewClickListener{
    private String Tag = RePostWeiboActivity.class.getName();
    private UsersAPI mUsersAPI;
    private Activity mContext;
    private TextView mCancal,mUserName,mTvRePostNum;
    private TextView mSendButton;
    private TextView publicbutton;
    private ImageView picture;
    private ImageView mentionbutton;
    private ImageView trendbutton;
    private ImageView emoticonbutton;
    private ImageView more_button;
    private EditText mEditText;
    private TextView mLimitTextView;
    private TextView mInputType;
    private LinearLayout mRepostlayout;
    private ImageView repostImg;
    private TextView repostName;
    private TextView repostContent;
    private RecyclerView mRecyclerView;
    private ImageView mBlankspace;
    private LinearLayout mIdea_linearLayout;
    private RelativeLayout mRlRepostLocation;
    private RelativeLayout mRlCommentReweet;

    private ArrayList<ImageInfoBean> mSelectImgList = new ArrayList<ImageInfoBean>();
    private Status mStatus;
    private Comment mComment;
    private String mIdeaType;


    /**
     * 最多输入140个字符
     */
    private static final int TEXT_LIMIT = 140;

    /**
     * 在只剩下5个字可以输入的时候，做提醒
     */
    private static final int TEXT_REMIND = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_item_repost_weibo);
        mContext = RePostWeiboActivity.this;
        mUsersAPI = new UsersAPI(mContext, Constants.APP_KEY, AccessTokenKeeper.readAccessToken(mContext));
        mInputType = (TextView) findViewById(R.id.inputType);
        mCancal = (TextView) findViewById(R.id.idea_cancel);
        mUserName = (TextView) findViewById(R.id.idea_username);
        mSendButton = (TextView) findViewById(R.id.idea_send);
        publicbutton = (TextView) findViewById(R.id.publicbutton);
        picture = (ImageView) findViewById(R.id.picture);
        mentionbutton = (ImageView) findViewById(R.id.mentionbutton);
        trendbutton = (ImageView) findViewById(R.id.trendbutton);
        emoticonbutton = (ImageView) findViewById(R.id.emoticonbutton);
        more_button = (ImageView) findViewById(R.id.more_button);
        mEditText = (EditText) findViewById(R.id.idea_content);
        mLimitTextView = (TextView) findViewById(R.id.limitTextView);
        mRepostlayout = (LinearLayout) findViewById(R.id.repost_layout);
        repostImg = (ImageView) findViewById(R.id.repost_img);
        repostName = (TextView) findViewById(R.id.repost_name);
        repostContent = (TextView) findViewById(R.id.repost_content);
        mRecyclerView = (RecyclerView) findViewById(R.id.ImgList);
        mBlankspace = (ImageView) findViewById(R.id.blankspace);
        mIdea_linearLayout = (LinearLayout) findViewById(R.id.idea_linearLayout);
        mTvRePostNum = (TextView) findViewById(R.id.tv_repost_inputtext_num);//输入字数
        mRlRepostLocation = (RelativeLayout) findViewById(R.id.rl_repost_location);//底部位置栏
        mRlCommentReweet = (RelativeLayout) findViewById(R.id.rl_comment_reweet);//同时转发
        mIdeaType = getIntent().getStringExtra("ideaType");
        mStatus = getIntent().getParcelableExtra("status");
        mComment = getIntent().getParcelableExtra("comment");
        mInputType.setText(mIdeaType);
        refreshUserName();
        initContent();
        setUpListener();
        mEditText.setTag(false);

        if (getIntent().getBooleanExtra("startAlumbAcitivity", false) == true) {
            Intent intent = new Intent(RePostWeiboActivity.this, AlbumActivity.class);
            intent.putParcelableArrayListExtra("selectedImglist", mSelectImgList);
            startActivityForResult(intent, 0);

        }
        mEditText.post(new Runnable() {
            @Override
            public void run() {
                setLimitTextColor(mLimitTextView, mEditText.getText().toString());
                mEditText.requestFocus();
            }
        });


    }

    private void setLimitTextColor(TextView mLimitTextView, String content) {
        long length = calculateWeiboLength(content);
        if (length > TEXT_LIMIT) {
            long outOfNum = length - TEXT_LIMIT;
            mLimitTextView.setTextColor(Color.parseColor("#e03f22"));
            mLimitTextView.setText("-" + outOfNum + "");
        } else if (length == TEXT_LIMIT) {
            mLimitTextView.setText(0 + "");
            mLimitTextView.setTextColor(Color.parseColor("#929292"));
        } else if (TEXT_LIMIT - length <= TEXT_REMIND) {
            mLimitTextView.setText(TEXT_LIMIT - length + "");
            mLimitTextView.setTextColor(Color.parseColor("#929292"));
        } else {
            mLimitTextView.setText("");
        }
    }

    /**
     * 计算微博文本的长度，统计是否超过140个字，其中中文和全角的符号算1个字符，英文字符和半角字符算半个字符
     *
     * @param c
     * @return 微博的长度，结果四舍五入
     */
    public long calculateWeiboLength(CharSequence c) {
        double len = 0;
        for (int i = 0; i < c.length(); i++) {
            int temp = (int) c.charAt(i);
            if (temp > 0 && temp < 127) {
                len += 0.5;
            } else {
                len++;
            }
        }
        return Math.round(len);
    }


    /****
     * 填充转发内容
     *  1.转发的原创内容
     *  2.转发的是转发内容
     */
    private void initContent() {
        switch (mIdeaType){
            case PostService.POST_SERVICE_COMMENT_STATUS://评论微博
                //评论微博
                commentWeibo();
            break;
            case PostService.POST_SERVICE_CREATE_WEIBO://发微博
                //发微博
                createWeibo();
            break;
            case PostService.POST_SERVICE_REPLY_COMMENT://回复评论
            break;
            case PostService.POST_SERVICE_REPOST_STATUS://转发微博
                //填充转发的内容
                repostWeiBo();
            break;
        }
    }

    /***
     * 发微博
     */
    private void createWeibo() {
        if (mStatus == null){
            return;
        }
        mRepostlayout.setVisibility(View.GONE);
        mRlRepostLocation.setVisibility(View.VISIBLE);
        mRlCommentReweet.setVisibility(View.GONE);
        mEditText.setHint("说说新鲜事...");

        mEditText.setFilters(new InputFilter[]{
                new InputFilter.LengthFilter(140 + weiBoContentLength)
        });

        EditTextChangeListenner(false);
        changeSendButtonBg();
    }

    /***
     * 评论微博
     */
    private void commentWeibo() {
        if (mStatus == null){
            return;
        }
        mRepostlayout.setVisibility(View.GONE);
        mRlRepostLocation.setVisibility(View.GONE);
        mRlCommentReweet.setVisibility(View.VISIBLE);
        mEditText.setHint("写评论yo...");

        mEditText.setFilters(new InputFilter[]{
                new InputFilter.LengthFilter(140 + weiBoContentLength)
        });

        EditTextChangeListenner(false);
        changeSendButtonBg();
    }

    /*****
     * 转发微博的内容
     */
    private int weiBoContentLength;
    private boolean original = false;
    private void repostWeiBo() {
        if (mStatus == null){
            return;
        }
        mRepostlayout.setVisibility(View.VISIBLE);
        mRlCommentReweet.setVisibility(View.GONE);
        mEditText.setHint("说说分享心得...");

        //如果转发的是转发微博
        if (mStatus.retweeted_status != null ){
            weiBoContentLength = WeiBoContentTextUtil.getWeiBoContent("//@" + mStatus.user.name + ":"
                    + mStatus.text, mContext, mEditText).length();
            mEditText.setText(WeiBoContentTextUtil.getWeiBoContent("//@"+mStatus.user.name + ":"
                    + mStatus.text,mContext,mEditText));
            FillContent.fillMentionCenterContent(mStatus.retweeted_status,repostImg,repostName,repostContent);
            mEditText.setSelection(0);//光标处在最前端
            mEditText.setFilters(new InputFilter[]{
                    new InputFilter.LengthFilter(TEXT_LIMIT + weiBoContentLength)
            });
            EditTextChangeListenner(true);

        }else if (mStatus.retweeted_status == null ){//转发的是原创微博
            FillContent.fillMentionCenterContent(mStatus,repostImg,repostName,repostContent);
            mEditText.setFilters(new InputFilter[]{
                    new InputFilter.LengthFilter(TEXT_LIMIT)
            });

            EditTextChangeListenner(false);

        }
        ILog.d(Tag,"转发微博的内容---------------------------");
        changeSendButtonBg();//根据输入的文本数量，改变发送按钮的状态
    }

    private int length;

    private void EditTextChangeListenner(boolean original) {
        mEditText.addTextChangedListener(watcher);
    }

    /**转发输入框的监听事件***/
    private TextWatcher watcher = new TextWatcher() {
        private CharSequence inputString;
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            inputString = s;
            mTvRePostNum.setText(0+" "+ "/ 140");
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            changeSendButtonBg();
            setLimitTextColor(mLimitTextView, inputString.toString());
            mTvRePostNum.setText((original? s.length() : s.length() - weiBoContentLength)+" "+ "/ 140");

            if (length >= 135 && length < TEXT_LIMIT) {
                    ShowToast.Show(mContext,"还可以输入"+ (TEXT_LIMIT - length)+"个字");
                }else if (length >= TEXT_LIMIT){
                    ShowToast.Show(mContext,"字数已经达到140个字，不能再输入");

            }

            ILog.d(Tag,"监听---------------------------");
            ILog.d(Tag,mEditText.getText().toString() + "11111-----------");
        }
    };


    /**
     * 设置监听事件
     */
    private void setUpListener() {
        mCancal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.zoomin,R.anim.zoomout);//TODO:
            }
        });
        picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RePostWeiboActivity.this, AlbumActivity.class);
                intent.putParcelableArrayListExtra("selectedImglist", mSelectImgList);
                startActivityForResult(intent, 0);
            }
        });
        mentionbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditText.getText().insert(mEditText.getSelectionStart(), "@");
            }
        });
        trendbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditText.getText().insert(mEditText.getSelectionStart(), "##");
                mEditText.setSelection(mEditText.getSelectionStart() - 1);
            }
        });
        emoticonbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showShort(mContext, "正在开发此功能...");
            }
        });
        more_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showShort(mContext, "正在开发此功能...");
            }
        });
        mEditText.addTextChangedListener(watcher);
        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //在发微博状态下，如果发的微博没有图片，且也没有文本内容，识别为空
                if (!isRetweetWeiBoState() && mStatus == null && mSelectImgList.size() == 0 && (mEditText.getText().toString().isEmpty() || mEditText.getText().toString().length() == 0)) {
                    ToastUtil.showShort(mContext, "发送的内容不能为空");
                    return;
                }

//                if (calculateWeiboLength(mEditText.getText().toString()) > TEXT_LIMIT) {
//                    ToastUtil.showShort(mContext, "文本超出限制" + TEXT_LIMIT + "个字！请做调整");
//                    return;
//                }
                if (mSelectImgList.size() > 1) {
                    ToastUtil.showShort(mContext, "由于新浪的限制，第三方微博客户端只允许上传一张图，请做调整");
                    return;
                }
                Intent intent = new Intent(mContext, PostService.class);
                Bundle bundle = new Bundle();

                switch (mIdeaType) {
                    case PostService.POST_SERVICE_CREATE_WEIBO:
                        WeiBoCreateBean weiboBean = new WeiBoCreateBean(mEditText.getText().toString(), mSelectImgList);
                        intent.putExtra("postType", PostService.POST_SERVICE_CREATE_WEIBO);
                        bundle.putParcelable("weiBoCreateBean", weiboBean);
                        intent.putExtras(bundle);
                        break;
                    case PostService.POST_SERVICE_REPOST_STATUS:
                        WeiBoCreateBean repostBean = new WeiBoCreateBean(mEditText.getText().toString(), mSelectImgList, mStatus);
                        intent.putExtra("postType", PostService.POST_SERVICE_REPOST_STATUS);
                        bundle.putParcelable("weiBoCreateBean", repostBean);
                        intent.putExtras(bundle);
                        break;
                    case PostService.POST_SERVICE_COMMENT_STATUS:
                        intent.putExtra("postType", PostService.POST_SERVICE_COMMENT_STATUS);
                        WeiBoCommentBean weiBoCommentBean = new WeiBoCommentBean(mEditText.getText().toString(), mStatus);
                        bundle.putParcelable("weiBoCommentBean", weiBoCommentBean);
                        intent.putExtras(bundle);
                        break;
                    case PostService.POST_SERVICE_REPLY_COMMENT:
                        intent.putExtra("postType", PostService.POST_SERVICE_REPLY_COMMENT);
                        CommentReplyBean commentReplyBean = new CommentReplyBean(mEditText.getText().toString(), mComment);
                        bundle.putParcelable("commentReplyBean", commentReplyBean);
                        intent.putExtras(bundle);
                        break;
                }
                startService(intent);
                finish();
            }
        });
        mSendButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    pressSendButton();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    changeSendButtonBg();
                }
                return false;
            }
        });

        mBlankspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KeyBoardUtil.openKeybord(mEditText, mContext);
            }
        });

    }

    private void pressSendButton() {

            mSendButton.setBackgroundResource(R.drawable.compose_send_corners_highlight_press_bg);
            mSendButton.setTextColor(Color.parseColor("#ebeef3"));
    }

    /**刷新顶部用户名**/
    private void refreshUserName() {
        long uid = Long.parseLong(AccessTokenKeeper.readAccessToken(mContext).getUid());
        mUsersAPI.show(uid, new RequestListener() {
            @Override
            public void onComplete(String response) {//成功
                User user = User.parse(response);
                if (user != null){
                    mUserName.setText(user.name);//设置用户名
                }
            }

            @Override
            public void onWeiboException(WeiboException e) {//异常
                ErrorInfo errorInfo = ErrorInfo.parse(e.getMessage());
                ShowToast.Show(mContext,errorInfo+"");
            }
        });
    }


    /**
     * 根据输入的文本数量，决定发送按钮的背景
     */
    private void changeSendButtonBg() {
        //如果有文本，或者有图片，或者是处于转发微博状态
        if (mEditText.getText().toString().length() > 0 || mSelectImgList.size() > 0 || (isRetweetWeiBoState())) {
            highlightSendButton();
        } else {
            sendNormal();
        }
    }

    private void sendNormal() {
        mSendButton.setBackgroundResource(R.drawable.compose_send_corners_bg);
        mSendButton.setTextColor(Color.parseColor("#b3b3b3"));
        mSendButton.setClickable(false);
        mSendButton.setEnabled(false);
    }

    private void highlightSendButton() {
        mSendButton.setBackgroundResource(R.drawable.compose_send_corners_highlight_bg);
        mSendButton.setTextColor(Color.parseColor("#fbffff"));
        mSendButton.setClickable(true);
        mSendButton.setEnabled(true);
    }

    /**
     * 判断此页是处于转发微博还是发微博状态
     *
     * @return
     */
    public boolean isRetweetWeiBoState() {
        if (mStatus != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 0:
                if (data != null) {
                    mSelectImgList = data.getParcelableArrayListExtra("selectImgList");
                    initImgList();
                    changeSendButtonBg();
                }
                break;
        }
    }

    public void initImgList() {
        mRecyclerView.setVisibility(View.VISIBLE);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 3);
        ImgListAdapter imgListAdapter = new ImgListAdapter(mSelectImgList, mContext);
        imgListAdapter.setOnFooterViewClickListener(this);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(imgListAdapter);
    }

    @Override
    public void OnFooterViewClick() {
        Intent intent = new Intent(RePostWeiboActivity.this, AlbumActivity.class);
        intent.putParcelableArrayListExtra("selectedImglist", mSelectImgList);
        startActivityForResult(intent, 0);
    }
}
