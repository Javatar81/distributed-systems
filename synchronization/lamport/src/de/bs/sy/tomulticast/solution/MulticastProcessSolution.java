package de.bs.sy.tomulticast.solution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import de.bs.sy.lamport.Message;
import de.bs.sy.lamport.ProcessWithClock;
import de.bs.sy.lamport.solution.ProcessWithClockSolution;

public class MulticastProcessSolution extends ProcessWithClockSolution{
	private Queue<Message> messageQueue = new PriorityQueue<>();
	private List<MulticastProcessSolution> allProcesses;
	private Map<Integer, Integer> messageAcks = new HashMap<>();

	public MulticastProcessSolution(int id, int clockStep) {
		super(id, clockStep);
	}

	public void registerAllProcesses(List<MulticastProcessSolution> allProcesses) {
		this.allProcesses = new ArrayList<>(allProcesses);
	}
	
	
	public void deliverMessageToApplication(Message message) {
		messageQueue.poll();
		System.out.println(String.format("P%s delivered message %s to application", getId(), message));
	}
		
	public void receiveAcknowledgement(Message message) {
		initMessageAcksIfEmpty(message);
		Integer acks = messageAcks.get(message.getMessageId());
		messageAcks.put(message.getMessageId(), acks + 1);
		PriorityQueue<Message> unmodifiedQueue = new PriorityQueue<>(messageQueue);
		for (Iterator<Message> iterator = unmodifiedQueue.iterator(); iterator.hasNext();) {
			Message candidateToDeliver = iterator.next();
			Integer ackNum = messageAcks.get(candidateToDeliver.getMessageId());
			if (ackNum >= allProcesses.size() && candidateToDeliver.equals(messageQueue.peek())) {
				deliverMessageToApplication(message);
			} else {
				System.out.println(String.format("Message M%s cannot be delivered by P%s", candidateToDeliver.getMessageId(), getId()));
				break;
			}
		}
	}

	private void initMessageAcksIfEmpty(Message message) {
		if(!messageAcks.containsKey(message.getMessageId())) {
			messageAcks.put(message.getMessageId(), 0);
		}
	}
	
	public boolean isInQueue(Message message) {
		return messageQueue.contains(message);
	}
	
	@Override
	public void receiveMessageFrom(Message message, ProcessWithClock sender) {
		super.receiveMessageFrom(message, sender);
		messageQueue.add(message);
		System.out.println(String.format("P%s's queue is %s", super.getId(), messageQueue));
		System.out.println(String.format("P%s acknowledges M%s", super.getId(), message.getMessageId()));
		sendAcknowledgements(message);
	}

	private void sendAcknowledgements(Message message) {
		allProcesses.forEach(p -> p.receiveAcknowledgement(message));
	}
}
