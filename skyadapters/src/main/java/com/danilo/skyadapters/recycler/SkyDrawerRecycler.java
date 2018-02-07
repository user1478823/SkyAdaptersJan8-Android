package com.danilo.skyadapters.recycler;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.danilo.skyadapters.ErrorNotifications;
import com.danilo.skyadapters.R;


/**
 * Created by ttlnisoffice on 1/15/18.
 */

public class SkyDrawerRecycler extends RecyclerView {

    private Integer       customRow;
    private Integer       menuID;
    private LayoutManager layoutManager;

    public SkyDrawerRecycler(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta = context.getTheme().obtainStyledAttributes
                (attrs, R.styleable.SkyDrawerRecycler, 0, 0);

        customRow = ta.getResourceId(R.styleable.SkyDrawerRecycler_drawerCustomRow, 0);

        menuID = ta.getResourceId(R.styleable.SkyDrawerRecycler_drawerMenu, R.menu.default_drawer_items);
        if (menuID == R.menu.default_drawer_items) {
            new ErrorNotifications().displayError(context, "Add app:drawerMenu=§ into SkyDrawerRecycler xml");
        }
        int span = ta.getInt(R.styleable.SkyDrawerRecycler_drawerNumberOfRows, 1);

        layoutManager = new GridLayoutManager(context, span);

        ta.recycle();
    }

    public Integer getDrawerCustomRow() {
        return customRow;
    }

    public Integer getMenuID() {
        return menuID;
    }

    @Override
    public LayoutManager getLayoutManager() {
        return layoutManager;
    }
}
