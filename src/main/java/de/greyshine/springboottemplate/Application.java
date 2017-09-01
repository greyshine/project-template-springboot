package de.greyshine.springboottemplate;


import java.util.List;

import javax.jms.ConnectionFactory;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring4.SpringTemplateEngine;

@SpringBootApplication
public class Application extends WebMvcConfigurerAdapter {
	
	public static final String BEAN_JMSLISTENERCONTAINERFACTORY = "jmsListenerContainerFactory";
	
	@Bean
	public SpringTemplateEngine templateEngine(ServletContext inServletContext) {
		return new de.greyshine.springboottemplate.TemplateEngine();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new ExampleInterceptor()).addPathPatterns( "/*" );
		registry.addInterceptor(new CredentialsInterceptor("hello","world")).addPathPatterns( "/secured/*" );
	}
	
	@Bean
	public SessionListener getSessionListener(){
		return new SessionListener();
	}
	
	@Override
	 public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		 converters.add( new JsonHttpMessageConverter() );
	 }
	
	@Bean( name=BEAN_JMSLISTENERCONTAINERFACTORY )
    public JmsListenerContainerFactory<?> jmsListenerContainerFactory(ConnectionFactory connectionFactory,
                                                    DefaultJmsListenerContainerFactoryConfigurer configurer) {
        
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        
		// This provides all boot's default to this factory, including the message converter
        configurer.configure(factory, connectionFactory);
        
        
        
        // You could still override some of Boot's default if necessary.
        
        return factory;
    }


	public static class CredentialsInterceptor implements AsyncHandlerInterceptor {
		
		final String login, password;
		
		public CredentialsInterceptor(String inLogin, String inPassword) {
			login = inLogin;
			password = inPassword;
		}

		@Override
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
				throws Exception {
			
			// https://www.w3.org/Protocols/HTTP/HTRQ_Headers.html#z9
			
			String theLogin = null, thePassword = null;
			
			try {
				
				String theValue = request.getHeader( "Authorization" );
				if ( theValue == null || !theValue.trim().toLowerCase().matches( "user\\s\\w+:\\w+" ) ) {
					throw new Exception("bad autorization format: "+ theValue);
				}
				
				theValue = request.getHeader( "Authorization" ).trim().split( "\\s", -1 )[1];
				int idxColon = theValue.indexOf(':');
				theLogin = theValue.substring(0, idxColon).trim();
				thePassword = theValue.substring(idxColon+1).trim();
			
				if ( !login.equals( theLogin ) || !password.equals( thePassword )) {
					throw new Exception("autorization failed");
				}
				
			} catch(Exception e) {
				response.sendError( HttpServletResponse.SC_UNAUTHORIZED );
				return false;
			}
			
			return true;
		}

		@Override
		public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
				ModelAndView modelAndView) throws Exception {
		}

		@Override
		public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
				Exception ex) throws Exception {
		}

		@Override
		public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response,
				Object handler) throws Exception {
		}
	}
	
}
