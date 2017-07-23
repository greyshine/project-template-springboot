package de.greyshine.springboottemplate;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Index2Controller {

	@GetMapping( value="/index2.html" )
	public String show(HttpSession inSession) {
		
		final String file = "/static/index2.html";
		
		System.out.println( "This is a spring controller-method being invoked and return a String as a resource forward with file: "+ file +"\n  TODO: remove this message at\n  "+ new Throwable().getStackTrace()[0] );
		
		
		// placing the file in the root of the public resources folder would end in a circular delivery of the forwarding request
		
		return "forward:"+ file;
	}
	
}
