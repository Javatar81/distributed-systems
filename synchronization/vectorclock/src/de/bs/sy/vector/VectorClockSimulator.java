package de.bs.sy.vector;

import java.util.ArrayList;
import java.util.List;

public abstract class VectorClockSimulator {
	private List<ProcessWithVectorClock> processes = new ArrayList<>();
	private int messageCount = 0;
	
	public VectorClockSimulator(int procs) {
		for (int i = 0; i < procs; i++) {
			processes.add(newProcessWithClock(i, procs));
		}
	}
	
	public abstract ProcessWithVectorClock newProcessWithClock(int id, int procs);
	
	public void internalEvent(int process) {
		processes.get(process).internalEvent();
		System.out.println(String.format("%s after internal event", processes.get(process)));
	}
	
	public VectorMessage sendMessage(int processFrom, int processTo) {
		messageCount++;
		return sendMessage(messageCount, processFrom, processTo);
	}
	
	public VectorMessage sendMessage(VectorMessage message, int processTo) {
		processes.get(message.getSenderId()).sendMessageTo(message, processes.get(processTo));
		return message;
	}
	
	private VectorMessage sendMessage(int messageId, int processFrom, int processTo) {
		VectorMessage message = processes.get(processFrom).sendMessageTo(messageId, processes.get(processTo));
		System.out.println(processes);
		return message;
	}

	public List<ProcessWithVectorClock> getProcesses() {
		return processes;
	}
	
	
}
