package com.makhani.org.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.makhani.org.entity.Customer;
import com.makhani.org.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	//Need to inject the Customerservice
	@Autowired
	private CustomerService customerservice;

	@RequestMapping("/list")
	public String listcustomers(Model themodel) {
		
		//get customers from dao
		
		List<Customer> thecustomer = customerservice.getCustomers();
		
		//Add customer to model
		
		themodel.addAttribute("customer",thecustomer);
		
		return "list-customers";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model themodel) {
		
		Customer thecustomer = new Customer();
		
		themodel.addAttribute("customer",thecustomer);
		
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer")Customer thecustomer) {
		
		//save the customer using service
		
		customerservice.saveCustomer(thecustomer);
		
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId")int theId,Model themodel) {
		
		//get the customer from service
		
		Customer thecustomer = customerservice.getCustomer(theId);
		
		//set customer as model Attribute
		
		themodel.addAttribute("customer",thecustomer);
		
		return "customer-form";
	}
	
	@GetMapping("/delete")
	public String deletecustomer(@RequestParam("customerId")int theId) {
		
		//delete the customer
		
		customerservice.deleteCustomer(theId);
		
		return "redirect:/customer/list";
		
	}
	
	@GetMapping("/Search")
	public String SearchCustomers(@RequestParam("theSearchName")String theSearchName, Model themodel) {
		
		//search customers from the service
		
		List<Customer> thecustomer = customerservice.SearchCustomers(theSearchName);
		
		//Add the customers to model
		
		themodel.addAttribute("customer",thecustomer);
		
		return "list-customer";
		
	}
}
