package de.bs.sy.tomulticast.task;

import java.util.List;
import java.util.stream.Collectors;

import de.bs.sy.lamport.LamportSimulator;
import de.bs.sy.lamport.ProcessWithClock;

public class MulticastLamportSimulatorTask extends LamportSimulator{
	
	public MulticastLamportSimulatorTask(int... steps) {
		super(steps);
		List<MulticastProcessTask> processes = getProcesses()
				.stream()
				.map(MulticastProcessTask.class::cast)
				.collect(Collectors.toList());
		processes.forEach(p -> p.registerAllProcesses(processes));
	}
	
	@Override
	public ProcessWithClock newProcessWithClock(int id, int clockStep) {
		return new MulticastProcessTask(id, clockStep);
	}
	
}
