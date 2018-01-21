package com.danilo.skyadapters;

/**
 * Created by ttlnisoffice on 1/16/18.
 */

public class ActivityPOJO {

    int     view;
    Integer theme;

    public ActivityPOJO(int view, Integer theme) {
        this.view = view;
        this.theme = theme;
        
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public Integer getTheme() {
        return theme;
    }

    public void setTheme(Integer theme) {
        this.theme = theme;
    }
}
