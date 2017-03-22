package de.greyshine.springboottemplate;

import org.springframework.boot.SpringApplication;

public class Main {

	/**
	 * 
	 * Change server port by argument: <code>--server.port=&ltport;&gt;</code>
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		SpringApplication.run( Application.class, args )  ;
	}

}
