package cn.star.weibo.login.mine.bean;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;


/**
 * User: dcl
 * Method/Class Name:WeiboBean
 * Date: 2016-12-05
 * About:用户详情微博页的数据封装
 * param:
 * FIXME
 */


public class WeiboBean implements Serializable {
    ImageView ivIcon;
    TextView tvName;
    TextView tvDate;
    TextView tvFrom;
    TextView tvdiscuss;
    TextView tvAite;
    TextView tvContent;
    ImageView ivContent;

    public ImageView getIvIcon() {
        return ivIcon;
    }

    public void setIvIcon(ImageView ivIcon) {
        this.ivIcon = ivIcon;
    }

    public TextView getTvName() {
        return tvName;
    }

    public void setTvName(TextView tvName) {
        this.tvName = tvName;
    }

    public TextView getTvDate() {
        return tvDate;
    }

    public void setTvDate(TextView tvDate) {
        this.tvDate = tvDate;
    }

    public TextView getTvFrom() {
        return tvFrom;
    }

    public void setTvFrom(TextView tvFrom) {
        this.tvFrom = tvFrom;
    }

    public TextView getTvdiscuss() {
        return tvdiscuss;
    }

    public void setTvdiscuss(TextView tvdiscuss) {
        this.tvdiscuss = tvdiscuss;
    }

    public TextView getTvAite() {
        return tvAite;
    }

    public void setTvAite(TextView tvAite) {
        this.tvAite = tvAite;
    }

    public TextView getTvContent() {
        return tvContent;
    }

    public void setTvContent(TextView tvContent) {
        this.tvContent = tvContent;
    }

    public ImageView getIvContent() {
        return ivContent;
    }

    public void setIvContent(ImageView ivContent) {
        this.ivContent = ivContent;
    }
}
