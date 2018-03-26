package com.example.rxjavademo;

/**
 * Created by Sanguine on 2018/3/23.
 * 被观察者
 */

public interface MyObservableInterface {
    /*增加观察者*/
    public void add(MyObserver observer);

    /*删除观察者*/
    public void del(MyObserver observer);

    /*通知所有的观察者*/
    public void notifyObservers();

    /*自身的操作*/
    public void operation();
}
