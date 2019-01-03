package com.pan.learning.system;

import java.io.IOException;

public class RuntimeDemo {

    public static void main(String[] args) {

        getJVM();

        try {
            Runtime.getRuntime().exec("notepad.exe");
        } catch (IOException e) {
			e.printStackTrace();
		}
        
    }

    public static void getJVM(){
        Runtime rt = Runtime.getRuntime();
        System.out.printf("cpu 处理器核数: %s, JVM总内存: %.2f MB, 空余内存: %.2f MB, 最大可用内存: %.2f MB ", 
        rt.availableProcessors(), (rt.totalMemory()/1024d/1024d), rt.freeMemory()/1024d/1024d, rt.maxMemory()/1024d/1024d);
    }
}