package de.bs.consistency.sequential.prgorder;

import java.util.ArrayList;
import java.util.List;

public class OrderPrinter {
	private List<Integer> print = new ArrayList<>();
	private List<Integer> process = new ArrayList<>();
	
	public synchronized void print(int a, int b, int processNum) {
		print.add(a);
		print.add(b);
		process.add(processNum);
	}
	
	@Override
	public String toString() {
		StringBuilder strBuilder = new StringBuilder();
		int i = 0;
		for (Integer integer : print) {
			strBuilder.append(integer);
			if (i % 2 == 1) {
				strBuilder.append(" ");
			}
			i++;
		}
		strBuilder.append("(");
		for (Integer integer : process) {
			strBuilder.append(integer);
		}
		strBuilder.append(")");
		return strBuilder.toString();
	}

}
