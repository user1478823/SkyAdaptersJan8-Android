package com.example.skyadapters4.recycler;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.skyadapters3.RxBackground;
import com.example.skyadapters3.ToolbarAdapter;
import com.example.skyadapters3.ToolbarCustomizer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ttlnisoffice on 12/20/17.
 */

public abstract class RvActivityWithBackToggle extends RvBase implements RxBackground.RxBackgroundInterface {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getMyTheme() != null) {
            Integer theme = getMyTheme().getTheme();
            setTheme(theme);
        }
        setContentView(getActivityView());
        ToolbarAdapter toolbarAdapter = new ToolbarAdapter(this, getActivityView());
        toolbarAdapter.buildToolbarWithHomeUp();
        if (customizeToolbar() != null) {
            if (customizeToolbar().setToolbarTitle() != null) {
                toolbarAdapter.setToolbarTitle(customizeToolbar().setToolbarTitle());
            }
            if (customizeToolbar().setToolbarColor() != null) {
                toolbarAdapter.setToolbarColor(customizeToolbar().setToolbarColor());
            }
            if (customizeToolbar().setToolbarTextColor() != null) {
                toolbarAdapter.setToolbarTextColor(customizeToolbar().setToolbarTextColor());
            }
            if (customizeToolbar().setToolbarTypeFace() != null) {
                toolbarAdapter.setToolbarTypeFace(customizeToolbar().setToolbarTypeFace());
            }
        }

        ViewGroup vg = (ViewGroup) getLayoutInflater().inflate(getActivityView(), null);

        Integer rvID = null;
        for (int i = 0; i < vg.getChildCount(); i++) {
            if (vg.getChildAt(i) instanceof RecyclerView) {
                rvID = vg.getChildAt(i).getId();
            }
        }

        initRv(rvID);
        new RxBackground().executeInBackground(this, this);
    }

    public abstract int getActivityView();

    public abstract RvAdapter.RvInterface rvOnBind();
    public abstract RecyclerView.LayoutManager rvLayoutManager();
    public abstract ArrayList<Integer> rvCustomRow_holderIDS();

    public abstract ToolbarCustomizer customizeToolbar();
    public abstract ThemeManager getMyTheme();

    @Override
    public Integer getView() {
        return getActivityView();
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return rvLayoutManager();
    }

    @Override
    public ArrayList<Integer> getRvCustomRow_holderIDS() {
        return rvCustomRow_holderIDS();
    }

    @Override
    public RvAdapter.RvInterface getRvOnBind() {
        return rvOnBind();
    }

    @Override
    public List getDoInBackground() {
        return doInBackground();
    }

    @Override
    public void getOnResultReceived(List value) {
        onResultReceived(value);
    }

    public abstract List doInBackground();
    public abstract void onResultReceived(List value);
}
