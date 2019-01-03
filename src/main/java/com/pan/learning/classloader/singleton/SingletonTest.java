package com.pan.learning.classloader.singleton;

import com.pan.learning.classloader.ClassLoaderUtils;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class SingletonTest {

	public static void main(String[] args)
			throws Exception {

		/*Singleton a = Singleton.getInstance();
		Singleton b = Singleton.getInstance();
		Singleton c = Singleton.getInstance();
		System.out.printf("a==b?%s, b==c?%s \n", a==b, b==c);*/

		File f = new File("g:/jcl");
	 	URL u = f.toURI().toURL();
	 	//URL u = new URL("file:///g:/jcl");
		URL[] url = new URL[]{u};
		URLClassLoader cl1 = new URLClassLoader(url);
		URLClassLoader cl2 = new URLClassLoader(url);


		String className = "com.pan.learning.classloader.singleton.Singleton";
		System.out.println(cl1);
		System.out.println(cl2);
		ClassLoaderUtils.printClassPath(cl1);
		ClassLoaderUtils.printClassPath(cl2);

		Object cls1 = cl1.loadClass(className);   //使用父类委托特性
		Object cls2 = cl2.loadClass(className);   //使用缓存特性
		((Class) cls1).newInstance();
		((Class) cls2).newInstance();
		System.out.println(cls1 == cls2);

		//Class.forName(className);


	}
}
