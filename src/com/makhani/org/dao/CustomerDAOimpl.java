package com.makhani.org.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.makhani.org.entity.Customer;

@Repository
public class CustomerDAOimpl implements CustomerDAO{
	
	//need to inject session Factory
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Customer> getCustomers() {
		
		//get current hibernate session
		Session currentsession = sessionFactory.getCurrentSession();
		
		//create Query
		Query<Customer> theQuery = currentsession.createQuery("from Customer", Customer.class);
		
		//execute query and get result list
		List<Customer> customers = theQuery.getResultList();
		
		return customers;
	}

	@Override
	public void saveCustomer(Customer thecustomer) {
		
		// get current hibernate session
		
		Session currentsession = sessionFactory.getCurrentSession();
		
		//save the customer
		
		currentsession.saveOrUpdate(thecustomer);
	}

	@Override
	public Customer getCustomer(int theId) {
		
		// get current hibernate session
		
		Session currentsession = sessionFactory.getCurrentSession();
		
		//read from the d/b using key
		
		Customer thecustomer = currentsession.get(Customer.class, theId);
		
		return thecustomer;
	}

	@Override
	public void deleteCustomer(int theId) {
		
		// get current hibernate session
		
		Session currentsession = sessionFactory.getCurrentSession();
		
		//Delete object with key
		
		Query theQuery = currentsession.createQuery("delete from Customer where id=:customerId");
		
		theQuery.setParameter("customerId",theId);
		
		theQuery.executeUpdate();
	}

	@Override
	public List<Customer> SearchCustomers(String theSearchName) {
		
		// get current hibernate session
		
		Session currentsession = sessionFactory.getCurrentSession();
		
		Query theQuery = null;
		
		//only search by name if search name is not empty
		
		if(theSearchName != null && theSearchName.trim().length()>0) {
		
		//Search for firstname or Lastname
			
		theQuery = currentsession.createQuery("from Customer where lower(firstName) like:theSearchName or lower(lastName) like:theSearchName",Customer.class);
		
		theQuery.setParameter("theSearchName","%"+ theSearchName.toLowerCase()+"%");
		
		}
		else {
			
		//SearchName is empty so get all customers
		
		theQuery = currentsession.createQuery("from Customer",Customer.class);
		}
		//execute Query
		
		List<Customer> customers = theQuery.getResultList();
		
		return customers;
	}
}
