package com.zhaoman.manny_core.app;

import android.content.Context;

import java.util.HashMap;

/**
 * 不希望别人更改和继承 所以为final
 */
public final class Manny {

    /**
     * 对配置型进行初始化
     * @param context
     * @return
     */
   public static Configurator init(Context context){
       Configurator.getInstance()
               .getMannyConfigs()
               .put(ConfigType.APPLICATION_CONTEXT,context.getApplicationContext());
       return Configurator.getInstance();

   }


    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }


    public static <T> T getConfiguration(Object key){

       return getConfigurator().getConfiguration(key);
    }

    public static Context getApplication(){

       return (Context) getConfiguration(ConfigType.APPLICATION_CONTEXT);
   }
}
