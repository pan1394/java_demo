package com.pan.learning.enum2;

public class FirstSample{ 

    public static void main(String[] args)
    {
        System.out.println("we will not use 'Hello world'!");
        A ax = new A();
        Operation o = Operation.ADD;
        Operation m = Operation.valueOf("MINUS");
        Operation t = Enum.valueOf(Operation.class, "DEVIDE");
        int a = 3;
        int b = 5;
        System.out.println(o.eval(a, b));
        System.out.println(m.eval(a, b));
        System.out.println(t.eval(a, b));

    }
}
class A{}
enum Operation{
     
     ADD{
        int eval(int a, int b){
            return a + b;
        }
     },
     MINUS{
        @Override
        int eval(int a, int b) {
            return a-b;
        }
     },
     MULTIPY {
        @Override
        int eval(int a, int b) {
            return a*b;
        }
    },
     DEVIDE{
        @Override
        int eval(int a, int b) {
            return a/b;
        }

     };

     abstract int eval(int a, int b);
}
