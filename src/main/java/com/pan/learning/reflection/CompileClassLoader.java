package com.pan.learning.reflection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;

public class CompileClassLoader extends ClassLoader {

	private String javaCode = "";

	private String compiledClassName = "";

	public void setJavaCode(String javaCode) {
		this.javaCode = javaCode;
	}

	public void setCompiledClassName(String compiledClassName) {
		this.compiledClassName = compiledClassName;
	}

	private byte[] getBytes(String fileName) throws IOException {
		File f = new File(fileName);
		FileInputStream is = new FileInputStream(f);
		byte[] ret = new byte[(int)f.length()];
		is.read(ret);
		return ret;
	}

	private boolean compile(File java) throws IOException {
		String javaFile = java.getAbsolutePath();
		System.out.printf("CompileClassLoader:正在编译 %s ...\n", javaFile);
		Process p = Runtime.getRuntime().exec("javac " + javaFile);

		try {
			p.waitFor();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return p.exitValue() == 0;
	}

	private File writeJavaFile(String clsName) throws IOException {
		File tempFile = File.createTempFile(clsName,".java", new File("."));
		tempFile.deleteOnExit();
		try(FileWriter writer = new FileWriter(tempFile)){
			writer.write(this.javaCode);
		}
		return tempFile;
	}

	@Override protected Class<?> findClass(String name){
		try {
			File javaFile = writeJavaFile(name);
			compile(javaFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		String classFilePath = (String.format("./%s.class", name));
		byte[] classData = new byte[0];

		try {
			classData = getBytes(classFilePath);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return super.defineClass(name, classData, 0, classData.length );
	}


	public void exec(){
		try {
			Class clazz = this.loadClass(this.compiledClassName);
			Field init = clazz.getDeclaredField("init");
			init.setAccessible(true);
			init.get(null);
		} catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}


}
