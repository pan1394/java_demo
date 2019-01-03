package com.pan.learning.thread;

public class Printer{

    int num = 0;
    int alpha = 64;

    int current = 0;
    public synchronized void printNumber(){
        if(current ==0 || current > 64){
            num++;
            if(num < 53){
                System.out.print(num);
                if(num % 2 == 0){
                    try {
                        current = num;
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    public synchronized void printAlpha(){
        if(current < 53){
            alpha++;
            if(alpha < 65 + 26){
                current = alpha;
                System.out.print((char)alpha);
                this.notifyAll();
            }
        } 
    }

    public int getCurrent(){
        return current;
    }
    
    public static void main(String[] args ) throws InterruptedException {
        Printer p = new Printer();
        new Thread( new PrintNumber(p)).start();
        //Thread.sleep(2000);
        new Thread( new PrintA(p)).start();


    }
}

class PrintA implements Runnable{

    private Printer p ;
    public PrintA(Printer p){
        this.p = p;
    }
    @Override
    public void run() {
        while(true){
            p.printAlpha();
            if(p.getCurrent() == 90 ) break;
        }
    }
}
class PrintNumber implements Runnable{

    private Printer p ;
    public PrintNumber(Printer p){
        this.p = p;
    }
    @Override
    public void run() {
        while(true){ 
            p.printNumber();
            if(p.getCurrent() == 90 ) break;
        }
    }
}