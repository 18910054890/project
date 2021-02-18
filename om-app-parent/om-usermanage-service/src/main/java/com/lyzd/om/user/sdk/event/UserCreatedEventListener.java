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
public class UserCreatedEventListener extends AbstractEventBusListener { //<UserCreatedEvent> {

	@Autowired
	private UserCreatedHandler userCreatedHandler;
	
	@Subscribe
	public void handleEvent(UserCreatedEvent event) {
		System.out.println(Thread.currentThread().getId());
		userCreatedHandler.handleEvent(event);
	}
}
