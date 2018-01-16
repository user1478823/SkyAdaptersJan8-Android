package com.danilo.skyadapters;

import android.graphics.Typeface;

/**
 * Created by ttlnisoffice on 1/16/18.
 */

public class ToolbarPOJO {

    String   title;
    Integer  color;
    Typeface typeface;
    Integer  textColor;

    public ToolbarPOJO() {}

    public ToolbarPOJO(String title, Integer color, Typeface typeface, Integer textColor) {
        this.title = title;
        this.color = color;
        this.typeface = typeface;
        this.textColor = textColor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
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
