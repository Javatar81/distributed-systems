package de.bs.mt;

public class NumberGenerator {
	
	private static NumberGenerator instance;
	private int odd = -1;
	private int even = 0;
	
	private NumberGenerator() {
		
	}
	
	public static NumberGenerator instance(){
		if (instance == null) {
			instance = new NumberGenerator();
			System.out.println("Only Once");
		}
		return instance;
	}
	
	public int nextEven() {
		return even = even + 2;
	}
	
	public int nextOdd() {
		return odd = odd + 2;
	}

}
