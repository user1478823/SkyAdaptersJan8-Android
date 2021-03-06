package com.danilo.skyadapters.recycler.pojo;

import android.graphics.Typeface;
import android.widget.AdapterView;

import java.util.ArrayList;

/**
 * Created by ttlnisoffice on 1/25/18.
 */

public class ToolbarWithSpinnerPOJO extends ToolbarPOJO {

    private ArrayList<String>                  spinnerItems;
    private Integer                            customSpinnerLayout;
    private AdapterView.OnItemSelectedListener listener;

    public ToolbarWithSpinnerPOJO(Integer toolbarColor, ArrayList<String> spinnerItems, Integer customSpinnerLayout, AdapterView.OnItemSelectedListener listener) {
        super(toolbarColor);
        this.spinnerItems = spinnerItems;
        this.customSpinnerLayout = customSpinnerLayout;
        this.listener = listener;
    }

    public ArrayList<String> getSpinnerItems() {
        return spinnerItems;
    }

    public void setSpinnerItems(ArrayList<String> spinnerItems) {
        this.spinnerItems = spinnerItems;
    }

    public Integer getCustomSpinnerLayout() {
        return customSpinnerLayout;
    }

    public void setCustomSpinnerLayout(Integer customSpinnerLayout) {
        this.customSpinnerLayout = customSpinnerLayout;
    }

    public AdapterView.OnItemSelectedListener getListener() {
        return listener;
    }

    public void setListener(AdapterView.OnItemSelectedListener listener) {
        this.listener = listener;
    }
}
