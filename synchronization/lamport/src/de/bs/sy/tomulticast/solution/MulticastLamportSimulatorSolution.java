package de.bs.sy.tomulticast.solution;

import java.util.List;
import java.util.stream.Collectors;

import de.bs.sy.lamport.LamportSimulator;
import de.bs.sy.lamport.ProcessWithClock;

public class MulticastLamportSimulatorSolution extends LamportSimulator{

	public MulticastLamportSimulatorSolution(int... steps) {
		super(steps);
		List<MulticastProcessSolution> processes = getProcesses()
				.stream()
				.map(MulticastProcessSolution.class::cast)
				.collect(Collectors.toList());
		processes.forEach(p -> p.registerAllProcesses(processes));
	}
	
	@Override
	public ProcessWithClock newProcessWithClock(int id, int clockStep) {
		return new MulticastProcessSolution(id, clockStep);
	}

}
