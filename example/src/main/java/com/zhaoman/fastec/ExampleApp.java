package com.zhaoman.fastec;

import android.app.Application;


import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.zhaoman.manny_core.app.Manny;

import com.zhaoman.manny_core.net.interceptors.DebugInterceptor;
import com.zhaoman.manny_ec.database.DatabaseManager;
import com.zhaoman.manny_ec.icon.FontEcModule;

public class ExampleApp  extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Manny.init(this)
                .withApiHost("http://127.0.0.1/")
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withInterceptor(new DebugInterceptor("index",R.raw.test))
                .withWeChatAppId("")
                .withWeChatAppSecret("")
                .configure();
        //初始化数据库
        DatabaseManager.getInstance().init(this);


    }
}
