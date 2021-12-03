package de.bs.consistency.sequential.prgorder;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class OrderData {
	private volatile AtomicInteger x = new AtomicInteger();
	private volatile AtomicInteger y = new AtomicInteger();
	private volatile AtomicInteger z = new AtomicInteger();
	
	public int getX() {
		return x.get();
	}
	public void setX(int x) {
		this.x.set(x);
	}
	public int getY() {
		return y.get();
	}
	public void setY(int y) {
		this.y.set(y);
	}
	public int getZ() {
		return z.get();
	}
	public void setZ(int z) {
		this.z.set(z);
	}
	@Override
	public int hashCode() {
		return Objects.hash(x, y, z);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderData other = (OrderData) obj;
		return Objects.equals(x, other.x) && Objects.equals(y, other.y) && Objects.equals(z, other.z);
	}
	@Override
	public String toString() {
		return String.format("OrderData [x=%s, y=%s, z=%s]", x, y, z);
	}
	
	
	
}
