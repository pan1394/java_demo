package com.pan.learning.aio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {


    public static void main(String[] args) throws IOException {

        Socket s = new Socket();
        s.connect(new InetSocketAddress("127.0.0.1",2018));
        OutputStream os = s.getOutputStream();

        new Thread(()->{
            Scanner sc = new Scanner(System.in);
            while (true) {
                String input = sc.next();
                try {
                    os.write(input.getBytes("utf8"));
                    os.flush();
                    if("exit".equals(input)){
                        s.shutdownOutput();
                        break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }, "输入端").start();
        InputStream is = s.getInputStream();
        byte[] buffer = new byte[1024];
        while (true) {
            try{

                int total = is.read(buffer);
                String received = new String(buffer, 0, total, "utf8");
                System.out.printf("客户端%s接受:%s\n", s.getInetAddress().getHostAddress(),  received);
                if("exit".equals(received)){
                    s.close();
                    break;
                }
            }catch (IOException e){
                System.out.println(e.getLocalizedMessage());
                if(s.isClosed()){
                    break;
                }
            }
        }
    }
}
