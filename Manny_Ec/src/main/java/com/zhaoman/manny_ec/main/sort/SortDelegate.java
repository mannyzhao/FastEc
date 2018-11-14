package com.zhaoman.manny_ec.main.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.zhaoman.manny_core.delegates.bottom.BottomItemDelagate;
import com.zhaoman.manny_ec.R;

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
}
