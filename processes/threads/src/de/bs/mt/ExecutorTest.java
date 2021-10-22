package de.bs.mt;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorTest {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(3);
		executor.submit(() -> {
			System.out.println(String.format("My name is %s", Thread.currentThread().getName()));
		});
		executor.submit(() -> {
			System.out.println(String.format("My name is %s", Thread.currentThread().getName()));
		});
		executor.submit(() -> {
			System.out.println(String.format("My name is %s", Thread.currentThread().getName()));
		});
		executor.submit(() -> {
			System.out.println(String.format("My name is %s", Thread.currentThread().getName()));
		});
		executor.submit(() -> {
			System.out.println(String.format("My name is %s", Thread.currentThread().getName()));
		});
		executor.shutdown();
	}
}
