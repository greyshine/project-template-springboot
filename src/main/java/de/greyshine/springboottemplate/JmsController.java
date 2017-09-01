package de.greyshine.springboottemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import de.greyshine.springboottemplate.jms.JmsMessageReceiver;

@Controller
public class JmsController {
	
	@Autowired
	JmsTemplate jmsTemplate;
	
	@GetMapping( "/jms/simple-success" )
	@ResponseBody
	public void jmsSimple() {
		jmsTemplate.convertAndSend( JmsMessageReceiver.JMS_DESTINATION_NAME, "Hello new message!");
	}
		
}
