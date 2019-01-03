package com.pan.learning.aio;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AIOServer {

    private static final int PORT = 2018;

    static final List<AsynchronousSocketChannel> channelList = new ArrayList<>();

    private AsynchronousServerSocketChannel serverSocketChannel;

    public AIOServer creat() throws IOException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        AsynchronousChannelGroup channelGroup = AsynchronousChannelGroup.withThreadPool(executorService);
        AsynchronousServerSocketChannel serverChannel = AsynchronousServerSocketChannel.open(channelGroup);
        serverChannel.bind(new InetSocketAddress(PORT));
        this.serverSocketChannel = serverChannel;
        return this;
    }

    public void run() {
        serverSocketChannel.accept(null, new AcceptHandler(serverSocketChannel));       //异步接受通道

       /*new Thread(()->{
            while(true){
                try {
                    Thread.sleep(1000*3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Iterator<AsynchronousSocketChannel> it = channelList.iterator();
                while(it.hasNext()){
                    AsynchronousSocketChannel c = it.next();

                    if(!c.isOpen()){
                        it.remove();
                        try {
                            System.out.printf("客户端%s从服务器断开.\n", c.getRemoteAddress());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();*/
    }

    public static void main(String[] args) throws IOException {

        AIOServer server = new AIOServer();
        server.creat().run();

        Scanner scanner = new Scanner(System.in);
        while(true){
            String p = scanner.next();
            if ("exit".equals(p)) {
                System.out.println("exit!");
                System.exit(1);
            }
        }
        /*byte[] t = "你好".getBytes("utf-8");

        ByteBuffer  buffer = ByteBuffer.wrap(t);
        CharBuffer cbuffer = buffer.asCharBuffer();
        System.out.println(cbuffer);
        System.out.println(Charset.forName("UTF8").decode(buffer));

        InputStream is = new ByteArrayInputStream(t);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line = reader.readLine();

        System.out.println(new String(t, 0, t.length, "utf-8"));
        System.out.println(line);*/
    }

    class AcceptHandler implements CompletionHandler<AsynchronousSocketChannel, Object> {

        private AsynchronousServerSocketChannel serverSocketChannel;

        public AcceptHandler(AsynchronousServerSocketChannel serverSocketChannel) {
            this.serverSocketChannel = serverSocketChannel;
        }

        private ByteBuffer bf = ByteBuffer.allocate(1024);

        @Override
        public void completed(AsynchronousSocketChannel socketChannel, Object attachment) {
            AIOServer.channelList.add(socketChannel);                           //连接的socketChannel
            serverSocketChannel.accept(null, this);          //为下一个客户端请求开放accept方法
            try {
                SocketAddress address = socketChannel.getRemoteAddress();
                System.out.println("连接的客户端地址:" +address);
            } catch (IOException e) {
                e.printStackTrace();
            }


            socketChannel.read(bf, null, new CompletionHandler<Integer, Object>() {      //异步回调处理从socketChannel读取到的数据bf
                @Override
                public void completed(Integer integer, Object attachment) {
                    bf.flip();
                    byte[] dist =new byte[1024];
                    int length = bf.remaining();
                    String content = "";
                    if(length > 0){
                        bf.get(dist, 0, length);
                        try {
                            content = new String(dist, 0, length, "utf8");
                            System.out.printf("服务器收到的消息: %s \n", content);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }

                    ByteBuffer out = ByteBuffer.wrap(dist, 0, length);

                    Iterator<AsynchronousSocketChannel> it = channelList.iterator();
                    while (it.hasNext()) {
                        AsynchronousSocketChannel ch = it.next();
                        try {
                            out.rewind();
                            ch.write(out).get(500, TimeUnit.MILLISECONDS);
                        } catch (Exception e) {
                            System.out.println("error:" + e.getLocalizedMessage());
                            it.remove();
                            try {
                                System.out.printf("向客户端%s写入失败\n", socketChannel.getRemoteAddress() );
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                    bf.clear();
                    socketChannel.read(bf, null, this);
                }

                @Override
                public void failed(Throwable exc, Object attachment) {
                    /*System.out.println("error:" + exc);
                    try {
                        System.out.printf("客户端%s从服务器断开.\n", socketChannel.getRemoteAddress());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    channelList.remove(socketChannel);*/
                }
            });
        }

        @Override
        public void failed(Throwable exc, Object attachment) {
            System.out.println("连接失败:" + exc);
        }
    }

}
