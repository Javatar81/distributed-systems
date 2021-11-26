package de.bs.sy.comulticast;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import de.bs.sy.comulticast.solution.CoMulticastSimulatorSolution;
import de.bs.sy.vector.VectorClockSimulator;
import de.bs.sy.vector.VectorMessage;

public class CoMulticastSimulatorTest {
VectorClockSimulator sim;
	
	void assertClock(int process, int... count) {
		for (int i = 0; i < count.length; i++) {
			assertEquals(String.format("P%s has wrong clock count at index %s", process, i), count[i], sim.getProcesses().get(process).getClock().getCount(i));
		}
		
	}
	
	@Test
	void test1() {
		sim = new CoMulticastSimulatorSolution(3);
		assertClock(0, 0, 0, 0);
		assertClock(1, 0, 0, 0);
		assertClock(2, 0, 0, 0);
		VectorMessage message1 = sim.sendMessage(0, 1);
		assertClock(0, 1, 0, 0);
		assertClock(1, 1, 0, 0);
		assertClock(2, 0, 0, 0);
		VectorMessage message2 = sim.sendMessage(1, 2);
		assertClock(0, 1, 0, 0);
		assertClock(1, 1, 1, 0);
		assertClock(2, 0, 0, 0);
		sim.sendMessage(message2, 0);
		assertClock(0, 1, 1, 0);
		assertClock(1, 1, 1, 0);
		assertClock(2, 0, 0, 0);
		sim.sendMessage(message1, 2);
		assertClock(0, 1, 1, 0);
		assertClock(1, 1, 1, 0);
		assertClock(2, 1, 1, 0);
	}
}
