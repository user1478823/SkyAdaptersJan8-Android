package com.example.skyadapters4.recycler;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by ttlnisoffice on 12/20/17.
 */

public abstract class RvActivity extends AppCompatActivity {

    RvAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getView());
    }

    public void setupRV() {
        RecyclerView rv = (RecyclerView) findViewById(initRv());
        rv.setLayoutManager(rvLayoutManager());
        adapter = new RvAdapter(rvList(), holderIDS(), rvCustomRow(), rvOnBind());
        rv.setAdapter(adapter);
    }

    public abstract int getView();
    public abstract int initRv();
    public abstract int rvCustomRow();
    public abstract List rvList();
    public abstract List<Integer> holderIDS();
    public abstract RecyclerView.LayoutManager rvLayoutManager();
    public abstract RvAdapter.RvInterface rvOnBind();

    public void updateRV() {
        adapter.notifyDataSetChanged();
    }
}
