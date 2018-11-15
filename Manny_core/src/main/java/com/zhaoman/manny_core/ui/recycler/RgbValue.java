package com.zhaoman.manny_core.ui.recycler;

import com.google.auto.value.AutoValue;

/**
 * Author:zhaoman
 * Date:2018/11/15
 * Description:
 */
@AutoValue
public abstract class RgbValue {

    public abstract  int red();

    public abstract  int green();
    public abstract  int blue();

    public static RgbValue create(int red,int green,int blue){

        return new AutoValue_RgbValue(red,green,blue);

    }
}
