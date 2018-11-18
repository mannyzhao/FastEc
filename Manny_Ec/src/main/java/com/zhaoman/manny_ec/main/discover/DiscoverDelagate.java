package com.zhaoman.manny_ec.main.discover;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.zhaoman.manny_core.delegates.bottom.BottomItemDelagate;
import com.zhaoman.manny_core.delegates.web.WebDelegateImpl;
import com.zhaoman.manny_ec.R;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Author:zhaoman
 * Date:2018/11/15
 * Description:
 */
public class DiscoverDelagate extends BottomItemDelagate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_discover;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }


    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

        final WebDelegateImpl delegate=WebDelegateImpl.create("index.html");
        delegate.setTopDelegate(this.getParentDelegate());

        loadRootFragment(R.id.web_container,delegate);
    }


    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }
}
