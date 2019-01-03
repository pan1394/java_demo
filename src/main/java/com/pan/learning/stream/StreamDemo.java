package com.pan.learning.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
public class StreamDemo {

	private List<Dish> menu = null;

	{
		menu = Arrays.asList(
				new Dish("pork", false, 800, Type.MEAT),
				new Dish("beef", false, 700, Type.MEAT),
				new Dish("chicken", false, 400, Type.MEAT),
				new Dish("french fries", false, 530, Type.OTHER),
				new Dish("rice", true, 120, Type.OTHER),
				new Dish("season fruit", true, 120, Type.OTHER ),
				new Dish("pizza", true, 550, Type.OTHER ),
				new Dish("prawns", false, 300, Type.FISH ),
				new Dish("salmon", false, 450, Type.FISH )
				);
	}

	public static void main(String[] args){
		StreamDemo demo = new StreamDemo();
		demo.groupingx();
	}

	public void test(){
		List<String> threeHighDishes = menu.stream()
											.filter(d-> d.getCalories()>150)
											.limit(3)
											.map(Dish::getName)
											.collect(toList());
		System.out.println(threeHighDishes);
	}

	public void flatMapTest(){
		String[] str = new String[]{"hello", "world"};
		List<String> words = Arrays.asList("hello", "world");
		List<String> resList = words.stream()
									.map((String s) -> s.split(""))
									.flatMap(Arrays::stream)
									.collect(toList());
		System.out.println(resList);
	}

	public void flatMapTest2(){
		List<Integer> a = Arrays.asList(1,3,5);
		List<Integer> b = Arrays.asList(2,4);

		a.stream().flatMap(
				i -> {
					Stream<int[]> stream =  b.stream().map(j -> new int[]{i, j});
					return stream;
				}
		).forEach(x -> System.out.printf("[%d, %d] \n", x[0], x[1]));
	}

	public void intStramTest(){
		IntStream.range(1, 100)
				.boxed()
				.flatMap(a ->  	( IntStream.range(a, 100)
											.filter(b -> Math.sqrt(a*a + b*b) % 1 == 0)
											.mapToObj(b -> new int[]{ a, b, (int)Math.sqrt(a*a + b*b)})
								)
						)
				.forEach(array -> System.out.printf("(%d, %d, %d) \n", array[0], array[1], array[2]));

	}

	public void fibnacii() {
		Stream.iterate(new int[]{0,1} , n-> new int[]{n[1], n[0]+n[1]})
		      .limit(20)
			  .forEach(a -> System.out.printf("(%d, %d) \n", a[0], a[1]));
	}

	public void groupingx() {
		Map<Type, List<Dish>> map = menu.stream().collect(groupingBy(Dish::getType));
		System.out.println(map);

		Map<CaloricLevel, List<Dish>> map1 = menu.stream().collect(groupingBy((Dish d) -> {
			if (d.getCalories() > 700)
				return CaloricLevel.FAT;
			if (d.getCalories() <= 700 && d.getCalories() > 300)
				return CaloricLevel.NORMAL;
			if (d.getCalories() <= 300)
				return CaloricLevel.DIET;
			return CaloricLevel.NORMAL;
		}));
		System.out.println(map1);
	}

	private enum CaloricLevel {DIET, NORMAL, FAT}

}
