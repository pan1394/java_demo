package com.pan.learning.async;

import java.util.Optional;

public class MyService {

	public String sayHelloService(String name) {
		delay(Optional.of(1));
		return String.format("welcome %s, this is a time consumed service.", name);
	}

	private void delay(Optional<Integer> optI) {
		int i = optI.orElse(1);
		try {
			Thread.sleep(1000 * i );
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
