package com.danilo.skyadapters.recycler.tab_layout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.danilo.skyadapters.R;
import com.danilo.skyadapters.recycler.pojo.ToolbarPOJO;

/**
 * Created by ttlnisoffice on 2/12/18.
 */

public abstract class TabRvActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_rv_activity_layout);

        TabLayout tabLayout = findViewById(R.id.tab_tab_rv_activity);
        ViewPager viewPager = findViewById(R.id.view_pager_tab_rv_activity);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(),
                                                        getTabFragmentPOJOS());
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(adapter);
    }

    public abstract TabFragmentPOJO[] getTabFragmentPOJOS();
}
