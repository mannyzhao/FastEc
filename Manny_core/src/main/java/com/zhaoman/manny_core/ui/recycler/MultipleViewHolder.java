package com.zhaoman.manny_core.ui.recycler;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Author:zhaoman
 * Date:2018/11/14
 * Description:
 */
public class MultipleViewHolder extends BaseViewHolder {

    public MultipleViewHolder(View view) {
        super(view);
    }

    public static MultipleViewHolder create(View view){

        return new MultipleViewHolder(view);
    }
}
