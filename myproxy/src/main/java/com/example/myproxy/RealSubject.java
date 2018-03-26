package com.example.myproxy;

/**
 * Created by Sanguine on 2018/3/22.
 */

public class RealSubject implements Subject {
    @Override
    public void request(int i, String o) {
        System.out.println("request:" + "int i = " + i + ",String o = " + o);
    }
}
