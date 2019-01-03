package com.pan.learning.stream.generic;

import java.util.ArrayList;
import java.util.List;

public class Test<T> {

    T t;


    public void test2(T t) {

    }

    public static <E> void test(E t){

    }

    @SuppressWarnings("unchecked")
    public void heapPollution(){
        List list = new ArrayList<Integer>();
        list.add(20);
//        List<String> ls = list;
        System.out.println(list.get(0));
    }


    public static void main(String[] args) {

        Test t = new Test();
        t.heapPollution();
    }
}
