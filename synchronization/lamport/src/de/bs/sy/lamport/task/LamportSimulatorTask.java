package de.bs.sy.lamport.task;

import de.bs.sy.lamport.LamportSimulator;
import de.bs.sy.lamport.ProcessWithClock;

public class LamportSimulatorTask extends LamportSimulator{
	
	public LamportSimulatorTask(int... steps) {
		super(steps);
	}
	@Override
	public ProcessWithClock newProcessWithClock(int id, int clockStep) {
		return new ProcessWithClockTask(id, clockStep);
	}

}
