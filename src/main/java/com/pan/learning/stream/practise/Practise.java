package com.pan.learning.stream.practise;

import java.util.Arrays;
import java.util.List;
import static java.util.Comparator.comparing;

public class Practise {

    List<Transaction> transactions = null;

    {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 700)
        );
    }

    public static void main(String[] args) {
        Practise demo = new Practise();
        demo.a1();
        demo.a2();
        demo.a3();
        demo.a4();
        demo.a5();
        demo.a6();
        demo.a7();
        demo.a8();

    }

    private void a1(){
        System.out.println("找出2011年发生的所有交易, 并按交易额排序:");
        transactions.stream().filter(t -> t.getYear()==2011).sorted(comparing(Transaction::getValue)).forEach(System.out::println);
    }

    private void a2() {
        System.out.println("交易员来自哪些不同城市:");
        transactions.stream().map(t ->t.getTrader().getCity()).distinct().forEach(System.out::println);
    }

    private void a3() {
        System.out.println("所有来自剑桥的交易员, 按姓名排序:");
        transactions.stream()
                .map(Transaction::getTrader)
                .distinct()
                .filter(t-> t.getCity().equals("Cambridge"))
                .sorted(comparing(Trader::getName))
                .forEach(System.out::println);
    }

    private void a4(){
        System.out.println("所有交易员的姓名, 按字母排序:");
        transactions.stream().map(t-> t.getTrader().getName()).distinct().sorted(comparing(String::toString)).forEach(System.out::println);
    }

    private void a5() {
        System.out.println("有没有交易员在米兰工作:");
        transactions.stream().map(Transaction::getTrader).filter(t->t.getCity().equals("Milan")).findAny().ifPresent(System.out::println);
    }

    private void a6() {
        System.out.println("打印在剑桥的交易员的所有交易额:");
        transactions.stream().filter(t ->t.getTrader().getCity().equals("Cambridge")).forEach(t -> System.out.println(t.getValue()));
    }

    private void a7() {
        System.out.println("所有交易中, 最高交易额是:");
        transactions.stream().map(Transaction::getValue).reduce(Integer::max).ifPresent(System.out::println);
    }

    private void a8(){
        System.out.println("找到交易额最小的交易:");
        transactions.stream().sorted(comparing(Transaction::getValue)).limit(1).findAny().ifPresent(System.out::println);
    }
}
