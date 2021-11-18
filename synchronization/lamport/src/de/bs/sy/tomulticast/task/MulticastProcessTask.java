package de.bs.sy.tomulticast.task;

import java.util.List;

import de.bs.sy.lamport.Message;
import de.bs.sy.lamport.ProcessWithClock;
import de.bs.sy.lamport.solution.ProcessWithClockSolution;

public class MulticastProcessTask extends ProcessWithClockSolution{

	public MulticastProcessTask(int id, int clockStep) {
		super(id, clockStep);
	}

	public void registerAllProcesses(List<MulticastProcessTask> allProcesses) {
		//TODO Implement
	}
	
	public void deliverMessageToApplication(Message message) {
		//TODO Implement and print event
	}
	
	public void receiveAcknowledgement(Message message) {
		//TODO Implement and print event
	}
	
	public boolean isInQueue(Message message) {
		//TODO Implement
		return false;
	}
	
	@Override
	public void receiveMessageFrom(Message message, ProcessWithClock sender) {
		super.receiveMessageFrom(message, sender);
		//TODO Add additional logic
	}
}
