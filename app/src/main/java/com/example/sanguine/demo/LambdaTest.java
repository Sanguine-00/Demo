package com.example.sanguine.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Sanguine on 2018/3/23.
 * Lambda 表达式测试类
 */

public class LambdaTest {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
        oldWay(names);
        lambdaOne(names);
        lambdaTwo(names);
        lambdaThree(names);
    }

    public static void lambdaOne(List<String> list) {
        System.out.println("lambda one way");
        List<String> names = new ArrayList<>();
        names.addAll(list);
        Collections.sort(names, (String a, String b) -> {
            return b.compareTo(a);
        });
        System.out.println("name = " + names);
    }

    public static void lambdaTwo(List<String> list) {
        System.out.println("lambda two way");
        List<String> names = new ArrayList<>();
        names.addAll(list);
        Collections.sort(names, (String a, String b) -> b.compareTo(a));
        System.out.println("name = " + names);

    }

    public static void lambdaThree(List<String> list) {
        System.out.println("lambda three way");
        List<String> names = new ArrayList<>();
        names.addAll(list);
        Collections.sort(names, (a, b) -> b.compareTo(a));
        System.out.println("name = " + names);
    }

    public static void oldWay(List<String> list) {
        System.out.println("old way");
        List<String> names = new ArrayList<>();
        names.addAll(list);
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return b.compareTo(a);
            }
        });
        System.out.println("name = " + names);
    }
}
