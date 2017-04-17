package de.greyshine.springboottemplate;

import javax.servlet.ServletContext;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.spring4.SpringTemplateEngine;

@SpringBootApplication
public class Application {
	
	@Bean
	public SpringTemplateEngine templateEngine(ServletContext inServletContext) {
	    return new de.greyshine.springboottemplate.TemplateEngine();
	}

}
