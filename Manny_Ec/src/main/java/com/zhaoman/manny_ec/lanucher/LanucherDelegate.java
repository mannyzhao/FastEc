package com.zhaoman.manny_ec.lanucher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.zhaoman.manny_core.app.AccountManager;
import com.zhaoman.manny_core.app.IUserChecker;
import com.zhaoman.manny_core.delegates.MannyDelegate;
import com.zhaoman.manny_core.ui.launcher.ILauncherListener;
import com.zhaoman.manny_core.ui.launcher.OnLauncherFinishTag;
import com.zhaoman.manny_core.ui.launcher.ScrollLanucherTag;
import com.zhaoman.manny_core.utils.storage.MannyPreference;
import com.zhaoman.manny_core.utils.timer.BaseTimerTask;
import com.zhaoman.manny_core.utils.timer.ITimerListener;
import com.zhaoman.manny_ec.R;
import com.zhaoman.manny_ec.R2;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author:zhaoman
 * Date:2018/11/10
 * Description：启动页面
 */
public class LanucherDelegate extends MannyDelegate implements ITimerListener {

    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView mTvTimer;
    private Timer mTimer;
    private int mCount = 5;

    private ILauncherListener mILauncherListener=null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener) mILauncherListener = (ILauncherListener) activity;
    }

    @OnClick(R2.id.tv_launcher_timer)
    void onClickTimerView() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
            checkIsShowScroll();
        }
    }
    private void initTimer() {
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task, 0, 1000);
    }

    private void checkIsShowScroll(){
        if (!MannyPreference.getAppFlag(ScrollLanucherTag.HAS_FIRST_LAUNCHER_APP.name())){
            start(new LauncherScrollDelegate(),SINGLETASK);
        }else{
            //   检查用户是否已经登录了app

            AccountManager.checkAccount(new IUserChecker() {
                @Override
                public void onSignIn() {
                    if (mILauncherListener!=null){

                        mILauncherListener.onLanucherFinish(OnLauncherFinishTag.SIGNED);
                    }

                }

                @Override
                public void onNotSignIn() {
                    if (mILauncherListener!=null){

                        mILauncherListener.onLanucherFinish(OnLauncherFinishTag.NOT_SIGNED);
                    }
                }
            });
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_lanucher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {


        // mTvTimer=rootView.findViewById(R.id.tv_launcher_timer);
        initTimer();
    }

    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mTvTimer != null) {
                    mTvTimer.setText(MessageFormat.format("跳过\n{0}s", mCount));
                    mCount--;
                    if (mCount < 0) {
                        if (mTimer != null) {
                            mTimer.cancel();
                            mTimer = null;
                            checkIsShowScroll();
                        }
                    }
                }

            }
        });
    }
}
