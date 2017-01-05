package cn.star.weibo.mvp.presenter.iml;

import android.content.Context;
import android.content.Intent;

import com.nostra13.universalimageloader.core.ImageLoader;

import cn.star.weibo.MainActivity;
import cn.star.weibo.common.login.AccessTokenKeeper;
import cn.star.weibo.mvp.model.TokenListModel;
import cn.star.weibo.mvp.model.iml.TokenListModelImp;
import cn.star.weibo.mvp.presenter.SettingActivityPresent;
import cn.star.weibo.mvp.view.SettingActivityView;
import cn.star.weibo.unlogin.activity.UnLoginActivity;
import cn.star.weibo.utils.ToastUtil;

/**
 * Created by dash on 16/5/18.
 */
public class SettingActivityPresentImp implements SettingActivityPresent {

    private SettingActivityView settingActivityView;
    private TokenListModel tokenListModel;

    public SettingActivityPresentImp(SettingActivityView settingActivityView) {
        this.settingActivityView = settingActivityView;
        tokenListModel = new TokenListModelImp();
    }

    @Override
    public void logout(final Context context) {
        tokenListModel.deleteToken(context, AccessTokenKeeper.readAccessToken(context).getUid());
        AccessTokenKeeper.clear(context);
        tokenListModel.switchToken(context, 0, new TokenListModel.OnTokenSwitchListener() {
            @Override
            public void onSuccess() {
                Intent intent = new Intent(context, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
            }

            @Override
            public void onError(String error) {
                Intent intent = new Intent(context, UnLoginActivity.class);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public void clearCache(Context context) {
        ImageLoader.getInstance().clearDiskCache();
        ImageLoader.getInstance().clearMemoryCache();
        ToastUtil.showShort(context, "缓存清理成功！");
    }
}
