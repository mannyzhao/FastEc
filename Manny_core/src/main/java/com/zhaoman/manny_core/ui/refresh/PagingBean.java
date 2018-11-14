package com.zhaoman.manny_core.ui.refresh;

/**
 * Author:zhaoman
 * Date:2018/11/14
 * Description: 用来存储分页相关数据
 */
public final class PagingBean {
    // 当前是第几页
    private int mPageIndex=0;
    //总数据条数
    private int mTotal=0;
    // 一页显示几条数据
    private int mPageSize=0;
    //当前已经显示的数据条数
    private int mCurrent=0;
    //加载延迟
    private int mDelayed=0;


    public int getPageIndex() {
        return mPageIndex;
    }

    public PagingBean setPageIndex(int mPageIndex) {
        this.mPageIndex = mPageIndex;
        return this;
    }

    public int getTotal() {
        return mTotal;
    }

    public PagingBean setTotal(int mTotal) {
        this.mTotal = mTotal;
        return this;
    }

    public int getPageSize() {
        return mPageSize;
    }

    public PagingBean setPageSize(int mPageSize) {
        this.mPageSize = mPageSize;
        return this;
    }

    public int getCurrent() {
        return mCurrent;
    }

    public PagingBean setCurrent(int mCurrent) {
        this.mCurrent = mCurrent;
        return this;
    }

    public int getDelayed() {
        return mDelayed;
    }

    public PagingBean setDelayed(int mDelayed) {
        this.mDelayed = mDelayed;
        return this;
    }

    PagingBean addIndex(){
       mPageIndex++;
       return this;
    }
}
