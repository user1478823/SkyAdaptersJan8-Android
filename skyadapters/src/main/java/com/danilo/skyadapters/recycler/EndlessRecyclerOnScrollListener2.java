package com.danilo.skyadapters.recycler;

public class EndlessRecyclerOnScrollListener2 {

    private int               visibleThreshold;
    private int               onWhichPositionToDisableIsLoading;
    private boolean           isLoading = false;
    private LoadMoreInterface loadMoreInterface;

    public EndlessRecyclerOnScrollListener2(int visibleThreshold,
                                            int onWhichPositionToDisableIsLoading,
                                            LoadMoreInterface loadMoreInterface) {
        this.visibleThreshold                   = visibleThreshold;
        this.onWhichPositionToDisableIsLoading  = onWhichPositionToDisableIsLoading;
        this.loadMoreInterface                  = loadMoreInterface;
    }

    public interface LoadMoreInterface {
        public void loadMore();
    }

    public int getVisibleThreshold() {
        return visibleThreshold;
    }

    public void setVisibleThreshold(int visibleThreshold) {
        this.visibleThreshold = visibleThreshold;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    public LoadMoreInterface getLoadMoreInterface() {
        return loadMoreInterface;
    }

    public void setLoadMoreInterface(LoadMoreInterface loadMoreInterface) {
        this.loadMoreInterface = loadMoreInterface;
    }

    public int getOnWhichPositionToDisableIsLoading() {
        return onWhichPositionToDisableIsLoading;
    }

    public void setOnWhichPositionToDisableIsLoading(int onWhichPositionToDisableIsLoading) {
        this.onWhichPositionToDisableIsLoading = onWhichPositionToDisableIsLoading;
    }
}
