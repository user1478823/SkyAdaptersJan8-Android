package com.danilo.skyadapters.recycler;

import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ttlnisoffice on 12/20/17.
 */

public abstract class RvActivityWithSpinner extends RvActivity {
    public abstract int getToolbarID();

    @Override
    public int getView() {
        return getActivityView();
    }

    @Override
    public int initRv() {
        return getRvID();
    }

    @Override
    public int rvCustomRow() {
        return getRvCustomRow();
    }

    @Override
    public List rvList() {
        return getRvList();
    }

    @Override
    public List<Integer> holderIDS() {
        return getHolderIDS();
    }

    @Override
    public RecyclerView.LayoutManager rvLayoutManager() {
        return getRvLayoutManager();
    }

    @Override
    public RvAdapter.RvAdapterInterface rvOnBind() {
        return getRvOnBind();
    }

    public abstract RvAdapter.RvAdapterInterface getRvOnBind();
    public abstract int getActivityView();
    public abstract int getRvID();
    public abstract int getRvCustomRow();
    public abstract List getRvList();
    public abstract ArrayList<Integer> getHolderIDS();
    public abstract RecyclerView.LayoutManager getRvLayoutManager();

    public abstract String   getToolbarTitle();
    public abstract Integer  getToolbarColor();
    public abstract Typeface getToolbarTypeFace();
    public abstract Integer  getToolbarTextColor();
}
