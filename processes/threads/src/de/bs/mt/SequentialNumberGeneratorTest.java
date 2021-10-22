package de.bs.mt;

public class SequentialNumberGeneratorTest {
	public static void main(String[] args) {
		for (int i = 0; i < 5000; i++) {
			System.out.println(NumberGenerator.instance().nextOdd());
		}	
	}
}
