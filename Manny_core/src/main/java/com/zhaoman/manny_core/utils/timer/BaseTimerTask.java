package com.zhaoman.manny_core.utils.timer;

import java.util.TimerTask;

/**
 * Author:zhaoman
 * Date:2018/11/10
 * Description：
 */
public class BaseTimerTask extends TimerTask {

    private ITimerListener mITimerListener=null;

    public BaseTimerTask(ITimerListener mITimerListener) {
        this.mITimerListener = mITimerListener;
    }

    @Override
    public void run() {
        if (mITimerListener!=null){
            mITimerListener.onTimer();
        }
    }
}
