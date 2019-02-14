package com.pan.learning.random;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomObject{

    public static void main(String[] args){
//        printRandomNum(System.nanoTime());
//        printRandomNum(System.currentTimeMillis());

        new Thread(()->{
            Date d = new Date();
            for (int i = 0; i < 10; i++) {
                create(d);
            }
        }).start();
    }

    private static void create(Date d){
        long time = d.getTime();
        Timestamp ts = new Timestamp(time);
        System.out.println(getEventKey(ts));

    }

    private static void printRandomNum(long seed){
        System.out.print("START: ");
        Random rand = new Random(seed);
        for(int i=0; i<10; i++){
            System.out.print(rand.nextInt(25) + ", ");
        }
        System.out.println(" END");
    }

    private static void printRandomNum() {
        System.out.print("START: ");
        Random rand = new Random();
        for(int i=0; i<10; i++){
            System.out.print(rand.nextInt(1000) + ", ");
        }
        System.out.println(" END");
    }

    static Set<Integer> diff = new HashSet<>();

    public static String getEventKey(Timestamp currentTime) {
        String eventKey;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String formatted = sdf.format(currentTime);

        Random random = new Random(System.nanoTime());
        int rNumber = random.nextInt(1000);
        String micro = String.format("%03d", rNumber);
//        SimpleDateFormat fmt = new SimpleDateFormat("SSS");
//        String micro = fmt.format(new Date());
//        if(GET_TIME == 999) GET_TIME = 100;
        eventKey = String.format("%s%s", formatted, micro);
        return eventKey;
    }
}