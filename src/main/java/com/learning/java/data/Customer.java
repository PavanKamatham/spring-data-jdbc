package com.learning.java.data;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import jakarta.validation.constraints.NotNull;

/**
 * Represent a Customer.
 */
public class Customer implements Serializable {

	/**
	 * Generated SerialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/** The unique Identifier of a customer. */
	@NotNull
	private String id;
	/** The name of customer. */
	@NotNull
	private String name;
	/** The address of customer. */
	private String address;

	/**
	 * Default constructor
	 */
	public Customer() {

	}

	/**
	 * Parameterized constructor
	 * 
	 * @param customerId
	 * @param customerName
	 * @param customerAddress
	 */
	public Customer(String customerId, String customerName, String customerAddress) {
		this.id = customerId;
		this.name = customerName;
		this.address = customerAddress;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (!(obj instanceof Customer))
			return false;

		Customer customer = (Customer) obj;
		return this.id.equals(customer.id) && this.name.equals(customer.name) && this.address.equals(customer.address);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = result * prime + ((null == id) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
