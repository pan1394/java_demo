package com.pan.learning.nio;

import java.nio.ByteBuffer;

public class Test {

	public static void main(String[] args) {

		ByteBuffer buff = ByteBuffer.allocate(10);

		buff.clear();
		System.out.println("after clear -> remaing: " + buff.remaining());
		System.out.println("after clear -> hasremaining:" + buff.hasRemaining());

		buff.limit(0);
		System.out.println("after set limit 0 -> remaing: " + buff.remaining());
		System.out.println("after set limit 0 -> hasremaining:" + buff.hasRemaining());

		buff.clear();
		buff.flip();
		buff.flip();
		System.out.println("after 2 times flip() -> remaing: " + buff.remaining());
		System.out.println("after 2 times flip() -> hasremaining:" + buff.hasRemaining());

		buff.clear();
		byte a = 0x10;
		byte b = 010;
		byte c = 0B0010;
		int d = 0b0010;

		buff.put(a);
		buff.put(b);
		buff.put(c);
		System.out.println("after put() -> remaing: " + buff.remaining());
		System.out.println("after put() -> hasremaining:" + buff.hasRemaining());

		buff.flip();
		System.out.println("after flip() -> position:" + buff.position());
		System.out.println("after flip() -> remaing: " + buff.remaining());
		System.out.println("after flip() -> hasremaining:" + buff.hasRemaining());


		buff.rewind();
		System.out.println("after rewind() again -> position:" + buff.position());
		System.out.println("after rewind() again -> remaing: " + buff.remaining());
		System.out.println("after rewind() again-> hasremaining:" + buff.hasRemaining());

		buff.get();
		buff.get();
		buff.get();
		System.out.println("after read all -> remaining: " + buff.remaining());
		System.out.println("after read all -> hasremaining:" + buff.hasRemaining());


		buff.rewind();
		buff.compact();
		System.out.println("after compact() -> position:" + buff.position());
		System.out.println("after compact-> remaing: " + buff.remaining());
		System.out.println("after compact-> hasremaining:" + buff.hasRemaining());

		System.out.println(buff.get());
		System.out.println(buff.get());
		System.out.println(buff.get());
		System.out.println("after read all -> position:" + buff.position());
		System.out.println("after read all -> remaining: " + buff.remaining());
		System.out.println("after read all -> hasremaining:" + buff.hasRemaining());
	}
}
