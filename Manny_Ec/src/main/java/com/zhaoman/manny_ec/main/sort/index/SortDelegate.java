package com.zhaoman.manny_ec.main.sort.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.zhaoman.manny_core.delegates.bottom.BottomItemDelagate;
import com.zhaoman.manny_ec.R;
import com.zhaoman.manny_ec.main.sort.content.ContentDelegate;

/**
 * Author:zhaoman
 * Date:2018/11/13
 * Description:
 */
public class SortDelegate extends BottomItemDelagate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final ListVerticalDelegate listVerticalDelegate = new ListVerticalDelegate();
        loadRootFragment(R.id.vertical_list_container,listVerticalDelegate);
      loadRootFragment(R.id.sort_content_container,ContentDelegate.newInstance(1),false,true);



    }
}
