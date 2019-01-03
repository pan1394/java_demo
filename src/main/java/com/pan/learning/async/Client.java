package com.pan.learning.async;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;

public class Client {

	private final MyService service = new MyService();

	private void start() {

		ExecutorService executorService = Executors.newFixedThreadPool(1);
		Runnable task = getRunnable(service::sayHelloService,"Captin Jack",  System.out::println);
		executorService.submit(task);
		executorService.shutdown();

	}

	private Runnable getRunnable(Function<String, String> serviceFunction, String input, Callback<String> callback) {

		Runnable r = () ->{
			//String res = service.sayHelloService(input);
			String res = serviceFunction.apply(input);
			callback.execute(res);
			System.out.printf("Service thread %s invoked end.\n", Thread.currentThread().getId());
		};

		return r;
	}

	public static void main(String[] args) {
		Client client = new Client();
		client.start();
		System.out.printf("Main Thread %s end!\n", "main");

	}
}
