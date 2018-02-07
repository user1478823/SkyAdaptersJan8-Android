package com.danilo.skyadapters.recycler.pojo;

import android.content.Intent;
import android.graphics.Typeface;

import com.danilo.skyadapters.RvAdapter;

/**
 * Created by ttlnisoffice on 1/16/18.
 */

public class ToolbarWithDrawerPOJO extends ToolbarWithUpPOJO {

    private Intent[]                           drawerActivitiesToLaunch;
    private RvAdapter.RvDrawerAdapterInterface drawerAdapterInterface;
    private Integer                            drawerItemsColor;
    private Integer                            drawerCustomRow;
    private Integer                            drawerMenu;
    private Integer                            numberOfRows;

    public ToolbarWithDrawerPOJO(Integer toolbarColor, String title, Typeface typeface, Integer textColor, Integer drawerItemsColor, Integer drawerCustomRow, Integer drawerMenu, Integer numberOfRows, Intent[] drawerActivitiesToLaunch) {
        super(toolbarColor, title, typeface, textColor);
        this.drawerActivitiesToLaunch = drawerActivitiesToLaunch;
        this.drawerItemsColor = drawerItemsColor;
        this.drawerCustomRow = drawerCustomRow;
        this.drawerMenu = drawerMenu;
        this.numberOfRows = numberOfRows;
    }

    public ToolbarWithDrawerPOJO(Integer toolbarColor, String title, Typeface typeface, Integer textColor, Integer drawerItemsColor, Integer drawerCustomRow, Integer drawerMenu, Integer numberOfRows, RvAdapter.RvDrawerAdapterInterface drawerAdapterInterface) {
        super(toolbarColor, title, typeface, textColor);
        this.drawerAdapterInterface = drawerAdapterInterface;
        this.drawerItemsColor = drawerItemsColor;
        this.drawerCustomRow = drawerCustomRow;
        this.drawerMenu = drawerMenu;
        this.numberOfRows = numberOfRows;
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

    public Integer getDrawerCustomRow() {
        return drawerCustomRow;
    }

    public void setDrawerCustomRow(Integer drawerCustomRow) {
        this.drawerCustomRow = drawerCustomRow;
    }

    public Integer getDrawerMenu() {
        return drawerMenu;
    }

    public void setDrawerMenu(Integer drawerMenu) {
        this.drawerMenu = drawerMenu;
    }

    public Integer getNumberOfRows() {
        return numberOfRows;
    }

    public void setNumberOfRows(Integer numberOfRows) {
        this.numberOfRows = numberOfRows;
    }

    public RvAdapter.RvDrawerAdapterInterface getDrawerAdapterInterface() {
        return drawerAdapterInterface;
    }

    public void setDrawerAdapterInterface(RvAdapter.RvDrawerAdapterInterface drawerAdapterInterface) {
        this.drawerAdapterInterface = drawerAdapterInterface;
    }
}
