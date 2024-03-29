package com.makhani.org.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.makhani.org.dao.CustomerDAO;
import com.makhani.org.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	//need to inject customerDao
	@Autowired
	private CustomerDAO customerDAO;
	
	@Override
	@Transactional
	public List<Customer> getCustomers() {
		
		return customerDAO.getCustomers();
	}

	@Override
	@Transactional
	public void saveCustomer(Customer thecustomer) {
		
		customerDAO.saveCustomer(thecustomer);
		
	}

	@Override
	@Transactional
	public Customer getCustomer(int theId) {
		
		return customerDAO.getCustomer(theId);
		
	}

	@Override
	@Transactional
	public void deleteCustomer(int theId) {
		
		customerDAO.deleteCustomer(theId);
		
	}

	@Override
	@Transactional
	public List<Customer> SearchCustomers(String theSearchName) {
		
		
		return customerDAO.SearchCustomers(theSearchName);
		
	}

}
