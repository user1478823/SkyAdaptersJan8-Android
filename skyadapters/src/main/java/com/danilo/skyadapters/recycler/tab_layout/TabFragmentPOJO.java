package com.danilo.skyadapters.recycler.tab_layout;

/**
 * Created by ttlnisoffice on 2/12/18.
 */

public class TabFragmentPOJO {

    private int tabFragmentLayout;
    private TabFragment.TabFragmentInterface tabFragmentInterface;
    private String tabTitle;
    private Integer tabIcon;

    public TabFragmentPOJO(int tabFragmentLayout, TabFragment.TabFragmentInterface tabFragmentInterface, String tabTitle, int tabIcon) {
        this.tabFragmentLayout = tabFragmentLayout;
        this.tabFragmentInterface = tabFragmentInterface;
        this.tabTitle = tabTitle;
        this.tabIcon = tabIcon;
    }

    public int getTabFragmentLayout() {
        return tabFragmentLayout;
    }

    public void setTabFragmentLayout(int tabFragmentLayout) {
        this.tabFragmentLayout = tabFragmentLayout;
    }

    public TabFragment.TabFragmentInterface getTabFragmentInterface() {
        return tabFragmentInterface;
    }

    public void setTabFragmentInterface(TabFragment.TabFragmentInterface tabFragmentInterface) {
        this.tabFragmentInterface = tabFragmentInterface;
    }

    public String getTabTitle() {
        return tabTitle;
    }

    public void setTabTitle(String tabTitle) {
        this.tabTitle = tabTitle;
    }

    public Integer getTabIcon() {
        return tabIcon;
    }

    public void setTabIcon(Integer tabIcon) {
        this.tabIcon = tabIcon;
    }
}
