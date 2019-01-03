package com.pan.learning.optional;

import java.util.Optional;
import java.util.Properties;

public class PropertiesDemo {

	private Properties props = null;
	{
		props = new Properties();
		props.setProperty("a", "5");
		props.setProperty("b", "true");
		props.setProperty("c", "-3");
	}

	public int readDuration(Properties props, String name) {

		return  Optional.ofNullable((String)props.get(name))
							 .flatMap(PropertiesDemo::stringToInteger)
							.filter(x -> x >0)
							.orElse(0);
	/*	Optional<Integer> v = Optional.empty();
		try {
			v = Optional.ofNullable(Integer.parseInt((String)props.get(name)));
		} catch (NumberFormatException e) {}
		return v.filter(x-> x > 0).orElse(0);*/
	}

	public static Optional<Integer> stringToInteger(String s) {
		Optional<Integer> res = Optional.empty();
		try {
			res = Optional.ofNullable(Integer.parseInt(s));
		} catch (NumberFormatException e) { }
		return res;
	}

	public static void main(String[] args) {
		PropertiesDemo demo = new PropertiesDemo();
		assertEquals(5, demo.readDuration(demo.props, "a"));
		assertEquals(0, demo.readDuration(demo.props, "b"));
		assertEquals(0, demo.readDuration(demo.props, "c"));
		assertEquals(0, demo.readDuration(demo.props, "d"));
	}

	private static void assertEquals(int i, int a) {
		System.out.println(i == a);
	}
}
