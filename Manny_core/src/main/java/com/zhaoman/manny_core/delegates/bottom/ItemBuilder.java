package com.zhaoman.manny_core.delegates.bottom;

import java.util.LinkedHashMap;

/**
 * Author:zhaoman
 * Date:2018/11/13
 * Description:
 */
public final class ItemBuilder {

    private final LinkedHashMap<BottomTabBean,BottomItemDelagate> ITEMS=new LinkedHashMap<>();
    static ItemBuilder builder(){
        return new ItemBuilder();
    }

    public ItemBuilder addItem(BottomTabBean bean,BottomItemDelagate delegate ){
        ITEMS.put(bean,delegate);
        return this;
    }


    public ItemBuilder addItems(LinkedHashMap<BottomTabBean,BottomItemDelagate> items ){
        ITEMS.putAll(items);
        return this;
    }

    public final LinkedHashMap<BottomTabBean,BottomItemDelagate> build(){
        return ITEMS;
    }

}
