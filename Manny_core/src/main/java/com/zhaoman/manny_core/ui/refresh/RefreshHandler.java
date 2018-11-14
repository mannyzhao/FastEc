package com.zhaoman.manny_core.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;

import com.zhaoman.manny_core.app.Manny;

import java.nio.file.FileAlreadyExistsException;

/**
 * Author:zhaoman
 * Date:2018/11/14
 * Description:
 */
public class RefreshHandler  implements SwipeRefreshLayout.OnRefreshListener {

    private final SwipeRefreshLayout REFRESH_LAYOUT;

    public RefreshHandler(SwipeRefreshLayout REFRESH_LAYOUT) {
        this.REFRESH_LAYOUT = REFRESH_LAYOUT;
        REFRESH_LAYOUT.setOnRefreshListener(this);
    }

    private void refresh(){
        REFRESH_LAYOUT.setRefreshing(true);
        Manny.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                REFRESH_LAYOUT.setRefreshing(false);
            }
        },2000);

    }

    @Override
    public void onRefresh() {
        refresh();
    }
}
