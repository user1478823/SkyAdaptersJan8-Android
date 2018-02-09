package com.danilo.skyadapters.recycler;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ttlnisoffice on 12/20/17.
 */

public class RvManager {

    private RvActivity a;
    private RecyclerView rv;
    private int customRow;
    private List rvList;
    private ArrayList<Integer> ids;

    public RvManager(RvActivity a) {
        this.a = a;
    }

    public RvManager oneInitRv(int rvID) {
        rv = (RecyclerView) a.findViewById(rvID);
        return this;
    }

    public RvManager twoCustomRow(int customRow) {
        this.customRow = customRow;
        return this;
    }

    public RvManager threeRvSize(List rvList) {
        this.rvList = rvList;
        return this;
    }

    public RvManager fourHolderIDS(ArrayList<Integer> ids) {
        this.ids = ids;
        return this;
    }

    public RvManager fiveLayoutManager(RecyclerView.LayoutManager manager) {
        rv.setLayoutManager(manager);
        return this;
    }

    public void sixFinnalOnBind(RvAdapter.RvAdapterInterface rvAdapterInterface) {
        rv.setAdapter(new RvAdapter(a, rvList, ids, customRow, rvAdapterInterface));
    }
}
