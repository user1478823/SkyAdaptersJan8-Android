package com.danilo.skyadapters.recycler;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.danilo.skyadapters.R;

/**
 * Created by ttlnisoffice on 1/10/18.
 */

public class SkyRecycler extends RecyclerView {

    private Integer customRow;

    public SkyRecycler(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.getTheme().obtainStyledAttributes
                (attrs, R.styleable.SkyRecycler, 0, 0);

        customRow = ta.getInteger(R.styleable.SkyRecycler_customRow, 0);
    }

    public Integer getCustomRow() {
        return customRow;
    }
}
