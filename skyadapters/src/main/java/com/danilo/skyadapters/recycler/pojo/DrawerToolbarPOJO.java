package com.danilo.skyadapters.recycler.pojo;

import android.graphics.Typeface;

/**
 * Created by ttlnisoffice on 1/16/18.
 */

public class DrawerToolbarPOJO extends ToolbarPOJO {

    private Integer drawerItemsColor;

    public DrawerToolbarPOJO(Integer color, Integer drawerItemsColor) {
        //super(title, color, typeface, textColor);
        super(color);
        this.drawerItemsColor = drawerItemsColor;
    }

    public interface DrawerToolbarCustomizer {
        public DrawerToolbarPOJO customizeDrawerToolbar();
    }

    public Integer getDrawerItemsColor() {
        return drawerItemsColor;
    }

    public void setDrawerItemsColor(Integer drawerItemsColor) {
        this.drawerItemsColor = drawerItemsColor;
    }
}
