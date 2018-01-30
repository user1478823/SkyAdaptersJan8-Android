package com.danilo.skyadapters.recycler;

import com.danilo.skyadapters.RxBackground;
import com.danilo.skyadapters.VolleyGet;
import com.danilo.skyadapters.recycler.pojo.ActivityPOJO;
import com.danilo.skyadapters.recycler.pojo.ToolbarPOJO;

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

    public interface BackgroundThread {
        public RxBackground.RxBackgroundInterface doInBackground();
    }

    public interface NetworkOperation {
        public VolleyGet connect();
    }
}
