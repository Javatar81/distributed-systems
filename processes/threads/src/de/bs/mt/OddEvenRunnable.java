package de.bs.mt;

public class OddEvenRunnable {
	public static void main(String[] args) {
		Runnable odd = new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i < 10000; i = i + 2) {
					System.out.println(i);
				}
			}
		};
		Runnable even = new Runnable() {
			@Override
			public void run() {
				for (int i = 2; i <= 10000; i = i + 2) {
					System.out.println(i);
				}
			}
		};
		Thread oddThread = new Thread(odd);
		oddThread.start();
		Thread evenThread = new Thread(even);
		evenThread.start();
	}
}
