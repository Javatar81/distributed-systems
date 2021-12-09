package de.bs.consistency.clients;

import java.util.List;

public interface Datastore {

	void addDataStore(Datastore ds);

	int read(List<Integer> readSet);

	void write(int id, int x);

	void increment(int id);

	List<Integer> getWrites();

}