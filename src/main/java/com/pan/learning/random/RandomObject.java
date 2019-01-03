package com.pan.learning.random;

import java.util.Random;

public class RandomObject{

    public static void main(String[] args){
        printRandomNum(System.nanoTime());
        printRandomNum(System.currentTimeMillis());
    }

    private static void printRandomNum(long seed){
        System.out.print("START: ");
        Random rand = new Random(seed);
        for(int i=0; i<10; i++){
            System.out.print(rand.nextInt(25) + ", ");
        }
        System.out.println(" END");
    }
}