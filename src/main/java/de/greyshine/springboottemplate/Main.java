package de.greyshine.springboottemplate;

import org.springframework.boot.SpringApplication;

public class Main {

	/**
	 * 
	 * Change application.properties default: <code>--spring.config.location=...</code>
	 * See: https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-external-config.html
     *
	 * Change server port by argument: <code>--server.port=&ltport;&gt;</code>
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
		
		SpringApplication.run( Application.class, args )  ;
	}

}
