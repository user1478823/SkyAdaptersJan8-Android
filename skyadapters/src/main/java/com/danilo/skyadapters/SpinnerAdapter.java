package com.danilo.skyadapters;

import android.app.Activity;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.ArrayList;

/**
 * Created by ttlnisoffice on 12/8/17.
 */

public class SpinnerAdapter {

    private Activity a;
    private Spinner spinner;

    public SpinnerAdapter(Activity a) {
        this.a = a;
    }

    public SpinnerAdapter attachSpinner(ArrayList<String> spinnerItems, Integer customSpinnerLayout,
                                        AdapterView.OnItemSelectedListener listener, LinearLayout ll) {
        Spinner spinner = new Spinner(a);
        int layout = android.R.layout.simple_list_item_1;
        if (customSpinnerLayout != null) {
            layout = customSpinnerLayout;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(a, layout, spinnerItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(listener);
        ll.addView(spinner);
        return this;
    }

    public SpinnerAdapter buildSpinner(int spinnerID, ArrayList<String> spinnerItems, Integer customSpinnerLayout,
                                       AdapterView.OnItemSelectedListener listener) {
        spinner = (Spinner) a.findViewById(spinnerID);
        int layout = android.R.layout.simple_list_item_1;
        if (customSpinnerLayout != null) {
            layout = customSpinnerLayout;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(a, layout, spinnerItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(listener);

        return this;
    }

    public SpinnerAdapter setSpinnerColor(int color){
        Drawable spinnerDrawable = spinner.getBackground().getConstantState().newDrawable();
        spinnerDrawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            spinner.setBackground(spinnerDrawable);
        }else{
            spinner.setBackgroundDrawable(spinnerDrawable);
        }
        return this;
    }
}
