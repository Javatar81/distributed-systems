package de.bs.mt;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicNumberGenerator {
	private static AtomicNumberGenerator instance;
	private AtomicInteger odd = new AtomicInteger(-1);
	private AtomicInteger even = new AtomicInteger(0);
	
	
	private AtomicNumberGenerator() {
		
	}
	
	public static synchronized AtomicNumberGenerator instance(){
		if (instance == null) {
			instance = new AtomicNumberGenerator();
			System.out.println("Only Once");
		}
		return instance;
	}
	
	public long nextOdd() {
		return odd.updateAndGet(i -> 
			i + 2
		);
	}
	
	public long nextEven() {
		return even.updateAndGet(i -> 
			i + 2
		);
	}
}
