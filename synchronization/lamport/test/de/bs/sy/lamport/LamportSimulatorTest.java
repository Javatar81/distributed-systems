package de.bs.sy.lamport;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import de.bs.sy.lamport.solution.LamportSimulatorSolution;

class LamportSimulatorTest {
	LamportSimulator sim;
	
	void assertClocks(int... clocks) {
		for (int i = 0; i < clocks.length; i++) {
			assertEquals(clocks[i], sim.getProcesses().get(i).getClock().getCount(), "P"+ i + " has wrong clock count");
		}
	}

	
	@Test
	void test1() {
		sim = new LamportSimulatorSolution(6, 8, 10);
		assertClocks(0, 0, 0);
		sim.nextCycle();
		assertClocks(6, 8, 10);
		sim.sendMessage(0, 1);
		assertClocks(12, 16, 20);
		sim.nextCycle();
		assertClocks(18, 24, 30);
		sim.sendMessage(1, 2);
		assertClocks(24, 32, 40);
		sim.nextCycle();
		assertClocks(30, 40, 50);
		sim.nextCycle();
		assertClocks(36, 48, 60);
		sim.sendMessage(2, 1);
		assertClocks(42, 61, 70);
		sim.nextCycle();
		assertClocks(48, 69, 80);
		sim.sendMessage(1, 0);
		assertClocks(70, 77, 90);
		sim.nextCycle();
		assertClocks(76, 85, 100);
	}
	
	@Test
	void test2() {
		sim = new LamportSimulatorSolution(6, 8, 10);
		assertClocks(0, 0, 0);
		sim.nextCycle();
		assertClocks(6, 8, 10);
		sim.sendMessage(0, 1);
		assertClocks(12, 16, 20);
		sim.sendMessage(2, 1);
		assertClocks(18, 24, 30);
		sim.nextCycle();
		assertClocks(24, 32, 40);
		sim.sendMessage(1, 2);
		assertClocks(30, 40, 50);
		sim.nextCycle();
		assertClocks(36, 48, 60);
		sim.sendMessage(2, 1);
		assertClocks(42, 61, 70);
		sim.nextCycle();
		assertClocks(48, 69, 80);
		sim.sendMessage(1, 0);
		assertClocks(70, 77, 90);
		sim.nextCycle();
		assertClocks(76, 85, 100);
	}

}
