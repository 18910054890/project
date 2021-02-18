package com.lyzd.om.user.sdk.event;

import org.springframework.stereotype.Component;

import com.lyzd.om.shared.annotation.ConsumerPointCut;
import com.lyzd.om.shared.event.EventHandler;

/**
 * @author Thinker
 *
 */
@Component
public class UserCreatedHandler implements EventHandler <UserCreatedEvent>{

	
	@ConsumerPointCut
	public void handleEvent(UserCreatedEvent event) {
		System.out.println(event);
	}

	
}
