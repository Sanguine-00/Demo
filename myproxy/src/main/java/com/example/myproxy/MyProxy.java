package com.example.myproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Sanguine on 2018/3/22.
 */

public class MyProxy {
    public static Subject getProxyObj(final RealSubject subject) {
        return (Subject) Proxy.newProxyInstance(Subject.class.getClassLoader(), new Class<?>[]{Subject.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("调用方法之前");
                if (method.getDeclaringClass() == Object.class) {
                    return method.invoke(this, args);
                }
                Object result = method.invoke(subject, args);
                System.out.println("调用方法之后");
                return result;
            }
        });
    }
}
