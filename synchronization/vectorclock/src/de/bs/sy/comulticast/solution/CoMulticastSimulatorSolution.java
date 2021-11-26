package de.bs.sy.comulticast.solution;

import de.bs.sy.vector.ProcessWithVectorClock;
import de.bs.sy.vector.VectorClockSimulator;

public class CoMulticastSimulatorSolution extends VectorClockSimulator {
	
	public CoMulticastSimulatorSolution(int procs) {
		super(procs);
	}

	@Override
	public ProcessWithVectorClock newProcessWithClock(int id, int procs) {
		return new CoMulticastProcessSolution(id, procs);
	}

}
