package com.pan.learning.system;

import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;
import java.util.Set;
import static java.lang.System.out;

public class Demo {

    public static void main(String[] args) {

        getEnvs();
        getProperties();
        System.out.printf("current time mill : %s \n", System.currentTimeMillis());
        System.out.printf("current time mill : %s \n", System.nanoTime());
        System.gc();
        System.exit(0);
    }

	private static void getEnvs() {
		Map<String,String> map =  System.getenv();
        Set<Entry<String, String>> setMap = map.entrySet();
        for(Entry<String, String> e : setMap){
            out.printf("%s : %s \n", e.getKey(), e.getValue());
        }
    }
    
    private static void getProperties(){
        Properties p = System.getProperties();
        Set<Object> keys = p.keySet();
        for(Object e : keys){
            out.printf("%s : %s \n", e, p.get(e));
        }
    }

   
}