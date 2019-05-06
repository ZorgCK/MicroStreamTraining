package one.microstream.microstreamtraining.dal;

import java.util.List;

import one.microstream.microstreamtraining.business.storage.StorageUtils;
import one.microstream.microstreamtraining.entities.Customer;

public class CustomerDAO {

	public static List<Customer> findAll() {
		return StorageUtils.ROOT.get().getCustomers();
	}
	
	public static Customer find(final Integer id) {
		return null;
	}
	
	public static void insert(final Customer customer) {
		findAll().add(customer);
		StorageUtils.STORAGE.store(findAll());
	}
	
	public static void update(final Customer customer) {
		StorageUtils.STORAGE.store(customer);
	}
	
	public static void delete(final Customer customer) {
		findAll().remove(customer);
		StorageUtils.STORAGE.store(findAll());
	}
}
