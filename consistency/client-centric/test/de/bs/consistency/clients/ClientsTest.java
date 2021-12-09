package de.bs.consistency.clients;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import de.bs.consistency.clients.task.ClientTask;
import de.bs.consistency.clients.task.DatastoreTask;

public class ClientsTest {
	private Datastore ds1 = new DatastoreTask(1);
	private Datastore ds2 = new DatastoreTask(2);
	
	{
		ds1.addDataStore(ds2);
		ds2.addDataStore(ds1);
	}
	
	@Test
	public void monotonicRead() {
		Client client = new ClientTask();
		ds1.increment(1);
		ds2.increment(2);
		assertEquals(1, client.read(ds1));
		assertEquals(2, client.read(ds2));
	}
	
}
