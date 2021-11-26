package de.bs.sy.vector.solution;

import de.bs.sy.vector.ProcessWithVectorClock;
import de.bs.sy.vector.VectorClockSimulator;

public class VectorClockSimulatorSolution extends VectorClockSimulator{

	public VectorClockSimulatorSolution(int procs) {
		super(procs);
	}

	@Override
	public ProcessWithVectorClock newProcessWithClock(int id, int clockSize) {
		return new ProcessWithVectorClockSolution(id, clockSize);
	}

}
