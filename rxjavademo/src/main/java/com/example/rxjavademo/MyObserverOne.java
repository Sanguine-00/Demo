package com.example.rxjavademo;

/**
 * Created by Sanguine on 2018/3/23.
 * 观察者一号
 */

public class MyObserverOne implements MyObserver {
    @Override
    public void onNext() {
        System.out.println("MyObserverOne:onNext()");
    }
}
