package com.example.rxjavademo;

/**
 * Created by Sanguine on 2018/3/23.
 */

public class Test {

    public static void main(String[] args) {
        MyObservableInterface observable = new MyObservable();
        MyObserverOne one = new MyObserverOne();
        MyObserverTwo two = new MyObserverTwo();
        observable.add(one);
        observable.add(two);
        observable.operation();

        observable.del(one);
        observable.del(two);
        observable.operation();
    }


//    public static void main(String[] args) {
//        String[] names = new String[]{"张三", "李四", "王二麻"};
//        Observer<String> observer = new Observer<String>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                System.out.println("d = " + d);
//            }
//
//            @Override
//            public void onNext(String s) {
//                System.out.println("s = [" + s + "]");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                System.out.println("e = [" + e + "]");
//            }
//
//            @Override
//            public void onComplete() {
//                System.out.println("onComplete");
//            }
//        };
//        Observable observable = Observable.fromArray(names);
//        observable.subscribe(observer);
//    }
}
