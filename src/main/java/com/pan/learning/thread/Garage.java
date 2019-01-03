package com.pan.learning.thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Garage{

    boolean[] warehouse = new boolean[3];
     
    List<Integer> list = new ArrayList<>();

    {
        list.add(0);
        list.add(1);
        list.add(2);
    }

    private int takePosition(boolean taken){
        Collections.shuffle(list);
        for(Integer i : list){
            if(warehouse[i] == taken)
                return i;
        } 
        return -1;
    }

    public synchronized void park(){
        int pos = takePosition(false);
        if(pos != -1){
            warehouse[pos] = true; 
            System.out.printf("%s parked position %d \n", Thread.currentThread().getName(), pos);
            this.notifyAll();
        }
        else{
            try {
                System.out.printf("%s no position, waiting... \n", Thread.currentThread().getName());
                this.wait();
                park();
            } catch (InterruptedException e) { 
                e.printStackTrace();
            }
        }
    }

    public synchronized void leave(){
        int pos = takePosition(true);
        if(pos != -1){
            warehouse[pos] = false;
            System.out.printf("%s drived away from position %d \n", Thread.currentThread().getName(), pos);
            this.notifyAll();
        }else{
            System.out.printf("%s: No cars in the park. \n", Thread.currentThread().getName());
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args){

        Garage garage = new Garage();

        Runnable park = ()->{
            for(int i=0; i< 10; i++){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                garage.park();
            }
        };
        
        Runnable leave = () ->{
            for(int i=0; i<20; i++){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                garage.leave();
            }
        };
        new Thread(park , "worker1").start();
        new Thread(park , "worker2").start();
        new Thread(park , "worker3").start();
        new Thread(leave , "A").start();
        new Thread(leave , "B").start();
    
    }
}