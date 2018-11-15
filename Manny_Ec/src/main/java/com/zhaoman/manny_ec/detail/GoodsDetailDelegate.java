package com.zhaoman.manny_ec.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.zhaoman.manny_core.delegates.MannyDelegate;
import com.zhaoman.manny_ec.R;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Author:zhaoman
 * Date:2018/11/15
 * Description:
 */
public class GoodsDetailDelegate extends MannyDelegate {


    public static GoodsDetailDelegate create(){

       return new GoodsDetailDelegate();
    }
    @Override
    public Object setLayout() {
        return R.layout.delagate_goods_detail;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }
}
