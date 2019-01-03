package com.pan.learning.re;

import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularDemo{

    private static String template = "hi, **a string that you can write** whaaat did you say?";
    public static void main(String[] args){
        test();
        String x = replace(template);
        System.out.println(x);
    }

    private static String replace(String input){

        Pattern p  = Pattern.compile("\\*\\*(.+)\\*\\*");
        Matcher m = p.matcher(input);
        if(m.find()){
            //return m.replaceAll(String.format("<h>%s<h>", m.group(1)));
            return m.replaceAll(MessageFormat.format("<h>{0}<h>", m.group(1)));
        }
        return input;

    }

	private static void test() {
		String input = "pan1394@126.com";
        Pattern p  = Pattern.compile("((.+)@(.+)\\.com)");
        Matcher m = p.matcher(input);
        
        if(m.find()){
            System.out.println("matched");
            System.out.println(m.group(1));
            System.out.println(m.group(2));
            System.out.println(m.group(3));
        }
    }
}