package com.example.skyadapters4.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

/**
 * Created by ttlnisoffice on 12/19/17.
 */

public class RvHolder extends RecyclerView.ViewHolder {

    public View[] views;

    public RvHolder(View itemView, List<Integer> ids) {
        super(itemView);

        views = new View[ids.size()];

        for (int i = 0; i < ids.size(); i++) {
            views[i] = itemView.findViewById(ids.get(i));
        }
    }
}
