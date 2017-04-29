package de.greyshine.springboottemplate;


import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring4.SpringTemplateEngine;

@SpringBootApplication
public class Application extends WebMvcConfigurerAdapter {
	
	@Bean
	public SpringTemplateEngine templateEngine(ServletContext inServletContext) {
		return new de.greyshine.springboottemplate.TemplateEngine();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new ExampleInterceptor()).addPathPatterns( "/*" );
	}
}
