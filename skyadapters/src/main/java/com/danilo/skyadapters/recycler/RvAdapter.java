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
    private List adapterList;
    private List<Integer> ids;
    private List<Integer> li = new ArrayList<>();
    private int customRow;
    private RvAdapterInterface rvAdapterInterface;


    public RvAdapter(RvActivity a, List list, List<Integer> ids, int customRow, RvAdapterInterface rvAdapterInterface) {
        this.a = a;
        this.adapterList = list;
        this.ids = ids;
        this.customRow = customRow;
        this.rvAdapterInterface = rvAdapterInterface;
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
        a.onBind(adapterList.get(holder.getAdapterPosition()), holder, holder.getAdapterPosition());
        //rvAdapterInterface.onBindViewHolder(adapterList.get(position), holder, position);
        if (a.getEn() != null && position == a.list.size() - a.getEn().getOnWhichPositionToDisableIsLoading()) {
            a.getEn().setLoading(false);
        }
    }

    @Override
    public int getItemCount() {
        return adapterList.size();
    }

    public void updateDataSet(List value) {
        this.adapterList.addAll(value);
        notifyDataSetChanged();
    }

    public interface RvAdapterInterface {
        public void onBindViewHolder(Object list, RvHolder holder, int position);
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
