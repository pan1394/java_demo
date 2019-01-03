package com.pan.learning.stream.demo;

import java.util.Arrays;
import java.util.List;

public class StreamDemo {

    public static void main(String[] args) {
        StreamDemo demo = new StreamDemo();
        demo.sum();
    }

    private void sum() {
        //Integer.sum(1, 3);
        List<Integer> lst = Arrays.asList(1,2,3,5,69,7);
        int total = lst.stream().reduce(0, Integer::sum);
        System.out.println(total);
    }


}
