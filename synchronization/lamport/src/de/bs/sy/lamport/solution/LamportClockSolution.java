package de.bs.sy.lamport.solution;

import java.util.concurrent.atomic.AtomicInteger;

import de.bs.sy.lamport.LamportClock;

public class LamportClockSolution implements LamportClock {
	private final int step;
	private AtomicInteger counter = new AtomicInteger(0);

	public LamportClockSolution(int step) {
		super();
		this.step = step;
	}
	
	@Override
	public int getCount() {
		return counter.get();
	}
	
	@Override
	public int tickAndGet() {
		return counter.addAndGet(step);
	}
	
	@Override
	public void adjust(int newValue) {
		counter.set(newValue);
	}

	@Override
	public String toString() {
		return "[s=" + step + ", c=" + counter + "]";
	}
}
