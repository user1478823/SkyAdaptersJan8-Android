package com.danilo.skyadapters;



import com.danilo.skyadapters.recycler.RvActivity;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ttlnisoffice on 12/29/17.
 */

public class RxBackground {

    public void executeInBackground(final RvActivity a, final RxBackgroundInterface rxBackgroundInterface) {
        Observable<List> observable = Observable.just("a") //had to add something
                .map(new Function<String, List>() {
                    @Override
                    public List apply(String s) throws Exception {
                        return rxBackgroundInterface.getDoInBackground();
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());


        observable.subscribe(a.buildObserver(new RvActivity.ObserverInterface() {
            @Override
            public void onNext(List value) {
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
    }

    public interface RxDoInBackground {
        public List doInBackground();
        public void onResultReceived(List list);
    }

    public void doInBackground(final RxDoInBackground rxDoInBackground) {
        Observable<List> observable = Observable.just("a")
                .map(new Function<String, List>() {
                    @Override
                    public List apply(String s) throws Exception {
                        return rxDoInBackground.doInBackground();
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());

        Observer<List> observer = new Observer<List>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List value) {
                rxDoInBackground.onResultReceived(value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        observable.subscribe(observer);
    }
}
