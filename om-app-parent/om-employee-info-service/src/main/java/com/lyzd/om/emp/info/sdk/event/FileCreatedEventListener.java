package com.lyzd.om.emp.info.sdk.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.eventbus.Subscribe;
import com.lyzd.om.spring.common.event.messaging.guava.AbstractEventBusListener;

/**
 * When file upload completed,  this listener can be triggered.
 * 
 * @author Thinker
 *
 */
@Component
public class FileCreatedEventListener extends AbstractEventBusListener { //<UserCreatedEvent> {

	@Autowired
	private FileCreatedHandler fileCreatedHandler;
	
	@Subscribe
	public void handleEvent(FileCreatedEvent event) {
		
		fileCreatedHandler.handleEvent(event);
		
	}
}
