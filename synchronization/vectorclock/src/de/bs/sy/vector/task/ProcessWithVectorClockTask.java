package de.bs.sy.vector.task;

import de.bs.sy.vector.ProcessWithVectorClock;
import de.bs.sy.vector.VectorClock;
import de.bs.sy.vector.VectorMessage;

public class ProcessWithVectorClockTask implements ProcessWithVectorClock{
	
	public ProcessWithVectorClockTask(int id, int clockSize) {
		// TODO implement
	}
	
	@Override
	public void internalEvent() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public VectorMessage sendMessageTo(int messageId, ProcessWithVectorClock receiver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VectorMessage sendMessageTo(VectorMessage message, ProcessWithVectorClock receiver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void receiveMessageFrom(VectorMessage message, ProcessWithVectorClock sender) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public VectorClock getClock() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}

}
