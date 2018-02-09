package com.danilo.skyadapters.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ttlnisoffice on 12/19/17.
 */

public class RvAdapter extends RecyclerView.Adapter<RvHolder> {

    private RvActivity a;
    private List list;
    private List<Integer> ids;
    private List<Integer> li = new ArrayList<>();
    private int customRow;
    private RvAdapterInterface rvAdapterInterface;

    public RvAdapter(RvActivity a, List list, List<Integer> ids, int customRow, RvAdapterInterface rvAdapterInterface) {
        this.a = a;
        this.list = list;
        this.ids = ids;
        this.customRow = customRow;
        this.rvAdapterInterface = rvAdapterInterface;
        setHasStableIds(true);
    }

    @Override
    public RvHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (ids.size() == 0) {
            findAllIDs((ViewGroup) inflater.inflate(customRow,null));
            return new RvHolder(inflater.inflate(customRow, parent, false), li);
        } else {
            return new RvHolder(inflater.inflate(customRow, parent, false), ids);
        }
    }

    @Override
    public void onBindViewHolder(RvHolder holder, int position) {
        rvAdapterInterface.onBindViewHolder(holder, position);
        if (a.getEn().getVisibleTreshold() == position + 1) {
            a.rv.addOnScrollListener((a.getEn()));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface RvAdapterInterface {
        public void onBindViewHolder(RvHolder holder, int position);
    }

    private void findAllIDs(ViewGroup viewGroup) {
        int count = viewGroup.getChildCount();
        for (int i = 0; i < count; i++) {
            View view = viewGroup.getChildAt(i);
            if (view.getId() != -1) {
                li.add(view.getId());
            }
            else if (view instanceof ViewGroup) {
                findAllIDs((ViewGroup) view);
            }
        }
    }

}
