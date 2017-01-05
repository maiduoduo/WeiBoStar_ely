package cn.star.weibo.login.home.bean;

import android.os.Parcel;
import android.os.Parcelable;

import cn.star.weibo.bean.Status;


/**
 * User: dcl
 * Method/Class Name:CommentReplyBean
 * Date: 2016-12-06
 * About:
 * param:
 * FIXME
 */
public class WeiBoCommentBean implements Parcelable {
    /**
     * 要发送的文本
     */
    public String content;
    /**
     * 要评论的微博或者
     */
    public Status status;

    public WeiBoCommentBean(String content, Status status) {
        this.content = content;
        this.status = status;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.content);
        dest.writeParcelable(this.status, flags);
    }

    protected WeiBoCommentBean(Parcel in) {
        this.content = in.readString();
        this.status = in.readParcelable(Status.class.getClassLoader());
    }

    public static final Creator<WeiBoCommentBean> CREATOR = new Creator<WeiBoCommentBean>() {
        @Override
        public WeiBoCommentBean createFromParcel(Parcel source) {
            return new WeiBoCommentBean(source);
        }

        @Override
        public WeiBoCommentBean[] newArray(int size) {
            return new WeiBoCommentBean[size];
        }
    };
}
