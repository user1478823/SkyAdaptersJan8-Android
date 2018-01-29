package com.danilo.skyadapters.recycler;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.danilo.skyadapters.recycler.pojo.ActivityPOJO;
import com.danilo.skyadapters.R;
import com.danilo.skyadapters.RxBackground;
import com.danilo.skyadapters.SpinnerAdapter;
import com.danilo.skyadapters.ToolbarAdapter;
import com.danilo.skyadapters.recycler.pojo.ToolbarPOJO;
import com.danilo.skyadapters.recycler.pojo.ToolbarWithDrawerPOJO;
import com.danilo.skyadapters.recycler.pojo.ToolbarWithSpinnerPOJO;
import com.danilo.skyadapters.recycler.pojo.ToolbarWithUpPOJO;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by ttlnisoffice on 12/20/17.
 */

public abstract class RvActivityWithBackToggle extends AppCompatActivity {

    private ActionBarDrawerToggle toggle;
    public List list = null;
    public  RvAdapter adapter;
    public RecyclerView rv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityPOJO aP = null;
        if (RvInterface.ActivityContent.class.isAssignableFrom(this.getClass())) {
            RvInterface.ActivityContent activityContent = ((RvInterface.ActivityContent) this);
            aP = activityContent.getActivityContent();
        }

        if (aP == null || aP.getView() == null) {
            setContentView(R.layout.default_layout);
        } else {
            setContentView(aP.getView());
        }
        if (aP != null && aP.getTheme() != null) {
            setTheme(aP.getTheme());
        }

        ToolbarAdapter toolbarAdapter = new ToolbarAdapter(this);

        ToolbarPOJO toolbarP;
        if (RvInterface.ToolbarCustomizer.class.isAssignableFrom(this.getClass())) {
            RvInterface.ToolbarCustomizer toolbarCustomizer = ((RvInterface.ToolbarCustomizer) this);
            toolbarP = toolbarCustomizer.customizeToolbar();

            if (toolbarP != null) {
                Toolbar toolbar = findViewById(R.id.toolbar);
                toolbar.setTitle("");
                if (toolbarP.getColor() != null) toolbar.setBackgroundColor(toolbarP.getColor());
                switch (toolbarP.getClass().getSimpleName()) {
                    case "ToolbarWithDrawerPOJO":
                        ToolbarWithDrawerPOJO toolbarDrawer = (ToolbarWithDrawerPOJO) toolbarP;
                        //if (toolbarDrawer.getTitle()     != null) toolbarAdapter.setToolbarTitle    (toolbarDrawer.getTitle());
                        //if (toolbarDrawer.getColor()     != null) toolbarAdapter.setToolbarColor    (toolbarDrawer.getColor());
                        if (toolbarDrawer.getTextColor() != null) toolbarAdapter.setToolbarTextColor(toolbarDrawer.getTextColor());
                        if (toolbarDrawer.getTypeface()  != null) toolbarAdapter.setToolbarTypeFace (toolbarDrawer.getTypeface());
                        if (toolbarDrawer.getColor() != null) toolbarAdapter.setToolbarColor(toolbarDrawer.getColor());
                        toggle = toolbarAdapter.buildToolbarWithNavDrawer(
                                 toolbarDrawer.getDrawerActivitiesToLaunch(),
                                 toolbarDrawer.getDrawerMenu(),
                                 toolbarDrawer.getDrawerCustomRow(),
                                 toolbarDrawer.getNumberOfRows(),
                                 toolbarDrawer.getDrawerItemsColor(),
                                 R.id.rv_drawer,
                                toolbarDrawer.getTitle());
                        if (toggle != null) {
                            toggle.syncState();
                        }
                        break;
                        case "ToolbarWithUpPOJO":
                        ToolbarWithUpPOJO toolbarUp = (ToolbarWithUpPOJO) toolbarP;
                        //if (toolbarUp.getTitle()     != null) toolbarAdapter.setToolbarTitle    (toolbarUp.getTitle());
                        if (toolbarUp.getTextColor() != null) toolbarAdapter.setToolbarTextColor(toolbarUp.getTextColor());
                        if (toolbarUp.getTypeface()  != null) toolbarAdapter.setToolbarTypeFace (toolbarUp.getTypeface());
                        //if (toolbarUp.getColor() != null) toolbarAdapter.setToolbarColor(toolbarUp.getColor());
                        toolbarAdapter.buildToolbarWithHomeUp(toolbarUp.getTitle());
                        break;
                    case "ToolbarWithSpinnerPOJO":
                        ToolbarWithSpinnerPOJO toolbarSpinner = (ToolbarWithSpinnerPOJO) toolbarP;
                        //Toolbar toolbar = findViewById(R.id.toolbar);
                        //toolbar.setTitle("");
                        LinearLayout ll = findViewById(R.id.ll);
                        new SpinnerAdapter(this).attachSpinner(toolbarSpinner.getSpinnerItems(),
                                                                  toolbarSpinner.getCustomSpinnerLayout(),
                                                                  toolbarSpinner.getListener(),
                                                                  ll);
                        break;
                }
            }
        }

        Integer rvID = null;
        if (aP == null || aP.getView() == null) {
            rvID = R.id.rv;
        } else {
            ViewGroup vg = (ViewGroup) getLayoutInflater().inflate(aP.getView(), null);

            for (int i = 0; i < vg.getChildCount(); i++) {
                if (vg.getChildAt(i) instanceof RecyclerView) {
                    rvID = vg.getChildAt(i).getId();
                }
            }
        }
        initRv(rvID);
        new RxBackground().executeInBackground(this, getRxBackgroundInterface());
    }

    protected abstract RxBackground.RxBackgroundInterface getRxBackgroundInterface();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        } else return false;
    }

    public void initRv(Integer rvID) {
        if (rvID != null) {
            rv = (RecyclerView) findViewById(rvID);
            rv.setLayoutManager(new LinearLayoutManager(this));
            if (RvInterface.EndlessRvOnScrollListener.class.isAssignableFrom(this.getClass())) {
                RvInterface.EndlessRvOnScrollListener onScrollInterface = (RvInterface.EndlessRvOnScrollListener) this;
                rv.addOnScrollListener(onScrollInterface.onScroll());
            }
        } else {
            Toast.makeText(this, "Error: RvID is null", Toast.LENGTH_LONG).show();
        }
    }

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

    public abstract ArrayList<Integer> getRvCustomRow_holderIDS();
    public abstract RvAdapter.RvAdapterInterface getRvOnBind();

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
