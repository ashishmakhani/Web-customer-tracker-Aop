package com.makhani.org.service;

import java.util.List;

import com.makhani.org.entity.Customer;

public interface CustomerService {
	
	public List<Customer> getCustomers();

	public void saveCustomer(Customer thecustomer);

	public Customer getCustomer(int theId);

	public void deleteCustomer(int theId);

	public List<Customer> SearchCustomers(String theSearchName);

}
