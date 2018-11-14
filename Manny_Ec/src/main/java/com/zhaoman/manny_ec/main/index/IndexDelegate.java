package com.zhaoman.manny_ec.main.index;

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
public class IndexDelegate extends BottomItemDelagate{
    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
