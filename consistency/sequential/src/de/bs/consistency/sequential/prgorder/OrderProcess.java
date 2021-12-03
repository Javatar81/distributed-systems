package de.bs.consistency.sequential.prgorder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
/**
 * https://stackoverflow.com/questions/4934913/are-static-variables-shared-between-threads
 * https://stackoverflow.com/questions/13247539/jmm-sequential-consistency
 * @author bschmeling
 *
 */
public class OrderProcess {

	private volatile OrderData data = new OrderData();
	private ExecutorService executor = Executors.newFixedThreadPool(67);
	private OrderPrinter printer = new OrderPrinter();
	
	public static void main(String[] args) throws InterruptedException {
		OrderProcess process = new OrderProcess();
		for (int i = 0; i < 500; i++) {
			process.mix(i);
			process.waitRoundFinished();
			process.printAll();
			process.reset();
		}
		process.shutdown();
	}
	
	private void printAll() {
		System.out.println(printer);
	}
	
	private void mix(int i) {
		if (i % 6 == 0) {
			runP1();
			runP2();
			runP3();
		} else if (i % 6 == 1) {
			runP1();
			runP3();
			runP2();
		} else if (i % 6 == 2) {
			runP2();
			runP1();
			runP3();
		} else if (i % 6 == 3) {
			runP2();
			runP3();
			runP1();
		} else if (i % 6 == 4) {
			runP3();
			runP2();
			runP1();
		} else if  (i % 6 == 5) {
			runP3();
			runP1();
			runP2();
		}
	}
	
	private void reset() {
		data = new OrderData();
		printer = new OrderPrinter();
	}
	
	private void shutdown() {
		executor.shutdown();
	}
	
	private void waitRoundFinished() throws InterruptedException {
		executor.awaitTermination(50, TimeUnit.MILLISECONDS);
	}
	
	private void runP1() {
		executor.submit(() -> {
			data.setX(1);
			print(data.getY(), data.getZ(), 1);
		});
		
	}
	
	private void runP2() {
		executor.submit(() -> {
			data.setY(1);
			print(data.getX(), data.getZ(), 2);
		});
	}
	
	private void runP3() {
		executor.submit(() -> {
			data.setZ(1);
			print(data.getX(), data.getY(), 3);
		});
	}
	
	private void print(int a, int b, int process) {
		printer.print(a, b, process);
	}
}
