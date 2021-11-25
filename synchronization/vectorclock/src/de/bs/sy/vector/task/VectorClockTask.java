package de.bs.sy.vector.task;

import de.bs.sy.vector.VectorClock;

public class VectorClockTask implements VectorClock{

	@Override
	public void increment(int index) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public VectorClock merge(VectorClock other) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean causallyPrecedes(VectorClock other) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getCount(int index) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int[] getCounts() {
		// TODO Auto-generated method stub
		return null;
	}

}
