package com.danilo.skyadapters.recycler.pojo;

import android.content.Intent;
import android.graphics.Typeface;

/**
 * Created by ttlnisoffice on 1/16/18.
 */

public class ToolbarWithDrawerPOJO extends ToolbarWithUpPOJO {

    private Intent[] drawerActivitiesToLaunch;
    private Integer  drawerItemsColor;


    public ToolbarWithDrawerPOJO(Integer toolbarColor, String title, Typeface typeface, Integer textColor, Intent[] drawerActivitiesToLaunch, Integer drawerItemsColor) {
        super(toolbarColor, title, typeface, textColor);
        this.drawerActivitiesToLaunch = drawerActivitiesToLaunch;
        this.drawerItemsColor = drawerItemsColor;
    }

    public Intent[] getDrawerActivitiesToLaunch() {
        return drawerActivitiesToLaunch;
    }

    public void setDrawerActivitiesToLaunch(Intent[] drawerActivitiesToLaunch) {
        this.drawerActivitiesToLaunch = drawerActivitiesToLaunch;
    }

    public Integer getDrawerItemsColor() {
        return drawerItemsColor;
    }

    public void setDrawerItemsColor(Integer drawerItemsColor) {
        this.drawerItemsColor = drawerItemsColor;
    }
}
