package de.bs.sy.vector;

public class VectorMessage implements Comparable<VectorMessage>{
	private final int messageId;
	private final int senderId;
	private final VectorClock clock;
	
	public VectorMessage(int messageId, int senderId, VectorClock clock) {
		super();
		this.messageId = messageId;
		this.senderId = senderId;
		this.clock = clock;
	}

	public int getMessageId() {
		return messageId;
	}

	public int getSenderId() {
		return senderId;
	}

	public VectorClock getClock() {
		return clock;
	}

	@Override
	public String toString() {
		return String.format("M%s [senderId=%s, clock=%s]", messageId, senderId, clock);
	}

	@Override
	public int compareTo(VectorMessage other) {
		int[] comparisonResult = new int[clock.getCounts().length];
 		for (int i = 0; i < clock.getCounts().length; i++) {
 			comparisonResult[i] = Integer.compare(getClock().getCount(i), other.getClock().getCount(i)); 
		}
 		for (int i = 0; i < clock.getCounts().length; i++) {
 			if((comparisonResult[0] < 0 && comparisonResult[i] > 0)
 					|| (comparisonResult[0] > 0 && comparisonResult[i] < 0)) {
 				// Not comparable
 				return 0;
 			}
		}
 		return comparisonResult[0];
	}
	
}
