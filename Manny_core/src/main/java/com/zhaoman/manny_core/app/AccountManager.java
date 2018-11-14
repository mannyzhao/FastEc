package com.zhaoman.manny_core.app;

import com.zhaoman.manny_core.utils.storage.MannyPreference;

/**
 * Author:zhaoman
 * Date:2018/11/12
 * Description:
 */
public class AccountManager {
    private enum SignTag{
        SIGN_TAG
    }

    //保存用户登录状态
    public static void setSignState(boolean state){

        MannyPreference.setAppFlag(SignTag.SIGN_TAG.name(),state);
    }


    private static boolean isSingIn(){

        return MannyPreference.getAppFlag(SignTag.SIGN_TAG.name());
    }

    public static void checkAccount(IUserChecker checker){


        if (isSingIn()){
            checker.onSignIn();
        }else{

            checker.onNotSignIn();
        }

    }
}
