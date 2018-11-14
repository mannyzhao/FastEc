package com.zhaoman.manny_core.ui.loader;


import android.content.Context;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.util.WeakHashMap;

/**
 * loading 图形
 */
public class LoaderCreator {

    private static final WeakHashMap<String,Indicator> LOADING_MAP=new WeakHashMap<>();

    static AVLoadingIndicatorView create(String type, Context context){

        final AVLoadingIndicatorView avLoadingIndicatorView=new AVLoadingIndicatorView(context);

        if (LOADING_MAP.get(type)==null){

            final Indicator indicator=getIndictor(type);
            LOADING_MAP.put(type,indicator);
        }

        avLoadingIndicatorView.setIndicator(LOADING_MAP.get(type));
        return avLoadingIndicatorView;
    }



    private static Indicator getIndictor(String name){
        if (name==null|| name.isEmpty()){

            return null;
        }
        final StringBuilder drawableClassName=new StringBuilder();
        if (!name.contains(".")){

            final String defaultaPackageName=AVLoadingIndicatorView.class.getPackage().getName();
            drawableClassName.append(defaultaPackageName)
                    .append(".indicators")
                    .append(".");
        }

        drawableClassName.append(name);
        try {
            final Class<?> drawableClass=Class.forName(drawableClassName.toString());

            return (Indicator) drawableClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
