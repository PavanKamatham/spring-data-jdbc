package com.learning.java.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.learning.java.data.Customer;
import com.learning.java.exception.RecordNotFoundException;
import com.learning.java.exception.RequestException;

@Service
public class CustomerImpl implements ICustomer {

	Logger logger = LoggerFactory.getLogger(CustomerImpl.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public Customer getCustomer(String id) throws RequestException {
		Customer customer = null;
		try {
			customer = jdbcTemplate.queryForObject("Select * from Customer where id = ?",
					BeanPropertyRowMapper.newInstance(Customer.class), id);
		} catch (DataAccessException dae) {
			if (dae instanceof EmptyResultDataAccessException) {
				logger.error("SQLException {}" + ExceptionUtils.getStackTrace(dae));
				throw new RecordNotFoundException("CustomerNotFound", "database", "H2");
			} else {
				logger.error("SQLException {}" + ExceptionUtils.getStackTrace(dae));
				throw new RequestException("SQLException", "database", "H2", dae);
			}
		}
		return customer;
	}

	@Override
	public List<Customer> getCustomers() throws RequestException {
		List<Customer> customers = new ArrayList<>();
		try {
			List<Map<String, Object>> result = jdbcTemplate.queryForList("Select * from Customer");
			result.forEach(results -> {
				Customer cutomer = new Customer((String) results.get("id"), (String) results.get("name"),
						(String) results.get("address"));
				customers.add(cutomer);
			});
		} catch (DataAccessException dae) {
			if (dae instanceof EmptyResultDataAccessException) {
				logger.error("SQLException {}" + ExceptionUtils.getStackTrace(dae));
				throw new RecordNotFoundException("CustomerNotFound", "database", "H2");
			} else {
				logger.error("SQLException {}" + ExceptionUtils.getStackTrace(dae));
				throw new RequestException("SQLException", "database", "H2", dae);
			}
		}
		return customers;
	}

	@Override
	public Customer saveCustomer(Customer customer) throws RequestException {
		try {
			int result = jdbcTemplate.update("Insert INTO Customer(id,name,address)VALUES(?,?,?)", customer.getId(),
					customer.getName(), customer.getAddress());
			if (result == 1) {
				return customer;
			}
		} catch (DataAccessException dae) {
			logger.error("SQLException {}" + ExceptionUtils.getStackTrace(dae));
			throw new RequestException("SQLException", "database", "H2", dae);
		}
		return customer;
	}

	@Override
	public List<Customer> saveCustomers(List<Customer> customers) throws RequestException {
		try {
			List<Object[]> params = new ArrayList<>();
			for (Customer customer : customers) {
				Object[] param = new Object[] { customer.getId(), customer.getName(), customer.getAddress() };
				params.add(param);
			}
			jdbcTemplate.batchUpdate("Insert INTO Customer(id,name,address)VALUES(?,?,?)", params);

		} catch (DataAccessException dae) {
			logger.error("SQLException {}" + ExceptionUtils.getStackTrace(dae));
			throw new RequestException("SQLException", "database", "H2", dae);
		}
		return customers;
	}

	@Override
	public Customer deleteCustomer(Customer customer) throws RequestException, RecordNotFoundException {
		try {
			Object[] param = new Object[] { customer.getId() };
			int result = jdbcTemplate.update("Delete Customer where id=?", param);
			if (result == 1) {
				return customer;
			} else {
				throw new RecordNotFoundException("CustomerNotFound", "database", "H2");
			}
		} catch (DataAccessException dae) {
			logger.error("SQLException {}" + ExceptionUtils.getStackTrace(dae));
			throw new RequestException("SQLException", "database", "H2", dae);
		}
	}

	@Override
	public List<Customer> deleteCustomers() throws RequestException, RecordNotFoundException {
		try {
			List<Customer> customers = getCustomers();
			int result = jdbcTemplate.update("Delete from Customer");
			if (result >= 1) {
				return customers;
			} else {
				throw new RecordNotFoundException("CustomerNotFound", "database", "H2");
			}
		} catch (DataAccessException dae) {
			logger.error("SQLException {}" + ExceptionUtils.getStackTrace(dae));
			throw new RequestException("SQLException", "database", "H2", dae);
		}
	}

	@Override
	public Customer deleteCustomerById(String id) throws RequestException, RecordNotFoundException {
		try {
			Customer customer = getCustomer(id);
			int result = jdbcTemplate.update("Delete from customer where id=?", new Object[] { id });
			if (result > 0) {
				return customer;
			} else {

				throw new RecordNotFoundException("CustomerNotFound", "database", "H2");
			}
		} catch (DataAccessException dae) {
			logger.error("SQLException {}" + ExceptionUtils.getStackTrace(dae));
			throw new RequestException("SQLException", "database", "H2", dae);
		}
	}
}
