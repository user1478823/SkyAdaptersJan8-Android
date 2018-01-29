package com.danilo.skyadapters.recycler;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;


import com.danilo.skyadapters.ToolbarAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ttlnisoffice on 12/20/17.
 */

public abstract class RvActivityWithCustomBackToggle { /*extends RvActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ToolbarAdapter toolbarAdapter = new ToolbarAdapter(this, getActivityView());
        toolbarAdapter.buildToolbarWithCustomBackIcon(getToolbarIcon());
        if (getToolbarTitle() != null) {
            toolbarAdapter.setToolbarTitle(getToolbarTitle());
        }
        if (getToolbarColor() != null) {
            toolbarAdapter.setToolbarColor(getToolbarColor());
        }
        if (getToolbarTextColor() != null) {
            toolbarAdapter.setToolbarTextColor(getToolbarTextColor());
        }
        if (getToolbarTypeFace() != null) {
            toolbarAdapter.setToolbarTypeFace(getToolbarTypeFace());
        }
    }

    public abstract int getToolbarID();
    public abstract int getToolbarIcon();

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
        return getRvSize();
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
    public abstract List getRvSize();
    public abstract ArrayList<Integer> getHolderIDS();
    public abstract RecyclerView.LayoutManager getRvLayoutManager();

    public abstract String   getToolbarTitle();
    public abstract Integer  getToolbarColor();
    public abstract Typeface getToolbarTypeFace();
    public abstract Integer  getToolbarTextColor();*/

}
