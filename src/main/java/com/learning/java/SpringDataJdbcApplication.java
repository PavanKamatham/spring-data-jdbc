package com.learning.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * Class that can be used to bootstrap and launch a Spring application from a
 * Java main method.
 */
@SpringBootApplication
public class SpringDataJdbcApplication {

	/**
	 * Create an appropriate {@link ApplicationContext} instance (depending on your
	 * classpath) and start the Tomcat server.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringDataJdbcApplication.class, args);
	}

}
