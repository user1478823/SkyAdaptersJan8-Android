package com.example.skyadapters4.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by ttlnisoffice on 12/19/17.
 */

public class RvAdapter extends RecyclerView.Adapter<RvHolder> {

    private List list;
    private List<Integer> ids;
    private int customRow;
    private RvInterface rvInterface;

    public RvAdapter(List list, List<Integer> ids, int customRow, RvInterface rvInterface) {
        this.list = list;
        this.ids = ids;
        this.customRow = customRow;
        this.rvInterface = rvInterface;
    }

    @Override
    public RvHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new RvHolder(inflater.inflate(customRow, parent, false), ids);
    }

    @Override
    public void onBindViewHolder(RvHolder holder, int position) {
        rvInterface.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface RvInterface {
        public void onBindViewHolder(RvHolder holder, int position);
    }
}
