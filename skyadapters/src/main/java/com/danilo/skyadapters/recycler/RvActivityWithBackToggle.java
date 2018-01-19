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

    private ToolbarPOJO.ToolbarCustomizer toolbarCustomizer;

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

        ToolbarPOJO toolbarP;
        if (ToolbarPOJO.ToolbarCustomizer.class.isAssignableFrom(this.getClass())) {
            toolbarCustomizer = ((ToolbarPOJO.ToolbarCustomizer) this);
            toolbarP = toolbarCustomizer.customizeToolbar();

            if (toolbarP.getTitle()     != null) toolbarAdapter.setToolbarTitle(toolbarP.getTitle());
            if (toolbarP.getColor()     != null) toolbarAdapter.setToolbarColor(toolbarP.getColor());
            if (toolbarP.getTextColor() != null) toolbarAdapter.setToolbarTextColor(toolbarP.getTextColor());
            if (toolbarP.getTypeface()  != null) toolbarAdapter.setToolbarTypeFace(toolbarP.getTypeface());
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
    public abstract ArrayList<Integer> rvCustomRow_holderIDS();

    //public abstract ToolbarPOJO customizeToolbar();

    @Override
    public ArrayList<Integer> getRvCustomRow_holderIDS() {
        return rvCustomRow_holderIDS();
    }

    @Override
    public RvAdapter.RvInterface getRvOnBind() {
        return rvOnBind();
    }
}
