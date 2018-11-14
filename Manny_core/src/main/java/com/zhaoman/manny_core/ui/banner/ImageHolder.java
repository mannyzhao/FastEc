package com.zhaoman.manny_core.ui.banner;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;

/**
 * Author:zhaoman
 * Date:2018/11/14
 * Description:
 */
public class ImageHolder implements Holder<String> {

    private AppCompatImageView mImageView=null;

    @Override
    public View createView(Context context) {
        mImageView=new AppCompatImageView(context);
        return mImageView;
    }

    @Override
    public void UpdateUI(Context context, int position, String data) {
        Glide.with(context)
                .load(data)
                .into(mImageView);
    }
}
