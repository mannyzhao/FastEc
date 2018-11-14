package com.zhaoman.manny_core.ui.recycler;

import java.util.ArrayList;

/**
 * Author:zhaoman
 * Date:2018/11/14
 * Description:
 */
public abstract class DataConverter {

    protected final ArrayList<MultipleItemEntity> ENTITES=new ArrayList<>();

    private String mJsonData=null;

    public abstract ArrayList<MultipleItemEntity> convert();

    public DataConverter setJsonData(String  json){
        this.mJsonData=json;
        return this;
    }

    protected String  getJsonData(){
        if (mJsonData==null||mJsonData.isEmpty()){
            throw new NullPointerException("DATA IS NULL");
        }

        return mJsonData;
    }

}
