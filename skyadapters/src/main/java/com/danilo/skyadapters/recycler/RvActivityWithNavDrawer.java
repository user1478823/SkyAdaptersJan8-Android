package com.danilo.skyadapters.recycler;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;


import com.danilo.skyadapters.recycler.pojo.ActivityPOJO;
import com.danilo.skyadapters.recycler.pojo.ToolbarWithDrawerPOJO;
import com.danilo.skyadapters.RxBackground;
import com.danilo.skyadapters.ToolbarAdapter;

import java.util.ArrayList;

/**
 * Created by ttlnisoffice on 12/22/17.
 */

public abstract class RvActivityWithNavDrawer extends RvBase {

    private ActionBarDrawerToggle toggle;
    //private ToolbarWithDrawerPOJO.DrawerToolbarCustomizer toolbarCustomizer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityPOJO aP = getActivityPOJO();
        if (aP.getTheme() != null) setTheme(aP.getTheme());
        setContentView(aP.getView());

        ViewGroup vg = (ViewGroup) getLayoutInflater().inflate(aP.getView(), null);

        DrawerLayout drawerLayout = null;
        for (int i = 0; i < vg.getChildCount(); i++) {
            if (vg.getChildAt(i) instanceof DrawerLayout) {
                drawerLayout = (DrawerLayout) findViewById(vg.getChildAt(i).getId());
            }
        }

        ArrayList<Integer> rvs = new ArrayList<>();
        for (int i = 0; i < drawerLayout.getChildCount(); i++) {
            if (drawerLayout.getChildAt(i) instanceof RecyclerView) {
                rvs.add(drawerLayout.getChildAt(i).getId());
            }
            if (drawerLayout.getChildAt(i) instanceof LinearLayout) {
                LinearLayout linearLayout = (LinearLayout) findViewById(drawerLayout.getChildAt(i).getId());
                for (int j = 0; j < linearLayout.getChildCount(); j++) {
                    if (linearLayout.getChildAt(j) instanceof RecyclerView) {
                        rvs.add(linearLayout.getChildAt(j).getId());
                    }
                }
            }
        }

        /*ToolbarWithDrawerPOJO toolbarP = null;
        if (ToolbarWithDrawerPOJO.DrawerToolbarCustomizer.class.isAssignableFrom(this.getClass())) {
            toolbarCustomizer = ((ToolbarWithDrawerPOJO.DrawerToolbarCustomizer) this);
            toolbarP = toolbarCustomizer.customizeDrawerToolbar();
        }

        Integer drawerItemsColor = null;
        if (toolbarP != null) {
            drawerItemsColor = toolbarP.getDrawerItemsColor();
        }*/

        initRv(rvs.get(0));
        new RxBackground().executeInBackground(this, getRxBackgroundInterface());

        /*ToolbarAdapter toolbarAdapter = new ToolbarAdapter(this, aP.getView());
        toggle = toolbarAdapter.buildToolbarWithNavDrawer(
                drawerActivitiesToLaunch(),
                drawerItemsColor,
                rvs.get(1));

        if (toggle != null) {
            toggle.syncState();
        }

        /*if (toolbarP != null) {
            if (toolbarP.getTitle()     != null) toolbarAdapter.setToolbarTitle(toolbarP.getTitle());
            if (toolbarP.getColor()     != null) toolbarAdapter.setToolbarColor(toolbarP.getColor());
            if (toolbarP.getTextColor() != null) toolbarAdapter.setToolbarTextColor(toolbarP.getTextColor());
            if (toolbarP.getTypeface()  != null) toolbarAdapter.setToolbarTypeFace(toolbarP.getTypeface());
        }*/
    }

    public abstract RxBackground.RxBackgroundInterface getRxBackgroundInterface();
    public abstract ActivityPOJO getActivityPOJO();
    public abstract Intent[] drawerActivitiesToLaunch();


    @Override
    public RvAdapter.RvAdapterInterface getRvOnBind() {
        return rvOnBind();
    }

    @Override
    public ArrayList<Integer> getRvCustomRow_holderIDS() {
        return rvCustomRow_holderIDS();
    }

    public abstract RvAdapter.RvAdapterInterface rvOnBind();
    public abstract ArrayList<Integer> rvCustomRow_holderIDS();
    //public abstract ToolbarWithDrawerPOJO customizeToolbar();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (this.getClass().getSimpleName().contains("MainActivity") && toggle.onOptionsItemSelected(item)) {
            return true;
        } else return false;
    }
}

