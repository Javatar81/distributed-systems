package de.bs.sy.comulticast.task;

import de.bs.sy.vector.VectorMessage;
import de.bs.sy.vector.task.ProcessWithVectorClockTask;

public class CoMulticastProcessTask extends ProcessWithVectorClockTask {

	public CoMulticastProcessTask(int id, int clockSize) {
		super(id, clockSize);
	}

	public void deliverMessageToApplication(VectorMessage message) {
		System.out.println(String.format("P%s delivered message %s to application", getId(), message));
	}
}
