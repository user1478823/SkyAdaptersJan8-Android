package com.danilo.skyadapters.recycler;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.LinearLayout;


import com.danilo.skyadapters.recycler.pojo.ActivityPOJO;
import com.danilo.skyadapters.R;
import com.danilo.skyadapters.RxBackground;
import com.danilo.skyadapters.SpinnerAdapter;
import com.danilo.skyadapters.recycler.pojo.SpinnerPOJO;
import com.danilo.skyadapters.ToolbarAdapter;
import com.danilo.skyadapters.recycler.pojo.ToolbarPOJO;
import com.danilo.skyadapters.recycler.pojo.ToolbarWithSpinnerPOJO;
import com.danilo.skyadapters.recycler.pojo.ToolbarWithUpPOJO;

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

        ToolbarPOJO toolbarP;
        if (RvInterface.ToolbarCustomizer.class.isAssignableFrom(this.getClass())) {
            RvInterface.ToolbarCustomizer toolbarCustomizer = ((RvInterface.ToolbarCustomizer) this);
            toolbarP = toolbarCustomizer.customizeToolbar();

            switch (toolbarP.getClass().getSimpleName()) {
                case "ToolbarWithUpPOJO":
                    ToolbarWithUpPOJO toolbarUp = (ToolbarWithUpPOJO) toolbarP;
                    if (toolbarUp.getTitle()     != null) toolbarAdapter.setToolbarTitle    (toolbarUp.getTitle());
                    if (toolbarUp.getTextColor() != null) toolbarAdapter.setToolbarTextColor(toolbarUp.getTextColor());
                    if (toolbarUp.getTypeface()  != null) toolbarAdapter.setToolbarTypeFace (toolbarUp.getTypeface());
                    toolbarAdapter.buildToolbarWithHomeUp();
                    break;
                case "ToolbarWithSpinnerPOJO":
                    ToolbarWithSpinnerPOJO toolbarSpinner = (ToolbarWithSpinnerPOJO) toolbarP;
                    LinearLayout ll = findViewById(R.id.ll);
                    new SpinnerAdapter(this).attachSpinner(toolbarSpinner.getSpinnerItems(),
                                                              toolbarSpinner.getCustomSpinnerLayout(),
                                                              toolbarSpinner.getListener(),
                                                              ll);
                    break;
            }

            if (toolbarP.getColor() != null) toolbarAdapter.setToolbarColor(toolbarP.getColor());
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
        } else {
            toolbarAdapter.buildToolbarWithHomeUp();
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
