package de.bs.sy.lamport;

/**
 * 
 * A process that is able to send messages and has a Lamport clock
 *
 */
public interface ProcessWithClock {

	/**
	 * Clock of the process ticks
	 * @return The value of the clock after next cycle has been set
	 */
	int nextCycle();
	
	LamportClock getClock();

	/**
	 * Sends a message from current process to receiver process.
	 * Moves receiver to next cycle, calls receive for the receiver 
	 * and moves current process to the next cycle. 
	 * 
	 * @param messageId
	 * @param receiver
	 */
	Message sendMessageTo(int messageId, ProcessWithClock receiver);
	
	/**
	 * 
	 * @param message
	 * @param receiver
	 * @return
	 */
	Message sendMessageTo(Message message, ProcessWithClock receiver);

	/**
	 * Receives message and adjusts time if necessary.
	 * @param message
	 * @param sender
	 */
	void receiveMessageFrom(Message message, ProcessWithClock sender);

}