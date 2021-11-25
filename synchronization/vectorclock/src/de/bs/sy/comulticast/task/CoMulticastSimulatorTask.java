package de.bs.sy.comulticast.task;

import de.bs.sy.vector.ProcessWithVectorClock;
import de.bs.sy.vector.VectorClockSimulator;

public class CoMulticastSimulatorTask extends VectorClockSimulator {
	
	public CoMulticastSimulatorTask(int procs) {
		super(procs);
	}

	@Override
	public ProcessWithVectorClock newProcessWithClock(int id, int procs) {
		return new CoMulticastProcessTask(id, procs);
	}
	
}
