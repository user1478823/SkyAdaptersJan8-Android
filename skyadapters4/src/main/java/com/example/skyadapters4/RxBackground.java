package com.example.skyadapters4;

import com.example.skyadapters3.recycler.RvBase;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ttlnisoffice on 12/29/17.
 */

public class RxBackground {

    public void executeInBackground(RvBase a, final RxBackgroundInterface rxBackgroundInterface) {
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
                rxBackgroundInterface.getOnResultReceived(value);
            }
        }));
    }

    public interface RxBackgroundInterface {
        public List getDoInBackground();
        public void getOnResultReceived(List value);
    }
}
