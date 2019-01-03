package com.pan.learning.classloader.singleton;

public class Singleton {

	private static Singleton instance = new Singleton();

	/*private Singleton() {

	}

	public static Singleton getInstance() {
		return instance;
	}*/

	static{
		System.out.println(Singleton.instance);
	}
}
