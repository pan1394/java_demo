package com.pan.learning.classloader;

import java.io.IOException;

public class ClassLoaderPropTest {

	/**
	 * 查看系统加载器的classpath
	 *
	 * java -cp %CLASSPATH%;./target/classes com.pan.learning.classloader.ClassLoaderPropTest
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) {
		ClassLoaderUtils.printSystemClassPath();
	}
}
