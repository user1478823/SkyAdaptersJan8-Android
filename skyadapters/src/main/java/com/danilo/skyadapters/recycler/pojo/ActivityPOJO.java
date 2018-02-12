package com.danilo.skyadapters.recycler.pojo;

/**
 * Created by ttlnisoffice on 1/16/18.
 */

public class ActivityPOJO {

    private Integer view;
    private Integer theme;

    public ActivityPOJO(Integer view, Integer theme) {
        this.view = view;
        this.theme = theme;
        
    }

    public Integer getView() {
        return view;
    }

    public void setView(Integer view) {
        this.view = view;
    }

    public Integer getTheme() {
        return theme;
    }

    public void setTheme(Integer theme) {
        this.theme = theme;
    }
}
