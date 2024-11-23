package com.learning.java.service;

import java.util.List;

import com.learning.java.data.Customer;
import com.learning.java.exception.RecordNotFoundException;
import com.learning.java.exception.RequestException;

/**
 * The {@code ICustomer} interface provides multiple methods to perform CRUD
 * operations on Customer records.
 */
public interface ICustomer {
	public Customer getCustomer(String id) throws RequestException;

	public List<Customer> getCustomers() throws RequestException;

	public Customer saveCustomer(Customer customer) throws RequestException;

	public List<Customer> saveCustomers(List<Customer> customers) throws RequestException;

	public Customer deleteCustomer(Customer customer) throws RequestException, RecordNotFoundException;

	public List<Customer> deleteCustomers() throws RequestException, RecordNotFoundException;

	public Customer deleteCustomerById(String id) throws RequestException, RecordNotFoundException;

}
