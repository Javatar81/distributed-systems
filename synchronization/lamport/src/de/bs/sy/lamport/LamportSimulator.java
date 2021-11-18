package de.bs.sy.lamport;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class LamportSimulator {
	private List<ProcessWithClock> processes = new ArrayList<>();
	private int messageCount = 0;

	public abstract ProcessWithClock newProcessWithClock(int id, int clockStep);
	
	protected LamportSimulator(int... steps) {
		for (int i = 0; i < steps.length; i++) {
			processes.add(newProcessWithClock(i, steps[i]));
		}
	}
	
	public List<ProcessWithClock> getProcesses() {
		return new ArrayList<>(processes);
	}
	
	public void nextCycle() {
		processes.forEach(ProcessWithClock::nextCycle);
		System.out.println(processes);
	}

	public Message sendMessage(int processFrom, int processTo) {
		messageCount++;
		return sendMessage(messageCount, processFrom, processTo);
	}
	
	public Message sendMessage(Message message, int processTo) {
		processes.get(message.getSenderId()).sendMessageTo(message, processes.get(processTo));
		return message;
	}
	
	private Message sendMessage(int messageId, int processFrom, int processTo) {
		Message message = processes.get(processFrom).sendMessageTo(messageId, processes.get(processTo));
		nextCycleForOtherProcesses(processFrom, processTo);
		System.out.println(processes);
		return message;
	}

	private void nextCycleForOtherProcesses(int processFrom, int processTo) {
		int i = 0;
		for (Iterator<ProcessWithClock> iterator = processes.iterator(); iterator.hasNext(); i++) {
			ProcessWithClock process = iterator.next();
			if (i != processFrom && i != processTo) {
				process.nextCycle();
			}
		}
	}

	@Override
	public String toString() {
		return String.format("LamportSimulator [processes=%s, messageCount=%s]", processes, messageCount);
	}
}
