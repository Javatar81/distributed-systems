package de.bs.mt;

public class MultithreadingNumberGeneratorTest {
	public static void main(String[] args) {
		for (int i = 0; i < 20; i++) {
			createNumberGeneratorThread().start();
		}
	}
	
	private static Thread createNumberGeneratorThread() {
		return new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println(NumberGenerator.instance().nextEven());
			}
		}); 
	}
}
