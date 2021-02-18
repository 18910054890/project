package com.lyzd.om.user.sdk.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.eventbus.Subscribe;
import com.lyzd.om.spring.common.event.messaging.guava.AbstractEventBusListener;

/**
 * concrete business event listener
 * 
 * @author Thinker
 *
 */
@Component
public class UserNameUpdatedEventListener extends AbstractEventBusListener { //extends AbstractEventBusListener<UserNameUpdatedEvent> {

	@Autowired
	UserNameUpdateHandler testHandler;
	
	@Subscribe
	public void handleEvent(UserNameUpdatedEvent event) {
		System.out.println(event);
		testHandler.handleEvent(event);
	}
	

}
