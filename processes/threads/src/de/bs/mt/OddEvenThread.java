package de.bs.mt;

public class OddEvenThread {
	public static void main(String[] args) {
		Thread odd = new Thread() {
			@Override
			public void run() {
				for (int i = 1; i < 10000; i = i + 2) {
					System.out.println(i);
				}
			}
		};
		Thread even = new Thread() {
			@Override
			public void run() {
				for (int i = 2; i <= 10000; i = i + 2) {
					System.out.println(i);
				}
			}
		};
		odd.start();
		even.start();
	}
}
