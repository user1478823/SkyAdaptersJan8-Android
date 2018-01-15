package com.danilo.skyadapters.recycler;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.danilo.skyadapters.R;


/**
 * Created by ttlnisoffice on 1/15/18.
 */

public class SkyDrawerRecycler extends RecyclerView {

    private Integer customRow;
    private Integer menuID;

    public SkyDrawerRecycler(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta = context.getTheme().obtainStyledAttributes
                (attrs, R.styleable.SkyDrawerRecycler, 0, 0);

        customRow = ta.getResourceId(R.styleable.SkyDrawerRecycler_drawerCustomRow, 0);
        ta.recycle();

        menuID = ta.getResourceId(R.styleable.SkyDrawerRecycler_drawerMenu, 0);
    }

    public Integer getDrawerCustomRow() {
        return customRow;
    }

    public Integer getMenuID() {
        return menuID;
    }
}
