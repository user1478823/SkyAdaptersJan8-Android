package com.danilo.skyadapters.recycler;

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
import com.danilo.skyadapters.recycler.pojo.ToolbarPOJO;
import com.danilo.skyadapters.recycler.pojo.ToolbarWithDrawerPOJO;
import com.danilo.skyadapters.recycler.pojo.ToolbarWithSpinnerPOJO;
import com.danilo.skyadapters.recycler.pojo.ToolbarWithUpPOJO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by ttlnisoffice on 12/20/17.
 */

public abstract class RvActivity extends AppCompatActivity {

    public  ActionBarDrawerToggle toggle;
    public  List list = null;
    public  RvAdapter adapter;
    public  RecyclerView rv;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private RecyclerView drawerRv;
    private ArrayList<Integer> rvIds = new ArrayList<>();
    private EndlessRecyclerOnScrollListener en = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityPOJO aP = null;
        if (RvInterface.ActivityContent.class.isAssignableFrom(this.getClass())) {
            RvInterface.ActivityContent activityContent = ((RvInterface.ActivityContent) this);
            aP = activityContent.getActivityContent();
        }
        ToolbarPOJO toolbarP = null;
        if (RvInterface.ToolbarCustomizer.class.isAssignableFrom(this.getClass())) {
            RvInterface.ToolbarCustomizer toolbarCustomizer = ((RvInterface.ToolbarCustomizer) this);
            toolbarP = toolbarCustomizer.customizeToolbar();
        }

        if (aP != null && aP.getTheme() != null) {
            setTheme(aP.getTheme());
        }

        if (aP == null || aP.getView() == null) {
            if (toolbarP == null) {
                setContentView(R.layout.default_layout);
                rv = findViewById(R.id.rv);
            } else if (toolbarP.getClass().getSimpleName().contains("ToolbarWithDrawerPOJO")) {
                setContentView(R.layout.default_drawer_layout);
                rv = findViewById(R.id.rv_main);
                drawerLayout = findViewById(R.id.drawer_layout);
                drawerRv = findViewById(R.id.rv_drawer);
            } else {
                setContentView(R.layout.default_layout);
                rv = findViewById(R.id.rv);
            }
            toolbar = findViewById(R.id.toolbar);
        } else {
            setContentView(aP.getView());
            ViewGroup vg = (ViewGroup) getLayoutInflater().inflate(aP.getView(),null);
            for (int i = 0; i < vg.getChildCount(); i++) {
                if (vg.getChildAt(i) instanceof Toolbar) {
                    toolbar = (Toolbar) findViewById(vg.getChildAt(i).getId());
                }
                if (vg.getChildAt(i) instanceof RecyclerView) {
                    rv = findViewById(vg.getChildAt(i).getId());
                }
                if (vg.getChildAt(i) instanceof  DrawerLayout) {
                    drawerLayout = (DrawerLayout) findViewById(vg.getChildAt(i).getId());
                    //ArrayList<Integer> rvIDs = new ArrayList();
                    /*
                    for (int j = 0; j < drawerLayout.getChildCount(); j++) {
                        if (drawerLayout.getChildAt(j) instanceof RecyclerView) {
                            rvIDs.add(drawerLayout.getChildAt(j).getId());
                        }
                    }*/
                    findAllRvs(drawerLayout);
                    rv = findViewById(rvIds.get(0));
                    drawerRv = findViewById(rvIds.get(1));
                }
            }
        }

