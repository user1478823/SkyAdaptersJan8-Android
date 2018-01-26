package com.danilo.skyadapters;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * Created by ttlnisoffice on 11/17/17.
 */
public class ToolbarAdapter {

    private AppCompatActivity a;
    private Toolbar toolbar = null;
    private DrawerLayout drawerLayout = null;

    public ToolbarAdapter(AppCompatActivity a) {
        this.a = a;
        toolbar = a.findViewById(R.id.toolbar);
    }

    public ToolbarAdapter(AppCompatActivity a, int layoutID) {
        this.a = a;

        ViewGroup vg = (ViewGroup) a.getLayoutInflater().inflate(layoutID,null);
        for (int i = 0; i < vg.getChildCount(); i++) {
            if (vg.getChildAt(i) instanceof Toolbar) {
                toolbar = (Toolbar) a.findViewById(vg.getChildAt(i).getId());
            }
            if (vg.getChildAt(i) instanceof  DrawerLayout) {
                drawerLayout = (DrawerLayout) a.findViewById(vg.getChildAt(i).getId());
            }

        }

        /*if (toolbar == null && a.getClass().getSimpleName().contains("MainActivity")) {
            showErrorWarning("Toolbar is null, did you add Toolbar in xml or did you give it id?");
        }
        if (drawerLayout == null && a.getClass().getSimpleName().contains("MainActivity")) {
            showErrorWarning("DrawerLayout is null, did you add DrawerLayout in xml or did you give it id?");
        }*/
    }

    private void showErrorWarning(String message) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(a);
        alertBuilder.setTitle("Error");
        alertBuilder.setMessage(message);
        alertBuilder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });

        AlertDialog alert = alertBuilder.create();
        alert.show();
    }

    public ToolbarAdapter buildToolbar(boolean showTitle) {
        a.setSupportActionBar(toolbar);
        a.getSupportActionBar().setDisplayShowTitleEnabled(showTitle);
        return this;
    }

    public ToolbarAdapter buildToolbarWithHomeUp() {
        a.setSupportActionBar(toolbar);
        a.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a.finish();
            }
        });
        return this;
    }

    public ToolbarAdapter buildToolbarWithCustomBackIcon(int iconID) {
        a.setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(iconID);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.finish();
            }
        });
        return this;
    }

    /*public ActionBarDrawerToggle buildToolbarWithNavDrawer(int vgID,
                                                           Class[] activitiesToLaunch,
                                                           int menuID,
                                                           int customLayoutID,
                                                           RecyclerView.LayoutManager layoutManager,
                                                           int drawerItemColor, Integer drawerRvID){

        Menu menu = new PopupMenu(a, null).getMenu();
        a.getMenuInflater().inflate(menuID, menu);

        RvAdapter rvAdapter = new RvAdapter(a, menu,
                activitiesToLaunch, customLayoutID, layoutManager, drawerItemColor, drawerRvID);
        
        ActionBarDrawerToggle toggleBtn = null;
        if (drawerLayout != null) {
            toggleBtn = new ActionBarDrawerToggle(a, drawerLayout,
                    R.string.drawer_open, R.string.drawer_closed);
            drawerLayout.addDrawerListener(toggleBtn);
            //toggleBtn.syncState();
        }

        if (toolbar != null) {
            a.setSupportActionBar(toolbar);
            a.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        return toggleBtn;
    } */

    public ActionBarDrawerToggle buildToolbarWithNavDrawer(Intent[] activitiesToLaunch,
                                                           int menuID,
                                                           int customLayoutID,
                                                           int numOfRows,
                                                           Integer drawerItemColor, Integer drawerRvID){

        drawerLayout = a.findViewById(R.id.drawer_layout);
        drawerLayout.setVisibility(View.VISIBLE);

        RvAdapter rvAdapter = new RvAdapter(a, activitiesToLaunch, drawerItemColor, drawerRvID, menuID, customLayoutID, numOfRows);
/*
        ActionBarDrawerToggle toggleBtn = null;
        if (drawerLayout != null) {
            toggleBtn = new ActionBarDrawerToggle(a, drawerLayout,
                    R.string.drawer_open, R.string.drawer_closed);
            drawerLayout.addDrawerListener(toggleBtn);
        }

        if (toolbar != null) {
            }


*/
        ActionBarDrawerToggle toggleBtn = new ActionBarDrawerToggle(a, drawerLayout,
                R.string.drawer_open, R.string.drawer_closed);
        drawerLayout.addDrawerListener(toggleBtn);
        //toolbar.setTitle("");
        a.setSupportActionBar(toolbar);
        //a.getSupportActionBar().setTitle("Test title");
        a.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return toggleBtn;
    }

    public ToolbarAdapter setToolbarTitle(String title){
        //toolbar.setTitle(title);
        LinearLayout ll = a.findViewById(R.id.ll);
        ll.setVisibility(View.GONE);
        a.getSupportActionBar().setTitle(title);
        //a.setTitle(title);
        return this;
    }

    public ToolbarAdapter setToolbarColor(int color) {
        toolbar.setBackgroundColor(color);
        return this;
    }

    public ToolbarAdapter setToolbarTextColor(int color) {
        toolbar.setTitleTextColor(color);
        return this;
    }

    public ToolbarAdapter setToolbarTypeFace(Typeface tf) {
        ((TextView)toolbar.getChildAt(0)).setTypeface(tf);
        return this;
    }
}

