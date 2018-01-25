package com.danilo.skyadapters.recycler;

import com.danilo.skyadapters.ActivityPOJO;
import com.danilo.skyadapters.SpinnerAdapter;
import com.danilo.skyadapters.SpinnerPOJO;
import com.danilo.skyadapters.ToolbarPOJO;

/**
 * Created by ttlnisoffice on 1/22/18.
 */

public class RvInterface {

    public interface ActivityContent {
        public ActivityPOJO getActivityContent();
    }

    public interface ToolbarCustomizer {
        public ToolbarPOJO customizeToolbar();
    }

    public interface EndlessRvOnScrollListener {
        public EndlessRecyclerOnScrollListener onScroll();
    }

    public interface SpinnerManager {
        public SpinnerPOJO attachSpinner();
    }

}
