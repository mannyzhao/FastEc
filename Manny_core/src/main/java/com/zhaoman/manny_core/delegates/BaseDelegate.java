package com.zhaoman.manny_core.delegates;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhaoman.manny_core.activities.ProxyActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * 基础的delegate  就是fragment
 */
public abstract class BaseDelegate extends SwipeBackFragment {

    //提供方法 供子类传布局，可以是view 也可是id
    public abstract Object setLayout();

    private Unbinder mUnbinder=null;

    //具体的控件的一些绑定操作
    public abstract void onBindView(@Nullable Bundle savedInstanceState,View rootView);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View rootView;
        //如果传入的是布局id
        if (setLayout() instanceof Integer){
            rootView=inflater.inflate((Integer)setLayout(),container,false);
        }else if (setLayout() instanceof View){

            rootView=(View) setLayout();
        }else{
            throw new ClassCastException("setlayout() type must be int or view");
        }
        mUnbinder = ButterKnife.bind(this, rootView);
        onBindView(savedInstanceState,rootView);


        return rootView;
    }


    public final ProxyActivity getProxyActivity(){

        return (ProxyActivity) _mActivity;
    }


    @Override
    public void onDestroyView() {
         super.onDestroyView();
        if (mUnbinder!=null){
            mUnbinder.unbind();
        }
    }
}
