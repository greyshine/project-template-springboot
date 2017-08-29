package de.greyshine.springboottemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * It is also good to extend org.springframework.web.servlet.handler.HandlerInterceptorAdapter
 * @author greyshine
 *
 */
public class ExampleInterceptor implements AsyncHandlerInterceptor {
	
	public ExampleInterceptor() {
		System.out.println( new Throwable().getStackTrace()[0].getClassName() +" instantiated" );
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println( "here: "+ new Throwable().getStackTrace()[0] );
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		System.out.println( "here: "+ new Throwable().getStackTrace()[0] );
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		System.out.println( "here: "+ new Throwable().getStackTrace()[0] );
		
	}

	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println( "here: "+ new Throwable().getStackTrace()[0] );
	}
}
