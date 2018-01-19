package com.danilo.skyadapters.recycler;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * Created by ttlnisoffice on 12/29/17.
 */

public abstract class RvBase extends AppCompatActivity {

    public  List list = null;
    public  RvAdapter adapter;
    public RecyclerView rv;

    public void initRv(Integer rvID) {
        if (rvID != null) {
            rv = (RecyclerView) findViewById(rvID);
            rv.setLayoutManager(getLayoutManager());
            /*if (getEndlessRecyclerOnScrollListener() != null) {
                rv.addOnScrollListener(getEndlessRecyclerOnScrollListener());
            }*/
        } else {
            Toast.makeText(this, "Error: RvID is null", Toast.LENGTH_LONG).show();
        }
    }

    //public abstract EndlessRecyclerOnScrollListener getEndlessRecyclerOnScrollListener();

    public void populateRv(List value) {
        if (list == null) {
            list = value;
            adapter = new RvAdapter(list,
                    getRvCustomRow_holderIDS().subList(1, getRvCustomRow_holderIDS().size()),
                    getRvCustomRow_holderIDS().get(0),
                    getRvOnBind());
            rv.setAdapter(adapter);
        } else {
            list.addAll(value);
            adapter.notifyDataSetChanged();
        }
    }

    public abstract RecyclerView.LayoutManager getLayoutManager();
    public abstract ArrayList<Integer> getRvCustomRow_holderIDS();
    public abstract RvAdapter.RvInterface getRvOnBind();

    public Observer buildObserver(final ObserverInterface observerInterface) {
        return new Observer<List>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(List value) {
                observerInterface.onNext(value);
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        };
    }

    public interface ObserverInterface {
        public void onNext(List value);
    }
}
