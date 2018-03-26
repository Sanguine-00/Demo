package com.example.rxjavademo;

/**
 * Created by Sanguine on 2018/3/23.
 * 观察者二号
 */

public class MyObserverTwo implements MyObserver {
    @Override
    public void onNext() {
        System.out.println("MyObserverTwo:onNext()");
    }
}
