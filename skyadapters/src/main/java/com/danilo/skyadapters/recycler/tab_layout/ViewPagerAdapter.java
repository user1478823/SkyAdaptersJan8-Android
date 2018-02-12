package com.danilo.skyadapters.recycler.tab_layout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by ttlnisoffice on 2/12/18.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private TabFragmentPOJO[] tabFragmentPOJOS;

    public ViewPagerAdapter(FragmentManager fm, TabFragmentPOJO[] tabFragmentPOJOS) {
        super(fm);
        this.tabFragmentPOJOS = tabFragmentPOJOS;
    }

    @Override
    public Fragment getItem(int position) {
        return new TabFragment().newInstance(tabFragmentPOJOS[position]);
    }

    @Override
    public int getCount() {
        return tabFragmentPOJOS.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (tabFragmentPOJOS[position].getTabTitle() != null) {
            return tabFragmentPOJOS[position].getTabTitle();
        } else {
            return "";
        }
    }
}
