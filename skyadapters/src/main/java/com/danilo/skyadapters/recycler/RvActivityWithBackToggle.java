package com.danilo.skyadapters.recycler;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;


import com.danilo.skyadapters.ActivityPOJO;
import com.danilo.skyadapters.RxBackground;
import com.danilo.skyadapters.ToolbarAdapter;
import com.danilo.skyadapters.ToolbarPOJO;

import java.util.ArrayList;

/**
 * Created by ttlnisoffice on 12/20/17.
 */

public abstract class RvActivityWithBackToggle extends RvBase {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityPOJO aP = getActivityPOJO();
        if (aP.getTheme() != null) {
            Integer theme = aP.getTheme();
            setTheme(theme);
        }
        setContentView(aP.getView());
        ToolbarAdapter toolbarAdapter = new ToolbarAdapter(this, aP.getView());
        toolbarAdapter.buildToolbarWithHomeUp();

        ToolbarPOJO toolbar = customizeToolbar();

        if (toolbar != null) {
            if (toolbar.getTitle()     != null) toolbarAdapter.setToolbarTitle(toolbar.getTitle());
            if (toolbar.getColor()     != null) toolbarAdapter.setToolbarColor(toolbar.getColor());
            if (toolbar.getTextColor() != null) toolbarAdapter.setToolbarTextColor(toolbar.getTextColor());
            if (toolbar.getTypeface()  != null) toolbarAdapter.setToolbarTypeFace(toolbar.getTypeface());
        }

        ViewGroup vg = (ViewGroup) getLayoutInflater().inflate(aP.getView(), null);

        Integer rvID = null;
        for (int i = 0; i < vg.getChildCount(); i++) {
            if (vg.getChildAt(i) instanceof RecyclerView) {
                rvID = vg.getChildAt(i).getId();
            }
        }

        initRv(rvID);
        new RxBackground().executeInBackground(this, getRxBackgroundInterface());
    }

    protected abstract RxBackground.RxBackgroundInterface getRxBackgroundInterface();
    public abstract ActivityPOJO getActivityPOJO();
    public abstract RvAdapter.RvInterface rvOnBind();
    public abstract RecyclerView.LayoutManager rvLayoutManager();
    public abstract ArrayList<Integer> rvCustomRow_holderIDS();

    public abstract ToolbarPOJO customizeToolbar();

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
}
