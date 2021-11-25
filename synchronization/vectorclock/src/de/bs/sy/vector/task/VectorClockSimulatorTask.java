package de.bs.sy.vector.task;

import de.bs.sy.vector.ProcessWithVectorClock;
import de.bs.sy.vector.VectorClockSimulator;

public class VectorClockSimulatorTask extends VectorClockSimulator{

	public VectorClockSimulatorTask(int procs) {
		super(procs);
	}

	@Override
	public ProcessWithVectorClock newProcessWithClock(int id, int clockSize) {
		return new ProcessWithVectorClockTask(id, clockSize);
	}

}
