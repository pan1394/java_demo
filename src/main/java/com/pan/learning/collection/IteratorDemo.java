package com.pan.learning.collection;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class IteratorDemo{
    public static void main(String[] args){
        Set<String> set = new HashSet<>();
        set.add("BookA");
        set.add("BookB");
        set.add("BookC");
        
        Iterator<String> it = set.iterator();
        //it.forEachRemaining(str -> System.out.println(str));

        it.forEachRemaining(System.out::println);
    }
}