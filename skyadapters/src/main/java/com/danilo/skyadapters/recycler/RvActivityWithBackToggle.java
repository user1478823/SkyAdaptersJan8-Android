package com.danilo.skyadapters.recycler;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.LinearLayout;


import com.danilo.skyadapters.ActivityPOJO;
import com.danilo.skyadapters.R;
import com.danilo.skyadapters.RxBackground;
import com.danilo.skyadapters.SpinnerAdapter;
import com.danilo.skyadapters.SpinnerPOJO;
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
        ActivityPOJO aP = null;
        if (RvInterface.ActivityContent.class.isAssignableFrom(this.getClass())) {
            RvInterface.ActivityContent activityContent = ((RvInterface.ActivityContent) this);
            aP = activityContent.getActivityContent();
        }

        if (aP == null || aP.getView() == null) {
            setContentView(R.layout.default_layout);
        } else {
            setContentView(aP.getView());
        }
        if (aP != null && aP.getTheme() != null) {
            setTheme(aP.getTheme());
        }

        ToolbarAdapter toolbarAdapter = null;
        if (aP == null || aP.getView() == null) {
            toolbarAdapter = new ToolbarAdapter(this);
        } else {
            toolbarAdapter = new ToolbarAdapter(this, aP.getView());
        }
        toolbarAdapter.buildToolbarWithHomeUp();

        ToolbarPOJO toolbarP;
        if (RvInterface.ToolbarCustomizer.class.isAssignableFrom(this.getClass())) {
            RvInterface.ToolbarCustomizer toolbarCustomizer = ((RvInterface.ToolbarCustomizer) this);
            toolbarP = toolbarCustomizer.customizeToolbar();

            if (toolbarP.getTitle()     != null) toolbarAdapter.setToolbarTitle(toolbarP.getTitle());
            if (toolbarP.getColor()     != null) toolbarAdapter.setToolbarColor(toolbarP.getColor());
            if (toolbarP.getTextColor() != null) toolbarAdapter.setToolbarTextColor(toolbarP.getTextColor());
            if (toolbarP.getTypeface()  != null) toolbarAdapter.setToolbarTypeFace(toolbarP.getTypeface());
        }

        Integer rvID = null;
        if (aP == null || aP.getView() == null) {
            rvID = R.id.rv;
        } else {
            ViewGroup vg = (ViewGroup) getLayoutInflater().inflate(aP.getView(), null);

            for (int i = 0; i < vg.getChildCount(); i++) {
                if (vg.getChildAt(i) instanceof RecyclerView) {
                    rvID = vg.getChildAt(i).getId();
                }
            }
        }
        initRv(rvID);

        if (RvInterface.SpinnerManager.class.isAssignableFrom(this.getClass())) {
            RvInterface.SpinnerManager spinnerManager = ((RvInterface.SpinnerManager) this);
            SpinnerPOJO sP = spinnerManager.attachSpinner();

            LinearLayout ll = findViewById(R.id.ll);

            new SpinnerAdapter(this).attachSpinner(sP.getSpinnerItems(), sP.getCustomSpinnerLayout(),
                                                     sP.getListener(), ll);
        }

        new RxBackground().executeInBackground(this, getRxBackgroundInterface());
    }

    protected abstract RxBackground.RxBackgroundInterface getRxBackgroundInterface();
    //public abstract ActivityPOJO getActivityPOJO();
    public abstract RvAdapter.RvAdapterInterface rvOnBind();
    public abstract ArrayList<Integer> rvCustomRow_holderIDS();

    @Override
    public ArrayList<Integer> getRvCustomRow_holderIDS() {
        return rvCustomRow_holderIDS();
    }

    @Override
    public RvAdapter.RvAdapterInterface getRvOnBind() {
        return rvOnBind();
    }
}
