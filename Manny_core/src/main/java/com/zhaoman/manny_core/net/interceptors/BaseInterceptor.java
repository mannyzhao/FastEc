package com.zhaoman.manny_core.net.interceptors;

import java.io.IOException;
import java.util.LinkedHashMap;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Author:zhaoman
 * Date:2018/11/9
 * Description：
 */
public abstract class BaseInterceptor implements Interceptor {


    //GET 方式中 获取url中的所有参数对
    protected LinkedHashMap<String,String> getUrlParameters(Chain chain){
        final HttpUrl url=chain.request().url();
        int size=url.querySize();
        final LinkedHashMap<String,String> params=new LinkedHashMap<>();
        for (int i = 0; i <size ; i++) {
            params.put(url.queryParameterName(i),url.queryParameterValue(i));
        }

        return params;
    }
    //GET 方式中通过key 获取指定的value
    protected String getUrlParameters(Chain chain,String  key){
        final Request request=chain.request();
        return request.url().queryParameter(key);
    }

    //POST 方式中 从请求体中获取所有参数对
    protected LinkedHashMap<String,String> getBodyparameters(Chain chain){

        final FormBody formBody= (FormBody) chain.request().body();
        final LinkedHashMap<String,String> params=new LinkedHashMap<>();
        int size=formBody.size();

        for (int i = 0; i <size ; i++) {
            params.put(formBody.name(i),formBody.value(i));

        }
        return params;

    }


    protected String getBodyparameters(Chain chain,String key){

        return getBodyparameters(chain).get(key);
    }
}
