package com.danilo.skyadapters.recycler.tab_layout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import com.danilo.skyadapters.R;
import com.danilo.skyadapters.recycler.RvInterface;
import com.danilo.skyadapters.recycler.pojo.ActivityPOJO;


/**
 * Created by ttlnisoffice on 2/12/18.
 */

public abstract class TabRvActivity extends AppCompatActivity {

    private Integer[] ids = new Integer[2];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityPOJO aP = null;
        if (RvInterface.ActivityContent.class.isAssignableFrom(this.getClass())) {
            RvInterface.ActivityContent activityContent = ((RvInterface.ActivityContent) this);
            aP = activityContent.getActivityContent();
        }
        if (aP != null && aP.getTheme() != null) {
            setTheme(aP.getTheme());
        }

        TabLayout tabLayout = null;
        ViewPager viewPager = null;
        if (aP == null || aP.getView() == null) {
            setContentView(R.layout.tab_rv_activity_layout);
            tabLayout = findViewById(R.id.tab_tab_rv_activity);
            viewPager = findViewById(R.id.view_pager_tab_rv_activity);
        } else {
            setContentView(aP.getView());
            findIds((ViewGroup) getLayoutInflater().inflate(aP.getView(),null));
            tabLayout = findViewById(ids[0]);
            viewPager = findViewById(ids[1]);
        }

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(),
                                                        getTabFragmentPOJOS());
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(adapter);
    }

    public abstract TabFragmentPOJO[] getTabFragmentPOJOS();

    private void findIds(ViewGroup viewGroup) {
        int count = viewGroup.getChildCount();
        for (int i = 0; i < count; i++) {
            View view = viewGroup.getChildAt(i);
            if (view instanceof TabLayout) {
                ids[0] = view.getId();
            } else if (view instanceof ViewPager) {
                ids[1] = view.getId();
            } else if (view instanceof ViewGroup) {
                findIds((ViewGroup) view);
            }
        }
    }
}
