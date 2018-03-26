package com.example.rxjavademo;

/**
 * Created by Sanguine on 2018/3/23.
 *
 */

public class MyObservable extends AbstractMyObservableInterface {

    @Override
    public void operation() {
        System.out.println("MyObservable:operation()");
        notifyObservers();
    }
}
