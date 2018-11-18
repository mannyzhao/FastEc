package com.zhaoman.manny_core.app;

/**
 * 可能需要管理的配置项
 */
public enum ConfigType {


    //网络请求的域名
    API_HOST,
    //全局的上下文
    APPLICATION_CONTEXT,
    //控制配置的初始化是否完成
    CONFIG_READY,
    ICON,
    //okhttp拦截器
    INTERCEPTOR,

    WE_CHAT_APP_ID,
    WE_CHAT_APP_SECRET,
    ACTIVITY,
    HANDLER,
    JAVASCRIPT_INTERFACE

}
