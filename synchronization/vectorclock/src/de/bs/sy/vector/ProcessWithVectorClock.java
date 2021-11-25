package de.bs.sy.vector;

public interface ProcessWithVectorClock {

	void internalEvent();

	VectorMessage sendMessageTo(int messageId, ProcessWithVectorClock receiver);

	VectorMessage sendMessageTo(VectorMessage message, ProcessWithVectorClock receiver);

	void receiveMessageFrom(VectorMessage message, ProcessWithVectorClock sender);

	VectorClock getClock();

	int getId();

}