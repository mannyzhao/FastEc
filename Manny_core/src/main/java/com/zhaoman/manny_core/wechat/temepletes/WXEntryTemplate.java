package com.zhaoman.manny_core.wechat.temepletes;


import com.zhaoman.manny_core.activities.ProxyActivity;
import com.zhaoman.manny_core.delegates.MannyDelegate;
import com.zhaoman.manny_core.wechat.BaseWXEntryActivity;
import com.zhaoman.manny_core.wechat.MannyWeChat;

public class WXEntryTemplate extends BaseWXEntryActivity {

    @Override
    protected void onResume() {
        super.onResume();
        finish();
        //无动画效果消失
        overridePendingTransition(0,0);
    }

    @Override
    protected void onSignInSuccess(String userInfo) {

        MannyWeChat.getInstance().getSignInCallback().onSignInSuccess(userInfo);

    }
}
