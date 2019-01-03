package com.pan.learning.object;

import java.util.Objects;

public class ObjectDemo{
    public static void main(String[] args) {
        Long x = 3l;
        long y = ObjectDemo.add(x, 2l);
        System.out.println(y);
    }

    public static Long add(Long a, Long b){
        Objects.requireNonNull(a, "parameter a cannot be null");
        Objects.requireNonNull(b, "parameter a cannot be null");
        return a + b;
    }
    
}