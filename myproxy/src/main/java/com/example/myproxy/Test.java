package com.example.myproxy;

/**
 * Created by Sanguine on 2018/3/22.
 */

public class Test {
    public static void main(String[] args) {
        RealSubject realSubject = new RealSubject();
        Subject proxyObj = MyProxy.getProxyObj(realSubject);
        proxyObj.request(10, "123");
        realSubject.request(20,"456");
    }
}
