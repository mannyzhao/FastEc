package com.zhaoman.manny_core.wechat;

import android.app.Activity;

import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zhaoman.manny_core.app.ConfigType;
import com.zhaoman.manny_core.app.Manny;
import com.zhaoman.manny_core.wechat.callbacks.IWXChatSignInCallback;

/**
 * Author:zhaoman
 * Date:2018/11/12
 * Description:
 */
public class MannyWeChat {

    static final String APP_ID=Manny.getConfiguration(ConfigType.WE_CHAT_APP_ID);
    static final String SECRET=Manny.getConfiguration(ConfigType.WE_CHAT_APP_SECRET);
    private final  IWXAPI WXAPI;
    private IWXChatSignInCallback mSignInCallback= null;

    private static final class Holder{

        private static final MannyWeChat  INSTANCE=new MannyWeChat();
    }
    public static MannyWeChat getInstance(){
        return Holder.INSTANCE;
    }

    //构造初始化微信
    private  MannyWeChat(){
        final Activity activity = Manny.getConfiguration(ConfigType.ACTIVITY);
        WXAPI= WXAPIFactory.createWXAPI(activity,APP_ID,true);
        WXAPI.registerApp(APP_ID);
    }

    public final IWXAPI getWXAPI(){
        return WXAPI;
    }


    public MannyWeChat onSignSuccess(IWXChatSignInCallback callback){
        this.mSignInCallback=callback;
        return this;
    }

    public IWXChatSignInCallback getSignInCallback(){
        return mSignInCallback;
    }

    public final void signIn(){
        final SendAuth.Req req=new SendAuth.Req();
        req.scope="snsapi_userinfo";
        req.state="random_state";
        WXAPI.sendReq(req);
    }
}
