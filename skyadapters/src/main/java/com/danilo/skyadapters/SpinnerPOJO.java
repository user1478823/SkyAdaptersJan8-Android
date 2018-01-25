package com.danilo.skyadapters;

import android.widget.AdapterView;

import java.util.ArrayList;

/**
 * Created by ttlnisoffice on 1/25/18.
 */

public class SpinnerPOJO {

    private ArrayList<String>                  spinnerItems;
    private Integer                            customSpinnerLayout;
    private AdapterView.OnItemSelectedListener listener;

    public SpinnerPOJO(ArrayList<String> spinnerItems, Integer customSpinnerLayout, AdapterView.OnItemSelectedListener listener) {
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
