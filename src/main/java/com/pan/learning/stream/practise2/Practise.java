package com.pan.learning.stream.practise2;

import java.util.*;

import static java.util.stream.Collectors.*;

public class Practise {

    private List<Dish> menu = null;
    {
        menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", false, 800, Dish.Type.MEAT),
                new Dish("season fruit", false, 350, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH)
        );
    }
    public static void main(String[] args){
        Practise p = new Practise();
        p.reduceinng();

    }

    private void test() {
        menu.stream().collect(counting());
        menu.stream().count();

        Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);

        Optional<Dish> mostCaloriesDish = menu.stream().collect(maxBy(dishCaloriesComparator));
        mostCaloriesDish.ifPresent(System.out::println);

        menu.stream().collect(minBy(dishCaloriesComparator)).ifPresent(System.out::println);
    }

    private void summing() {

        int totalCalories = menu.stream().collect(summingInt(Dish::getCalories));
        int totalCalories2 = menu.stream().mapToInt(Dish::getCalories).sum();
        System.out.printf("total calories: %d, %d \n", totalCalories, totalCalories2);
        Double averageCalories = menu.stream().collect(averagingDouble(Dish::getCalories));
        OptionalDouble averageCalories2 = menu.stream().mapToInt(Dish::getCalories).average();
        System.out.printf("%.2f, %.2f\n", averageCalories, averageCalories2.getAsDouble());
        System.out.println(averageCalories +" == "+ averageCalories2.getAsDouble());

        DoubleSummaryStatistics collect = menu.stream().collect(summarizingDouble(Dish::getCalories));
        System.out.println(collect);
    }

    private void joiningDemo() {
        String shortName1 = menu.stream().map(Dish::getName).collect(joining(", "));
        //String shortName = menu.stream().collect(Collectors.joining());
        System.out.println(shortName1);
    }

    private void reduceinng() {
        int total = menu.stream().collect(reducing(0, Dish::getCalories, (a,b) -> a+b));
        int max =   menu.stream().collect(reducing(0, Dish::getCalories, Integer::max));
        int min =   menu.stream().collect(reducing(0, Dish::getCalories, Integer::min));
        int sum =   menu.stream().collect(reducing(0, Dish::getCalories, Integer::sum));


//        String shortName = menu.stream().collect(reducing());
        System.out.printf("%d, %d, %d, %d, %s\n", total, max, min, sum, "sadf");
    }
}
