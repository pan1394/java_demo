package com.pan.learning.async.path;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathDemo {

	public static void main(String[] args) throws IOException {

		Path path = Paths.get(".");
		System.out.println("path 路径个数: " + path.getNameCount());
		System.out.println("path root路径: " + path.getRoot());
		System.out.println("path 绝对路径: " + path.toAbsolutePath());
		System.out.println("path 绝对路径根路径: " + path.toAbsolutePath().getRoot());

		Path path2 = Paths.get("./src/main/java/com/pan/async/path/PathDemo.java");
		System.out.println(path2.toAbsolutePath());
 		Files.lines(path2).forEach(System.out::println);
//		Files.list(path).forEach(System.out::println);

	}
}
