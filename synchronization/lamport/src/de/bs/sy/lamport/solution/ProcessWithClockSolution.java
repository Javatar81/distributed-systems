package de.bs.sy.lamport.solution;

import de.bs.sy.lamport.LamportClock;
import de.bs.sy.lamport.Message;
import de.bs.sy.lamport.ProcessWithClock;

public class ProcessWithClockSolution implements ProcessWithClock {
	private final LamportClock clock;
	private final int id;
	
	public ProcessWithClockSolution(int id, int clockStep) {
		clock = new LamportClockSolution(clockStep);
		this.id = id;
	}
	
	@Override
	public void receiveMessageFrom(Message message, ProcessWithClock sender) {
		this.nextCycle();
		if (message.getSendTime() > clock.getCount()) {
			clock.adjust(message.getSendTime() + 1);
			System.out.println(String.format("P%s adjusts clock to %s", id, clock.getCount()));
		} 
	}
	
	@Override
	public int nextCycle() {
		return clock.tickAndGet();
	}
	
	@Override
	public Message sendMessageTo(int messageId, ProcessWithClock receiver) {
		Message message = new Message(messageId, id, clock.getCount());
		return sendMessageTo(message, receiver);
	}
	
	@Override
	public Message sendMessageTo(Message message, ProcessWithClock receiver) {
		System.out.println(String.format("Sent %s to %s", message, receiver));
		this.nextCycle();
		receiver.receiveMessageFrom(message, this);
		return message;
	}

	@Override
	public String toString() {
		return String.format("P%s %s", id, clock);
	}

	@Override
	public LamportClock getClock() {
		return clock;
	}

	protected int getId() {
		return id;
	}
	
}
