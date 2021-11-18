package de.bs.sy.lamport;

public interface LamportClock {

	/**
	 * 
	 * @return current count of the clock
	 */
	int getCount();

	/**
	 * Adds step to the current count of the clock
	 * @return The count after adding step
	 */
	int tickAndGet();

	/**
	 * Sets the count to the desired value
	 * @param newValue
	 */
	void adjust(int newValue);

}