package com.zhaoman.fastec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.zhaoman.manny_core.activities.ProxyActivity;
import com.zhaoman.manny_core.app.Manny;
import com.zhaoman.manny_core.delegates.MannyDelegate;
import com.zhaoman.manny_core.ui.launcher.ILauncherListener;
import com.zhaoman.manny_core.ui.launcher.OnLauncherFinishTag;
import com.zhaoman.manny_ec.main.index.EcBottomDelegate;
import com.zhaoman.manny_ec.sign.ISignListener;
import com.zhaoman.manny_ec.sign.SignInDelegate;

import qiu.niorgai.StatusBarCompat;


public class MainActivity extends ProxyActivity  implements ISignListener,ILauncherListener {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final android.support.v7.app.ActionBar actionBar= getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }

        Manny.getConfigurator().withActivity(this);

        StatusBarCompat.translucentStatusBar(this,true);


    }

    @Override
    public MannyDelegate setRootDelegate() {
        return new EcBottomDelegate();
    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(this,"登录成功",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSignUpSuccess() {

        Toast.makeText(this,"注册成功",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLanucherFinish(OnLauncherFinishTag tag) {
        switch (tag){

            case SIGNED:
                Toast.makeText(this,"启动结束,用户登录了",Toast.LENGTH_LONG).show();
                startWithPop(new EcBottomDelegate());
                break;
            case NOT_SIGNED:
                Toast.makeText(this,"启动结束,用户没登录",Toast.LENGTH_LONG).show();

                startWithPop(new SignInDelegate());
                break;
                default:
                    break;
        }
    }
}
