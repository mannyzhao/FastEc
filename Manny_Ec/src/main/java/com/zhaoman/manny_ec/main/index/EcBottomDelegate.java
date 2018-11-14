package com.zhaoman.manny_ec.main.index;

import com.zhaoman.manny_core.delegates.bottom.BaseBottomDelegate;
import com.zhaoman.manny_core.delegates.bottom.BottomItemDelagate;
import com.zhaoman.manny_core.delegates.bottom.BottomTabBean;
import com.zhaoman.manny_core.delegates.bottom.ItemBuilder;
import com.zhaoman.manny_ec.main.sort.SortDelegate;

import java.util.LinkedHashMap;

/**
 * Author:zhaoman
 * Date:2018/11/13
 * Description:
 */
public class EcBottomDelegate extends BaseBottomDelegate {


    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelagate> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean,BottomItemDelagate> ITEMS=new LinkedHashMap<>();
        ITEMS.put(new BottomTabBean("{fa-home}","主页"),new IndexDelegate());
        ITEMS.put(new BottomTabBean("{fa-sort}","分类"),new SortDelegate());
        ITEMS.put(new BottomTabBean("{fa-compass}","发现"),new IndexDelegate());
        ITEMS.put(new BottomTabBean("{fa-shopping-cart}","购物车"),new IndexDelegate());
        ITEMS.put(new BottomTabBean("{fa-user}","我的"),new IndexDelegate());
        return builder.addItems(ITEMS).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return 0;
    }
}
