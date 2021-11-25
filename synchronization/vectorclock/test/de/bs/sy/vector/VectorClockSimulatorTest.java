package de.bs.sy.vector;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import de.bs.sy.vector.task.VectorClockSimulatorTask;

public class VectorClockSimulatorTest {

	VectorClockSimulator sim;
	
	void assertClock(int process, int... count) {
		for (int i = 0; i < count.length; i++) {
			assertEquals(String.format("P%s has wrong clock count at index %s", process, i), count[i], sim.getProcesses().get(process).getClock().getCount(i));
		}
		
	}
	
	@Test
	void test1() {
		sim = new VectorClockSimulatorTask(3);
		assertClock(0, 0, 0, 0);
		assertClock(1, 0, 0, 0);
		assertClock(2, 0, 0, 0);
		sim.internalEvent(0);
		assertClock(0, 1, 0, 0);
		assertClock(1, 0, 0, 0);
		assertClock(2, 0, 0, 0);
		sim.sendMessage(0, 1);
		assertClock(0, 2, 0, 0);
		assertClock(1, 2, 1, 0);
		assertClock(2, 0, 0, 0);
		sim.internalEvent(2);
		assertClock(0, 2, 0, 0);
		assertClock(1, 2, 1, 0);
		assertClock(2, 0, 0, 1);
		sim.sendMessage(1, 2);
		assertClock(0, 2, 0, 0);
		assertClock(1, 2, 2, 0);
		assertClock(2, 2, 2, 2);
	}
}
