package com.pan.learning.url;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;


public class URLDemo {

    public static void main(String[] args) throws URISyntaxException, IOException{

        String url1 = "file:///g:/test.txt";
        String url2 = "http://www.baidu.com";

        URL x = new URL(url2);
        System.out.println(x.getHost());
        System.out.println(x.getProtocol());
        System.out.println(x.getDefaultPort());
        System.out.println(x.getFile());
        System.out.println(x.toURI());
        URLConnection connection = x.openConnection();
        System.out.println(connection.getContentEncoding());
        InputStream is = x.openStream();
        Reader reader = new InputStreamReader(is);
        BufferedReader breader = new BufferedReader(reader);
        String line = null;
        while((line = breader.readLine()) != null){
            System.out.println(line + "\n");
        }
        
    }
}