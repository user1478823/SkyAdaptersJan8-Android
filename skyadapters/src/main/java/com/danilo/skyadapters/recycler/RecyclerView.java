package com.danilo.skyadapters.recycler;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.danilo.skyadapters.R;

/**
 * Created by ttlnisoffice on 1/10/18.
 */

public class RecyclerView extends RecyclerView {

    private Integer customRow;

    public RecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.getTheme().obtainStyledAttributes
                (attrs, R.styleable.RecyclerView, 0, 0);

        customRow = ta.getResourceId(R.styleable.RecyclerView_customRow, 0);
        ta.recycle();
    }

    public Integer getCustomRow() {
        return customRow;
    }
}
