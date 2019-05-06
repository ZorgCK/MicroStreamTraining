package one.microstream.microstreamtraining.entities;

import java.util.ArrayList;
import java.util.List;

import one.microstream.microstreamtraining.business.storage.StorageUtils;

public class DataRoot {

	private List<Customer> customers;

	public DataRoot() {
		super();
	}

	public List<Customer> getCustomers() {
		if (this.customers == null) {
			this.customers = new ArrayList<>();
			StorageUtils.STORAGE.store(this);
		}
		return this.customers;
	}

	public void setCustomers(final List<Customer> customers) {
		this.customers = customers;
	}
}
