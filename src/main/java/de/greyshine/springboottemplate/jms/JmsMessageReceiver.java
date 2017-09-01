package de.greyshine.springboottemplate.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import de.greyshine.springboottemplate.Application;

@Component
public class JmsMessageReceiver {
	
	public static final String JMS_DESTINATION_NAME = "messages-simple";

	static int tries = 0;
	
    @JmsListener(destination = JMS_DESTINATION_NAME, containerFactory = Application.BEAN_JMSLISTENERCONTAINERFACTORY)
    public void receiveMessageSimple(String inMessage) {
    
    	++tries;
    	System.out.println("try ("+ tries +") <" + inMessage + ">");
    	
    	if ( tries < 5 ) {
    		
    		throw new RuntimeException("manual exception");
    	}
    	
    	tries = 0;
    	
    	System.out.println("Received 1 <" + inMessage + ">");
    }
   
}
