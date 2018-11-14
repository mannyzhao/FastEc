package com.zhaoman.manny_ec.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhaoman.manny_core.app.AccountManager;
import com.zhaoman.manny_ec.database.DatabaseManager;
import com.zhaoman.manny_ec.database.UserProfile;

/**
 * Author:zhaoman
 * Date:2018/11/12
 * Description:
 */
public class SignHandler {

    //注册
    public static void onSignUp(String response,ISignListener signListener){

        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
        DatabaseManager.getInstance().getDao().insert(profile);
        //已经注册完并登录成功了
        AccountManager.setSignState(true);
        signListener.onSignUpSuccess();
    }


    //登录
    public static void onSignIn(String response,ISignListener signListener){

        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
        DatabaseManager.getInstance().getDao().insert(profile);
        //已经注册完并登录成功了
        AccountManager.setSignState(true);
        signListener.onSignInSuccess();
    }
}


