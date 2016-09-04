package com.example.jobs4smcyouth.Utilities.EventBus;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by samsiu on 9/3/16.
 */
public class RxEventBus {
    private final Subject<Object, Object> bus = new SerializedSubject<>(PublishSubject.create());

    public void post(Object o){
        bus.onNext(o);
    }

    public Observable<Object> getBusObservable() {
        return bus;
    }
}
