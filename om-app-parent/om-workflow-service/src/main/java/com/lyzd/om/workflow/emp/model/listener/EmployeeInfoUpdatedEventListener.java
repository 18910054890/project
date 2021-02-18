package com.lyzd.om.workflow.emp.model.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.eventbus.Subscribe;
import com.lyzd.om.emp.sdk.event.MessageNotifyEvent;
import com.lyzd.om.spring.common.event.messaging.guava.AbstractEventBusListener;

/**
 * Employee info update  event listener
 * 
 * @author Thinker
 *
 */
@Component
public class EmployeeInfoUpdatedEventListener extends AbstractEventBusListener { //extends AbstractEventBusListener<UserNameUpdatedEvent> {

	@Autowired
	EmployeeInfoUpdateHandler testHandler;
	
	@Subscribe
	public void handleEvent(MessageNotifyEvent event) {
		System.out.println(event);
		testHandler.handleEvent(event);
	}
	

}
