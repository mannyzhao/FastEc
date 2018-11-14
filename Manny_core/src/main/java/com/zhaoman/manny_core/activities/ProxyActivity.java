package com.zhaoman.manny_core.activities;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ContentFrameLayout;

import com.zhaoman.manny_core.R;
import com.zhaoman.manny_core.delegates.MannyDelegate;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * 单activity 开发模式，整个项目中只存在一个activity，作为容器，继承自Fragmentation 中的SupportActivity
 */
public abstract class ProxyActivity extends SupportActivity {


    public abstract MannyDelegate setRootDelegate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }


    //初始化根容器
    private void initContainer(@Nullable Bundle savedInstanceState){

        @SuppressLint("RestrictedApi")
        final ContentFrameLayout container=new ContentFrameLayout(this);
        container.setId(R.id.delegate_container);
        setContentView(container);
        if (savedInstanceState==null){
            loadRootFragment(R.id.delegate_container,setRootDelegate());
        }
    }


    /**
     * 由于是单activity架构 可以在这做一些垃圾回收的操作
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }
}
