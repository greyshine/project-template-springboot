package de.greyshine.springboottemplate;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener, HttpSessionActivationListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		System.out.println( "http session created: "+ se );
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println( "http session destroyed: "+ se );
	}

	@Override
	public void sessionWillPassivate(HttpSessionEvent se) {
		System.out.println( "http session will passivate: "+ se );
		
	}

	@Override
	public void sessionDidActivate(HttpSessionEvent se) {
		System.out.println( "http session did activate: "+ se );
		
	}

}
