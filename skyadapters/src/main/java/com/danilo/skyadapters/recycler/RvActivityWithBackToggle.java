package com.danilo.skyadapters.recycler;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
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

        ToolbarPOJO toolbarP;
        if (RvInterface.ToolbarCustomizer.class.isAssignableFrom(this.getClass())) {
            RvInterface.ToolbarCustomizer toolbarCustomizer = ((RvInterface.ToolbarCustomizer) this);
            toolbarP = toolbarCustomizer.customizeToolbar();

            if (toolbarP != null) {
                Toolbar toolbar = findViewById(R.id.toolbar);
                toolbar.setTitle("");
                if (toolbarP.getColor() != null) toolbar.setBackgroundColor(toolbarP.getColor());
                if (toolbarP.getClass().getSimpleName().contains("ToolbarWithDrawerPOJO") || toolbarP.getClass().getSimpleName().contains("ToolbarWithUpPOJO")) {
                    String title = null;
                    ToolbarWithUpPOJO toolbarUp = (ToolbarWithUpPOJO) toolbarP;
                    if (toolbarUp.getTextColor() != null) toolbar.setTitleTextColor(toolbarUp.getTextColor());
                    if (toolbarUp.getTypeface()  != null) ((TextView)toolbar.getChildAt(0)).setTypeface(toolbarUp.getTypeface());
                    if (toolbarP.getClass().getSimpleName().contains("ToolbarWithDrawerPOJO")) {
                        ToolbarWithDrawerPOJO toolbarDrawer = (ToolbarWithDrawerPOJO) toolbarP;
                        //if (toolbarDrawer.getTextColor() != null) toolbar.setTitleTextColor(toolbarDrawer.getTextColor());
                        //if (toolbarDrawer.getTypeface()  != null) ((TextView)toolbar.getChildAt(0)).setTypeface(toolbarDrawer.getTypeface());
                        if (toolbarDrawer.getTitle() != null) title = toolbarDrawer.getTitle();
                        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
                        drawerLayout.setVisibility(View.VISIBLE);
                        new com.danilo.skyadapters.RvAdapter(this, toolbarDrawer.getDrawerActivitiesToLaunch(),
                                toolbarDrawer.getDrawerItemsColor(), toolbarDrawer.getDrawerMenu(),
                                toolbarDrawer.getDrawerCustomRow(), toolbarDrawer.getNumberOfRows());
                        toggle = new ActionBarDrawerToggle(this, drawerLayout,
                                 R.string.drawer_open, R.string.drawer_closed);
                        drawerLayout.addDrawerListener(toggle);
                    } else {
                        ToolbarWithUpPOJO toolbarUp2 = (ToolbarWithUpPOJO) toolbarP;
                        //if (toolbarUp.getTextColor() != null) toolbar.setTitleTextColor(toolbarUp.getTextColor());
                        //if (toolbarUp.getTypeface()  != null) ((TextView)toolbar.getChildAt(0)).setTypeface(toolbarUp.getTypeface());
                        if (toolbarUp2.getTitle() != null) title = toolbarUp2.getTitle();
                    }
                    if (title != null) {
                        toolbar.setTitle(title);
                    }
                    setSupportActionBar(toolbar);
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    if (toggle != null) {
                        toggle.syncState();
                    }
                } else if (toolbarP.getClass().getSimpleName().contains("ToolbarWithSpinnerPOJO")) {
                    ToolbarWithSpinnerPOJO toolbarSpinner = (ToolbarWithSpinnerPOJO) toolbarP;
                    LinearLayout ll = findViewById(R.id.ll);
                    new SpinnerAdapter(this).attachSpinner(toolbarSpinner.getSpinnerItems(),
                            toolbarSpinner.getCustomSpinnerLayout(),
                            toolbarSpinner.getListener(),
                            ll);
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
