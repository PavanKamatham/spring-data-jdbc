package com.learning.java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.java.data.Customer;
import com.learning.java.exception.RecordNotFoundException;
import com.learning.java.exception.ErrorResponse;
import com.learning.java.exception.RequestException;
import com.learning.java.service.CustomerImpl;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.servers.Server;

/**
 * Class to represent multiple resources to retrieve/created/update/delete
 * customer records in H2 database.
 */
@RestController
@RequestMapping("/spring-data-jdbc/v1")
@OpenAPIDefinition(info = @Info(title = "spring-data-jdbc-demo", description = "To perform CRUD operations using spring data jdbc module"), servers = @Server(url = "http://localhost:8080/spring-data-jdbc/v1"))
public class JDBCController {

	@Autowired
	CustomerImpl customerImpl;

	@GetMapping(path = "/customer/{id}")
	@Operation(summary = "To retrieve customer record based on given Id.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "The customer information", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Customer.class)) }),
			@ApiResponse(responseCode = "400", description = "InvalidRequestParameter", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }),
			@ApiResponse(responseCode = "404", description = "Record not found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }) })
	public Customer getCustomer(@PathVariable String id) throws RequestException {
		return customerImpl.getCustomer(id);
	}

	@GetMapping(path = "/customer/customers")
	@Operation(summary = "To retrieve all customer records.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "The List of customers", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Customer.class)) }),
			@ApiResponse(responseCode = "400", description = "InvalidRequestParameter", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }),
			@ApiResponse(responseCode = "404", description = "Record not found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }) })
	public List<Customer> getCustomers() throws RequestException {
		return customerImpl.getCustomers();
	}

	@Operation(summary = "To create customer record.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "The created customer information", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Customer.class)) }),
			@ApiResponse(responseCode = "400", description = "InvalidRequestParameter", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }) })
	@PostMapping(path = "/customer")
	public Customer saveCustomer(@RequestBody Customer customer) throws RequestException {
		return customerImpl.saveCustomer(customer);
	}

	@Operation(summary = "To create customers record.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "The created customer information", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Customer.class)) }),
			@ApiResponse(responseCode = "400", description = "InvalidRequestParameter", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }) })
	@PostMapping(path = "/customer/customers")
	public List<Customer> saveCustomers(@RequestBody List<Customer> customers) throws RequestException {
		return customerImpl.saveCustomers(customers);
	}

	@DeleteMapping(path = "/customer")
	@Operation(summary = "To deleted customer record.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "The deleted customer information", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Customer.class)) }),
			@ApiResponse(responseCode = "400", description = "InvalidRequestParameter", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }) })
	public Customer deleteCustomer(@RequestBody Customer customer) throws RequestException, RecordNotFoundException {
		return customerImpl.deleteCustomer(customer);
	}

	@DeleteMapping(path = "/customer/customers")
	@Operation(summary = "To deleted all customer records.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "The deleted customers information", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Customer.class)) }),
			@ApiResponse(responseCode = "400", description = "InvalidRequestParameter", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }) })
	public List<Customer> deleteCustomers() throws RequestException, RecordNotFoundException {
		return customerImpl.deleteCustomers();
	}

	@DeleteMapping(path = "/customer/{id}")
	@Operation(summary = "To deleted customer record by given Id.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "The deleted customer information", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Customer.class)) }),
			@ApiResponse(responseCode = "400", description = "InvalidRequestParameter", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }) })

	public Customer deleteCustomerById(@PathVariable String id) throws RequestException, RecordNotFoundException {
		return customerImpl.deleteCustomerById(id);
	}
}
