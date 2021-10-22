package de.bs.mt;

public class TsNumberGenerator {
	private static TsNumberGenerator instance;
	private long odd = -1;
	private long even = 0;
	
	private TsNumberGenerator() {
		
	}
	
	public static synchronized TsNumberGenerator instance(){
		if (instance == null) {
			instance = new TsNumberGenerator();
			System.out.println("Only Once");
		}
		return instance;
	}
	
	public synchronized long nextOdd() {
		return odd = odd + 2;
	}
	
	public synchronized long nextEven() {
		return even = even + 2;
	}
}
