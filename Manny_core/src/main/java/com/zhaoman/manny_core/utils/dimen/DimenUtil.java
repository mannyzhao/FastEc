package com.zhaoman.manny_core.utils.dimen;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.zhaoman.manny_core.app.Manny;

public class DimenUtil {


    public static int getScreenWidth(){

        final Resources resources = Manny.getApplication().getResources();
        final DisplayMetrics displayMetrics=resources.getDisplayMetrics();

        return displayMetrics.widthPixels;
    }



    public static int getScreenHeight(){

        final Resources resources = Manny.getApplication().getResources();
        final DisplayMetrics displayMetrics=resources.getDisplayMetrics();

        return displayMetrics.heightPixels;
    }
}
