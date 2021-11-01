package de.bs.mt;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class CallableTest {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executor = Executors.newFixedThreadPool(1);
		Future<Integer> future = executor.submit(() -> {
			 TimeUnit.SECONDS.sleep(5);
			 return 5;
		});
		System.out.println(future.get());
	}
}
