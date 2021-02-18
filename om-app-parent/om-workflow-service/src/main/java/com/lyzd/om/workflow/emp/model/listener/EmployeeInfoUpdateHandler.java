package com.lyzd.om.workflow.emp.model.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lyzd.om.emp.sdk.event.MessageNotifyEvent;
import com.lyzd.om.shared.annotation.ConsumerPointCut;
import com.lyzd.om.shared.event.EventHandler;
import com.lyzd.om.workflow.emp.service.impl.MessageService;

/**
 * 
 * Notify related  employee handling message, when an employee submit an application.
 * 
 * @author Thinker
 *
 */
@Component
public class EmployeeInfoUpdateHandler implements EventHandler <MessageNotifyEvent>{

	@Autowired
	MessageService hrMessageService;
	
	@ConsumerPointCut
	public void handleEvent(MessageNotifyEvent event) {
		hrMessageService.save(event);
	}

	
}
