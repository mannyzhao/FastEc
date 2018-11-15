package com.zhaoman.manny_ec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.joanzapata.iconify.widget.IconTextView;
import com.zhaoman.manny_core.delegates.MannyDelegate;
import com.zhaoman.manny_core.delegates.bottom.BottomItemDelagate;
import com.zhaoman.manny_core.ui.recycler.BaseDecoration;
import com.zhaoman.manny_core.ui.refresh.RefreshHandler;
import com.zhaoman.manny_ec.R;
import com.zhaoman.manny_ec.R2;

import butterknife.BindView;

import static com.zhaoman.manny_core.ui.recycler.BaseDecoration.create;

/**
 * Author:zhaoman
 * Date:2018/11/13
 * Description:
 */
public class IndexDelegate extends BottomItemDelagate{

    @BindView(R2.id.rv_index)
    RecyclerView mRecyclerView=null;
    @BindView(R2.id.srl_index)
    SwipeRefreshLayout mRefreshLayout=null;
    @BindView(R2.id.tb_index)
    Toolbar mToobar=null;
    @BindView(R2.id.icon_index_scan)
    IconTextView mIconScan;
    @BindView(R2.id.et_search_view)
    AppCompatEditText mSearchView=null;

    private RefreshHandler mRefreshHandler=null;
    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

        mRefreshHandler = RefreshHandler.create(mRefreshLayout,mRecyclerView,new IndexDataConvert());
    }

    private void initRefreshLayout(){

        mRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        mRefreshLayout.setProgressViewOffset(true,120,300);
    }


    private void  initRecycleView(){

        final GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration(BaseDecoration.create(ContextCompat.getColor(getContext(),R.color.colorAccent),5));

        final EcBottomDelegate ecBottomDelegate = getParentDelegate();
        mRecyclerView.addOnItemTouchListener(IndexItemClickListener.create(ecBottomDelegate));

    }


    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefreshLayout();
        initRecycleView();
       mRefreshHandler.firstPage("index");
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }


}
