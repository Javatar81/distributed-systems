package de.bs.sy.comulticast.solution;

import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;

import de.bs.sy.vector.ProcessWithVectorClock;
import de.bs.sy.vector.VectorMessage;
import de.bs.sy.vector.solution.ProcessWithVectorClockSolution;
import de.bs.sy.vector.solution.VectorClockSolution;

public class CoMulticastProcessSolution extends ProcessWithVectorClockSolution {
	private Queue<VectorMessage> messageQueue = new PriorityQueue<>();
	
	public CoMulticastProcessSolution(int id, int clockSize) {
		super(id, clockSize);
	}
	
	@Override
	public void internalEvent() {
		// Do nothing, clock must not be incremented
	}
	
	@Override
	public void receiveMessageFrom(VectorMessage message, ProcessWithVectorClock sender) {
		System.out.println(String.format("P%s received %s", getId(), message)); 
		// Do not increment clock!!!
		if (receivedMessageIsNextFromSender(message) && receivedHasSeenAllMessagesOfSender(message)) {
			setClock((VectorClockSolution) getClock().merge(message.getClock()));
			deliverMessageToApplication(message);
			processQueue();
		} else {
			System.out.println(String.format("P%s cannot deliver M%s and adds it to queue", getId(), message.getMessageId()));
			messageQueue.add(message);
		}
		System.out.println(String.format("P%s clock is %s", getId(), getClock()));
	}
	
	private void processQueue() {
		PriorityQueue<VectorMessage> unmodifiedQueue = new PriorityQueue<>(messageQueue);
		for (Iterator<VectorMessage> iterator = unmodifiedQueue.iterator(); iterator.hasNext();) {
			VectorMessage candidateToDeliver = iterator.next();
			if (receivedMessageIsNextFromSender(candidateToDeliver) && receivedHasSeenAllMessagesOfSender(candidateToDeliver)) {
				setClock((VectorClockSolution) getClock().merge(candidateToDeliver.getClock()));
				messageQueue.poll();
				deliverMessageToApplication(candidateToDeliver);
			}
		}
	}
	
	private boolean receivedMessageIsNextFromSender(VectorMessage message) {
		return message.getClock().getCount(message.getSenderId()) == getClock().getCount(message.getSenderId()) + 1;
	}
	
	private boolean receivedHasSeenAllMessagesOfSender(VectorMessage message) {
		for (int i = 0; i < message.getClock().getCounts().length; i++) {
			if (message.getSenderId() != i && message.getClock().getCount(i) > getClock().getCount(i)) {
				return false;
			}
		}
		return true;
	}
	
	public void deliverMessageToApplication(VectorMessage message) {
		System.out.println(String.format("P%s delivered message %s to application", getId(), message));
	}

}
