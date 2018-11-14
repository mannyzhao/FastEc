package com.zhaoman.manny_core.app;


import android.app.Activity;
import android.os.Handler;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Interceptor;


/**
 * 管理各种配置项 配置文件的存储以及获取
 */
public class Configurator {

    //存储信息的数据结构 存储的数据可能是各种数据类型 故使用object
    private static final HashMap<Object,Object> MANNY_CONFIGS=new HashMap<>();
    //存储字体信息
    private static final ArrayList<IconFontDescriptor> ICONS=new ArrayList<>();
    //网络拦截器
    private static final ArrayList<Interceptor>  INTERCEPTORS=new ArrayList<>();

    private static final Handler HANDLER = new Handler();

    private Configurator(){
        //刚开始进行初始化的时候  必定是没有完成的 所以传入false
        MANNY_CONFIGS.put(ConfigType.CONFIG_READY,false);
        MANNY_CONFIGS.put(ConfigType.HANDLER, HANDLER);
    }


    /**
     * 静态内部类 单例模式实现. 线程安全的
     */

    public static Configurator getInstance(){

        return Holder.INSTANCE;
    }
    private static class Holder{

        private static final Configurator INSTANCE=new Configurator();

    }

    /**
     * 获取配置项数据结构共外部调用
     * @return 返回配置数据结构
     */
    final HashMap<Object,Object> getMannyConfigs(){

        return MANNY_CONFIGS;
    }

    /**
     * 表明配置已经全部完成  类似Builder 模式 的最后的build
     */
    public final void configure(){
        initIcons();
        MANNY_CONFIGS.put(ConfigType.CONFIG_READY,true);
    }

    /**
     * 初始化字体数据
     */
    private void initIcons(){
        //如果里面已经保存了字体数据
        if (ICONS.size()>0){

            final Iconify.IconifyInitializer initializer=Iconify.with(ICONS.get(0));

            for (int i = 0; i <ICONS.size() ; i++) {

                initializer.with(ICONS.get(i));
            }
        }

    }




    /**
     * 配置网络访问主机地址
     * @param host 传入的主机地址
     * @return 返回本身
     */
    public final Configurator withApiHost(String host){

        MANNY_CONFIGS.put(ConfigType.API_HOST,host);
        return this;
    }


    public final Configurator withIcon(IconFontDescriptor descriptor){

        ICONS.add(descriptor);
        return this;
    }

    public final Configurator withInterceptor(Interceptor interceptor){

        INTERCEPTORS.add(interceptor);
        MANNY_CONFIGS.put(ConfigType.INTERCEPTOR,INTERCEPTORS);
        return this;
    }


    public final Configurator withWeChatAppId(String appId){

        MANNY_CONFIGS.put(ConfigType.WE_CHAT_APP_ID,appId);
        return this;
    }

    public final Configurator withWeChatAppSecret(String appSecret){

        MANNY_CONFIGS.put(ConfigType.WE_CHAT_APP_SECRET,appSecret);
        return this;
    }

    public final Configurator withActivity(Activity activity){

        MANNY_CONFIGS.put(ConfigType.ACTIVITY,activity);
        return this;
    }
    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors){

        INTERCEPTORS.addAll(interceptors);
        MANNY_CONFIGS.put(ConfigType.INTERCEPTOR,INTERCEPTORS);
        return this;
    }


    /**
     * 检查配置是否已经完成
     */

    private void checkConfiguration(){

        final boolean isReady=(boolean)MANNY_CONFIGS.get(ConfigType.CONFIG_READY);
        //如果配置没有完成 抛出运行时异常
        if (!isReady){
            throw new RuntimeException("Configuration is not ready,call configure");
        }

    }


    /**
     * 获取具体某项的配置数据
     * @param key 传入的配置类型
     * @param <T> 泛型
     * @return 返回值
     */
    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Object key) {

        checkConfiguration();
        final Object value = MANNY_CONFIGS.get(key);
        if (value == null) {
            throw new NullPointerException(key.toString() + " IS NULL");

        }
        return (T) MANNY_CONFIGS.get(key);

    }

}
