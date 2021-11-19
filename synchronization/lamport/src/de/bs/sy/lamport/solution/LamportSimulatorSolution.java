package de.bs.sy.lamport.solution;

import de.bs.sy.lamport.LamportSimulator;
import de.bs.sy.lamport.ProcessWithClock;

public class LamportSimulatorSolution extends LamportSimulator{

	public LamportSimulatorSolution(int... steps) {
		super(steps);
	}
	@Override
	public ProcessWithClock newProcessWithClock(int id, int clockStep) {
		return new ProcessWithClockSolution(id, clockStep);
	}

}
