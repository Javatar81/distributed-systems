package de.bs.sy.tomulticast;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import de.bs.sy.lamport.LamportSimulator;
import de.bs.sy.lamport.Message;
import de.bs.sy.tomulticast.task.MulticastLamportSimulatorTask;
import de.bs.sy.tomulticast.task.MulticastProcessTask;


public class ToMulticastSimulatorTest {
	LamportSimulator sim;
	
	void assertMessageInQueue(Message message, int processNumber) {
		MulticastProcessTask process = (MulticastProcessTask) sim.getProcesses().get(processNumber);
		assertTrue(String.format("Message %s not in queue of P%s ", message, processNumber), process.isInQueue(message));
	}
	
	void assertMessageNotInQueue(Message message, int processNumber) {
		MulticastProcessTask process = (MulticastProcessTask) sim.getProcesses().get(processNumber);
		assertFalse(String.format("Message %s not in queue of P%s ", message, processNumber), process.isInQueue(message));
	}

	@Test
	void test1() {
		sim = new MulticastLamportSimulatorTask(6, 8, 10, 12);
		Message message1 = sim.sendMessage(0, 0);
		assertMessageInQueue(message1, 0);
		Message message2 = sim.sendMessage(3, 3);
		assertMessageInQueue(message1, 0);
		assertMessageInQueue(message2, 3);
		sim.sendMessage(message1, 1);
		assertMessageInQueue(message1, 0);
		assertMessageInQueue(message2, 3);
		assertMessageInQueue(message1, 1);
		sim.sendMessage(message2, 2);
		assertMessageInQueue(message1, 0);
		assertMessageInQueue(message2, 3);
		assertMessageInQueue(message1, 1);
		assertMessageInQueue(message2, 2);
		sim.sendMessage(message1, 2);
		assertMessageInQueue(message1, 0);
		assertMessageInQueue(message2, 3);
		assertMessageInQueue(message1, 1);
		assertMessageInQueue(message2, 2);
		assertMessageInQueue(message1, 2);
		sim.sendMessage(message2, 1);
		assertMessageInQueue(message1, 0);
		assertMessageInQueue(message2, 3);
		assertMessageInQueue(message1, 1);
		assertMessageInQueue(message2, 2);
		assertMessageInQueue(message1, 2);
		assertMessageInQueue(message2, 1);
		sim.sendMessage(message1, 3);
		// message 1 can be delivered, top queue and all acks
		assertMessageNotInQueue(message1, 0);
		assertMessageInQueue(message2, 3);
		assertMessageNotInQueue(message1, 1);
		assertMessageInQueue(message2, 2);
		assertMessageNotInQueue(message1, 2);
		assertMessageInQueue(message2, 1);
		assertMessageNotInQueue(message1, 3);
		sim.sendMessage(message2, 0);	
		// message 2 can be delivered, top queue and all acks
		assertMessageNotInQueue(message1, 0);
		assertMessageNotInQueue(message2, 3);
		assertMessageNotInQueue(message1, 1);
		assertMessageNotInQueue(message2, 2);
		assertMessageNotInQueue(message1, 2);
		assertMessageNotInQueue(message2, 1);
		assertMessageNotInQueue(message1, 3);
		assertMessageNotInQueue(message2, 0);
	}
	
}
