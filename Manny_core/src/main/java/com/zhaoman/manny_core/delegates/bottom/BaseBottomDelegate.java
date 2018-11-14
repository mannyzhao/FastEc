package com.zhaoman.manny_core.delegates.bottom;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.joanzapata.iconify.widget.IconTextView;
import com.zhaoman.manny_core.R;
import com.zhaoman.manny_core.R2;
import com.zhaoman.manny_core.delegates.MannyDelegate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Author:zhaoman
 * Date:2018/11/13
 * Description:
 */
public abstract class BaseBottomDelegate extends MannyDelegate  implements View.OnClickListener{

    private final ArrayList<BottomItemDelagate> ITEM_DELEGATES=new ArrayList<>();
    private final ArrayList<BottomTabBean> TAB_BEANS=new ArrayList<>();

    private final LinkedHashMap<BottomTabBean,BottomItemDelagate> ITEMS=new LinkedHashMap<>();

    private int mCurrentDelegate=0;
    private int mIndexDelegate=0;
    private int mClickedColor= Color.RED;
    @BindView(R2.id.bottom_bar)
    LinearLayoutCompat mBottomBar=null;
    public abstract LinkedHashMap<BottomTabBean,BottomItemDelagate> setItems(ItemBuilder builder);

    public abstract int setIndexDelegate();
    @ColorInt
    public abstract int setClickedColor();

    @Override
    public Object setLayout() {
        return R.layout.delegate_bottom;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mIndexDelegate=setIndexDelegate();
        if (setClickedColor()!=0){
            mClickedColor=setClickedColor();
        }

        final ItemBuilder builder=ItemBuilder.builder();
        final LinkedHashMap<BottomTabBean,BottomItemDelagate> items=setItems(builder);
        ITEMS.putAll(items);
        for(Map.Entry<BottomTabBean,BottomItemDelagate> item:ITEMS.entrySet()){

            final BottomTabBean key = item.getKey();
            final BottomItemDelagate value = item.getValue();
            TAB_BEANS.add(key);
            ITEM_DELEGATES.add(value);

        }

    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        final  int size= ITEMS.size();
        for (int i = 0; i < size; i++) {

             LayoutInflater.from(getContext()).inflate(R.layout.bottom_item_icon_text_layout,mBottomBar);
             final RelativeLayout item= (RelativeLayout) mBottomBar.getChildAt(i);
             //设置每个item 的点击事件
            item.setTag(i);
            item.setOnClickListener(this);
            final IconTextView itemIcon= (IconTextView) item.getChildAt(0);
            final AppCompatTextView itemText= (AppCompatTextView) item.getChildAt(1);
            final BottomTabBean bean=TAB_BEANS.get(i);
            //初始化数据
            itemIcon.setText(bean.getICON());
            itemText.setText(bean.getTITLE());

            if (i==mIndexDelegate){
                itemIcon.setTextColor(mClickedColor);
                itemText.setTextColor(mClickedColor);
            }
        }

        final SupportFragment[] delagateArray=ITEM_DELEGATES.toArray(new SupportFragment[size]);
        loadMultipleRootFragment(R.id.bottom_bar_delegate_container,mIndexDelegate,delagateArray);
    }




    private void resetColor(){
        final int count=mBottomBar.getChildCount();

        for (int i = 0; i <count ; i++) {
            final RelativeLayout item= (RelativeLayout) mBottomBar.getChildAt(i);
            final IconTextView itemIcon= (IconTextView) item.getChildAt(0);
            final AppCompatTextView itemText= (AppCompatTextView) item.getChildAt(1);
            itemIcon.setTextColor(Color.GRAY);
            itemText.setTextColor(Color.GRAY);
        }
    }


    @Override
    public void onClick(View v) {
        final int tag=(int)v.getTag();
        resetColor();
        final RelativeLayout item= (RelativeLayout) v;
        final IconTextView itemIcon= (IconTextView) item.getChildAt(0);
        final AppCompatTextView itemText= (AppCompatTextView) item.getChildAt(1);
        itemIcon.setTextColor(mClickedColor);
        itemText.setTextColor(mClickedColor);
        showHideFragment(ITEM_DELEGATES.get(tag),ITEM_DELEGATES.get(mCurrentDelegate));
        //注意先后顺序
        mCurrentDelegate=tag;
    }
}
