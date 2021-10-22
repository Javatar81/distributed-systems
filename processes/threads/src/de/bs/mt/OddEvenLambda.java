package de.bs.mt;

import java.util.function.IntPredicate;
import java.util.stream.IntStream;

public class OddEvenLambda {
	public static void main(String[] args) {
		Thread even = new Thread(() -> printRange(i -> i % 2 == 0));
		Thread odd = new Thread(() -> printRange(i -> i % 2 == 1));
		even.start();
		odd.start();
	}
	
	private static void printRange(IntPredicate filter) {
		IntStream.range(0, 10000)
			.filter(filter)
			.forEach(System.out::println);
	}
}
