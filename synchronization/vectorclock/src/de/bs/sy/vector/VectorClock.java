package de.bs.sy.vector;

public interface VectorClock {

	/**
	 * Increments clock at index
	 * @param index
	 */
	void increment(int index);

	/**
	 * Merges the clock by returning a new merged Clock.
	 * Does not modify the instance merge is called on.
	 * @param other
	 * @return
	 */
	VectorClock merge(VectorClock other);

	/**
	 * Does the clock causally precede the other clock?
	 * @param other
	 * @return
	 */
	boolean causallyPrecedes(VectorClock other);

	/**
	 * Get the count clock at given index.
	 * @param index
	 * @return
	 */
	int getCount(int index);
	
	/**
	 * 
	 * @return The counts of the clock.
	 */
	public int[] getCounts();

}