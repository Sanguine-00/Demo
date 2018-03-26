package com.example.rxjavademo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sanguine on 2018/3/23.
 * 被观察者
 */

public abstract class AbstractMyObservableInterface implements MyObservableInterface {
    List<MyObserver> list = new ArrayList<>();

    @Override
    public void add(MyObserver observer) {
        list.add(observer);
    }

    @Override
    public void del(MyObserver observer) {
        list.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (MyObserver observer :
                list) {
            if (observer != null) {
                observer.onNext();
            }
        }
    }
}
