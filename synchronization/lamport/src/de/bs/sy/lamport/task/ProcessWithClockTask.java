package de.bs.sy.lamport.task;

import de.bs.sy.lamport.LamportClock;
import de.bs.sy.lamport.Message;
import de.bs.sy.lamport.ProcessWithClock;

public class ProcessWithClockTask implements ProcessWithClock{
	
	public ProcessWithClockTask(int id, int clockStep) {
		// TODO Implement
	}

	@Override
	public int nextCycle() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public LamportClock getClock() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message sendMessageTo(int messageId, ProcessWithClock receiver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void receiveMessageFrom(Message message, ProcessWithClock sender) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Message sendMessageTo(Message message, ProcessWithClock receiver) {
		// TODO Auto-generated method stub
		return null;
	}
}
