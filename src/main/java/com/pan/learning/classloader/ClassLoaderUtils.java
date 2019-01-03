package com.pan.learning.classloader;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

public class ClassLoaderUtils {

	/**
	 * 打印加载器的所有class path 路径.
	 * @param cl 指定加载器
	 */
	public static void printClassPath(ClassLoader cl){
		try {
			Enumeration<URL> resources = cl.getResources("");
			while (resources.hasMoreElements()) {
				System.out.println(resources.nextElement());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 打印系统类加载器class path 路径.
	 */
	public static void printSystemClassPath() {
		ClassLoader systemLoader = ClassLoader.getSystemClassLoader();
		System.out.printf("系统类加载器: %s\n" , systemLoader);
		ClassLoaderUtils.printClassPath(systemLoader);
	}
}
