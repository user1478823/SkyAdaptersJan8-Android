package com.danilo.skyadapters;



import com.danilo.skyadapters.recycler.RvBase;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ttlnisoffice on 12/29/17.
 */

public class RxBackground {

    public void executeInBackground(final RvBase a, final RxBackgroundInterface rxBackgroundInterface) {
        Observable<List> observable = Observable.just("a") //had to add something
                .map(new Function<String, List>() {
                    @Override
                    public List apply(String s) throws Exception {
                        return rxBackgroundInterface.getDoInBackground();
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());


        observable.subscribe(a.buildObserver(new RvBase.ObserverInterface() {
            @Override
            public void onNext(List value) {
                //rxBackgroundInterface.getOnResultReceived(value);
                if (value != null) {
                    a.populateRv(value);
                } else {
                    new ErrorNotifications().displayError(a, "Returned list is null");
                }

            }
        }));
    }

    public interface RxBackgroundInterface {
        public List getDoInBackground();
        //public void getOnResultReceived(List value);
    }
}
