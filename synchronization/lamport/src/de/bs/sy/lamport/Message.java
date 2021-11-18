package de.bs.sy.lamport;

import java.util.Objects;

public class Message implements Comparable<Message>{
	private final int messageId;
	private final int senderId;
	private final int sendTime;

	public Message(int messageId, int senderId, int sendTime) {
		this.messageId = messageId;
		this.senderId = senderId;
		this.sendTime = sendTime;
	}

	public int getSendTime() {
		return sendTime;
	}
	
	public int getMessageId() {
		return messageId;
	}

	public int getSenderId() {
		return senderId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(messageId, sendTime, senderId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		return messageId == other.messageId && sendTime == other.sendTime && senderId == other.senderId;
	}

	@Override
	public String toString() {
		return String.format("M%s [sId=%s, sTime=%s]", messageId, senderId, sendTime);
	}

	@Override
	public int compareTo(Message o) {
		int sendTimeCompare = Integer.compare(this.sendTime, o.sendTime);
		if (sendTimeCompare != 0) {
			return sendTimeCompare;
		} else {
			return Integer.compare(this.senderId, o.senderId);			
		}
	}
}
