package one.microstream.microstreamtraining.entities;

import java.util.List;

public class DataRoot {

	private List<Customer> customers;

	public DataRoot() {
		super();
	}

	public List<Customer> getCustomers() {
		return this.customers;
	}

	public void setCustomers(final List<Customer> customers) {
		this.customers = customers;
	}
}
