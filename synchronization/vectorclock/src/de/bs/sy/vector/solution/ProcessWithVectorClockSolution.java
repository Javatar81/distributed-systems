package de.bs.sy.vector.solution;

import de.bs.sy.vector.ProcessWithVectorClock;
import de.bs.sy.vector.VectorClock;
import de.bs.sy.vector.VectorMessage;

public class ProcessWithVectorClockSolution implements ProcessWithVectorClock {
	private VectorClockSolution clock;
	private final int id;

	public ProcessWithVectorClockSolution(int id, int clockSize) {
		super();
		this.clock = new VectorClockSolution(clockSize);
		this.id = id;
	}
	
	@Override
	public void internalEvent() {
		clock.increment(id);
	}
	
	@Override
	public VectorMessage sendMessageTo(int messageId, ProcessWithVectorClock receiver) {
		clock.increment(id);
		VectorMessage message = new VectorMessage(messageId, id, new VectorClockSolution(clock));
		return sendMessageTo(message, receiver);
	}
	
	@Override
	public VectorMessage sendMessageTo(VectorMessage message, ProcessWithVectorClock receiver) {
		receiver.receiveMessageFrom(message, this);
		return message;
	}
	
	@Override
	public void receiveMessageFrom(VectorMessage message, ProcessWithVectorClock sender) {
		System.out.println(String.format("P%s received %s", id, message));
		clock = new VectorClockSolution(clock.merge(message.getClock()));
		clock.increment(id);
		System.out.println(String.format("P%s clock is %s", id, clock));
	}

	@Override
	public VectorClock getClock() {
		return clock;
	}
	
	protected void setClock(VectorClockSolution clock) {
		this.clock = clock;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return String.format("P%s [clock=%s]", id, clock);
	}
	
	
}
