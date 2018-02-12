package com.danilo.skyadapters.recycler.tab_layout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ttlnisoffice on 2/12/18.
 */

public class TabFragment extends Fragment {

    private TabFragmentPOJO tabFragmentPOJO;

    public TabFragment newInstance(TabFragmentPOJO tabFragmentPOJO){
        return new TabFragment().setTabFragmentPOJO(tabFragmentPOJO);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(tabFragmentPOJO.getTabFragmentLayout(), container, false);
        tabFragmentPOJO.getTabFragmentInterface().onCreateView(fragmentView);
        return fragmentView;
    }


    public interface TabFragmentInterface {
        public void onCreateView(View fragmentView);
    }

    public TabFragment setTabFragmentPOJO(TabFragmentPOJO tabFragmentPOJO) {
        this.tabFragmentPOJO = tabFragmentPOJO;
        return this;
    }
}