        if (toolbarP != null) {
            if (toolbarP.getColor() != null) toolbar.setBackgroundColor(toolbarP.getColor());
            if (toolbarP.getClass().getSimpleName().contains("ToolbarWithDrawerPOJO") || toolbarP.getClass().getSimpleName().contains("ToolbarWithUpPOJO")) {
                ToolbarWithUpPOJO toolbarUp = (ToolbarWithUpPOJO) toolbarP;
                if (toolbarUp.getTextColor() != null) toolbar.setTitleTextColor(toolbarUp.getTextColor());
                if (toolbarUp.getTypeface()  != null) ((TextView)toolbar.getChildAt(0)).setTypeface(toolbarUp.getTypeface());
                if (toolbarUp.getUpIcon() != null) toolbar.setNavigationIcon(toolbarUp.getUpIcon());
                if (toolbarUp.getTitle() != null) {
                    toolbar.setTitle(toolbarUp.getTitle());
                } else {
                    toolbar.setTitle("");
                }
                if (toolbarP.getClass().getSimpleName().contains("ToolbarWithDrawerPOJO")) {
                    ToolbarWithDrawerPOJO toolbarDrawer = (ToolbarWithDrawerPOJO) toolbarP;
                    if (toolbarDrawer.getDrawerActivitiesToLaunch() != null) {
                        new com.danilo.skyadapters.RvAdapter(this, toolbarDrawer.getDrawerActivitiesToLaunch(),
                                toolbarDrawer.getDrawerItemsColor(), toolbarDrawer.getDrawerMenu(),
                                toolbarDrawer.getDrawerCustomRow(), toolbarDrawer.getNumberOfRows(), drawerRv);
                    } else {
                        new com.danilo.skyadapters.RvAdapter(this, toolbarDrawer.getDrawerAdapterInterface(),
                                toolbarDrawer.getDrawerItemsColor(), toolbarDrawer.getDrawerMenu(),
                                toolbarDrawer.getDrawerCustomRow(), toolbarDrawer.getNumberOfRows(), drawerRv);
                    }

                    toggle = new ActionBarDrawerToggle(this, drawerLayout,
                            R.string.drawer_open, R.string.drawer_closed);
                    drawerLayout.addDrawerListener(toggle);
                }
                setSupportActionBar(toolbar);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                if (toggle != null) {
                    toggle.syncState();
                } else {
                    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            finish();
                        }
                    });
                }
            } else if (toolbarP.getClass().getSimpleName().contains("ToolbarWithSpinnerPOJO")) {
                ToolbarWithSpinnerPOJO toolbarSpinner = (ToolbarWithSpinnerPOJO) toolbarP;
                toolbar.setTitle("");
                LinearLayout ll = findViewById(R.id.ll);
                new SpinnerAdapter(this).attachSpinner(toolbarSpinner.getSpinnerItems(),
                        toolbarSpinner.getCustomSpinnerLayout(),
                        toolbarSpinner.getListener(),
                        ll);
                setSupportActionBar(toolbar);
            }
        }

        initRv();

        if (RvInterface.BackgroundThread.class.isAssignableFrom(this.getClass())) {
            RvInterface.BackgroundThread backgroundThread = ((RvInterface.BackgroundThread) this);
            new RxBackground().executeInBackground(this, backgroundThread.doInBackground());
        }

        if (RvInterface.NetworkOperation.class.isAssignableFrom(this.getClass())) {
            RvInterface.NetworkOperation networkOperation = ((RvInterface.NetworkOperation) this);
            networkOperation.connect();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return toggle.onOptionsItemSelected(item);
    }

    public void initRv() {
        if (rv != null) {
            if (RvInterface.RvLayoutManager.class.isAssignableFrom(this.getClass())) {
                RvInterface.RvLayoutManager rvLayoutManager = (RvInterface.RvLayoutManager) this;
                rv.setLayoutManager(rvLayoutManager.getLayoutManager());
            } else {
                rv.setLayoutManager(new LinearLayoutManager(this));
            }
            if (RvInterface.EndlessRvOnScrollListener.class.isAssignableFrom(this.getClass())) {
                RvInterface.EndlessRvOnScrollListener onScrollInterface = (RvInterface.EndlessRvOnScrollListener) this;
                en = onScrollInterface.onScroll();
                /*if (onScrollInterface.onScroll() != null) {
                    rv.addOnScrollListener(onScrollInterface.onScroll());
                }*/
            }
        } else {
            Toast.makeText(this, "Error: RvID is null", Toast.LENGTH_LONG).show();
        }
    }

    public void populateRv(List value) {
        if (list == null) {
            list = value;
            adapter = new RvAdapter(this, list,
                    getRvCustomRow_holderIDS().subList(1, getRvCustomRow_holderIDS().size()),
                    getRvCustomRow_holderIDS().get(0),
                    getRvOnBind());
            rv.setAdapter(adapter);
        } else {
            boolean duplicate = false;
            Object[] stockArr = new String[list.size()];
            stockArr = list.toArray(stockArr);
            for (int i = 0; i < value.size(); i++) {

                if (!contains(stockArr, value.get(i))/*!Arrays.asList(list).get(i).contains(value.get(i))*/) {
                    list.add(value.get(i));
                    adapter.notifyDataSetChanged();
                }

            }

            /*if (list.get(list.size()-1).equals(value.get(value.size()-1))) {
                duplicate = true;
            }*/
            /*if (!duplicate) {
                list.addAll(value);
                adapter.notifyDataSetChanged();
            }*/
            //adapter.notifyDataSetChanged();
        }
    }

    public static <T> boolean contains(final T[] array, final T v) {
        if (v == null) {
            for (final T e : array)
                if (e == null)
                    return true;
        } else {
            for (final T e : array)
                if (e == v || v.equals(e))
                    return true;
        }

        return false;
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

    private void findAllRvs(ViewGroup viewGroup) {
        int count = viewGroup.getChildCount();
        for (int i = 0; i < count; i++) {
            View view = viewGroup.getChildAt(i);
            if (view instanceof RecyclerView)
                rvIds.add(view.getId());
            else if (view instanceof ViewGroup) {
                findAllRvs((ViewGroup) view);
            }
        }
    }

    public EndlessRecyclerOnScrollListener getEn() {
        return en;
    }
}
