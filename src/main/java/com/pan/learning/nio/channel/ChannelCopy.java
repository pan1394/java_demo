package com.pan.learning.nio.channel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

public class ChannelCopy {

	public static void main(String... args) throws Exception {

		if (args.length != 2) {
			throw new Exception("需要两个参数: source, dest");
		}
/*
		String source = args[0];
		String dest = args[1];
*/
		String source = "g:/downloads/Java编程思想第4版.pdf";
		String dest = "g:/downloads/xxx.pdf";
		if (channelCopy(new File(source), new File(dest))) {
			System.out.printf("文件拷贝成功, 目标文件 %s", dest);
		}
	}

	private static boolean channelCopy(File src, File dest) throws Exception {
		try(ReadableByteChannel readableByteChannel = Channels.newChannel(new FileInputStream(src));
			WritableByteChannel writableByteChannel = Channels.newChannel(new FileOutputStream(dest))){

			ByteBuffer temp = ByteBuffer.allocateDirect(1024 * 100);

			while ( readableByteChannel.read(temp) != -1) {

				temp.flip();
				while (temp.hasRemaining()) {
					writableByteChannel.write(temp);
				}
				temp.clear();
			}

		}
		return true;
	}
}
