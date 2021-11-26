package de.bs.sy.vector.solution;

import java.util.Arrays;

import de.bs.sy.vector.VectorClock;

public class VectorClockSolution implements VectorClock {
	int[] counts;
	
	public VectorClockSolution(VectorClock value) {
		counts = Arrays.copyOf(value.getCounts(), value.getCounts().length);
	}
	
	public VectorClockSolution(int size) {
		counts = new int[size];
	}

	@Override
	public int getCount(int index) {
		return counts[index];
	}
	
	@Override
	public void increment(int index) {
		counts[index]++;
	}
	
	@Override
	public VectorClock merge(VectorClock other) {
		VectorClockSolution result = new VectorClockSolution(this);
		for (int i = 0; i < counts.length; i++) {
			result.setCount(i, Math.max(counts[i], other.getCount(i)));
		}
		return result;
	}
	
	@Override
	public boolean causallyPrecedes(VectorClock other) {
		for (int i = 0; i < counts.length; i++) {
			if(counts[i] >= other.getCount(i)) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public int[] getCounts() {
		return counts;
	}

	public void setCounts(int[] counts) {
		this.counts = counts;
	}
	
	public void setCount(int index, int count) {
		this.counts[index] = count;
	}

	@Override
	public String toString() {
		return "VectorClock [" + Arrays.toString(counts) + "]";
	}
	
	
}
