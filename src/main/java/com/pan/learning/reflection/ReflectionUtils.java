package com.pan.learning.reflection;

public class ReflectionUtils {

	private static final String scopeFmt  = "class %s { private static int init = 0; static {  %s  }}";

	private static final String className = "Scope";

	public static void eval(String str){
		 CompileClassLoader loader = new CompileClassLoader();
		 loader.setCompiledClassName(className);
		 loader.setJavaCode(String.format(scopeFmt, className , str));
		 loader.exec();
	}

	public static void main(String[] args) {

		//ReflectionUtils.eval("int i = 4;");
		ReflectionUtils.eval("System.out.println(\"aaa\"); int i= 4; "
				+ "int x = 14 * i;"
				+ "System.out.println(x);"
				+ "System.out.printf(\"%.2f \\n \",Math.sqrt(2));");
	}
}
