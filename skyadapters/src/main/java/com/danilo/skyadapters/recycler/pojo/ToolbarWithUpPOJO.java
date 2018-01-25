package com.danilo.skyadapters.recycler.pojo;

import android.graphics.Typeface;

/**
 * Created by ttlnisoffice on 1/25/18.
 */

public class ToolbarWithUpPOJO extends ToolbarPOJO {

    private String   title;
    private Typeface typeface;
    private Integer  textColor;

    public ToolbarWithUpPOJO(Integer toolbarColor, String title, Typeface typeface, Integer textColor) {
        super(toolbarColor);
        this.title     = title;
        this.typeface  = typeface;
        this.textColor = textColor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Typeface getTypeface() {
        return typeface;
    }

    public void setTypeface(Typeface typeface) {
        this.typeface = typeface;
    }

    public Integer getTextColor() {
        return textColor;
    }

    public void setTextColor(Integer textColor) {
        this.textColor = textColor;
    }
}
