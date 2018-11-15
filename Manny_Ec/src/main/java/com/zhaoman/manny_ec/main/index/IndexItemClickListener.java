package com.zhaoman.manny_ec.main.index;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.zhaoman.manny_core.delegates.MannyDelegate;
import com.zhaoman.manny_ec.detail.GoodsDetailDelegate;

import retrofit2.http.DELETE;

/**
 * Author:zhaoman
 * Date:2018/11/15
 * Description:
 */
public class IndexItemClickListener extends SimpleClickListener {

    private final MannyDelegate DELEGATE;

    public IndexItemClickListener(MannyDelegate delegate) {
        this.DELEGATE = delegate;
    }

    public static IndexItemClickListener create(MannyDelegate delegate){

        return new IndexItemClickListener(delegate);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

        final GoodsDetailDelegate goodsDetailDelegate = GoodsDetailDelegate.create();
        DELEGATE.start(goodsDetailDelegate);


    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
